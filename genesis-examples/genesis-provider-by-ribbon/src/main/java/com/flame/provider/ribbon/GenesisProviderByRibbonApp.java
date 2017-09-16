package com.flame.provider.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sungang on 2016/10/21.
 */
@SpringBootApplication
@EnableDiscoveryClient
//hystrix 监控
@EnableCircuitBreaker
/***
 *
 *  configuration 默认使用的是 RibbonClientConfiguration
 *
 *  多个RibbonClient
 */
@RibbonClient(name = "genesis-provider-goods", configuration = TestConfiguration.class)
/**
 *
 */
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)
})
public class GenesisProviderByRibbonApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisProviderByRibbonApp.class, args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("GenesisProviderByRibbonApp Successfully");
    }

    /**
     * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
     *
     * @return restTemplate
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
