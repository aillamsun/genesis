package com.flame.provider.good;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by sungang on 2016/11/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GenesisGoodsProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisGoodsProviderApp.class, args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisGoodsProviderApp Boot Successfully");
    }

}
