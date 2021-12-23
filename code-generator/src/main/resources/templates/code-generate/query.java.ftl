package ${queryTemplateDTO.packageName};

<#if entityLombokModel>
import lombok.Data;
</#if>
<#if queryTemplateDTO.superClassCompleteName??>
import ${queryTemplateDTO.superClassCompleteName};
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
<#if queryTemplateDTO.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${queryTemplateDTO.className} extends ${queryTemplateDTO.superClassSimpleName}{
<#else>
public class ${queryTemplateDTO.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if queryTemplateDTO.ignoreColumns?seq_contains(field.propertyName)>
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
