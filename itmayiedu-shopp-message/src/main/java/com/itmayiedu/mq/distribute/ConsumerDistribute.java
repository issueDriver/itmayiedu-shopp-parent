package com.itmayiedu.mq.distribute;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.adapter.MessageAdapter;
import com.itmayiedu.constants.MQInterfaceType;
import com.itmayiedu.service.SMSMailboxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 功能描述：(消费消息 mq 从队列中获取最新消息)
 */
@Slf4j
@Component
public class ConsumerDistribute  {
    @Autowired
    private SMSMailboxService smsMailboxService;

    @JmsListener(destination = "mail_queue")
    public void distribute(String json) {
        log.info("###消息服务###收到消息,消息内容 json:{}", json);
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject jsonObject = new JSONObject().parseObject(json);
        JSONObject header = jsonObject.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");
        MessageAdapter messageAdapter = null;
        switch (interfaceType) {
            // 发送邮件
            case MQInterfaceType.SMS_MAIL:
                messageAdapter=smsMailboxService;
                break;

            default:
                break;
        }
        JSONObject content=jsonObject.getJSONObject("content");
        messageAdapter.distribute(content);

    }
}
