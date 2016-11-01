package com.flame.server.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by sungang on 2016/10/27.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientEurekaApplication.class, args);
    }
}
