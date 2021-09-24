package com.craffic.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }

    /*
     * 1. @SpringBootApplication注解是整个springboot程序的入口，加在启动类上的
     * 2. 该注解主要包含三个主要的注解：@SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan
     *    2.1 @SpringBootConfiguration注解，实质上也是一个@Configuration注解，表面光这是一个配置类，可以在该类上配置Bean。这个类的角色相当于applicationContent.xml
     *    2.2 @EnableAutoConfiguration注解，表明这个类开启自动化配置。开发者在任意时刻可以使用自己定义的配置代替自动化配置中的某一个配置
     *    2.3 @ComponentScan注解，默认扫描的类都位于当前类所在包下面，因此在实际项目中吧启动类放在根包中。
     *        @ComponentScan注解，会扫描@Service、@Repository、@Component、@Configuration这些注解
     */
}
