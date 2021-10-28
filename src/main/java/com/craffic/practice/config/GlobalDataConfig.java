package com.craffic.practice.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * @ControllerAdvice 是一个全局数据处理组件，所以也可以在@ControllerAdvice中配置全局数据
 *                   使用@ModelAttribute注解进行配置
 */
@ControllerAdvice
public class GlobalDataConfig {

    /**
     * 在全局配置中加userInfo方法，返回一个map
     * 该方法用ModelAttribute注解标记着，其中key就是注解的user_info，value就是map
     * 此时在任意请求的controller中的model中，通过方法中的model都可以获取到userInfo的值
     * @return
     */
    @ModelAttribute("user_info")
    public Map<String, String> userInfo(){
        Map<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("name", "罗贯中");
        userInfoMap.put("gender", "男");
        return userInfoMap;
    }

}
