package com.flame.provider.zuul.controller;

import com.flame.core.result.ResultBody;
import com.flame.core.result.ResultGenerator;
import com.flame.mapper.AuthorityMapper;
import com.flame.model.Authority;
import com.flame.provider.zuul.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sungang on 2017/9/17.
 */
@RestController
@RequestMapping("auth")
public class JWTController {


    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthorityMapper authorityMapper;



    @PostMapping
    public ResultBody createAuthenticationToken(String app_kay,String secrity_key, Device device) {
        Authority authority = new Authority();
        authority.setAppKey(app_kay);
        authority.setSecrityKey(secrity_key);
        Authority authority1 = authorityMapper.selectOne(authority);
        final String token = jwtTokenUtil.generateToken(authority1);
        authority1.setToken(token);
        authorityMapper.updateByPrimaryKeySelective(authority1);
        // Return the token
        return ResultGenerator.genSuccessResult(token);
    }




    @GetMapping("error")
    public ResultBody error() {


        return ResultGenerator.genSuccessResult();
    }


}
