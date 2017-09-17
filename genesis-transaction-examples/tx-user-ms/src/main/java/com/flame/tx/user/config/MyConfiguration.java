package com.flame.tx.user.config;

import com.lorne.tx.springcloud.feign.TransactionRestTemplateInterceptor;
import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by sungang on 2017/6/28.
 */

@Configuration
public class MyConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().requestInterceptor(new TransactionRestTemplateInterceptor());
    }
}
