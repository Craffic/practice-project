package com.craffic.job.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyQuartzJob {

    /**
     * JobBean可以是一个普通的bean，用@Component注解将之注册到Spring容器中
     */
    public void sayHello(){
        System.out.println("My first quartz job: " + new Date());
    }
}
