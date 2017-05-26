package com.flame.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
//开启Feign调用接口
@EnableFeignClients
//如果你不需要数据源,将它从auto-config排除
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//hystrix 监控
@EnableCircuitBreaker
 public class GenesisProviderByFeignApp extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GenesisProviderByFeignApp.class);
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GenesisProviderByFeignApp.class);

        app.run(args);
    }
}
