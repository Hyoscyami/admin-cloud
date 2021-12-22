package com.xushifei.generator.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.xushifei.common.dto.BaseAddReq;
import com.xushifei.common.dto.BaseUpdateReq;
import com.xushifei.common.entity.BaseEntity;
import com.xushifei.common.dto.BaseQueryReq;
import com.xushifei.common.vo.BaseVO;
import com.xushifei.generator.config.GeneratorCodeConfig;
import com.xushifei.generator.dto.BaseCodeTemplateDTO;
import com.xushifei.generator.dto.ControllerTemplateDTO;
import com.xushifei.generator.enums.CodeTemplateEnum;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;

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
  /** base entity 和 base vo等基础父类的字段，子类不需要生成这些 */
  private static final List<String> BASE_COLUMNS =
      Arrays.asList(
          "id",
          "type",
          "name",
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

  public static void main(String[] args) {
    GeneratorCodeConfig config = getDefaultGenerator("authorization.server", "authorization");
    config.setTableName("client");
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
        .injectionConfig(builder -> initInjectionConfigBuilder(builder, generatorCodeConfig))
        .templateConfig(CodeGeneratorUtils::initTemplateConfig)
        .templateEngine(new MyFreemarkerTemplateEngine())
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
    builder
        .entityBuilder()
        .enableLombok()
        .disableSerialVersionUID()
        .enableRemoveIsPrefix()
        .superClass(BaseEntity.class);
    // controller配置
    builder.controllerBuilder().enableRestStyle().enableHyphenStyle();
    // service配置
    builder
        .serviceBuilder()
        .convertServiceFileName(
            (entityName -> String.format(CodeTemplateEnum.MANAGER.getValue(), entityName)))
        .convertServiceImplFileName(
            (entityName -> String.format(CodeTemplateEnum.MANAGER_IMPL.getValue(), entityName)));
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
        .entity(CodeTemplateEnum.ENTITY_TEMPLATE_PATH.getValue())
        .controller(CodeTemplateEnum.CONTROLLER_TEMPLATE_PATH.getValue())
        .build();
  }

  /**
   * 自定义注入配置
   *
   * @param builder
   */
  private static void initInjectionConfigBuilder(
      InjectionConfig.Builder builder, GeneratorCodeConfig generatorCodeConfig) {
    builder
        .beforeOutputFile(
            ((tableInfo, objectMap) -> {
              objectMap.put("addTemplateDTO", getAddTemplateDTO(tableInfo.getEntityName()));
              objectMap.put("updateTemplateDTO", getUpdateTemplateDTO(tableInfo.getEntityName()));
              objectMap.put("queryTemplateDTO", getQueryTemplateDTO(tableInfo.getEntityName()));
              objectMap.put("voTemplate", getVOTemplate(tableInfo.getEntityName()));
              objectMap.put(
                  "controllerDTO",
                  getControllerTemplate(tableInfo.getEntityName(), generatorCodeConfig));
              objectMap.put(
                  "baseOutPutFilePath",
                  generatorCodeConfig.getClassOutPutFilePath()
                      + File.separator
                      + generatorCodeConfig
                          .getParentPackageName()
                          .replaceAll("\\.", Matcher.quoteReplacement(File.separator)));
            }))
        .customMap(Collections.singletonMap("ignoreColumns", BASE_COLUMNS))
        // 占个坑位，随便填的，会跑自定义生成文件就行
        .customFile(
            Collections.singletonMap("add", CodeTemplateEnum.ADD_DTO_TEMPLATE_PATH.getValue()))
        .build();
  }

  /**
   * 获取controller代码生成模板
   *
   * @param entityName
   * @return
   */
  private static ControllerTemplateDTO getControllerTemplate(
      final String entityName, GeneratorCodeConfig generatorCodeConfig) {
    ControllerTemplateDTO dto = new ControllerTemplateDTO();
    String serviceCompleteName =
        String.format(
            CodeTemplateEnum.IMPORT_SERVICE_PATH.getValue(),
            generatorCodeConfig.getParentPackageName(),
            entityName);
    dto.setServiceClassCompleteName(serviceCompleteName);
    String addReqCompleteName =
        String.format(
            CodeTemplateEnum.IMPORT_ADD_PATH.getValue(),
            generatorCodeConfig.getParentPackageName(),
            entityName);
    dto.setAddReqCompleteName(addReqCompleteName);
    String updateReqCompleteName =
        String.format(
            CodeTemplateEnum.IMPORT_UPDATE_PATH.getValue(),
            generatorCodeConfig.getParentPackageName(),
            entityName);
    dto.setAddReqCompleteName(updateReqCompleteName);
    String queryReqCompleteName =
        String.format(
            CodeTemplateEnum.IMPORT_QUERY_PATH.getValue(),
            generatorCodeConfig.getParentPackageName(),
            entityName);
    dto.setAddReqCompleteName(queryReqCompleteName);
    return dto;
  }

  /**
   * 获取vo代码生成模板
   *
   * @param entityName
   * @return
   */
  private static BaseCodeTemplateDTO getVOTemplate(final String entityName) {
    BaseCodeTemplateDTO dto = new BaseCodeTemplateDTO();
    dto.setSuperClassCompleteName(BaseVO.class.getName());
    dto.setSuperClassSimpleName(BaseVO.class.getSimpleName());
    dto.setClassName(String.format(CodeTemplateEnum.VO_CLASS_NAME.getValue(), entityName));
    dto.setIgnoreColumns(BASE_COLUMNS);
    return dto;
  }

  /**
   * 初始化查询DTO
   *
   * @param entityName
   * @return
   */
  private static BaseCodeTemplateDTO getQueryTemplateDTO(final String entityName) {
    BaseCodeTemplateDTO dto = new BaseCodeTemplateDTO();
    dto.setSuperClassCompleteName(BaseQueryReq.class.getName());
    dto.setSuperClassSimpleName(BaseQueryReq.class.getSimpleName());
    dto.setClassName(String.format(CodeTemplateEnum.QUERY_DTO_CLASS_NAME.getValue(), entityName));
    dto.setIgnoreColumns(
        Arrays.asList(
            "id",
            "name",
            "tenantId",
            "createTime",
            "modifyTime",
            "creatorId",
            "modifierId",
            "creatorName",
            "modifierName"));
    return dto;
  }
  /**
   * 初始化更新DTO
   *
   * @param entityName
   * @return
   */
  private static BaseCodeTemplateDTO getUpdateTemplateDTO(final String entityName) {
    BaseCodeTemplateDTO dto = new BaseCodeTemplateDTO();
    dto.setIgnoreColumns(
        Arrays.asList(
            "tenantId",
            "deleted",
            "createTime",
            "modifyTime",
            "creatorId",
            "modifierId",
            "creatorName",
            "modifierName"));
    dto.setClassName(String.format(CodeTemplateEnum.UPDATE_DTO_CLASS_NAME.getValue(), entityName));
    dto.setSuperClassSimpleName(BaseUpdateReq.class.getSimpleName());
    dto.setSuperClassCompleteName(BaseUpdateReq.class.getName());
    return dto;
  }
  /**
   * 初始化新增DTO
   *
   * @param entityName
   * @return
   */
  private static BaseCodeTemplateDTO getAddTemplateDTO(final String entityName) {
    BaseCodeTemplateDTO dto = new BaseCodeTemplateDTO();
    dto.setIgnoreColumns(
        Arrays.asList(
            "id",
            "tenantId",
            "deleted",
            "createTime",
            "modifyTime",
            "creatorId",
            "modifierId",
            "creatorName",
            "modifierName"));
    dto.setClassName(String.format(CodeTemplateEnum.ADD_DTO_CLASS_NAME.getValue(), entityName));
    dto.setSuperClassSimpleName(BaseAddReq.class.getSimpleName());
    dto.setSuperClassCompleteName(BaseAddReq.class.getName());
    return dto;
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
        .service("manager")
        .serviceImpl("manager.impl")
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
