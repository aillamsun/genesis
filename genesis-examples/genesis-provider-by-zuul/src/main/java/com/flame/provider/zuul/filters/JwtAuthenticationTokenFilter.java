package com.flame.provider.zuul.filters;

import com.flame.core.constant.ErrorInfoEnum;
import com.flame.core.result.ErrorInfo;
import com.flame.core.result.ResultBody;
import com.flame.mapper.AuthorityMapper;
import com.flame.model.Authority;
import com.flame.provider.zuul.exception.JwtAuthenticationTokenException;
import com.flame.provider.zuul.utils.JwtTokenUtil;
import com.flame.provider.zuul.utils.MessageUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sungang on 2017/9/17.
 */
public class JwtAuthenticationTokenFilter extends ZuulFilter {


    private static Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);


    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    /**
     * 该函数需要返回一个字符串来代表过滤器的类型，而这个类型就是在HTTP请求过程中定义的各个阶段。在Zuul中默认定义了四种不同生命周期的过滤器类型，具体如下
     * pre：可以在请求被路由之前调用。
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用。
     * error：处理请求时发生错误时被调用。
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：通过int值来定义过滤器的执行顺序，数值越小优先级越高
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。在该函数中，我们可以实现自定义的过滤逻辑，
     * 来确定是否要拦截当前的请求，不对其进行后续的路由，
     * 或是在请求路由返回结果之后，对处理结果做一些加工等
     *
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            auth(request);
        } catch (JwtAuthenticationTokenException e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ErrorInfo errorInfo = e.getErrorInfo();
            getMessage(errorInfo, e.getArgs());
            ctx.set("error.message", errorInfo.getMessage());
            ctx.set("error.code",ErrorInfoEnum.AUTH_ERROR.getCode());
        }
        return null;
    }


    private void auth(HttpServletRequest request) throws JwtAuthenticationTokenException {
        String authToken = request.getHeader(this.tokenHeader);
        if (StringUtils.isBlank(authToken)) {
            throw new JwtAuthenticationTokenException(ErrorInfoEnum.AUTH_ERROR);
        }
        Authority authority = new Authority();
        authority.setToken(authToken);
        authority = authorityMapper.selectOne(authority);
        if (authority == null) {
            throw new JwtAuthenticationTokenException(ErrorInfoEnum.AUTH_ERROR);
        }
    }


    private void getMessage(ErrorInfo errorInfo, Object... agrs) {
        String message = null;
        if (!org.springframework.util.StringUtils.isEmpty(errorInfo.getCode())) {
            message = MessageUtils.message(errorInfo.getCode(), agrs);
        }
        if (message == null) {
            message = errorInfo.getMessage();
        }
        errorInfo.setMessage(message);
    }
}
