package com.flame.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
//开启Feign调用接口
@EnableFeignClients
//如果你不需要数据源,将它从auto-config排除
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class GenesisProviderByFeignApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisProviderByFeignApp.class,args);
    }
    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisProviderApp Successfully");
    }

}
