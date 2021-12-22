package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if entityLombokModel>
import lombok.Data;
</#if>
<#if voTemplate.superClassCompleteName??>
import ${voTemplate.superClassCompleteName};
import lombok.EqualsAndHashCode;
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
@Schema(name = "${table.comment!}", description = "${table.comment!}展示层")
<#if voTemplate.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${voTemplate.className} extends ${voTemplate.superClassSimpleName}{
<#else>
public class ${voTemplate.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if voTemplate.ignoreColumns?seq_contains(field.propertyName)>
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
