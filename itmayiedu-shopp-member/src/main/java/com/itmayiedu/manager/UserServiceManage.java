package com.itmayiedu.manager;

import com.itmayiedu.api.entity.UserEntity;

public interface UserServiceManage {
    /**
     *
     * @methodDesc: 功能描述:(注册服务)
     * @param: @param
     *             UserEntity
     */
    public void regist(UserEntity userEntity);

    public String md5PassSalt(String phone,String password);
}
