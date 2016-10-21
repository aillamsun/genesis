package com.flame.demo.goods.controller;

import com.alibaba.fastjson.JSON;
import com.flame.core.resource.BaseResult;
import com.flame.core.web.controller.BaseController;
import com.flame.demo.goods.model.Goods;
import com.flame.demo.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 商品添加
     * @param goods
     * @return
     */
    @PostMapping
    public BaseResult add(@RequestBody Goods goods) {
        BaseResult result = new BaseResult();
        try {
            int i = goodsService.insert(goods);
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
    public String getGoods() {
        List<Goods> goodsList = goodsService.selectAll();
        return JSON.toJSONString(goodsList);
    }

    @GetMapping("/{goods_id}")
    public String getGoods(@PathVariable("goods_id") Long goods_id) {
        Goods goods = goodsService.selectByKey(goods_id);
        return JSON.toJSONString(goods);
    }

    @DeleteMapping("/{goods_id}")
    public BaseResult delGoods(@PathVariable("goods_id") Long goods_id) {
        BaseResult result = new BaseResult();
        try {
            int i = goodsService.deleteByPrimaryKey(goods_id);
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

    @PutMapping
    public BaseResult updateGoods(@RequestBody Goods goods) {
        BaseResult result = new BaseResult();
        try {
            int i = goodsService.updateByPrimaryKeySelective(goods);
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
