<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service;

<#include "/java_imports.include">
import ${basepackage}.entity.${className};


/**
 * 
 * ${table.remarks} 服务接口
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
public interface ${className}Service {
    
    /**
     * 查询${table.remarks},带分页
     * @param ${classNameLower}
     * @return
     */
	long getTotalCount(${className} ${classNameLower});
    
    /**
     * 查询${table.remarks},带分页
     * @param ${classNameLower}
     * @return
     */
    List<${className}> getList(${className} ${classNameLower});

    /**
     * 新增${table.remarks}
     * @param ${classNameLower}
     */
    void insert(${className} ${classNameLower});

    /**
     * 修改${table.remarks}
     * @param ${classNameLower}
     */
    void update(${className} ${classNameLower});

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
