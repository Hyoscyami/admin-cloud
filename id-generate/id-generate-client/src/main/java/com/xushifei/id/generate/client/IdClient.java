package com.xushifei.id.generate.client;

import com.xushifei.common.model.ApiResponse;
import com.xushifei.id.generate.beans.dto.req.SegmentIdReq;
import com.xushifei.id.generate.beans.dto.req.SnowflakeIdReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 分布式ID
 *
 * @author xushifei
 * @since 2021/12/10
 */
@FeignClient(value = "id-generate-server", fallback = IdClientFallBack.class)
public interface IdClient {
  /**
   * 号段模式单个id获取
   *
   * @param req
   * @return
   */
  @PostMapping(value = "/api/segment/getSegmentId")
  ApiResponse<Long> getSegmentId(@RequestBody SegmentIdReq req);

  /**
   * 批量获取号段模式唯一id
   *
   * @param req
   * @return
   */
  @PostMapping(value = "/api/segment/listSegmentIds")
  ApiResponse<List<Long>> listSegmentIds(@RequestBody SegmentIdReq req);
  /**
   * 雪花算法
   *
   * @param req
   * @return
   */
  @PostMapping(value = "/api/snowflake/getSnowflakeId")
  ApiResponse<Long> getSnowflakeId(@RequestBody SnowflakeIdReq req);

  /**
   * 批量获取雪花算法id
   *
   * @param req
   * @return
   */
  @PostMapping(value = "/api/snowflake/listSnowflakeIds")
  ApiResponse<List<Long>> listSnowflakeIds(@RequestBody SnowflakeIdReq req);
}
