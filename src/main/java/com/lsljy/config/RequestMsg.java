package com.lsljy.config;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.api.message.Header;
import lombok.Data;

/**
 * 统一的封装格式
 */
@Data
public class RequestMsg extends JSONObject {

    private Header header;

}
