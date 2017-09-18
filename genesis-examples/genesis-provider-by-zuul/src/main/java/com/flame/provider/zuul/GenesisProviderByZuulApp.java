package com.flame.provider.zuul;

import com.flame.provider.zuul.utils.SpringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.undertow.UndertowDeploymentInfoCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * Created by sungang on 2016/12/20.
 */
@SpringBootApplication
@EnableZuulProxy
@MapperScan("com.flame.mapper")
public class GenesisProviderByZuulApp {

    public static void main(String[] args) {
        SpringApplication.run(GenesisProviderByZuulApp.class, args);
    }


    /**
     * 正则 匹配路由 规则
     *
     * genesis-provider-goods-v1
     *
     * 请求方式 zuul:port/v1/genesis-provider-goods/**
     *
     *
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper() {
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
    }

    @Bean
    public SpringUtils springUtilsBean() {
        return new SpringUtils();
    }


//    @Bean
//    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
//        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
//        factory.addDeploymentInfoCustomizers(new UndertowDeploymentInfoCustomizer() {
//            @Override
//            public void customize(DeploymentInfo deploymentInfo) {
//                deploymentInfo.setAllowNonStandardWrappers(true);
//            }
//        });
//        return factory;
//    }
}
