package com.baayso.springcloud.common.datasource.mybatis.tenant;

import javax.servlet.http.HttpServletRequest;

import com.baayso.springcloud.common.core.controller.CommonController;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSchemaHandler;

/**
 * 租户处理器(schema 级)。
 *
 * @author ChenFangjie (2020/8/9 11:13)
 * @since 0.1
 */
public class BasicTenantSchemaHandler implements TenantSchemaHandler {

    @Override
    public String getTenantSchema() {
        HttpServletRequest request = CommonController.getRequest();

        return request.getHeader("tenantCode");
    }

    @Override
    public boolean doTableFilter(String tableName) {
        // 这里可以判断是否过滤表

        return tableName.startsWith("sys_");
    }

}
