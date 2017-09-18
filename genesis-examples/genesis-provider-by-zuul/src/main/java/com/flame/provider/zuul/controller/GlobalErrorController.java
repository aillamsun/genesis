package com.flame.provider.zuul.controller;

import com.flame.core.result.ResultBody;
import com.flame.core.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 重写 Zuul SendErrorFilter 转发的ErrorController
 * Created by sungang on 2017/9/18.
 */

@RestController
public class GlobalErrorController implements ErrorController {


    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return errorPath;
    }

    @RequestMapping(value = "${error.path:/error}", produces = "application/vnd.error+json")
    public ResultBody error(HttpServletRequest request) {
        final String statusCode = getErrorStatus(request);
        final String errorMessage = getErrorMessage(request);
        ResultBody resultBody = ResultGenerator.genSuccessResult();
        resultBody.setCode(statusCode);
        resultBody.setMessage(errorMessage);
        return resultBody;
    }

    private String getErrorStatus(HttpServletRequest request) {
        String statusCode = (String)request.getAttribute("error.code");
        return statusCode != null ? statusCode : String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private String getErrorMessage(HttpServletRequest request) {
        String errorMsg = (String) request.getAttribute("javax.servlet.error.message");
        return errorMsg != null ? errorMsg : "Unexpected error occurred";
    }
}
