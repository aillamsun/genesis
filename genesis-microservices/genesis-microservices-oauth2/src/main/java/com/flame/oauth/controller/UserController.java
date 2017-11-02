package com.flame.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by sungang on 2017/9/25.
 */
@RestController
@RequestMapping("/")
public class UserController {


    @GetMapping(value = "/current")
    public Principal getUser(Principal principal) {
        return principal;
    }
}
