package com.xushifei.generator.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.generator.config.GeneratorCodeConfig;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 代码生成工具
 *
 * @author xushifei
 * @date 2021/11/16
 */
public class CodeGeneratorUtils {
  /** 数据库连接地址模板 */
  private static final String DATA_SOURCE_URL =
      "jdbc:mysql://localhost:3306/%s?useUnicode=true&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8";

  public static void main(String[] args) {
    GeneratorCodeConfig config = getDefaultGenerator("authorization.server", "authorization");
    // config.setTableName("client");
    generateCode(config);
  }

  /**
   * 生成代码
   *
   * @param generatorCodeConfig
   */
  public static void generateCode(final GeneratorCodeConfig generatorCodeConfig) {
    FastAutoGenerator.create(initDataSourceConfigBuilder(generatorCodeConfig))
        .globalConfig(builder -> initGlobalConfigBuilder(builder, generatorCodeConfig))
        .packageConfig(builder -> initPackageConfigBuilder(builder, generatorCodeConfig))
        .strategyConfig(builder -> initStrategyConfigBuilder(builder, generatorCodeConfig))
        .injectionConfig(CodeGeneratorUtils::initInjectionConfigBuilder)
        .templateConfig(CodeGeneratorUtils::initTemplateConfig)
        .templateEngine(new FreemarkerTemplateEngine())
        .execute();
  }

  /**
   * 策略配置
   *
   * @param builder
   * @param generatorCodeConfig
   * @return
   */
  public static void initStrategyConfigBuilder(
      StrategyConfig.Builder builder, final GeneratorCodeConfig generatorCodeConfig) {
    builder.enableCapitalMode();
    if (StringUtils.hasLength(generatorCodeConfig.getTableName())) {
      builder.addInclude(generatorCodeConfig.getTableName());
    }
    // 实体配置
    builder.entityBuilder().enableLombok().enableRemoveIsPrefix().superClass(BaseEntity.class);
    // controller配置
    builder.controllerBuilder().enableRestStyle().enableHyphenStyle();
    // service配置
    builder
        .serviceBuilder()
        .convertServiceFileName((entityName -> "I" + entityName + "Support"))
        .convertServiceImplFileName((entityName -> entityName + "Support"));
    // mapper配置
    builder.mapperBuilder().enableBaseResultMap().enableBaseColumnList();
    builder.build();
  }
  /**
   * 模板配置
   *
   * @return
   */
  public static void initTemplateConfig(TemplateConfig.Builder builder) {
    builder
        .entity("/templates/code-generate/entity.java")
        .controller("/templates/code-generate/controller.java")
        .build();
  }

  /**
   * 自定义注入配置
   *
   * @param builder
   */
  private static void initInjectionConfigBuilder(InjectionConfig.Builder builder) {
    List<String> entityIgnoreColumns =
        Arrays.asList(
            "id",
            "type",
            "deleted",
            "code",
            "tenantId",
            "note",
            "status",
            "createTime",
            "modifyTime",
            "creatorId",
            "modifierId",
            "creatorName",
            "modifierName");
    builder.customMap(Collections.singletonMap("ignoreColumns", entityIgnoreColumns)).build();
  }
  /**
   * 包配置
   *
   * @param builder
   * @param generatorCodeConfig
   * @return
   */
  public static void initPackageConfigBuilder(
      PackageConfig.Builder builder, final GeneratorCodeConfig generatorCodeConfig) {
    builder
        .moduleName(generatorCodeConfig.getModuleName())
        .service("support")
        .serviceImpl("support.impl")
        .pathInfo(
            Collections.singletonMap(
                OutputFile.mapperXml, generatorCodeConfig.getXmlOutPutFilePath()))
        .parent(generatorCodeConfig.getParentPackageName())
        .build();
  }
  /**
   * 数据源配置
   *
   * @param generatorCodeConfig
   * @return
   */
  public static DataSourceConfig.Builder initDataSourceConfigBuilder(
      final GeneratorCodeConfig generatorCodeConfig) {
    return new DataSourceConfig.Builder(
            generatorCodeConfig.getDataSourceUrl(),
            generatorCodeConfig.getDataSourceUserName(),
            generatorCodeConfig.getDataSourcePassword())
        .dbQuery(new MySqlQuery())
        .typeConvert(new MySqlTypeConvert())
        .keyWordsHandler(new MySqlKeyWordsHandler());
  }

  /**
   * 初始化全局配置
   *
   * @param builder
   * @param generatorCodeConfig
   * @return
   */
  public static void initGlobalConfigBuilder(
      GlobalConfig.Builder builder, final GeneratorCodeConfig generatorCodeConfig) {
    builder
        .fileOverride()
        .disableOpenDir()
        .outputDir(generatorCodeConfig.getClassOutPutFilePath())
        .author(generatorCodeConfig.getAuthorName())
        .build();
  }
  /**
   * 获取默认的代码生成对象
   *
   * @param packageName 包名
   * @param databaseName 数据库名
   * @return
   */
  public static GeneratorCodeConfig getDefaultGenerator(
      final String packageName, final String databaseName) {
    GeneratorCodeConfig generatorCodeConfig = new GeneratorCodeConfig();
    generatorCodeConfig.setAuthorName("xushifei");
    generatorCodeConfig.setClassOutPutFilePath("./" + packageName + "/src/main/java");
    generatorCodeConfig.setXmlOutPutFilePath("./" + packageName + "/src/main/resources/mapper");
    generatorCodeConfig.setDriverName("com.mysql.cj.jdbc.Driver");
    generatorCodeConfig.setDataSourceUrl(String.format(DATA_SOURCE_URL, databaseName));
    generatorCodeConfig.setDataSourceUserName("root");
    generatorCodeConfig.setDataSourcePassword("Root@123");
    generatorCodeConfig.setModuleName("");
    generatorCodeConfig.setParentPackageName("com.xushifei." + packageName);
    return generatorCodeConfig;
  }
}
