# 代码生成工具 #
## 环境要求 ##

+ JDK 1.8

## maven打包命令 ##

+ mvn clean package -Dmaven.test.skip=true
 
## 功能 ##

+ 支持oracle、mysql、sqlserver等主流数据库的代码生成
+ 支持按照表名生成和查询语句生成。
+ 支持定义模板生成，可修改为自己工程业务需要的模板。模板的结构和生成后的代码结构严格保持一致。


## 使用说明 ##
+ 编译前，需要将lib下面的jar安装到本地maven库中去，直接点击install_jar.bat即可安装。

+ resources/template是模板，可以根据自己的项目结构和要求重新修改

com.jsx.generate.GenerateTool 是生成工具的入口类，使用示例如下：
+ 表名生成
    generatorFacade.generateByTable("table1","table2");
    generatorFacade.generateByAllTable();
+ sql生成
    Sql userSql = new SqlFactory().parseSql("select t.id,t.account FROM user t where t.id = #userId#");
    userSql.setParameterClass("User");
    generatorFacade.generateBySql(userSql);

 

 










