package ${voTemplateDTO.packageName};

<#if entityLombokModel>
import lombok.Data;
</#if>
<#if voTemplateDTO.superClassCompleteName??>
import ${voTemplateDTO.superClassCompleteName};
import lombok.EqualsAndHashCode;
</#if>
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
<#if voTemplateDTO.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${voTemplateDTO.className} extends ${voTemplateDTO.superClassSimpleName}{
<#else>
public class ${voTemplateDTO.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if voTemplateDTO.ignoreColumns?seq_contains(field.propertyName)>
    <#else>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

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
