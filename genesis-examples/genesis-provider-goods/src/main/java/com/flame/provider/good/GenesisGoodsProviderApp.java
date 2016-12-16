package com.flame.provider.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sungang on 2016/11/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.flame.plugin.spring.mybatis.config", "com.flame.provider.good"})
public class GenesisGoodsProviderApp{


    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisGoodsProviderApp Boot Successfully");
    }

    public static void main(String[] args) {
        SpringApplication.run(GenesisGoodsProviderApp.class, args);
    }
}
