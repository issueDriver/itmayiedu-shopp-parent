package com.itmayiedu.api.service;

import com.itmayiedu.api.entity.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
@RequestMapping("/member")
public interface UserService {
    /**
     *
     * @methodDesc: 功能描述:(注册服务)
     * @param: @param
     *             UserEntity
     * @param: @return
     */
    @PostMapping("/regist")
    public Map<String, Object> regist(@RequestBody UserEntity UserEntity);
}
