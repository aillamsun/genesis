package com.flame.core.result;


import com.flame.core.constant.GlobalErrorInfoEnum;

import java.io.Serializable;

/**
 * 返回体
 * Created by sungang on 2017/5/19.
 */
public class ResultBody implements Serializable {
    /**
     * 响应代码
     */
    private String code = "200";

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public ResultBody() {
    }

    public ResultBody(ErrorInfo errorInfo) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
    }

    public ResultBody(Object result) {
        this.code = GlobalErrorInfoEnum.SUCCESS.getCode();
        this.message = GlobalErrorInfoEnum.SUCCESS.getMessage();
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public ResultBody setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultBody setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ResultBody setResult(Object result) {
        this.result = result;
        return this;
    }

}
