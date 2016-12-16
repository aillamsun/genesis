package com.flame.provider.good.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sungang on 2016/11/23.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

    @ExceptionHandler({RuntimeException.class})
//    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity handleControllerRestException(HttpServletRequest request, Throwable ex) {
        logger.debug("运行时异常！", ex);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity(new CustomMsg(status.value(), ex.getMessage(), null), status);
    }

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseEntity handleControllerException(HttpServletRequest request, Throwable ex) {
        logger.error("系统异常！", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity(new CustomMsg(status.value(), ex.getMessage(), null), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.OK;
        }
        return HttpStatus.valueOf(statusCode);
    }


}
