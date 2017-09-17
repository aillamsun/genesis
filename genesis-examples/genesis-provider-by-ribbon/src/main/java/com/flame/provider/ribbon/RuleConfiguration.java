package com.flame.provider.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 改类  不能 被 @SpringBootApplication 扫描到
 * Created by sungang on 2017/5/14.
 */
@Configuration
@ExcludeFromComponentScan
public class RuleConfiguration {


//    @Autowired
//    private IClientConfig config;

    /**
     * 自定义负载路由规则 默认：ZoneAvoidanceRule
     *
     * @return
     */
//    @Bean
//    public IRule ribbonRule() {
//        return new RandomRule();
//    }


}
