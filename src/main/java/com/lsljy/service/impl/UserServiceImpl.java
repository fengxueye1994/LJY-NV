package com.lsljy.service.impl;

import com.lsljy.mapper.UserInfMapper;
import com.lsljy.model.UserInf;
import com.lsljy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("user")
public class UserServiceImpl implements UserService {
    @Autowired (required = false)
    private UserInfMapper userMapper;

    @Override
    public UserInf selectUserById(String id) {
        return userMapper.selectUserInfById(id);
    }
}
