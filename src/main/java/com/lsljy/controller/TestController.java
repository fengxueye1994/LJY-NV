package com.lsljy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsljy.model.UserInf;
import com.lsljy.service.UserService;
import com.lsljy.tools.Json2JavaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunql
 * @description controller层
 * @date 2020/12/29 15:30
 */
@Controller
@RestController
@RequestMapping(value = "/user")
public class TestController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/list")
    public UserInf test(){
        UserInf user = userService.selectUserById("123456789");

        return user;
    }
//    @RequestMapping(value = "/finduser")
//    public UserInf test1(){
//        UserInf user = userService.selectUserById("123456789");
//
//        return user;
//    }

    @RequestMapping(value = "/finduser", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") //这里的method要设置为RequestMethod.POST
    public JSONObject creatClusterAndNodes(@RequestBody JSONObject jsonParam) throws JsonProcessingException {

        Json2JavaUtils.json2Object(jsonParam,UserInf.class);
        UserInf user1 = Json2JavaUtils.json2Object(jsonParam,UserInf.class);//json对象转换为java对象
        System.out.println(jsonParam);
        UserInf user = userService.selectUserById(user1.getUserId());
        ObjectMapper aa =new ObjectMapper();
        String bb = aa.writeValueAsString(user);
        return JSON.parseObject(bb);
    }



}