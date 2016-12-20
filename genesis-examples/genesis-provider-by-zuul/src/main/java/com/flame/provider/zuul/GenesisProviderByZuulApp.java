package com.flame.provider.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by sungang on 2016/12/20.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GenesisProviderByZuulApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisProviderByZuulApp.class, args);
    }
}
