package com.flame.provider.good;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sungang on 2016/11/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.flame.mapper")
//使用@EnableCircuitBreaker注解开启断路器功能
@EnableCircuitBreaker
//@EnableOAuth2Client
public class GenesisGoodsProviderApp extends SpringBootServletInitializer{


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GenesisGoodsProviderApp.class);
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GenesisGoodsProviderApp.class);

        app.run(args);
    }
}
