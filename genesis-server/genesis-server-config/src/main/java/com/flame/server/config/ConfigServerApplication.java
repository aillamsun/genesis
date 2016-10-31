package com.flame.server.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 通过@EnableConfigServer注解激活配置服务.
 * 说明：
 * 在application.yml中有个git.uri的配置，目前配置的是
 * 获取git上的资源信息遵循如下规则：
 * Created by sungang on 2016/10/27.
 */
@SpringBootApplication
@EnableConfigServer
@RefreshScope
public class ConfigServerApplication {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(ConfigServerApplication.class).web(true).run(args);
    }

    //程序首次启动调用
    public void run(String... strings) throws Exception {
        System.out.println("Genesis Server Config Boot Successfully");
    }
}
