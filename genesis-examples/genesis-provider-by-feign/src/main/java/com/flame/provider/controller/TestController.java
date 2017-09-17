package com.flame.provider.controller;

import com.flame.provider.service.TestFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sungang on 2017/9/16.
 */
@Api(value = "测试Feign", description = "测试Feign", tags = "测试Feign", position = 0)
@RestController
@RequestMapping("/feign")
public class TestController {

    @Autowired
    private TestFeignClient feignClient;

    @GetMapping("{serviceName}")
    public String findServiceNameFromEurekaServiceName(@PathVariable("serviceName")String serviceName){
        return feignClient.findServiceNameFromEurekaServiceName(serviceName);

    }
}
