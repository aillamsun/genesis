package com.flame.provider.ribbon.goods.controller;

import com.flame.model.Goods;
import com.flame.provider.ribbon.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sungang on 2017/5/14.
 */

@RestController
@RequestMapping("goods")
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public List<Goods> findAll() {
        return this.goodsService.findAll();
    }


    @GetMapping("{id}")
    public Goods findById(@PathVariable Long id) {
        return this.goodsService.findById(id);
    }
}
