package com.decucin.loginservice.service;


import com.decucin.common.vo.Result;
import com.decucin.loginservice.dao.pojo.SysUser;
import com.decucin.loginservice.vo.params.LoginParam;

public interface LoginService {

    Result login(LoginParam loginParam);

    Result logout(String token);

    Result register(LoginParam loginParam);

//    SysUser checkToken(String token);

}
