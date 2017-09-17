package com.flame.tx.user.service;

import com.flame.mapper.UserMapper;
import com.flame.model.TUser;
import com.flame.model.TUserMoney;
import com.flame.tx.user.client.UserMoneyClient;
import com.lorne.tx.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sungang on 2017/9/16.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMoneyClient userMoneyClient;

    public List<TUser> findAll() {
        return userMapper.selectAll();
    }

    public TUser findById(Long id) {
        TUser user = userMapper.selectByPrimaryKey(id);
//        TUserMoney userMoney = userMoneyClient.findByUserId(id);
//        user.setUserMoney(userMoney);
        return user;
    }

    @TxTransaction
    @Transactional
    public int save() {

        TUser user = new TUser();
        user.setUsername("Test Tx");
        user.setPwd("1");
        user.setAge(20);
        int rs1 = userMapper.insert(user);
        /**
         * 保存 余额 分布式服务
         */
        int rs2 = userMoneyClient.save();

        /**
         * 抛出异常
         */
        int v = 100 / 0;
        return rs1 + rs2;
    }
}
