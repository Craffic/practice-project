package com.craffic.practice.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloAction {

    @GetMapping("/hello")
    public String hello(){
        return "Hello spring boot!";
    }

}
