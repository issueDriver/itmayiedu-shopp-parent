package com.itmayiedu.api.service.impl;

import com.itmayiedu.api.entity.UserEntity;
import com.itmayiedu.api.service.UserService;
import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.manager.UserServiceManage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@Slf4j
@RestController
public class UserServiceImpl extends BaseApiService implements UserService {
    @Autowired
    private UserServiceManage userServiceManage;

    @Override
    public Map<String, Object> regist(@RequestBody UserEntity userEntity) {
        if(StringUtils.isEmpty(userEntity.getUserName())){
            return setResutParameterError("用户名称不能为空!");
        }
        if(StringUtils.isEmpty(userEntity.getPassword())){
            return setResutParameterError("密码不能为空!");
        }
        try {
            userServiceManage.regist(userEntity);
            return setResutSuccess();
        } catch (Exception e) {
            log.error("###regist() ERRPR:",e);
            return setResutError("注册失败!");
        }


    }
}
