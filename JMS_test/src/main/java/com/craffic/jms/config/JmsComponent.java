package com.craffic.jms.config;

import com.craffic.jms.domain.JmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class JmsComponent {

    @Autowired
    private JmsMessagingTemplate messagingTemplate;
    @Autowired
    private Queue queue;

    public void send(JmsMessage message){
        messagingTemplate.convertAndSend(this.queue, message);
    }

    @JmsListener(destination = "amq")
    public void receive(JmsMessage message){
        System.out.println("receive: " + message);
    }
}
