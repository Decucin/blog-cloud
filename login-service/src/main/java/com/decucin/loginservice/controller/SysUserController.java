package com.decucin.loginservice.controller;

import com.decucin.common.JWTTokenUtils;
import com.decucin.common.vo.Result;
import com.decucin.loginservice.service.SysUserService;
import com.decucin.loginservice.vo.UserVo;
import com.decucin.loginservice.vo.params.PasswordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/20 14:38
 * @description：与用户有关功能
 * @modified By：
 * @version: 1.0$
 */
@RestController
@RequestMapping("user/users")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader(JWTTokenUtils.TOKEN_HEADER) String token){
        return sysUserService.findUserVoByToken(token);
    }

    @GetMapping("showInfo")
    public Result showInfo(@RequestHeader(JWTTokenUtils.TOKEN_HEADER) String token){
        return sysUserService.showInfo(token);
    }

    @PutMapping("updateInfo")
    public Result updateInfo(@RequestHeader(JWTTokenUtils.TOKEN_HEADER) String token, @RequestBody UserVo userVo){
        return sysUserService.updateInfo(token, userVo);
    }

    @PutMapping("changePassword")
    public Result changePassword(@RequestHeader(JWTTokenUtils.TOKEN_HEADER) String token, @RequestBody PasswordParam params){
        return sysUserService.changePassword(token, params);
    }

    @GetMapping("{id}")
    public Result findUserById(@PathVariable("id") Long id){
        return sysUserService.findUserNameById(id);
    }
}
