package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if entityLombokModel>
import lombok.Data;
</#if>
import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(name = "${table.comment!}", description = "新增${table.comment!}")
<#if addTemplateDto.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${addTemplateDto.className} extends ${addTemplateDto.superClassSimpleName}{
<#else>
public class ${addTemplateDto.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if addTemplateDto.ignoreColumns?seq_contains(field.propertyName)>
    <#else>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    /**
     * ${field.comment}
     */
    </#if>
    @Schema(description = "${field.comment!}")
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->

}
