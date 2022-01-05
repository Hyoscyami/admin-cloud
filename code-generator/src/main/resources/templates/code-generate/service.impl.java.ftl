package ${basePackageName}.service.impl;

import ${package.Entity}.${entity};
import ${basePackageName}.manager.${entity}Manager;
import ${basePackageName}.service.${entity}Service;
import ${voTemplateDTO.packageName}.${entity}VO;
import ${basePackageName}.dto.req.add.Add${entity}Req;
import ${basePackageName}.dto.req.query.Query${entity}Req;
import ${basePackageName}.dto.req.update.Update${entity}Req;
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
public class ${entity}ServiceImpl extends BaseServiceImpl<${entity}Manager, Add${entity}Req, Update${entity}Req, Query${entity}Req, ${entity}VO, ${entity}>
    implements ${entity}Service {

  /**
   * 新增请求转实体
   *
   * @param req
   * @return
   */
  @Override
  public ${entity} convertAddReqToEntity(Add${entity}Req req) {
    ${entity} entity = new ${entity}();
    BeanUtils.copyProperties(req, entity);
    return entity;
  }

  /**
   * 更新请求转entity
   *
   * @param req
   * @return
   */
  @Override
  protected ${entity} convertUpdateReqToEntity(Update${entity}Req req) {
    ${entity} entity = new ${entity}();
    BeanUtils.copyProperties(req, entity);
    return entity;
  }

  /**
   * entity转VO
   *
   * @param entity
   * @return
   */
  @Override
  protected ${entity}VO convertEntityToVO(${entity} entity) {
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
    super.preSave(entity);
  }

  /**
   * 删除预处理
   *
   * @param entity
   */
  @Override
  protected void preRemove(${entity} entity) {
    super.preRemove();
  }

  /**
   * 更新预处理
   *
   * @param entity
   */
  @Override
  protected void preUpdate(${entity} entity) {
    super.preUpdate();
  }
}
