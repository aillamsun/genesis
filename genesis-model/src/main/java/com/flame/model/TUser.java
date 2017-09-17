package com.flame.model;

import com.flame.core.model.BaseModel;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by sungang on 2017/9/16.
 */
@Table(name = "t_user")
@NameStyle(Style.normal)
public class TUser extends BaseModel{

    @Id
    private Long id;

    private String username;

    private String pwd;

    private Integer age;

    @Transient
    private TUserMoney userMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TUserMoney getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(TUserMoney userMoney) {
        this.userMoney = userMoney;
    }
}
