package com.flame.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 使用@EnableZuulProxy注解激活zuul。
 * 跟进该注解可以看到该注解整合了@EnableCircuitBreaker、@EnableDiscoveryClient，是个组合注解，目的是简化配置。
 * Created by sungang on 2016/10/25.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GenesisApiGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GenesisApiGatewayApp.class, args);
    }
}
