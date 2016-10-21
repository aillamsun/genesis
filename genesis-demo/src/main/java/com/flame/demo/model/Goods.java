package com.flame.demo.model;

import com.flame.core.model.BaseModel;

import java.util.Date;

/**
 * 商品实体
 * Created by sungang on 2016/10/21.
 */
public class Goods extends BaseModel {

    private Long id;

    private String goodsName;

    private int stock;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
