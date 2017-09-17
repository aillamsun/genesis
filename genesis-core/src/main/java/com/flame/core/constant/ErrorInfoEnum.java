package com.flame.core.constant;


import com.flame.core.result.ErrorInfo;

/**
 * Created by sungang on 2017/5/19.
 */
public enum ErrorInfoEnum implements ErrorInfo {

    AUTH_ERROR("000000","认证失败!");

    private String code;

    private String message;

    ErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
