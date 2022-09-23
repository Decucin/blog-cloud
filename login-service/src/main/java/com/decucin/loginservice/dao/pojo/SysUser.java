package com.decucin.loginservice.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/19 11:02
 * @description：对应数据库表decucin_sys_user
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String account;
    private Boolean admin;
    private String avatar;
    private Date createDate;
    private String email;
    private String mobilePhoneNumber;
    private String nickname;
    private String password;
    private String salt;
    private Boolean sex;
}
