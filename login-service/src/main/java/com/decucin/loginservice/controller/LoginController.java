package com.decucin.loginservice.controller;


import com.decucin.common.JWTTokenUtils;
import com.decucin.common.vo.Result;
import com.decucin.loginservice.service.LoginService;
import com.decucin.loginservice.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 23:53
 * @description：登录功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping ("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Result login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader(JWTTokenUtils.TOKEN_HEADER) String token){
        return loginService.logout(token);
    }

    @PostMapping("register")
    public Result register(@RequestBody LoginParam loginParam){
        return loginService.register(loginParam);
    }

}
