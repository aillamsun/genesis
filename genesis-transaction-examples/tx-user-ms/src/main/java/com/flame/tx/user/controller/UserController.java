package com.flame.tx.user.controller;

import com.flame.model.TUser;
import com.flame.tx.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by sungang on 2017/9/16.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<TUser> list() {
        return userService.findAll();
    }


    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public TUser listById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public int save() {
        return userService.save();
    }
}
