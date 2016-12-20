package com.flame.provider.service;

import com.flame.core.response.BaseResult;
import com.flame.model.Goods;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */
@FeignClient(name = "genesis-provider-goods")
public interface IGoodsService {


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
}
