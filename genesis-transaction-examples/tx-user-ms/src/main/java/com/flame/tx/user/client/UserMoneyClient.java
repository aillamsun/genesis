package com.flame.tx.user.client;

import com.lorne.tx.springcloud.feign.TransactionRestTemplateConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sungang on 2017/9/17.
 */
@FeignClient(name = "genesis-tx-user-money-ms", configuration = TransactionRestTemplateConfiguration.class)
public interface UserMoneyClient {

    @RequestMapping(value = "/user-money/save",method = RequestMethod.POST)
    int save();
}
