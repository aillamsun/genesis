package com.flame.tx.user.money.client;

import com.flame.model.UserMoney;
import com.lorne.tx.springcloud.feign.TransactionRestTemplateConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lorne on 2017/6/27.
 */
@FeignClient(name = "genesis-tx-user-ms", configuration = TransactionRestTemplateConfiguration.class)
public interface UserClient {

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    List<UserMoney> findAll();

}
