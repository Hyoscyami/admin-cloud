package com.xushifei.generator.config;

import lombok.Data;

/**
 * mybatis plus生成代码配置类
 *
 * @author xushifei
 * @date 2021/11/16
 */
@Data
public class GeneratorCodeConfig {
  /** 代码作者姓名 */
  private String authorName;
  /** 数据库表名 */
  private String tableName;
  /** java文件输出路径 */
  private String classOutPutFilePath;
  /** xml文件输出路径 */
  private String xmlOutPutFilePath;
  /** 数据库连接地址 */
  private String dataSourceUrl;
  /** 数据库账号 */
  private String dataSourceUserName;
  /** 数据库密码 */
  private String dataSourcePassword;
  /** 数据库驱动名称 */
  private String driverName;
  /** 模块名称 */
  private String moduleName;
  /** 包名 */
  private String parentPackageName;
}
