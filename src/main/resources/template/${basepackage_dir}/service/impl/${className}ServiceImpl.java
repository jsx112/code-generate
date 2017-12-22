<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.service.impl;

<#include "/java_imports.include">

import javax.transaction.Transactional;
import ${basepackage}.dao.jpa.${className}Repository;
import ${basepackage}.dao.mybatis.${className}Dao;
import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * ${table.remarks} 服务实现
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@Service
public class ${className}ServiceImpl implements ${className}Service {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(${className}ServiceImpl.class);
	
	@Autowired
    private ${className}Repository ${classNameLower}Repository;
    
    @Autowired
    private ${className}Dao ${classNameLower}Dao;
    
    /**
     * 查询${table.remarks},带分页
     * @param ${classNameLower}
     * @return
     */
    public long getTotalCount(${className} ${classNameLower}){
    	return ${classNameLower}Dao.getTotalCount(${classNameLower});
    }
    
    /**
     * 查询${table.remarks},带分页
     * @param ${classNameLower}
     * @return
     */
    public List<${className}> getList(${className} ${classNameLower}){
    	return ${classNameLower}Dao.getList(${classNameLower});
    }

    /**
     * 新增${table.remarks}
     * @param ${classNameLower}
     */
    public void insert(${className} ${classNameLower}) {
    	${classNameLower}Repository.save(${classNameLower});
    }

    /**
     * 修改${table.remarks}
     * @param ${classNameLower}
     */
    public void update(${className} ${classNameLower}) {
    	${classNameLower}Repository.save(${classNameLower});
    }

    /**
     * 根据Id查询${table.remarks}
     * @param id
     */
    public ${className} getById(Long id){
    	return ${classNameLower}Dao.getById(id);
    }

    /**
     * 删除${table.remarks}
     * @param id
     */
    public void deleteById(Long id) {
    	${classNameLower}Repository.delete(id);
    }

}
