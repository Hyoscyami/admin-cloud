package ${basePackageName}.service;

import ${package.Entity}.${entity};
import ${serviceTemplateDTO.baseServiceCompleteName};
import ${voTemplateDTO.packageName}.${entity}VO;
import ${basePackageName}.dto.req.add.Add${entity}Req;
import ${basePackageName}.dto.req.query.Query${entity}Req;
import ${basePackageName}.dto.req.update.Update${entity}Req;
/**
 * ${table.comment!}业务类
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity}Service extends BaseService<Add${entity}Req, Update${entity}Req, Query${entity}Req, ${entity}VO, ${entity}> {

}