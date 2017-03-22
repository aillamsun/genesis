package com.flame.provider.zuul;

import com.flame.provider.zuul.filters.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

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

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
