package com.craffic.practice.config;

import com.craffic.practice.intercepter.MyIntercepter1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 对除了/hello接口不拦截外，其他接口都拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercepter1())
                .addPathPatterns("/**")
                .excludePathPatterns("/hello");
    }
}
