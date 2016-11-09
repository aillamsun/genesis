package com.flame.provider.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GenesisOrderProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisOrderProviderApp.class, args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisOrderProviderApp Successfully");
    }

}
