package com.decucin.articleservice.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    private static final String FROM_HEADER = "Gateway";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String fromHeader = request.getHeader("From");
        if(FROM_HEADER.equals(fromHeader)){
            return true;
        }
        return false;
    }
}
