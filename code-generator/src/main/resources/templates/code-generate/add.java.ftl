package ${package.Entity};

<#if entityLombokModel>
import lombok.Data;
</#if>
<#if addTemplateDTO.superClassCompleteName??>
import ${addTemplateDTO.superClassCompleteName};
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
<#if addTemplateDTO.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${addTemplateDTO.className} extends ${addTemplateDTO.superClassSimpleName}{
<#else>
public class ${addTemplateDTO.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if addTemplateDTO.ignoreColumns?seq_contains(field.propertyName)>
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
