<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.jpa;

<#include "/java_imports.include">
import ${basepackage}.entity.${className};
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 
 * ${table.remarks}数据库操作类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
public interface ${className}Repository  extends JpaRepository<${className},Long> {
    

}
