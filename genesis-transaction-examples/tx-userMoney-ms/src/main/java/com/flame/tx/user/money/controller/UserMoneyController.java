package com.flame.tx.user.money.controller;

import com.flame.model.TUser;
import com.flame.model.TUserMoney;
import com.flame.tx.user.money.service.UserMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sungang on 2017/9/16.
 */
@RestController
@RequestMapping("user-money")
public class UserMoneyController {

    @Autowired
    private UserMoneyService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<TUserMoney> list() {
        return userService.findAll();
    }


    @RequestMapping(value = "/list/{userId}", method = RequestMethod.GET)
    public TUserMoney findByUserId(@PathVariable("userId") Long userId) {
        return userService.findByUserId(userId);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int save() {
        return userService.save();
    }
}
