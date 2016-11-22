package com.flame.provider.good;

import com.flame.plugin.spring.mybatis.config.MapperProperties;
import com.flame.plugin.spring.mybatis.config.PageHelperProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by sungang on 2016/11/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.flame.plugin.spring.mybatis.config","com.flame.provider.good"})
public class GenesisGoodsProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisGoodsProviderApp.class, args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisGoodsProviderApp Boot Successfully");
    }

}
