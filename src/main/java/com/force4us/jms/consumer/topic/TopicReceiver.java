package com.force4us.jms.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Author: 孙海军【haijun.sun@jrj.com.cn】
 * @Date: 2017/10/29
 * @Time: 17:24
 **/
@Component
public class TopicReceiver implements MessageListener{


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("TopicReceiver1接收到消息:"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}