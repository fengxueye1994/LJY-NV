package com.lsljy.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lsljy.model.UserInf;

/**
 * 响应报文Json分装类，所有的请求返回报文都是这个封装类
 * 需要配置好请求头请求体等组件
 */
public class ResponseMsg extends JSONObject {

    /**
     * 因为JsonObject取值赋值方法和Map一样，所以需要转换
     */
    private UserInf userInf;
    public <T> void setUserInf(T t) throws JsonProcessingException {
        this.put("userInf",t);
    }
    public UserInf getUserInf(){
        return (UserInf) this.get("userInf");
    }
}
