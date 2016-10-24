package com.flame.demo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GenesisDemoOrderServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisDemoOrderServiceApp.class, args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("Genesis Demo Order Service Boot Successfully");
    }

}
