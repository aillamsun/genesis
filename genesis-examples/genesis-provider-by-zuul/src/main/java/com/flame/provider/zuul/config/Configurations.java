package com.flame.provider.zuul.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {serviceId}-{version}  -> /{route}/{version}
 *
 * Created by sungang on 2016/12/20.
 */
@Configuration
public class Configurations {

//    @Bean
//    public PatternServiceRouteMapper serviceRouteMapper() {
//        return new PatternServiceRouteMapper("(?<serviceId>^.+)-(?<version>v.+$)","${route}/${version}");
//    }
}
