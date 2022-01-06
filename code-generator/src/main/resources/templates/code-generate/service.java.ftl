package ${basePackageName}.service;

import ${serviceTemplateDTO.baseServiceCompleteName};
import ${voTemplateDTO.packageName}.${entity}VO;
import ${controllerDTO.addReqCompleteName!};
import ${controllerDTO.updateReqCompleteName!};
import ${controllerDTO.queryReqCompleteName!};
/**
 * ${table.comment!}业务类
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity}Service extends BaseService<Add${entity}Req, Update${entity}Req, Query${entity}Req, ${entity}VO> {

}