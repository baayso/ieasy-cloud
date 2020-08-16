package com.baayso.springcloud.common.core.interceptor;

import com.baayso.commons.interceptor.DataDigestInterceptorAdapter;

/**
 * 验证数据摘要拦截器。
 *
 * @author ChenFangjie (2017/2/25 19:21)
 * @since 0.1
 */
public class DataDigestInterceptor extends DataDigestInterceptorAdapter {

    @Override
    protected String getSalt() {
        return "";
    }

}
