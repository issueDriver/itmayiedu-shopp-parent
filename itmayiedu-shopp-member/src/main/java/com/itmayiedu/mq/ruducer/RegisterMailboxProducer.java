package com.itmayiedu.mq.ruducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service("registerMailboxProducer")
public class RegisterMailboxProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 功能描述:(消息服务发送者)
     * @param destination
     * @param json
     */
    public void send(Destination destination,String json){
        jmsMessagingTemplate.convertAndSend(destination, json);
    }
}
