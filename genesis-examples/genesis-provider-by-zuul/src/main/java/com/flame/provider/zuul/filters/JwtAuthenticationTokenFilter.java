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


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String authToken = request.getHeader(this.tokenHeader);
        if (StringUtils.isBlank(authToken)) {
            throw new JwtAuthenticationTokenException(ErrorInfoEnum.AUTH_ERROR);
        }
//        log.info("checking authentication für Token: " + authToken);
        Authority authority = new Authority();
        authority.setToken(authToken);
        authority = authorityMapper.selectOne(authority);
        if (authority != null) {
            return null;
        } else {
//            ResultBody result = new ResultBody(ErrorInfoEnum.AUTH_ERROR);
//            getMessage(result);
//            return result;
            throw new JwtAuthenticationTokenException(ErrorInfoEnum.AUTH_ERROR);
        }
    }


    private void getMessage(ResultBody resultBody, Object... agrs) {
        String message = null;
        if (!org.springframework.util.StringUtils.isEmpty(resultBody.getCode())) {
            message = MessageUtils.message(resultBody.getCode(), agrs);
        }
        if (message == null) {
            message = resultBody.getMessage();
        }
        resultBody.setMessage(message);
    }
}
