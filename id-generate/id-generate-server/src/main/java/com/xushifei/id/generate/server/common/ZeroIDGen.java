package com.xushifei.id.generate.server.common;

import com.xushifei.id.generate.server.service.IDGen;

public class ZeroIDGen implements IDGen {
  @Override
  public Result get(String key) {
    return new Result(0, Status.SUCCESS);
  }

  @Override
  public boolean init() {
    return true;
  }
}
