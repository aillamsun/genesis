package com.flame.provider.good.controller;

import com.alibaba.fastjson.JSON;
import com.flame.core.response.BaseResult;
import com.flame.core.web.controller.BaseController;
import com.flame.model.Goods;
import com.flame.provider.good.service.GoodsService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sungang on 2016/10/21.
 */

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;


    @GetMapping
    public List<Goods> getGoods() {
        PageHelper.startPage(1,2);
        List<Goods> goodsList = goodsService.selectAll();
        return goodsList;
    }

    @GetMapping("/{goods_id}")
    public Goods getGoodsById(@PathVariable("goods_id") Long goods_id) {
        Goods goods = goodsService.selectByKey(goods_id);
        log.info(JSON.toJSONString(goods));
        return goods;
    }

    /**
     * 商品添加
     *
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

    @PutMapping("/{goods_id}")
    public BaseResult updateGoods(@PathVariable("goods_id") Long goods_id, @RequestBody Goods goods) {
        BaseResult result = new BaseResult();
        try {
            goods.setId(goods_id);
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
