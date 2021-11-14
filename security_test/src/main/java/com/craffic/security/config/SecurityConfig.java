package com.craffic.security.config;

import com.craffic.security.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 基于内存认证
//        auth.inMemoryAuthentication()
//                .withUser("root").password("123").roles("ADMIN", "DBA")
//                .and()
//                .withUser("admin").password("123").roles("ADMIN", "USER")
//                .and()
//                .withUser("Craffic").password("123").roles("USER");
        auth.userDetailsService(userService);
    }

//    /**
//     * 自定义管理接口和角色的权限关系
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().
//                // 定义admin接口必须有ADMIN角色才能访问
//                antMatchers("/admin/**").
//                hasRole("ADMIN").
//                // user接口必须是ADMIN或者USER才能访问
//                antMatchers("/user/**").
//                access("hasAnyRole('ADMIN', 'USER')").
//                // db接口必须是ADMIN或者DBA才能访问
//                antMatchers("/db/**").
//                access("hasRole('ADMIN') and hasRole('DBA')").
//                // 除了上面定义的接口，其他接口都需要认证后才能访问
//                anyRequest().
//                authenticated().
//                // 定义登录接口为/login，即可以调用login接口发起一个post请求登录，登录参数必须为username和password
//                and().
//                formLogin().
//                // 自定义的登录页面，提供用户未授权而登录一个需要授权接口时，就会跳转到该登录页面
//                loginPage("/login_page").
//                loginProcessingUrl("/login").
//                usernameParameter("userName").
//                passwordParameter("passwd").
//                // 登录成功处理事件
//                successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
//                        // Authentication参数是用来获取用户登录信息的
//                        Object principal = auth.getPrincipal();
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = response.getWriter();
//                        response.setStatus(200);
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("status", 200);
//                        map.put("msg", principal);
//                        ObjectMapper objMapper = new ObjectMapper();
//                        out.write(objMapper.writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                }).
//                failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
//                        response.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = response.getWriter();
//                        response.setStatus(401);
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("status", "401");
//                        if (e instanceof LockedException){
//                            map.put("msg", "账户被锁定，登录失败！");
//                        } else if(e instanceof BadCredentialsException) {
//                            map.put("mag", "，用户名或密码错误，登录失败！");
//                        } else if (e instanceof DisabledException) {
//                            map.put("msg", "，账户被禁用，登录失败！");
//                        } else if (e instanceof AccountExpiredException) {
//                            map.put("msg", "，账户已过期，登录失败！");
//                        } else if (e instanceof CredentialsExpiredException) {
//                            map.put("msg", "，密码已过期，登录失败！");
//                        } else {
//                            map.put("msg", "登录失败！");
//                        }
//                        ObjectMapper objMapper = new ObjectMapper();
//                        out.write(objMapper.writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                }).
//                // 表示和登录相关的接口都不需要验证
//                permitAll()
//                // 开启注销登录的配置
//                .and().logout()
//                // 配置注销登录请求url为“/logout”，默认也是/logout，清除认证信息，使session失效
//                .logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true)
//                // 可以在logoutHandler中完成一些数据清理工作，比如说cookie的清除
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
//
//                    }
//                })
//                // 处理注销成功后的业务逻辑，如返回一段json或者跳转回登录页面
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
//                        response.sendRedirect("/login_page");
//                    }
//                })
//                .and()
//                // 关闭csrf
//                .csrf()
//                .disable();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/admin/**").hasRole("admin").
                antMatchers("/user/**").hasRole("user").
                antMatchers("/db/**").hasRole("dba").
                anyRequest().
                authenticated().
                and().
                formLogin().loginProcessingUrl("/login").permitAll().
                successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
                        // Authentication参数是用来获取用户登录信息的
                        Object principal = auth.getPrincipal();
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(200);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", principal);
                        ObjectMapper objMapper = new ObjectMapper();
                        out.write(objMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                }).
                failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(401);
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", "401");
                        if (e instanceof LockedException){
                            map.put("msg", "账户被锁定，登录失败！");
                        } else if(e instanceof BadCredentialsException) {
                            map.put("mag", "，用户名或密码错误，登录失败！");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "，账户被禁用，登录失败！");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "，账户已过期，登录失败！");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "，密码已过期，登录失败！");
                        } else {
                            map.put("msg", "登录失败！");
                        }
                        ObjectMapper objMapper = new ObjectMapper();
                        out.write(objMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                }).
                // 表示和登录相关的接口都不需要验证
                permitAll()
                // 开启注销登录的配置
                .and().logout()
                // 配置注销登录请求url为“/logout”，默认也是/logout，清除认证信息，使session失效
                .logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true)
                // 可以在logoutHandler中完成一些数据清理工作，比如说cookie的清除
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {

                    }
                })
                // 处理注销成功后的业务逻辑，如返回一段json或者跳转回登录页面
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
                        response.sendRedirect("/login_page");
                    }
                })
                .and().
                csrf().disable();
    }
}
