package com.sung.result;

/**
 * 错误码接口
 * <p>
 * Created by sungang on 2017/5/19.
 */
public interface ErrorInfo {

    String getCode();

    String getMessage();

    void setMessage(String message);
}
