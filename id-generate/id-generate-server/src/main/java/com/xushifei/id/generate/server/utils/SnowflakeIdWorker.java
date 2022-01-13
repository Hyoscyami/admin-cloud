package com.xushifei.id.generate.server.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

/**
 * 雪花算法
 *
 * @author xushifei
 * @since 2021/12/13
 */
@Slf4j
public class SnowflakeIdWorker {

  /** 工作ID */
  private final long workerId;
  /** 14位的序列号 */
  private long sequence = 0L;

  public SnowflakeIdWorker(long workerId) {
    // sanity check for workerId
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(
          String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    log.info(
        "worker starting. timestamp left shift {}, worker id bits {} sequence bits {}, workerid {}",
        timestampLeftShift,
        workerIdBits,
        sequenceBits,
        workerId);

    this.workerId = workerId;
  }

  /** 初始时间戳2021-12-13 */
  private long startTimestamp = 1639405713277L;

  /** 工作ID长度 */
  private final long workerIdBits = 8L;
  /** 工作id最大值 */
  private long maxWorkerId = ~(-1L << workerIdBits);
  /** 序列号id长度 */
  private final long sequenceBits = 14L;
  /** 序列号最大值 */
  private long sequenceMask = ~(-1L << sequenceBits);

  /** 工作id需要左移的位数，14位 */
  private long workerIdShift = sequenceBits;
  /** 时间戳需要左移位数 14+8=22位 */
  private final long timestampLeftShift = sequenceBits + workerIdBits;

  /** 上次时间戳，初始值为负数 */
  private long lastTimestamp = -1L;

  /**
   * 下一个ID生成算法
   *
   * @return
   */
  public synchronized long nextId() {
    // 当前时间戳
    long timestamp = this.getCurrentTimestamp();

    // 获取当前时间戳如果小于上次时间戳，则表示时间戳获取出现异常
    if (timestamp < lastTimestamp) {
      log.error("clock is moving backwards.  Rejecting requests until {}.", lastTimestamp);
      throw new RuntimeException(
          String.format(
              "Clock moved backwards.  Refusing to generate id for %d milliseconds",
              lastTimestamp - timestamp));
    }

    // 获取当前时间戳如果等于上次时间戳（同一毫秒内），则在序列号加一；否则序列号赋值为0，从0开始。
    if (lastTimestamp == timestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = this.tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0;
    }

    // 将上次时间戳值刷新
    lastTimestamp = timestamp;

    /**
     * 返回结果： (timestamp - twepoch) << timestampLeftShift) 表示将时间戳减去初始时间戳，再左移相应位数 (datacenterId <<
     * datacenterIdShift) 表示将数据id左移相应位数 (workerId << workerIdShift) 表示将工作id左移相应位数 | 是按位或运算符，例如：x |
     * y，只有当x，y都为0的时候结果才为0，其它情况结果都为1。 因为个部分只有相应位上的值有意义，其它位上都是0，所以将各部分的值进行 | 运算就能得到最终拼接好的id
     */
    return ((timestamp - startTimestamp) << timestampLeftShift)
        | (workerId << workerIdShift)
        | sequence;
  }

  /**
   * 获取时间戳，并与上次时间戳比较，如果上一次时间戳大于当前时间戳，说明时钟回拨了，进行循环等待
   *
   * @param lastTimestamp
   * @return
   */
  private long tilNextMillis(long lastTimestamp) {
    long timestamp = this.getCurrentTimestamp();
    while (timestamp <= lastTimestamp) {
      timestamp = this.getCurrentTimestamp();
    }
    return timestamp;
  }

  /**
   * 获取当前时间戳，毫秒级
   *
   * @return
   */
  private long getCurrentTimestamp() {
    return Instant.now().toEpochMilli();
  }
}
