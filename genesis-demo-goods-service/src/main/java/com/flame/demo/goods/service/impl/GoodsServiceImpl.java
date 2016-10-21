package com.flame.demo.goods.service.impl;

import com.flame.core.service.impl.BaseServiceImpl;
import com.flame.demo.goods.mapper.GoodsMapper;
import com.flame.demo.goods.model.Goods;
import com.flame.demo.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sungang on 2016/10/21.
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
}
