package com.craffic.websocket.controller;

import com.craffic.websocket.domain.Chat;
import com.craffic.websocket.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class GreetingController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 客户端访问服务端的时候config中配置的服务端接收前缀也要加上 例：/app/hello
    @MessageMapping("/hello")
    //config中配置的订阅前缀记得要加上
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception{
        //也可以使用sendTo发送
        return message;
        // todo 用定时器定时发送消息到客户端
        //我们使用这个方法进行消息的转发发送！
        //this.simpMessagingTemplate.convertAndSend("/topic/notice", value);(可以使用定时器定时发送消息到客户端)
        //        @Scheduled(fixedDelay = 1000L)
        //        public void time() {
        //            messagingTemplate.convertAndSend("/system/time", new Date().toString());
        //        }
    }


    /**
     * 用SimpMessagingTemplate发送到broker注释掉
     */
    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat) throws Exception{
        String fromName = principal.getName();
        chat.setFrom(fromName);
        messagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }
}
