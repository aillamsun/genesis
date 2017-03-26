package com.flame.provider.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.flame.provider.order.mapper")
//使用@EnableCircuitBreaker注解开启断路器功能
@EnableCircuitBreaker
public class GenesisOrderProviderApp extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GenesisOrderProviderApp.class);
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GenesisOrderProviderApp.class);

        app.run(args);
    }

}
