<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.controller;

<#include "/java_imports.include">
import java.sql.Timestamp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jsx.entry.CODE;
import com.jsx.exception.BizException;
import com.jsx.util.StringUtil;
import com.jsx.util.Utils;
import ${basepackage}.entity.${className};
import ${basepackage}.service.${className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * ${table.remarks} rest服务
 * @author jiasx
 * @since 1.0
 * @version 2017-07-06 jiasx
 */
@RestController
@RequestMapping("/${classNameLower}")
@Api(tags = {"${table.remarks}"},description="${table.remarks}服务")
public class ${className}Controller {
	
	/**
	 * 日志记录
	 */
	private Logger logger = LoggerFactory.getLogger(${className}Controller.class);
	

    @Autowired
    private ${className}Service ${classNameLower}Service;
    
    /**
     * 查询${table.remarks},带分页
     * @param ${classNameLower} ${table.remarks}对象
     * @return
     */
    @RequestMapping("/count")
    @ApiOperation(value="查询${table.remarks}数量", notes="根据条件查询${table.remarks}数量",httpMethod = "POST", response = Long.class)
    public long getTotalCount(${className} ${classNameLower}){
    	return ${classNameLower}Service.getTotalCount(${classNameLower});
    }
    

	/**
	 * 查询${table.remarks}列表
	 * @param ${classNameLower} ${table.remarks}对象
	 * @return
	 */
	@RequestMapping(value="/list")
	@ApiOperation(value="查询${table.remarks}列表", notes="根据条件查询${table.remarks}列表，不分页",httpMethod = "POST", response = List.class)
	public List<${className}> getList(${className} ${classNameLower}){
		PageHelper.startPage(1,0);
		return ${classNameLower}Service.getList(${classNameLower});
	}

    /**
     * 新增${table.remarks}
     * @param ${classNameLower} ${table.remarks}对象
     */
    @RequestMapping("/insert")
    @ApiOperation(value="新增${table.remarks}", notes="新增${table.remarks}，必填项不能为空",httpMethod = "POST", response = String.class)
    public String insert(${className} ${classNameLower}) {
		${classNameLower}.setId(Utils.generateId());
        ${classNameLower}Service.insert(${classNameLower});

        return "OK";
    }

    /**
     * 修改${table.remarks}
     * @param ${classNameLower} ${table.remarks}对象
     */
    @RequestMapping("/update")
    @ApiOperation(value="更新${table.remarks}", notes="更新${table.remarks}，需要主键Id，必填项不能为空",httpMethod = "POST", response = String.class)
    public String update(${className} ${classNameLower}) {
    	${className} db${className} = ${classNameLower}Service.getById(${classNameLower}.getId());
    	if(db${className}==null || StringUtil.hasNull(db${className}.getId())){
    		 throw new BizException(CODE.partner_data_not_exists);
        }
    	<#list table.columns as column>
    	<#if column.sqlName!="id">
    	db${className}.set${column.columnName}(${classNameLower}.get${column.columnName}());
		</#if>
		</#list>
    	${classNameLower}Service.update(db${className});
		return "OK";
    }

    /**
     * 根据Id查询${table.remarks}
     * @param id 主键id
     */
    @RequestMapping("/get")
    @ApiOperation(value="获取${table.remarks}对象", notes="根据主键Id获取${table.remarks}对象",httpMethod = "POST", response = String.class)
    public ${className} getById(Long id){
		if(id == null){
			throw new BizException(CODE.checkNotNull);
		}
    	//先从缓存中查询，查询不到，再从数据库中查询
    	${className} ${classNameLower} = ${classNameLower}Service.getById(id);
        if(${classNameLower}==null){
            throw new BizException(CODE.has_no_data);
        }
    	return ${classNameLower};
    }

    /**
     * 删除${table.remarks}
     * @param id 主键id
     */
    @RequestMapping("/delete")
    @ApiOperation(value="删除${table.remarks}对象", notes="根据主键Id删除${table.remarks}对象",httpMethod = "POST", response = String.class)
    public String deleteById(Long id) {
    	if(id == null){
            throw new BizException(CODE.checkNotNull);
        }
    	${classNameLower}Service.deleteById(id);
    	//需要加上删除redis的语句
        return  ok();
    }

}
