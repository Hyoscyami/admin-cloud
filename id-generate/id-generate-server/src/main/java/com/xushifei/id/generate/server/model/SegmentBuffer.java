package com.xushifei.id.generate.server.model;

import lombok.Data;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 双buffer
 *
 * @author 许世飞
 */
@Data
public class SegmentBuffer {
  private String key;
  private Segment[] segments; // 双buffer
  private volatile int currentPos; // 当前的使用的segment的index
  private volatile boolean nextReady; // 下一个segment是否处于可切换状态
  private volatile boolean initOk; // 是否初始化完成
  private final AtomicBoolean threadRunning; // 线程是否在运行中
  private final ReadWriteLock lock;

  private volatile int step;
  private volatile int minStep;
  private volatile long updateTimestamp;

  public Segment getCurrent() {
    return segments[currentPos];
  }

  public Lock rLock() {
    return lock.readLock();
  }

  public Lock wLock() {
    return lock.writeLock();
  }

  public int nextPos() {
    return (currentPos + 1) % 2;
  }

  public void switchPos() {
    currentPos = nextPos();
  }

  public SegmentBuffer() {
    segments = new Segment[] {new Segment(this), new Segment(this)};
    currentPos = 0;
    nextReady = false;
    initOk = false;
    threadRunning = new AtomicBoolean(false);
    lock = new ReentrantReadWriteLock();
  }
}
