package ${basePackageName}.service.impl;

import ${package.Entity}.${entity};
import ${basePackageName}.manager.${entity}Manager;
import ${basePackageName}.service.${entity}Service;
import ${voTemplateDTO.packageName}.${entity}VO;
import ${addTemplateDTO.superClassCompleteName};
import ${updateTemplateDTO.superClassCompleteName};
import ${serviceTemplateDTO.baseServiceImplCompleteName};
import ${voTemplateDTO.superClassCompleteName};
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * ${table.comment!}业务类
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ${entity}ServiceImpl extends BaseServiceImpl<${entity}Manager, ${entity}>
    implements ${entity}Service {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public ${entity} convertAddReqToEntity(BaseAddReq req) {
    ${entity} client = new ${entity}();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * 更新请求转entity
   *
   * @param req
   * @return
   */
  @Override
  protected ${entity} convertUpdateReqToEntity(BaseUpdateReq req) {
    ${entity} client = new ${entity}();
    BeanUtils.copyProperties(req, client);
    return client;
  }

  /**
   * entity转VO
   *
   * @param entity
   * @return
   */
  @Override
  protected BaseVO convertEntityToVO(${entity} entity) {
    ${entity}VO vo = new ${entity}VO();
    BeanUtils.copyProperties(entity, vo);
    return vo;
  }

  /**
   * 入库预处理
   *
   * @param entity
   */
  @Override
  protected void preSave(${entity} entity) {
    entity.assignCreateInfo();
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(${entity} entity) {
    entity.assignDelInfo();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(${entity} entity) {
    entity.assignModifyInfo();
  }
}
