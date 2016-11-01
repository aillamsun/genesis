package com.flame.server.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 * Created by sungang on 2016/10/27.
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${message}")
    private String message;

    @GetMapping("/message")
    public String hello() {
        return this.message;
    }
}
