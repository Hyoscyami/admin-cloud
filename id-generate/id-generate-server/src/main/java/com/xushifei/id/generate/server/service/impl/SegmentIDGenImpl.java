package com.xushifei.id.generate.server.service.impl;

import com.xushifei.id.generate.server.common.Result;
import com.xushifei.id.generate.server.common.Status;
import com.xushifei.id.generate.server.entity.SegmentAlloc;
import com.xushifei.id.generate.server.model.Segment;
import com.xushifei.id.generate.server.model.SegmentBuffer;
import com.xushifei.id.generate.server.service.IDGen;
import com.xushifei.id.generate.server.support.ISegmentAllocSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 号段模式生成
 *
 * @author xushifei
 * @date 2021/12/10
 */
@Slf4j
@RequiredArgsConstructor
public class SegmentIDGenImpl implements IDGen {
  private static final Logger logger = LoggerFactory.getLogger(SegmentIDGenImpl.class);

  /** IDCache未初始化成功时的异常码 */
  private static final long EXCEPTION_ID_IDCACHE_INIT_FALSE = -1;
  /** key不存在时的异常码 */
  private static final long EXCEPTION_ID_KEY_NOT_EXISTS = -2;
  /** SegmentBuffer中的两个Segment均未从DB中装载时的异常码 */
  private static final long EXCEPTION_ID_TWO_SEGMENTS_ARE_NULL = -3;
  /** 最大步长不超过100,0000 */
  private static final int MAX_STEP = 1000000;
  /** 一个Segment维持时间为15分钟 */
  private static final long SEGMENT_DURATION = 15 * 60 * 1000L;

  private ExecutorService service =
      new ThreadPoolExecutor(
          5,
          Integer.MAX_VALUE,
          60L,
          TimeUnit.SECONDS,
          new SynchronousQueue<Runnable>(),
          new UpdateThreadFactory());
  private volatile boolean initOK = false;
  private Map<String, SegmentBuffer> cache = new ConcurrentHashMap<String, SegmentBuffer>();
  private ISegmentAllocSupport segmentAllocSupport;

  public static class UpdateThreadFactory implements ThreadFactory {

    private static int threadInitNumber = 0;

    private static synchronized int nextThreadNum() {
      return threadInitNumber++;
    }

    @Override
    public Thread newThread(Runnable r) {
      return new Thread(r, "Thread-Segment-Update-" + nextThreadNum());
    }
  }

  @Override
  public boolean init() {
    logger.info("Init ...");
    // 确保加载到kv后才初始化成功
    updateCacheFromDb();
    initOK = true;
    updateCacheFromDbAtEveryMinute();
    return initOK;
  }

