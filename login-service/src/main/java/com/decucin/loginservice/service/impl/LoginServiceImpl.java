package com.decucin.loginservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.decucin.common.JWTTokenUtils;
import com.decucin.common.PasswordUtils;
import com.decucin.common.vo.Result;
import com.decucin.common.vo.ResultEnum;
import com.decucin.loginservice.dao.pojo.SysUser;
import com.decucin.loginservice.service.LoginService;
import com.decucin.loginservice.service.SysUserService;
import com.decucin.loginservice.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/21 23:58
 * @description：这个类用于实现登录功能
 * @modified By：
 * @version: 1.0$
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @param loginParam
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/23 15:01
    **/
    @Override
    public Result login(LoginParam loginParam){
        /**
         *  TODO 登录功能
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        SysUser user = userService.findUserByUsername(loginParam.getUsername());
        if(user == null){
            return Result.fail(ResultEnum.USER_NOT_EXIST);
        }
        // 此时已经确保能找到对应的user

        if(PasswordUtils.formCompareDB(loginParam.getPassword(), user.getPassword())){
            // 创建token
            String token = JWTTokenUtils.createToken(user.getId(), true);
            // 将token放入redis中
            ValueOperations ops = redisTemplate.opsForValue();
            // redis中token的有效值为1day
            ops.set(JWTTokenUtils.TOKEN_PREFIX + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
            return Result.success(token);
        }else {
            return Result.fail(ResultEnum.ERROR_PASSWORD);
        }
    }

    /**
     * @param token
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/23 15:01
    **/
    @Override
    public Result logout(String token) {
        Long userId = JWTTokenUtils.getIdFromToken(token);
        if(userId == null){
            return Result.fail(ResultEnum.ERROR_TOKEN);
        }
        // 登出实际上只需要将token从redis删除即可
        redisTemplate.delete(JWTTokenUtils.TOKEN_PREFIX+token);
        return Result.success(null);
    }

    /**
     * @param loginParam
    *  @return com.decucin.blog.vo.Result
    *  @author decucin
    *  @date 2021/10/23 15:01
    **/
    @Override
    public Result register(LoginParam loginParam) {
        /**
         *  TODO 注册功能
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        // 用户名密码正确性校验
        if(StringUtils.isBlank(loginParam.getUsername()) || StringUtils.isBlank(loginParam.getPassword())){
            return Result.fail(ResultEnum.NOT_NULL);
        }
        // 判断该用户是否存在
        if(userService.findUserByUsername(loginParam.getUsername()) != null){
            return Result.fail(ResultEnum.USER_EXIST);
        }
        // 此时已经能确保用户未存在，并且注册用户输入了不为空的用户名和密码
        return Result.success(userService.addUser(loginParam));
    }

//    @Override
//    public SysUser checkToken(String token){
//        if(StringUtils.isBlank(token)){
//            return null;
//        }
//        Long userId = JWTTokenUtils.getIdFromToken(token);
//        if(userId == null){
//            return null;
//        }
//        return userService.findUserById(userId);
//    }
}
