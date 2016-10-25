package com.flame.demo.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GenesisDemoGoodsServiceApp2 {

    public static void main(String[] args) {
        SpringApplication.run(GenesisDemoGoodsServiceApp2.class, args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("Genesis Demo Goods Service Boot Successfully");
    }

}
