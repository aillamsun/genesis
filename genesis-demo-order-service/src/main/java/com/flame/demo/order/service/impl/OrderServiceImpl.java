package com.flame.demo.order.service.impl;

import com.flame.core.service.impl.BaseServiceImpl;
import com.flame.demo.order.mapper.OrderMapper;
import com.flame.demo.order.service.OrderService;
import com.flame.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sungang on 2016/10/24.
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
}
