package com.flame.demo.controller;

import com.alibaba.fastjson.JSON;
import com.flame.core.response.BaseResult;
import com.flame.core.web.controller.BaseController;
import com.flame.demo.service.IGoodsService;
import com.flame.model.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */
@Api(value = "用于测试商品的各种接口", description = "用于测试商品的各种接口", tags = "商品接口", position = 0)
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private IGoodsService goodsService;

    /**
     * 商品添加
     *
     * @param goods
     * @return
     */
    @ApiOperation("添加商品")
    @PostMapping
    public BaseResult add(@RequestBody Goods goods) {
        goods.setCreateTime(new Date());
        log.info(JSON.toJSONString(goods));
        BaseResult result = goodsService.add(goods);
        return result;
    }


    @ApiOperation("获取所有商品")
    @GetMapping
    public String getGoods() {
        List<Goods> goodsList = goodsService.getAll();
        log.info(JSON.toJSONString(goodsList));
        return JSON.toJSONString(goodsList);
    }

    @ApiOperation("获取商品明细")
    @GetMapping("/{goods_id}")
    public String getGoods(@ApiParam(value = "商品ID", required = true) @PathVariable("goods_id") Long goods_id) {
        Goods goods = goodsService.getById(goods_id);
        log.info(JSON.toJSONString(goods));
        return JSON.toJSONString(goods);
    }

    @ApiOperation("删除商品")
    @DeleteMapping("/{goods_id}")
    public BaseResult delGoods(@ApiParam(value = "商品ID",  required = true) @PathVariable("goods_id") Long goods_id) {
        BaseResult result = goodsService.deleteById(goods_id);
        return result;
    }

    @ApiOperation("更新商品")
    @PutMapping("/{goods_id}")
    public BaseResult updateGoods(
            @ApiParam(value = "商品ID",  required = true) @PathVariable("goods_id") Long goods_id,
            @ApiParam("商品") @RequestBody Goods goods) {
        BaseResult result = goodsService.updateById(goods_id, goods);
        return result;
    }
}
