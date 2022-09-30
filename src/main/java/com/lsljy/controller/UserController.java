package com.lsljy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsljy.config.ResponseMsg;
import com.lsljy.model.UserInf;
import com.lsljy.service.UserService;
import com.lsljy.tools.Json2JavaUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

/**
 * @author sunql
 * @description controller层
 * @date 2020/12/29 15:30
 */
@Slf4j//这个是logback注解，加上之后可以直接使用log来打印日志
@Controller
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    //1.指定存储类型(redis)
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
//    //2.未指定存储类型
//    @Autowired
//    private RedisTemplate redisTemplate1;
//    @Autowired
//    private ConfigKo configKo;
//(value = "/user")
//    @RequestMapping("${user.register.path}")
//    public UserInf test1(){
//        UserInf user = userService.selectUserById("123456789");
//
//        return user;
//    }
    @RequestMapping(value = "/list")
    public UserInf test(){
        UserInf user = userService.selectUserById("123456789");
        return null;
    }

//添加注释    
//    @RequestMapping(value = "/finduser")
//    public UserInf test1(){
//        UserInf user = userService.selectUserById("123456789");
//
//        return user;
//    }

    //这里的method要设置为RequestMethod.POST，同时指定utf-8的json格式，但是引入了依赖，可以不必写了
    @RequestMapping(value = "/finduser", method = RequestMethod.POST)
    public JSONObject login(@RequestBody JSONObject jsonParam) throws JsonProcessingException {

        Json2JavaUtils.json2Object(jsonParam, UserInf.class);
        UserInf user1 = Json2JavaUtils.json2Object(jsonParam, UserInf.class);//json对象转换为java对象
        System.out.println(jsonParam);
        UserInf user = userService.selectUserById(user1.getUserId());
        ObjectMapper aa = new ObjectMapper();
        String bb = aa.writeValueAsString(user);

        return JSON.parseObject(bb);
    }
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject registerUser(@RequestBody JSONObject jsonParam) {
        ResponseMsg res = new ResponseMsg();
        try {
            UserInf user1 = Json2JavaUtils.json2Object(jsonParam, UserInf.class);//json对象转换为java对象
            UserInf user = userService.selectUserById(user1.getUserId());
            res.setUserInf(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String aa = (String) redisTemplate.opsForValue().get("ljy");
        redisTemplate.opsForValue().set("ljy", "蓝洁瑛");
        System.out.println("数据："+aa);
        aa = (String) redisTemplate.opsForValue().get("ljy");
        System.out.println("数据："+aa);


//        ObjectMapper aa = new ObjectMapper();
//        String bb = aa.writeValueAsString(user);
//        UserInf aaa = res.getUserInf();
//        System.out.println("响应报文："+res);

//        String pretty = JSON.toJSONString(res, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteDateUseDateFormat);
//        logger.info("响应报文："+pretty);
        return res;
    }



}
