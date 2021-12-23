package ${updateTemplateDTO.packageName};

<#if entityLombokModel>
import lombok.Data;
</#if>
<#if updateTemplateDTO.superClassCompleteName??>
import ${updateTemplateDTO.superClassCompleteName};
import lombok.EqualsAndHashCode;
</#if>
import javax.validation.constraints.NotNull;
/**
 * <p>
 * ${table.comment!}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if entityLombokModel>
@Data
</#if>
<#if updateTemplateDTO.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${updateTemplateDTO.className} extends ${updateTemplateDTO.superClassSimpleName}{
<#else>
public class ${updateTemplateDTO.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.commonFields as field>
    <#if updateTemplateDTO.ignoreColumns?seq_contains(field.propertyName)>
    <#else>
    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
    @NotNull(message = "id不能为空")
    </#if>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#list table.fields as field>
    <#if updateTemplateDTO.ignoreColumns?seq_contains(field.propertyName)>
    <#else>
    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

}
