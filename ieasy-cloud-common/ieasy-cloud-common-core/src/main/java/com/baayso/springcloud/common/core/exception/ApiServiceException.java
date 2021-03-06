package com.baayso.springcloud.common.core.exception;

import com.baayso.commons.exception.ApiException;
import com.baayso.commons.tool.ResponseStatus;

/**
 * 专用于 API Service层的异常。
 *
 * @author ChenFangjie (2014/12/20 16:39:58)
 * @since 0.1
 */
public class ApiServiceException extends ApiException {

    private static final long serialVersionUID = -3247721709918992766L;

    public ApiServiceException() {
    }

    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public ApiServiceException(int code, String message) {
        super(code, message);
    }

    public ApiServiceException(ResponseStatus responseStatus, String message) {
        super(responseStatus, message);
    }

}
