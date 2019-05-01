package com.xt.mp.test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * 测试
 */
public class GeneratorTest {

    /**
     * 代码生成    示例代码
     */
    @Test
    public void testGenerator () {
        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) //是否支持AR模式
                .setAuthor("xt") //作者
                .setOutputDir("D:/ideaProjects/mybatis-plus/src/main/java") //生成路径
                .setFileOverride(true) //文件覆盖
                .setServiceName("%sService") //设置生成的service接口名首字母是否为I
                .setIdType(IdType.AUTO) //主键策略
                .setBaseResultMap(true)  // resultMap
                .setBaseColumnList(true) // sql 片段
        ;

        // 2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setUrl("jdbc:mysql://localhost:3306/mybatis?useSSL=false")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("root");

        // 3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setDbColumnUnderline(true) //表名 字段名 是否使用下滑线命名
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude("mp_employee2") //生成的表
                .setTablePrefix("mp_"); // 表前缀

        // 4. 包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.xt.mp")
                .setController("controller")
                .setEntity("bean")
                .setMapper("mapper")
                .setService("service")
                .setXml("mapper");

        // 5. 整合配置
        AutoGenerator ag = new AutoGenerator()
                .setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        // 6. 执行
        ag.execute();
    }
}
