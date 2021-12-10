package com.xushifei.id.generate.server.model;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 号段
 *
 * @author xushifei
 */
@Data
public class Segment {
  /** 当前id */
  private AtomicLong value = new AtomicLong(0);
  /** 最大id */
  private volatile long max;
  /** 步长 */
  private volatile int step;
  /** 双buffer */
  private SegmentBuffer buffer;

  public Segment(SegmentBuffer buffer) {
    this.buffer = buffer;
  }

  public long getIdle() {
    return this.getMax() - getValue().get();
  }
}
