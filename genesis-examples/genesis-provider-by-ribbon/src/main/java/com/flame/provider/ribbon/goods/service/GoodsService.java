package com.flame.provider.ribbon.goods.service;

import com.flame.model.Goods;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by sungang on 2017/5/14.
 */
@Service
public class GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackFindAll")
    public List<Goods> findAll() {
        // http://服务提供者的serviceId/url
        return this.restTemplate.getForObject("http://genesis-provider-goods/goods", List.class);
    }


    /**
     * hystrix fallbackFindAll 方法
     *
     */
    public List<Goods> fallbackFindAll() {
        GoodsService.LOGGER.info("异常发生，进入 fallbackFindAll 方法");
        Goods goods = new Goods();
        goods.setId(-1l);
        goods.setGoodsName("defaul good name");
        goods.setCreateTime(new Date());
        goods.setStock(-1);
        List<Goods> goodss = Lists.newArrayList();
        goodss.add(goods);
        return goodss;
    }

}
