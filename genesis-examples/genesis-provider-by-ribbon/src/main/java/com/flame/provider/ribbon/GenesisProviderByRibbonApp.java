package com.flame.provider.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
//hystrix 监控
@EnableCircuitBreaker
public class GenesisProviderByRibbonApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisProviderByRibbonApp.class,args);
    }
    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisProviderByRibbonApp Successfully");
    }

}
