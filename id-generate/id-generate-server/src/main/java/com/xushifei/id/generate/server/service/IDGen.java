package com.xushifei.id.generate.server.service;

import com.xushifei.id.generate.server.common.Result;

public interface IDGen {
  Result get(String key);

  boolean init();
}
