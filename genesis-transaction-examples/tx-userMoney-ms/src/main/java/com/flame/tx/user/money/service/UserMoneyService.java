package com.flame.tx.user.money.service;

import com.flame.mapper.UserMoneyMapper;
import com.flame.model.UserMoney;
import com.flame.tx.user.money.client.UserClient;
import com.lorne.tx.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sungang on 2017/9/16.
 */
@Service
public class UserMoneyService {

    @Autowired
    private UserMoneyMapper userMoneyMapper;

    @Autowired
    private UserClient userClient;

    public List<UserMoney> findAll() {
        return userMoneyMapper.selectAll();
    }

    public UserMoney findByUserId(Long userId){
        UserMoney userMoney = new UserMoney();
        userMoney.setUserId(userId);
        return userMoneyMapper.selectOne(userMoney);
    }

    @TxTransaction
    @Transactional
    public int save() {
        UserMoney userMoney = new UserMoney();
        userMoney.setUserId(1L);
        userMoney.setMoney(100D);
        int rs1 = userMoneyMapper.insert(userMoney);
        return rs1;
    }
}
