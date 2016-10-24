package com.flame.model;

import com.flame.core.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品实体
 * Created by sungang on 2016/10/21.
 */
@Table(name = "t_goods")
@NameStyle(Style.normal)
public class Goods extends BaseModel {

    @Id
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(value = "商品名称", required = true)
    private String goodsName;
    @ApiModelProperty(value = "商品库存", required = true)
    private int stock;
    @ApiModelProperty(value = "商品单价", required = true)
    private Double price;

    @ApiModelProperty(hidden = true)
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
