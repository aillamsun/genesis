package com.flame.provider.service;

import com.flame.core.response.BaseResult;
import com.flame.model.Goods;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

/**
 * Created by sungang on 2017/5/20.
 */
@Component
public class IGoodsServiceFallback implements IGoodsService {

    @Override
    public BaseResult add(@RequestBody Goods goods) {
        return null;
    }

    @Override
    public List<Goods> getAll() {
        IGoodsService.LOGGER.info("异常发生，进入 findAll 方法");
        Goods goods = new Goods();
        goods.setId(-1l);
        goods.setGoodsName("default good name");
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
