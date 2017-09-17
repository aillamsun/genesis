package com.flame.provider.zuul.exception;

import com.flame.core.result.ErrorInfo;

/**
 * Created by sungang on 2017/9/17.
 */
public class JwtAuthenticationTokenException extends RuntimeException{

    private ErrorInfo errorInfo;

    private Object[] args;

    public JwtAuthenticationTokenException(ErrorInfo errorInfo, Object... agrs) {
        this.errorInfo = errorInfo;
        this.args = agrs;
    }


    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
