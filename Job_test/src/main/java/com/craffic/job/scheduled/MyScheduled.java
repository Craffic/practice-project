package com.craffic.job.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyScheduled {

    /**
     * fixedDelay: 表示当前任务执行结束后1秒开启另外一个任务
     */
    @Scheduled(fixedDelay = 1000)
    public void fixedDelay(){
        System.out.println("fixedDelay:" + new Date());
    }

    /**
     * fixedRate = 2000：表示在当前任务开始执行2秒后开启另外一个定时任务
     */
    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        System.out.println("fixedRate: " + new Date());
    }

    /**
     * initialDelay = 1000：表示首次执行的延迟时间
     */
    @Scheduled(initialDelay = 1000, fixedRate = 2000)
    public void initialDelay(){
        System.out.println("initialDelay: " + new Date());
    }

    /**
     * cron表达式：表示每分钟执行一次
     */
    @Scheduled(cron = "0 * * * * ?")
    public void cron(){
        System.out.println("cron: " + new Date());
    }

}
