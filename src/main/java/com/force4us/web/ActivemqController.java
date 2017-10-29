package com.force4us.web;

import com.force4us.jms.producer.queue.QueueSender;
import com.force4us.jms.producer.topic.TopicSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: 孙海军【haijun.sun@jrj.com.cn】
 * @Date: 2017/10/29
 * @Time: 18:46
 **/
@Controller
@RequestMapping("/activemq")
public class ActivemqController {

    @Resource
    QueueSender queueSender;
    @Resource
    TopicSender topicSender;

    /**
     * 发送消息到队列
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("queueSender")
    public String queueSender(@RequestParam("message")String message){
        String opt="";
        try {
            queueSender.send("test.queue", message);
            opt="suc";
        } catch (Exception e) {
            opt=e.getCause().toString();
        }
        return opt;
    }

    /**
     * 发送消息到主题
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping("topicSender")
    public String topicSender(@RequestParam("message")String message){
        String opt="";
        try {
            topicSender.send("test.topic", message);
            opt="suc";
        } catch (Exception e) {
            opt=e.getCause().toString();
        }
        return opt;
    }

}
