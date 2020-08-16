package com.baayso.springcloud.common.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.baayso.commons.tool.BasicResponseStatus;
import com.baayso.commons.utils.Validator;
import com.baayso.springcloud.common.core.exception.ApiViolationException;

/**
 * 验证租户参数拦截器。
 *
 * @author ChenFangjie (2020/8/9 12:37)
 * @since 0.1
 */
public class TenantInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Validator validate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String tenantCode = request.getHeader("tenantCode");

        if (StringUtils.isBlank(tenantCode)) {
            throw new ApiViolationException(BasicResponseStatus.PARAMETER_MISSING, "缺少tenantCode参数");
        }

        if (!this.validate.isEnglishAndNumberAndUnderscore(tenantCode, 3, 30)) {
            throw new ApiViolationException(BasicResponseStatus.PARAMETER_CHECK_FAILED, "tenantCode参数为3-30个字母、数字或者下划线");
        }

        return super.preHandle(request, response, handler);
    }

}
