package com.xushifei.generator.utils;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xushifei.generator.enums.CodeTemplateEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * 自定义生成代码模板
 *
 * @author xushifei
 * @date 2021/12/16
 */
public class MyFreemarkerTemplateEngine extends AbstractTemplateEngine {
  private Configuration configuration;

  public MyFreemarkerTemplateEngine() {}

  @Override
  public MyFreemarkerTemplateEngine init(ConfigBuilder configBuilder) {
    this.configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    this.configuration.setDefaultEncoding(ConstVal.UTF8);
    this.configuration.setClassForTemplateLoading(FreemarkerTemplateEngine.class, "/");
    return this;
  }

  @Override
  public void writer(Map<String, Object> objectMap, String templatePath, File outputFile)
      throws Exception {
    Template template = this.configuration.getTemplate(templatePath);
    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
    Throwable var6 = null;

    try {
      template.process(objectMap, new OutputStreamWriter(fileOutputStream, ConstVal.UTF8));
    } catch (Throwable var15) {
      var6 = var15;
      throw var15;
    } finally {
      if (fileOutputStream != null) {
        if (var6 != null) {
          try {
            fileOutputStream.close();
          } catch (Throwable var14) {
            var6.addSuppressed(var14);
          }
        } else {
          fileOutputStream.close();
        }
      }
    }
  }

  @Override
  public String templateFilePath(String filePath) {
    return filePath + ".ftl";
  }

  /**
   * 自定义增删改查DTO生成
   *
   * @param customFile
   * @param tableInfo
   * @param objectMap
   */
  @Override
  public void outputCustomFile(
      Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
    customFile.forEach(
        (key, value) -> {
          // 新增DTO
          this.createAddDTO(tableInfo, objectMap);
          // 更新DTO
          this.createUpdateDTO(tableInfo, objectMap);
          // 查询DTO模板
          this.createQueryDTO(tableInfo, objectMap);
          // vo模板
          this.createVO(tableInfo, objectMap);
        });
  }

  /**
   * VO模板
   *
   * @param tableInfo
   * @param objectMap
   */
  private void createVO(TableInfo tableInfo, Map<String, Object> objectMap) {
    String className =
        String.format(CodeTemplateEnum.VO_CLASS_NAME.getValue(), tableInfo.getEntityName());
    String fileName =
        objectMap.get(CodeTemplateEnum.BASE_OUT_PUT_FILE_PATH.getValue())
            + File.separator
            + "vo"
            + File.separator
            + className
            + ".java";
    this.outputFile(new File(fileName), objectMap, CodeTemplateEnum.VO_TEMPLATE_PATH.getValue());
  }
  /**
   * 查询DTO模板
   *
   * @param tableInfo
   * @param objectMap
   */
  private void createQueryDTO(TableInfo tableInfo, Map<String, Object> objectMap) {
    String className =
        String.format(CodeTemplateEnum.QUERY_DTO_CLASS_NAME.getValue(), tableInfo.getEntityName());
    String fileName =
        objectMap.get(CodeTemplateEnum.BASE_OUT_PUT_FILE_PATH.getValue())
            + File.separator
            + "dto"
            + File.separator
            + "query"
            + File.separator
            + className
            + ".java";
    this.outputFile(
        new File(fileName), objectMap, CodeTemplateEnum.QUERY_DTO_TEMPLATE_PATH.getValue());
  }

  /**
   * 新增DTO模板
   *
   * @param tableInfo
   * @param objectMap
   */
  private void createAddDTO(TableInfo tableInfo, Map<String, Object> objectMap) {
    String className =
        String.format(CodeTemplateEnum.ADD_DTO_CLASS_NAME.getValue(), tableInfo.getEntityName());
    String fileName =
        objectMap.get(CodeTemplateEnum.BASE_OUT_PUT_FILE_PATH.getValue())
            + File.separator
            + "dto"
            + File.separator
            + "add"
            + File.separator
            + className
            + ".java";
    this.outputFile(
        new File(fileName), objectMap, CodeTemplateEnum.ADD_DTO_TEMPLATE_PATH.getValue());
  }
  /**
   * 更新DTO模板
   *
   * @param tableInfo
   * @param objectMap
   */
  private void createUpdateDTO(TableInfo tableInfo, Map<String, Object> objectMap) {
    String className =
        String.format(CodeTemplateEnum.UPDATE_DTO_CLASS_NAME.getValue(), tableInfo.getEntityName());
    String fileName =
        objectMap.get(CodeTemplateEnum.BASE_OUT_PUT_FILE_PATH.getValue())
            + File.separator
            + "dto"
            + File.separator
            + "update"
            + File.separator
            + className
            + ".java";
    this.outputFile(
        new File(fileName), objectMap, CodeTemplateEnum.ADD_DTO_TEMPLATE_PATH.getValue());
  }
}
