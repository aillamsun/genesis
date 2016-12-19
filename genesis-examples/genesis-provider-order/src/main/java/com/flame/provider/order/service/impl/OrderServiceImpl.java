package com.flame.provider.order.service.impl;

import com.flame.core.service.impl.BaseServiceImpl;
import com.flame.model.Order;
import com.flame.provider.order.mapper.OrderMapper;
import com.flame.provider.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sungang on 2016/10/24.
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        super(orderMapper);
        this.orderMapper = orderMapper;
    }
}
