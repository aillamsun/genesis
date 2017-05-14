package com.flame.provider.service;

import com.flame.core.response.BaseResult;
import com.flame.model.Goods;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */
@FeignClient(name = "genesis-provider-goods", fallback = IGoodsService.HystrixClientFallback.class)
public interface IGoodsService {

    Logger LOGGER = LoggerFactory.getLogger(IGoodsService.class);

    /**
     * 添加商品
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    BaseResult add(@RequestBody Goods goods);

    /**
     * 查找所有商品
     *
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    List<Goods> getAll();


    /**
     * 查找商品
     *
     * @param goods_id 商品ID
     * @return
     */
    @RequestMapping(value = "/goods/{goods_id}", method = RequestMethod.GET)
    Goods getById(@PathVariable("goods_id") Long goods_id);

    /**
     * 删除商品
     *
     * @param goods_id 商品ID
     * @return
     */
    @RequestMapping(value = "/goods/{goods_id}", method = RequestMethod.DELETE)
    BaseResult deleteById(@PathVariable("goods_id") Long goods_id);


    /**
     * 更新商品
     *
     * @param goods
     * @return
     */
    @RequestMapping(value = "/goods/{goods_id}", method = RequestMethod.PUT)
    BaseResult updateById(@PathVariable("goods_id") Long goods_id, @RequestBody Goods goods);


    @ComponentScan
    static class HystrixClientFallback implements IGoodsService {
        @Override
        public BaseResult add(@RequestBody Goods goods) {
            return null;
        }

        @Override
        public List<Goods> getAll() {
            IGoodsService.LOGGER.info("异常发生，进入 findAll 方法");
            Goods goods = new Goods();
            goods.setId(-1l);
            goods.setGoodsName("defaul good name");
            goods.setCreateTime(new Date());
            goods.setStock(-1);
            List<Goods> goodss = Lists.newArrayList();
            goodss.add(goods);
            return goodss;
        }

        @Override
        public Goods getById(@PathVariable("goods_id") Long goods_id) {
            return null;
        }

        @Override
        public BaseResult deleteById(@PathVariable("goods_id") Long goods_id) {
            return null;
        }

        @Override
        public BaseResult updateById(@PathVariable("goods_id") Long goods_id, @RequestBody Goods goods) {
            return null;
        }
    }
}
