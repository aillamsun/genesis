package com.flame.model;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by sungang on 2017/7/21.
 */
@Table(name = "USER")
@NameStyle(Style.normal)
public class User {

    private String id;

    private String loginName;

    private String password;

    private String nickName;

    private String email;

    private String phone;

    private Byte grade;

    private Boolean adminFlag;

    private List<UserAuthority> authorities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public Boolean getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(Boolean adminFlag) {
        this.adminFlag = adminFlag;
    }

    public List<UserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserAuthority> authorities) {
        this.authorities = authorities;
    }
}
