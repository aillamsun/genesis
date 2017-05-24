package com.flame.microservices.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @Autowired
    LoadBalancerClient lb;

    @RequestMapping("/")
    public ServiceInstance home() {
        return lb.choose("eureka-server-clustered");
    }
}
