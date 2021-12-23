package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${controllerDTO.serviceClassCompleteName!};
import ${controllerDTO.addReqCompleteName!};
import ${controllerDTO.updateReqCompleteName!};
import ${controllerDTO.queryReqCompleteName!};
import ${controllerDTO.responseCompleteName!};
import ${controllerDTO.responseUtilCompleteName!};
import ${controllerDTO.baseVOCompleteName!};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/api/${package.ModuleName}</#if>/api/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@RequiredArgsConstructor
public class ${table.controllerName} {
</#if>
  private final ${controllerDTO.serviceClassName} ${controllerDTO.serviceName};

  /**
   * 分页查询
   *
   * @param req 分页查询请求
   * @return
   */
  @PostMapping("/list")
  public ApiResponse<Page<BaseVO>> page(@Valid @RequestBody ${controllerDTO.queryReqSimpleName} req) {
    return ResponseUtils.success(${controllerDTO.serviceName}.page(req));
  }
  /**
   * 新增${table.comment!}
   *
   * @param req 新增${table.comment!}请求
   * @return
   */
  @PostMapping("/add")
  public ApiResponse<Object> add(@Valid @RequestBody ${controllerDTO.addReqSimpleName} req) {
    ${controllerDTO.serviceName}.save(req);
    return ResponseUtils.success();
  }

  /**
   * 删除${table.comment!}
   *
   * @param id ${table.comment!}id
   * @return
   */
  @PostMapping("/delete")
  public ApiResponse<Object> delete(@RequestParam("id") Long id) {
    ${controllerDTO.serviceName}.removeById(id);
    return ResponseUtils.success();
  }

  /**
   * 更新${table.comment!}
   *
   * @param req 更新${table.comment!}请求
   * @return
   */
  @PostMapping("/update")
  public ApiResponse<Object> update(@Valid @RequestBody ${controllerDTO.updateReqSimpleName} req) {
    ${controllerDTO.serviceName}.updateById(req);
    return ResponseUtils.success();
  }

  /**
   * 获取${table.comment!}
   *
   * @param id ${table.comment!}id
   * @return
   */
  @PostMapping("/getDetail")
  public ApiResponse<Object> getDetail(@RequestParam("id") Long id) {
    return ResponseUtils.success(${controllerDTO.serviceName}.getVOById(id));
  }
}
</#if>
