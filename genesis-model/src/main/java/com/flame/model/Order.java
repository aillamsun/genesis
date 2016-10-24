package com.flame.model;

import com.flame.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by sungang on 2016/10/21.
 */
@Table(name = "t_order")
@NameStyle(Style.normal)
@ApiModel
public class Order extends BaseModel {

    @Id
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(value = "商品Id",required = true)
    private Long goodsId;
    @ApiModelProperty(value = "商品名称",required = true)
    private String goodsName;
    @ApiModelProperty(value = "商品单价",required = true)
    private Double price;
    @ApiModelProperty(value = "个数",required = true)
    private Long num;
    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(value = "状态1:代付款,2:待发货,3:已发货",required = true)
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
