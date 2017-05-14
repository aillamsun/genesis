package com.flame.provider.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 改类  不能 被 @SpringBootApplication 扫描到
 *
 * Created by sungang on 2017/5/14.
 */
@Configuration
@RibbonClient(name = "",configuration = TestConfiguration.class)
public class TestConfiguration {
}
