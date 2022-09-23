package com.deucin.loginservice;

import com.decucin.common.AESEncryptUtil;
import com.decucin.common.JWTTokenUtils;
import com.decucin.loginservice.UserApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = {UserApplication.class})
@RunWith(SpringRunner.class)
public class Test {

    @org.junit.Test
    public void test() throws Exception {
        System.out.println(AESEncryptUtil.encrypt("Decucin123"));
    }

    @org.junit.Test
    public void test1(){
        System.out.println(JWTTokenUtils.getTokenBody("eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwiZXhwIjoxNjU0Njg3NTYxLCJpYXQiOjE2NTQwODI3NjF9.PeeEGAb7HhVDuvWKPW6FTlsgj5_lIaPfYl3BhljwTQvmDmHnotxch5U73PAzm_iflmqkH5uy_n39nAv--Ltx3w").get("id"));
    }

}
