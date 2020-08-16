package com.baayso.springcloud.common.core.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.baayso.commons.utils.JsonUtils;
import com.baayso.commons.utils.Validator;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

/**
 * 通用控制器。
 *
 * @author ChenFangjie (2016/12/12 14:43)
 * @since 0.1
 */
public class CommonController {

    /** 默认页码 */
    protected static final String DEFAULT_PAGE_NUM  = "1";
    /** 默认每页记录数 */
    protected static final String DEFAULT_PAGE_SIZE = "10";

    @Inject
    protected Validator validator;


    /**
     * 获取 HTTP 请求。
     *
     * @return {@linkplain HttpServletRequest}
     *
     * @since 1.0.0
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }

        return request;
    }

    /**
     * 获取 HTTP 响应。
     *
     * @return {@linkplain HttpServletResponse}
     *
     * @since 1.0.0
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = null;

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {
            response = ((ServletRequestAttributes) requestAttributes).getResponse();
        }

        return response;
    }

    /**
     * 获取本地化，从HttpServletRequest中获取，没有则返回Locale.SIMPLIFIED_CHINESE
     *
     * @return Locale
     */
    public static Locale getLocal() {
        HttpServletRequest request = getRequest();

        if (request == null) {
            return Locale.SIMPLIFIED_CHINESE;
        }

        return request.getLocale();
    }

    /**
     * 从 HTTP Request 中获取 accessToken（API访问凭证）。
     *
     * @return API访问凭证
     *
     * @since 1.0.0
     */
    public static String getAccessToken() {
        return getRequest().getParameter("accessToken");
    }

    /**
     * 从 HTTP Request 中获取 userToken（用户凭证）。
     *
     * @return 用户凭证
     *
     * @since 1.0.0
     */
    public static String getUserToken() {
        return getRequest().getParameter("userToken");
    }

    /**
     * 使用给定的 JSON Schema 验证给定的字符串，如果符合要求返回true。
     *
     * @param schema JSON 模式
     * @param json   字符串
     *
     * @return 符合要求返回true，否则返回false
     *
     * @since 1.0.0
     */
    public boolean isJson(JsonNode schema, String json) {
        try {
            JsonNode data = JsonUtils.INSTANCE.getMapper().readTree(json);

            ProcessingReport report = JsonSchemaFactory.byDefault().getValidator().validateUnchecked(schema, data);

            return report.isSuccess();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
