package com.beien.beienspringbootdbdoc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * 还在手动整理数据库文档？试试这个工具
 * 快速生成数据库数据字典文档的两种方式
 * 1.利用PowerDesigner逆向工程生成数据库数据字典文档
 * 2.SpringBoot+Screw快速生成数据库数据字典文档
 * 一键生成数据库表结构文档认准Screw工具
 * 简洁好用的数据库表结构文档工具，
 * 支持MySQL/MariaDB/SqlServer/Oracle
 * /PostgreSQL/TIDB/CacheDB 数据库
 *
 * @author 贝恩
 */
@SpringBootTest
class BeienDocumentGeneration {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 数据库文档生成
     */
    @Test
    void documentGeneration() {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        // 生成文件配置 创建screw的引擎配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir("D:\\workspace\\beien_springboot_dbdoc")
                // 打开目录
                .openOutputDir(true)
                // 文件类型 HTML->HTML文件  WORD->WORD文件  MD->Markdown文件
                .fileType(EngineFileType.WORD)
                //.fileType(EngineFileType.MD)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                // 自定义文件名称，即生成的数据库文档名称
                .fileName("验票服务数据库文档").build();
        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                // 版本
                .version("v1.0")
                // 描述
                .description("验票服务数据库设计文档")
                // 数据源
                .dataSource(dataSource)
                // 生成配置
                .engineConfig(engineConfig)
                // 生成配置
                .produceConfig(getProcessConfig())
                .build();
        // 执行screw，生成数据库文档
        new DocumentationExecute(config).execute();
    }
    /**
     * 配置想要生成的表+配置想要忽略的表
     */
    public static ProcessConfig getProcessConfig() {
        // 忽略表名
        List< String > ignoreTableName = Arrays.asList("t_user_info", "t_order");
        // 忽略表前缀，如忽略a开头的数据库表
        List< String > ignorePrefix = Arrays.asList("t_ticket");
        // 忽略表后缀
        List< String > ignoreSuffix = Arrays.asList("_test");
        // 需要生成数据库文档的表
        //List< String > designatedTableName = Arrays.asList("t_user", "t_role");
        // 需要生成数据库文档的表前缀
        List< String > designatedTablePrefix = Arrays.asList("t_", "t_role");
        // 需要生成数据库文档的表后缀
        List< String > designatedTableSuffix = Arrays.asList("_sys");
        // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
        return ProcessConfig.builder()
                // 根据名称指定表生成
                //.designatedTableName(designatedTableName)
                // 根据表前缀生成
               //.designatedTablePrefix(designatedTablePrefix)
                // 根据表后缀生成
                //.designatedTableSuffix(designatedTableSuffix)
                // 忽略表名
                .ignoreTableName(ignoreTableName)
                // 忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                // 忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
    }

}