  private void updateCacheFromDbAtEveryMinute() {
    ScheduledExecutorService service =
        Executors.newSingleThreadScheduledExecutor(
            new ThreadFactory() {
              @Override
              public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("check-idCache-thread");
                t.setDaemon(true);
                return t;
              }
            });
    service.scheduleWithFixedDelay(this::updateCacheFromDb, 60, 60, TimeUnit.SECONDS);
  }

  private void updateCacheFromDb() {
    logger.info("update cache from db");
    try {
      List<SegmentAlloc> segmentAllocList = segmentAllocSupport.list();
      if (CollectionUtils.isEmpty(segmentAllocList)) {
        return;
      }
      List<String> dbTags =
          segmentAllocList.stream().map(SegmentAlloc::getBizTag).collect(Collectors.toList());
      List<String> cacheTags = new ArrayList<>(cache.keySet());
      Set<String> insertTagsSet = new HashSet<>(dbTags);
      Set<String> removeTagsSet = new HashSet<>(cacheTags);
      // db中新加的tags灌进cache
      for (int i = 0; i < cacheTags.size(); i++) {
        String tmp = cacheTags.get(i);
        insertTagsSet.remove(tmp);
      }
      for (String tag : insertTagsSet) {
        SegmentBuffer buffer = new SegmentBuffer();
        buffer.setKey(tag);
        Segment segment = buffer.getCurrent();
        segment.setValue(new AtomicLong(0));
        segment.setMax(0);
        segment.setStep(0);
        cache.put(tag, buffer);
        logger.info("Add tag {} from db to IdCache, SegmentBuffer {}", tag, buffer);
      }
      // cache中已失效的tags从cache删除
      for (int i = 0; i < dbTags.size(); i++) {
        String tmp = dbTags.get(i);
        removeTagsSet.remove(tmp);
      }
      for (String tag : removeTagsSet) {
        cache.remove(tag);
        logger.info("Remove tag {} from IdCache", tag);
      }
    } catch (Exception e) {
      logger.warn("update cache from db exception", e);
    }
  }

  @Override
  public Result get(final String key) {
    if (!initOK) {
      return new Result(EXCEPTION_ID_IDCACHE_INIT_FALSE, Status.EXCEPTION);
    }
    if (cache.containsKey(key)) {
      SegmentBuffer buffer = cache.get(key);
      if (!buffer.isInitOk()) {
        synchronized (buffer) {
          if (!buffer.isInitOk()) {
            try {
              updateSegmentFromDb(key, buffer.getCurrent());
              logger.info("Init buffer. Update leafkey {} {} from db", key, buffer.getCurrent());
              buffer.setInitOk(true);
            } catch (Exception e) {
              logger.warn("Init buffer {} exception", buffer.getCurrent(), e);
            }
          }
        }
      }
      return getIdFromSegmentBuffer(cache.get(key));
    }
    return new Result(EXCEPTION_ID_KEY_NOT_EXISTS, Status.EXCEPTION);
  }

  public void updateSegmentFromDb(String bizTag, Segment segment) {
    SegmentBuffer buffer = segment.getBuffer();
    SegmentAlloc segmentAlloc = new SegmentAlloc();
    segmentAlloc.setBizTag(bizTag);
    if (!buffer.isInitOk()) {
      segmentAlloc = segmentAllocSupport.updateMaxIdAndGetLeafAlloc(segmentAlloc);
      buffer.setStep(segmentAlloc.getStep());
      buffer.setMinStep(segmentAlloc.getStep()); // leafAlloc中的step为DB中的step
    } else if (buffer.getUpdateTimestamp() == 0) {
      segmentAlloc = segmentAllocSupport.updateMaxIdAndGetLeafAlloc(segmentAlloc);
      buffer.setUpdateTimestamp(System.currentTimeMillis());
      buffer.setStep(segmentAlloc.getStep());
      buffer.setMinStep(segmentAlloc.getStep()); // leafAlloc中的step为DB中的step
    } else {
      long duration = System.currentTimeMillis() - buffer.getUpdateTimestamp();
      int nextStep = buffer.getStep();
      if (duration < SEGMENT_DURATION) {
        if (nextStep * 2 > MAX_STEP) {
          // do nothing
        } else {
          nextStep = nextStep * 2;
        }
      } else if (duration < SEGMENT_DURATION * 2) {
        // do nothing with nextStep
      } else {
        nextStep = nextStep / 2 >= buffer.getMinStep() ? nextStep / 2 : nextStep;
      }
      logger.info(
          "leafKey[{}], step[{}], duration[{}mins], nextStep[{}]",
          bizTag,
          buffer.getStep(),
          String.format("%.2f", ((double) duration / (1000 * 60))),
          nextStep);
      SegmentAlloc temp = new SegmentAlloc();
      temp.setBizTag(bizTag);
      temp.setStep(nextStep);
      segmentAlloc = segmentAllocSupport.updateMaxIdAndGetLeafAlloc(temp);
      buffer.setUpdateTimestamp(System.currentTimeMillis());
      buffer.setStep(nextStep);
      buffer.setMinStep(segmentAlloc.getStep()); // leafAlloc的step为DB中的step
    }
    // must set value before set max
    long value = segmentAlloc.getMaxId() - buffer.getStep();
    segment.getValue().set(value);
    segment.setMax(segmentAlloc.getMaxId());
    segment.setStep(buffer.getStep());
  }

  public Result getIdFromSegmentBuffer(final SegmentBuffer buffer) {
    while (true) {
      buffer.rLock().lock();
      try {
        final Segment segment = buffer.getCurrent();
        if (!buffer.isNextReady()
            && (segment.getIdle() < 0.9 * segment.getStep())
            && buffer.getThreadRunning().compareAndSet(false, true)) {
          service.execute(
              () -> {
                Segment next = buffer.getSegments()[buffer.nextPos()];
                boolean updateOk = false;
                try {
                  updateSegmentFromDb(buffer.getKey(), next);
                  updateOk = true;
                  logger.info("update segment {} from db {}", buffer.getKey(), next);
                } catch (Exception e) {
                  logger.warn(buffer.getKey() + " updateSegmentFromDb exception", e);
                } finally {
                  if (updateOk) {
                    buffer.wLock().lock();
                    buffer.setNextReady(true);
                    buffer.getThreadRunning().set(false);
                    buffer.wLock().unlock();
                  } else {
                    buffer.getThreadRunning().set(false);
                  }
                }
              });
        }
        long value = segment.getValue().getAndIncrement();
        if (value < segment.getMax()) {
          return new Result(value, Status.SUCCESS);
        }
      } finally {
        buffer.rLock().unlock();
      }
      waitAndSleep(buffer);
      buffer.wLock().lock();
      try {
        final Segment segment = buffer.getCurrent();
        long value = segment.getValue().getAndIncrement();
        if (value < segment.getMax()) {
          return new Result(value, Status.SUCCESS);
        }
        if (buffer.isNextReady()) {
          buffer.switchPos();
          buffer.setNextReady(false);
        } else {
          logger.error("Both two segments in {} are not ready!", buffer);
          return new Result(EXCEPTION_ID_TWO_SEGMENTS_ARE_NULL, Status.EXCEPTION);
        }
      } finally {
        buffer.wLock().unlock();
      }
    }
  }

  private void waitAndSleep(SegmentBuffer buffer) {
    int roll = 0;
    while (buffer.getThreadRunning().get()) {
      roll += 1;
      if (roll > 10000) {
        try {
          TimeUnit.MILLISECONDS.sleep(10);
          break;
        } catch (InterruptedException e) {
          logger.warn("Thread {} Interrupted", Thread.currentThread().getName());
          break;
        }
      }
    }
  }

  public Map<String, SegmentBuffer> getCache() {
    return cache;
  }
}
