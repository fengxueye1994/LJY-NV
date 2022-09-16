package com.lsljy.service;

import com.lsljy.model.UserInf;

public interface UserService {
    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    UserInf selectUserById(String id);
}
