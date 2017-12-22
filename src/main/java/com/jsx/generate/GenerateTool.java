package com.jsx.generate;

import com.jsx.generate.core.Generator;
import com.jsx.generate.core.GeneratorConstants;
import com.jsx.generate.core.GeneratorFacade;
import com.jsx.generate.core.GeneratorProperties;

import java.io.File;

/**
* 代码生成工具入口类
* @author jiasx
* @create 2017/12/22 12:05
**/
public class GenerateTool {
    
    public static void main(String[] args) throws Exception {
        GeneratorFacade generatorFacade = new GeneratorFacade();
        generatorFacade.deleteOutRootDir();
        Generator generate = generatorFacade.getGenerator();
        String templatePath = GenerateTool.class.getClassLoader().getResource("template").getPath();
        generate.addTemplateRootDir(new File(templatePath));
        GeneratorProperties.setProperty(GeneratorConstants.TABLE_REMOVE_PREFIXES, "t_");
        generatorFacade.generateByAllTable();
//        generatorFacade.generateByTable("table1","table2");

//        Sql userSql = new SqlFactory().parseSql("select t.id,t.account FROM user t where t.id = #userId#");
//        userSql.setParameterClass("User");
//        generatorFacade.generateBySql(userSql);
    }
}
