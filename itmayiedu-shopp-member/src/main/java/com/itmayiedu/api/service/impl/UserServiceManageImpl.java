package com.itmayiedu.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.constants.DBTableName;
import com.itmayiedu.constants.MQInterfaceType;
import com.itmayiedu.dao.UserDao;
import com.itmayiedu.manager.UserServiceManage;
import com.itmayiedu.mq.ruducer.RegisterMailboxProducer;
import com.itmayiedu.utils.DateUtils;
import com.itmayiedu.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
@Service
@Slf4j
public class UserServiceManageImpl implements UserServiceManage {
    @Resource
    private UserDao userDao;
    @Autowired
    private RegisterMailboxProducer registerMailboxProducer;
    @Value("${messages.queue}")
    private String MESSAGES_QUEUE;

    @Override
    public void regist(UserEntity userEntity) {
        userEntity.setCreated(DateUtils.getTimestamp());
        userEntity.setUpdated(DateUtils.getTimestamp());
        String phone = userEntity.getPhone();
        String password = userEntity.getPassword();
        userEntity.setPassword(md5PassSalt(phone, password));
        userDao.save(userEntity, DBTableName.TABLE_MB_USER);
        // 队列
        Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
        // 组装报文格式
        String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
        log.info("###regist() 注册发送邮件报文mailMessage:{}", mailMessage);

        registerMailboxProducer.send(activeMQQueue, mailMessage);

    }

    @Override
    public String md5PassSalt(String phone, String password) {
        String newPass = MD5Util.MD5(phone + password);
        return newPass;

    }

    private String mailMessage(String email, String userName) {
        JSONObject root = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", MQInterfaceType.SMS_MAIL);
        JSONObject content = new JSONObject();
        content.put("mail", email);
        content.put("userName", userName);
        root.put("header", header);
        root.put("content", content);
        return root.toJSONString();
    }


}
