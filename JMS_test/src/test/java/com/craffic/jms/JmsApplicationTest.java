package com.craffic.jms;

import com.craffic.jms.config.JmsComponent;
import com.craffic.jms.domain.JmsMessage;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsApplicationTest {

    @Autowired
    private JmsComponent jmsComponent;

    @Test
    public void contextLoads(){
        JmsMessage message = new JmsMessage();
        message.setContent("test activeMQ......");
        message.setDate(new Date());
        jmsComponent.send(message);
    }
}