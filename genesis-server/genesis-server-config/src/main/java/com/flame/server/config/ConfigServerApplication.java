package com.flame.server.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 通过@EnableConfigServer注解激活配置服务.
 * 说明：
 * 在application.yml中有个git.uri的配置，目前配置的是https://github.com/eacdy/spring-cloud-study/
 * 获取git上的资源信息遵循如下规则：
 *
 *
 * Created by sungang on 2016/10/27.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
}
