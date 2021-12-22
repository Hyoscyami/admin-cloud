package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if entityLombokModel>
import lombok.Data;
</#if>
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "${table.comment!}", description = "查询${table.comment!}")
<#if updateTemplateDto.superClassCompleteName??>
@EqualsAndHashCode(callSuper = true)
public class ${updateTemplateDto.className} extends ${updateTemplateDto.superClassSimpleName}{
<#else>
public class ${updateTemplateDto.className} {
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if updateTemplateDto.ignoreColumns?seq_contains(field.propertyName)>
    <#else>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if field.keyIdentityFlag>
    @NotNull(message = "id不能为空")
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
