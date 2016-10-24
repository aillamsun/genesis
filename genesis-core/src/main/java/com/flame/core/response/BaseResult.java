package com.flame.core.response;

/**
 * Created by sungang on 2016/10/21.
 */
public class BaseResult {

    private String code = "success";//状态码

    private String message;//附加消息

    private Object data; //附加数据


    public BaseResult() {
    }

    public BaseResult(String code) {
        this.code = code;
    }

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public BaseResult(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
