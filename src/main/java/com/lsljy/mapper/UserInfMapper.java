package com.lsljy.mapper;

import com.lsljy.model.UserInf;


public interface UserInfMapper {
    /**
     * 根据userId查询用户
     * @param userId
     * @return
     */
    UserInf selectUserInfById(String userId);
}
