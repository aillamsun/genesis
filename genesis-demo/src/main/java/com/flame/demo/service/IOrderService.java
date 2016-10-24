package com.flame.demo.service;

import com.flame.core.response.BaseResult;
import com.flame.model.Goods;
import com.flame.model.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sungang on 2016/10/24.
 */
@FeignClient(name = "genesis-demo-order-service"/*,fallback = IGoodsServiceFallBack.class*/)
public interface IOrderService {

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    BaseResult add(@RequestBody Order order);

    /**
     * 查找所有订单
     *
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    List<Order> getAll();


    /**
     * 查找订单详情
     *
     * @param order_id 订单详情
     * @return
     */
    @RequestMapping(value = "/order/{order_id}", method = RequestMethod.GET)
    Order getById(@PathVariable("order_id") Long order_id);

    /**
     * 删除商品
     *
     * @param order_id 商品ID
     * @return
     */
    @RequestMapping(value = "/order/{order_id}", method = RequestMethod.DELETE)
    BaseResult deleteById(@PathVariable("order_id") Long order_id);


    /**
     * 更新商品
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "/order/{order_id}", method = RequestMethod.PUT)
    BaseResult updateById(@PathVariable("order_id") Long order_id, @RequestBody Order order);
}
