package com.craffic.job.config;

import com.craffic.job.quartz.MySecondJob;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

@Configuration
public class QuartzConfig {

    /**
     * JobDetail的配置有两种方式：
     * 1、 通过MethodInvokingJobDetailFactoryBean类配置Bean，只需要指定Job的实例名和要调用的方法即可
     *     注册这种方式无法在创建JobDetail时传递参数
     */
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail1(){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myQuartzJob");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    /**
     * JobDetail的配置有两种方式：
     * 2、 通过JobDetailFactoryBean来实现的
     *     这种方式只需要指定JobClass即可，然后通过JobDataMap传递参数到Job中，Job中只需要提供属性名，并且提供一个相应的set方法即可接收参数
     */
    @Bean
    JobDetailFactoryBean jobDetail2(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name", "Craffic");
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }

    /**
     * Trigger有多种不同实现：
     * 这里展示常用的两种Tigger：SimpleTigger和CronTrigger
     * 1. 在SimpleTriggerFactoryBean对象中，首先设置JobDetail，然后通过setRepeatCount配置任务的循环次数
     *    setStartDelay配置任务启动延迟时间
     *    setRepeatInterval配置任务的时间间隔
     */
    @Bean
    SimpleTriggerFactoryBean simpleTrigger() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail1().getObject());
        bean.setRepeatCount(3);
        bean.setRepeatInterval(2000);
        return bean;
    }

    /**
     * 在CronTriggerFactoryBean中，主要配置JobDetail和Cron表达式。
     * 最后通过SchedulerFactoryBean创建SchedulerFactory，然后配置Trigger即可
     * @return
     */
    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail2().getObject());
        bean.setCronExpression("* * * * * ?");
        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        SimpleTrigger simpleTrigger = simpleTrigger().getObject();
        CronTrigger cronTrigger = cronTrigger().getObject();
        bean.setTriggers(simpleTrigger, cronTrigger);
        return bean;
    }
}
