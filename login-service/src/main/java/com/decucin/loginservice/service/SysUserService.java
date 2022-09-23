package com.decucin.loginservice.service;


import com.decucin.common.vo.Result;
import com.decucin.loginservice.dao.pojo.SysUser;
import com.decucin.loginservice.vo.UserVo;
import com.decucin.loginservice.vo.params.LoginParam;
import com.decucin.loginservice.vo.params.PasswordParam;

public interface SysUserService {

    Result findUserNameById(Long id);

    SysUser findUserByUsername(String userName);

    Result findUserVoByToken(String token);

    Integer addUser(LoginParam loginParam);


    Result showInfo(String token);


    Result updateInfo(String token, UserVo userVo);

    Result changePassword(String token, PasswordParam params);
}
