package ${package.Entity};

<#if entityLombokModel>
import lombok.Data;
</#if>
<#if updateTemplateDto.superClassCompleteName??>
import ${updateTemplateDto.superClassCompleteName};
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
<#if updateTemplateDto.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${updateTemplateDto.className} extends ${updateTemplateDto.superClassSimpleName}{
<#else>
public class ${updateTemplateDto.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.commonFields as field>
    <#if updateTemplateDto.ignoreColumns?seq_contains(field.propertyName)>
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
    <#if updateTemplateDto.ignoreColumns?seq_contains(field.propertyName)>
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
