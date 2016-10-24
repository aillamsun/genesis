package com.flame.demo.order.controller;

import com.flame.core.response.BaseResult;
import com.flame.core.web.controller.BaseController;
import com.flame.demo.order.service.OrderService;
import com.flame.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sungang on 2016/10/24.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {


    @Autowired
    private OrderService orderService;
    /**
     * 订单添加
     *
     * @param order
     * @return
     */
    @PostMapping
    public BaseResult add(@RequestBody Order order) {
        BaseResult result = new BaseResult();
        try {
            int i = orderService.insert(order);
            if (i == 1) {
                result.setMessage("添加成功");
            } else {
                result.setCode("500");
                result.setMessage("添加失败");
            }
        } catch (Exception ex) {
            result.setCode("500");
            result.setMessage("添加失败");
        }
        return result;
    }


    @GetMapping
    public List<Order> getOrder() {
        List<Order> orderList = orderService.selectAll();
        return orderList;
    }

    @GetMapping("/{order_id}")
    public Order getOrderById(@PathVariable("order_id") Long order_id) {
        Order order = orderService.selectByKey(order_id);
        return order;
    }


    @DeleteMapping("/{order_id}")
    public BaseResult delOrder(@PathVariable("order_id") Long order_id) {
        BaseResult result = new BaseResult();
        try {
            int i = orderService.deleteByPrimaryKey(order_id);
            if (i == 1) {
                result.setMessage("删除成功");
            } else {
                result.setCode("500");
                result.setMessage("删除失败");
            }
        } catch (Exception ex) {
            result.setCode("500");
            result.setMessage("删除失败");
        }
        return result;
    }

    @PutMapping("/{order_id}")
    public BaseResult updateOrder(@PathVariable("order_id") Long order_id, @RequestBody Order order) {
        BaseResult result = new BaseResult();
        try {
            order.setId(order_id);
            int i = orderService.updateByPrimaryKeySelective(order);
            if (i == 1) {
                result.setMessage("修改成功");
            } else {
                result.setCode("500");
                result.setMessage("修改失败");
            }
        } catch (Exception ex) {
            result.setCode("500");
            result.setMessage("修改失败");
        }
        return result;
    }

}
