package com.flame.provider.service;

import com.flame.config.CConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sungang on 2017/9/16.
 */

@FeignClient(name = "xxx",url = "http://localhost:8761/",configuration = CConfiguration.class)
public interface TestFeignClient {

    /**
     * http://localhost:8761/eureka/apps
     * @param serviceName
     * @return
     */
    @RequestMapping(value = "/eureka/apps/{serviceName}",method = RequestMethod.GET)
    String findServiceNameFromEurekaServiceName(@PathVariable("serviceName")String serviceName);


}
