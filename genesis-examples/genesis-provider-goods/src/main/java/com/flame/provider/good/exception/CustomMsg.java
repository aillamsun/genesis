package com.flame.provider.good.exception;

/**
 * Created by sungang on 2016/11/23.
 */
public class CustomMsg {

    private Integer code;

    private String message;

    private Object data;

    public CustomMsg() {
    }

    public CustomMsg(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
