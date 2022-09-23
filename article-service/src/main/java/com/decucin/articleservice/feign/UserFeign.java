package com.decucin.articleservice.feign;


import com.decucin.common.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeign {

    @GetMapping("user/users/{id}")
    Result findAuthorNameById(@PathVariable("id") Long id);

}
