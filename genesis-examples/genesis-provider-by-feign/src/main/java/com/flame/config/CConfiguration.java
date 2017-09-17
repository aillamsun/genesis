package com.flame.config;

import feign.Contract;
import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sungang on 2017/9/16.
 */
@Configuration
public class CConfiguration {

    /**
     * Contract feignContract: SpringMvcContract
     * 契约 默认是SpringMvcContract 所以可以使用SPring mvc相关注解
     *
     * 下面可以自定义契约
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "123456");
    }

    /**
     * The Logger.Level object that you may configure per client, tells Feign how much to log. Choices are:

        NONE, No logging (DEFAULT).

        BASIC, Log only the request method and URL and the response status code and execution time.

        HEADERS, Log the basic information along with request and response headers.

        FULL, Log the headers, body, and metadata for both requests and responses.

        默认是 NONE 没有日志
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLenel(){
        return Logger.Level.NONE;
    }
}
