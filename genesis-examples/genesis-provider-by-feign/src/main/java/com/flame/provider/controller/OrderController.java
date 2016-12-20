package com.flame.provider.controller;

import com.alibaba.fastjson.JSON;
import com.flame.core.response.BaseResult;
import com.flame.core.web.controller.BaseController;
import com.flame.model.Order;
import com.flame.provider.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */
@Api(value = "用于测试订单的各种接口", description = "用于测试订单的各种接口", tags = "订单接口", position = 1)
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /**
     * 商品订单
     *
     * @param order
     * @return
     */
    @ApiOperation("商品订单")
    @PostMapping
    public BaseResult add(@RequestBody Order order) {
        order.setCreateTime(new Date());
        BaseResult result = orderService.add(order);
        return result;
    }


    @ApiOperation("获取所有订单")
    @GetMapping
    public String getOrder() {
        List<Order> orderList = orderService.getAll();
        return JSON.toJSONString(orderList);
    }

    @ApiOperation("获取订单明细")
    @GetMapping("/{order_id}")
    public String getOrderById(@ApiParam("订单编号") @PathVariable("order_id") Long order_id) {
        Order order = orderService.getById(order_id);
        return JSON.toJSONString(order);
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/{order_id}")
    public BaseResult delOrder(@ApiParam("订单编号") @PathVariable("order_id") Long order_id) {
        BaseResult result = orderService.deleteById(order_id);
        return result;
    }

    @ApiOperation("更新订单")
    @PutMapping("/{order_id}")
    public BaseResult updateOrder(
            @ApiParam("订单编号") @PathVariable("goods_id") Long order_id,
            @ApiParam("订单") @RequestBody Order order) {
        BaseResult result = orderService.updateById(order_id, order);
        return result;
    }
}
