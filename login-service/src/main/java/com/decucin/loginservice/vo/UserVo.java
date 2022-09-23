package com.decucin.loginservice.vo;

import com.decucin.loginservice.dao.pojo.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：decucin
 * @date ：Created in 2021/10/23 15:35
 * @description：用于展示个人中心中的各种信息，是数据库中User表的一部分
 * @modified By：
 * @version: 1.0$
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String username;
    private String avatar;
    private String email;
    private String mobilePhoneNumber;
    private String nickname;
    private Boolean sex;

    /**
    *  @param user
    *  @return
    *  @author decucin
    *  @date 2021/10/25 12:48
    **/
    public UserVo(SysUser user){
        username = user.getAccount();
        nickname = user.getNickname();
        avatar = user.getAvatar();
        email = user.getEmail();
        mobilePhoneNumber = user.getMobilePhoneNumber();
        nickname = user.getNickname();
        sex = user.getSex();
    }
}
