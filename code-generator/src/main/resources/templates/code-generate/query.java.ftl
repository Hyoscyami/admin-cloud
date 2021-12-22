package ${package.Entity};

<#if entityLombokModel>
import lombok.Data;
</#if>
<#if queryTemplateDto.superClassCompleteName??>
import ${queryTemplateDto.superClassCompleteName};
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
<#if queryTemplateDto.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${queryTemplateDto.className} extends ${queryTemplateDto.superClassSimpleName}{
<#else>
public class ${queryTemplateDto.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if queryTemplateDto.ignoreColumns?seq_contains(field.propertyName)>
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
