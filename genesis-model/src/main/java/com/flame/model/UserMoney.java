package com.flame.model;

import com.flame.core.model.BaseModel;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sungang on 2017/9/16.
 */

@Table(name = "user_money")
@NameStyle(Style.normal)
public class UserMoney extends BaseModel{

    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Double money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
