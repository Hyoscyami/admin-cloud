package com.xushifei.core.web.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * IP工具类
 *
 * @author xushifei
 * @date 2021/4/25
 */
@Slf4j
public class IPUtils {
  /** 请求通过反向代理之后，可能包含请求客户端真实IP的HTTP HEADER 如果后续扩展，有其他可能包含IP的HTTP HEADER，加到这里即可 */
  private static final String[] POSSIBLE_HEADERS =
      new String[] {
        "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP",
        "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
      };

  private static final String UN_KNOWN = "unknown";
  private static final String LOCAL_HOST = "127.0.0.1";
  /** java获取ip可能为 */
  private static final String LOCAL_HOST_ANOTHER = "0:0:0:0:0:0:0:1";
  /**
   * 获取请求客户端的真实IP地址
   *
   * @param request javax.servlet.http.HttpServletRequest
   * @return 客户端端真实IP地址
   */
  public static String getRequestClientRealIp(HttpServletRequest request) {
    String ip;
    // 先检查代理：逐个HTTP HEADER检查过去，看看是否存在客户端真实IP
    for (String header : POSSIBLE_HEADERS) {
      ip = request.getHeader(header);
      if (!ObjectUtils.isEmpty(ip) && !UN_KNOWN.equalsIgnoreCase(ip)) {
        // 请求经过多次反向代理后可能会有多个IP值（以英文逗号分隔），第一个IP才是客户端真实IP
        return ip.contains(",") ? ip.split(",")[0] : ip;
      }
    }
    // 从所有可能的HTTP HEADER中都没有找到客户端真实IP，采用request.getRemoteAddr()来兜底
    ip = request.getRemoteAddr();
    if (LOCAL_HOST_ANOTHER.equals(ip) || LOCAL_HOST.equals(ip)) {
      // 说明是从本机发出的请求，直接获取并返回本机IP地址
      return getLocalRealIp();
    }
    return ip;
  }

  /**
   * ip字符串转long
   *
   * @param ipStr
   * @return
   */
  public static Long ipToLong(String ipStr) {
    String[] ip = ipStr.split("\\.");
    return (Long.parseLong(ip[0]) << 24)
        + (Long.parseLong(ip[1]) << 16)
        + (Long.parseLong(ip[2]) << 8)
        + Long.parseLong(ip[3]);
  }

  /**
   * long转ip字符串
   *
   * @param ipLong
   * @return
   */
  public static String longToIp(Long ipLong) {
    return (ipLong >>> 24)
        + "."
        + ((ipLong >>> 16) & 0xFF)
        + "."
        + ((ipLong >>> 8) & 0xFF)
        + "."
        + (ipLong & 0xFF);
  }

  /**
   * 获取本机IP地址
   *
   * @return 若配置了外网IP则优先返回外网IP；否则返回本地IP地址。如果本机没有被分配局域网IP地址（例如本机没有连接任何网络），则默认返回127.0.0.1
   */
  public static String getLocalRealIp() {
    // 本地IP
    String localIp = LOCAL_HOST;
    // 外网IP
    String netIp = null;

    Enumeration<NetworkInterface> netInterfaces;
    try {
      netInterfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e) {
      log.info("获取本地ip异常:", e);
      // 发生异常则返回null
      return null;
    }
    InetAddress ip;
    // 是否找到外网IP
    boolean isNetIpExist = false;
    while (netInterfaces.hasMoreElements() && !isNetIpExist) {
      Enumeration<InetAddress> address = netInterfaces.nextElement().getInetAddresses();
      while (address.hasMoreElements()) {
        ip = address.nextElement();
        if (!ip.isSiteLocalAddress()
            && !ip.isLoopbackAddress()
            && !ip.getHostAddress().contains(":")) {
          // 外网IP
          netIp = ip.getHostAddress();
          isNetIpExist = true;
          break;
        } else if (ip.isSiteLocalAddress()
            && !ip.isLoopbackAddress()
            && !ip.getHostAddress().contains(":")) {
          // 内网IP
          localIp = ip.getHostAddress();
        }
      }
    }

    if (!ObjectUtils.isEmpty(netIp)) {
      // 如果配置了外网IP则优先返回外网IP地址
      return netIp;
    }
    return localIp;
  }
}
