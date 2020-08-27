package com.baayso.springcloud.config.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baayso.springcloud.common.datasource.mybatis.InsertAndUpdateMetaObjectHandler;
import com.baayso.springcloud.common.datasource.mybatis.tenant.BasicTenantHandler;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;

/**
 * Mybatis-Plus 配置。
 *
 * @author ChenFangjie (2017/12/4 21:14)
 * @since 0.1
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.baayso.**.dao*")
public class MybatisPlusConfig {

    /** 插入与更新时自动填充字段处理器 */
    @Bean
    public InsertAndUpdateMetaObjectHandler insertAndUpdateMetaObjectHandler() {
        return new InsertAndUpdateMetaObjectHandler();
    }

    /** 乐观锁插件 */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus 分页插件
     * <p>
     * 文档： https://mybatis.plus/guide/tenant.html
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        // 租户SQL解析器(tenantCode 行级)
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new BasicTenantHandler());

        // 租户SQL解析器(schema 级)
        // CustomTenantSchemaSqlParser tenantSchemaSqlParser = new CustomTenantSchemaSqlParser();
        // tenantSchemaSqlParser.setTenantSchemaHandler(new BasicTenantSchemaHandler());

        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(tenantSqlParser);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setSqlParserList(sqlParserList);

        return paginationInterceptor;
    }

}
