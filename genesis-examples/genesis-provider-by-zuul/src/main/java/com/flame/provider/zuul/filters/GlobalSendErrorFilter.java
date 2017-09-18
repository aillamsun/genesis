package com.flame.provider.zuul.filters;

import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.util.ReflectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sungang on 2017/9/18.
 */
public class GlobalSendErrorFilter extends SendErrorFilter{

    private static Logger log = LoggerFactory.getLogger(GlobalSendErrorFilter.class);

    @Value("${error.path:/error}")
    private String errorPath;


    @Override
    public int filterOrder() {
        return -1;
    }


    @Override
    public Object run() {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();

            int statusCode = (Integer) ctx.get("error.status_code");
            request.setAttribute("javax.servlet.error.status_code", statusCode);

            if (ctx.containsKey("error.exception")) {
                Object e = ctx.get("error.exception");
                log.warn("Error during filtering", Throwable.class.cast(e));
                request.setAttribute("javax.servlet.error.exception", e);
            }

            if (ctx.containsKey("error.message")) {
                String message = (String) ctx.get("error.message");
                request.setAttribute("javax.servlet.error.message", message);
            }

            if (ctx.containsKey("error.code")) {
                String code = (String) ctx.get("error.code");
                request.setAttribute("error.code", code);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(
                    this.errorPath);
            if (dispatcher != null) {
                ctx.set(SEND_ERROR_FILTER_RAN, true);
                if (!ctx.getResponse().isCommitted()) {
                    dispatcher.forward(request, ctx.getResponse());
                }
            }
        }
        catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }
}
