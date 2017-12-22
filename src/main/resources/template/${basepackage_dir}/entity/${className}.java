<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

<#include "/java_imports.include">
import java.sql.Timestamp;
import java.io.Serializable;
import javax.persistence.*;
import com.hhly.partner.domain.base.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * ${table.remarks}实体类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Entity
@Table(name = "${table.sqlName}")
@ApiModel(value="${table.remarks}对象")
public class ${className}{
    
	/** FXM */
    private static final long serialVersionUID = 5454155825314635342L;
    
	<@generateFields/>
	<@generatePkProperties/>
	<@generateNotPkProperties/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>

	/**
	 * 重写String方法
	 *
	 * @return 字符串
	 */
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
		<#list table.columns as column>
			<#if !table.compositeId>
			.append("${column.columnName}",get${column.columnName}())
			</#if>
		</#list>
			.toString();
	}
	
	/**
	 * 重写hashCode方法
	 *
	 * @return hashCode
	 */
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			<#if !table.compositeId>
			.append(get${column.columnName}())
			</#if>
		</#list>
			.toHashCode();
	}
	
	/**
	 * 重写比较方法
	 *
	 * @param obj 对象
	 * @return true/false
	 */
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
				<#if !table.compositeId>
			.append(get${column.columnName}(),other.get${column.columnName}())
				</#if>
			</#list>
			.isEquals();
	}
}
<#macro generateFields>
<#if table.compositeId>
    /** 主键ID */
    @Id 
	private ${className}Id id;
	<#list table.columns as column>
		<#if !column.pk>
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
<#else>
	<#list table.columns as column>
	
	<#if column.pk>
	/** ${column.columnAlias}*/
	@Id 
	@Column(name = "${column.sqlName}")
	@ApiModelProperty(value="${column.columnAlias}" ,required=true,dataType="${column.javaType}")
	private ${column.javaType} ${column.columnNameLower};
	<#else>
	
	<#if column.isDateTimeColumn>
	/** ${column.columnAlias}开始时间*/
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="${column.columnAlias}开始，仅用在查询中" ,required=false,dataType="${column.javaType}")
	private ${column.javaType} ${column.columnNameLower}Begin;
	
	/** ${column.columnAlias}结束时间*/
	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="${column.columnAlias}结束，仅用在查询中" ,required=false,dataType="${column.javaType}")
	private ${column.javaType} ${column.columnNameLower}End;
	
	/** ${column.columnAlias}*/
	@Column(name = "${column.sqlName}")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="${column.columnAlias}" ,required=true,dataType="${column.javaType}")
	private ${column.javaType} ${column.columnNameLower};
	
	<#else>
	/** ${column.columnAlias}*/
	@Column(name = "${column.sqlName}")
	@ApiModelProperty(value="${column.columnAlias}" ,required=true,dataType="${column.javaType}")
	private ${column.javaType} ${column.columnNameLower};
	</#if>
	
	</#if>
	</#list>
</#if>

</#macro>

<#macro generatePkProperties>
	<#if table.compositeId>
	
	/**
     * 主键ID get方法
     * @return 获取主键ID
     */
	@EmbeddedId
	public ${className}Id getId() {
		return this.id;
	}
	
	/**
     * 主键ID set方法
     * @param id 主键ID
     */
	public void setId(${className}Id id) {
		this.id = id;
	}
	<#else>
		<#list table.columns as column>
			<#if column.pk>

    /**
     * ${column.columnAlias} set方法
     * @param ${column.columnNameLower} ${column.columnAlias}
     */
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	/**
     * ${column.columnAlias} get方法
     * @return 获取${column.columnAlias}
     */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
			</#if>
		</#list>
	</#if>
	
</#macro>

<#macro generateNotPkProperties>
	<#list table.columns as column>
		<#if !column.pk>
	
	<#if column.isDateTimeColumn>

    /**
     * ${column.columnAlias}开始时间 get方法
     * @return 获取${column.columnAlias}开始时间
     */
	public ${column.javaType} get${column.columnName}Begin() {
		return this.${column.columnNameLower}Begin;
	}
	
	/**
     * ${column.columnAlias}开始时间 set方法
     * @param ${column.columnNameLower} ${column.columnAlias}开始时间
     */
	public void set${column.columnName}Begin(${column.javaType} ${column.columnNameLower}Begin) {
		this.${column.columnNameLower}Begin = ${column.columnNameLower}Begin;
	}
	
	/**
	 * ${column.columnAlias}结束时间 get方法
	 * @return 获取${column.columnAlias}结束时间
	 */
	public ${column.javaType} get${column.columnName}End() {
		return this.${column.columnNameLower}Begin;
	}
	
	/**
     * ${column.columnAlias}结束时间 set方法
     * @param ${column.columnNameLower} ${column.columnAlias}结束时间
     */
	public void set${column.columnName}End(${column.javaType} ${column.columnNameLower}End) {
		this.${column.columnNameLower}End = ${column.columnNameLower}End;
	}
	
	</#if>
	/**
     * ${column.columnAlias} get方法
     * @return 获取${column.columnAlias}
     */
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	/**
     * ${column.columnAlias} set方法
     * @param ${column.columnNameLower} ${column.columnAlias}
     */
	public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
			
		</#if>
	</#list>
</#macro>




<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	<#list foreignKey.parentColumns?values as fkColumn>
	@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false)
    </#list>
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>

