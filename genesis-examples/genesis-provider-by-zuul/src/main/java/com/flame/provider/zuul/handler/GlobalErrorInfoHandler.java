package com.flame.provider.zuul.handler;

import com.flame.core.constant.GlobalErrorInfoEnum;
import com.flame.core.result.ErrorInfo;
import com.flame.core.result.ResultBody;
import com.flame.provider.zuul.exception.GlobalErrorInfoException;
import com.flame.provider.zuul.exception.JwtAuthenticationTokenException;
import com.flame.provider.zuul.utils.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一错误码异常处理
 * <p>
 * Created by sungang on 2017/5/19.
 */

@RestControllerAdvice
public class GlobalErrorInfoHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalErrorInfoHandler.class);

    /**
     * 全局系统异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, RuntimeException exception) {
        logger.error("全局异常:", exception.getMessage());
        ResultBody result = new ResultBody(GlobalErrorInfoEnum.NOT_FOUND);
        return result;
    }


    @ExceptionHandler(value = JwtAuthenticationTokenException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request, JwtAuthenticationTokenException exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        getMessage(errorInfo, exception.getArgs());
        logger.error("认证失败:", errorInfo.toString());
        ResultBody result = new ResultBody(errorInfo);
        return result;
    }

    /**
     * GlobalErrorInfoException 系统异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody handleGlobalErrorInfoException(HttpServletRequest request, GlobalErrorInfoException exception) {
        ErrorInfo errorInfo = exception.getErrorInfo();
        getMessage(errorInfo, exception.getArgs());
        logger.error("错误消息:", errorInfo.toString());
        ResultBody result = new ResultBody(errorInfo);
        return result;
    }

    private void getMessage(ErrorInfo errorInfo, Object... agrs) {
        String message = null;
        if (!StringUtils.isEmpty(errorInfo.getCode())) {
            message = MessageUtils.message(errorInfo.getCode(), agrs);
        }
        if (message == null) {
            message = errorInfo.getMessage();
        }
        errorInfo.setMessage(message);
    }
}