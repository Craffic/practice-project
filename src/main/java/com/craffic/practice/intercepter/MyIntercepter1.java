package com.craffic.practice.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyIntercepter1 implements HandlerInterceptor {


    /**
     * 拦截前操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyIntercepter1.preHandle......");
        return true;
    }

    /**
     * 拦截中操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyIntercepter1.postHandle......");
    }

    /**
     * 拦截后操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyIntercepter1.afterCompletion......");
    }
}
