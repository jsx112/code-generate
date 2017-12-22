<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao.mybatis;

<#include "/java_imports.include">
import ${basepackage}.entity.${className};
import org.springframework.stereotype.Repository;

/**
 * 
 * ${table.remarks}数据库操作类
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Repository
public interface ${className}Dao {
    
    /**
     * 查询${table.remarks},带分页
     * @param entry
     * @return
     */
	long getTotalCount(${className} entry);
    
    /**
     * 查询${table.remarks},带分页
     * @param entry
     * @return
     */
    List<${className}> getList(${className} entry);

    /**
     * 新增${table.remarks}
     * @param entry
     */
    void insert(${className} entry);

    /**
     * 修改${table.remarks}
     * @param entry
     */
    void update(${className} entry);

    /**
     * 根据Id查询${table.remarks}
     * @param id
     */
    ${className} getById(Long id);

    /**
     * 删除${table.remarks}
     * @param id
     */
    void deleteById(Long id);

}
