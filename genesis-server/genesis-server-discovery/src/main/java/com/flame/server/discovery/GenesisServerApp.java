package com.flame.server.discovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
//
@EnableEurekaServer
public class GenesisServerApp {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(GenesisServerApp.class).web(true).run(args);
    }
    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("Genesis Server Boot Successfully");
    }
}
