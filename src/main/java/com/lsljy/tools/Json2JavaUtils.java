package com.lsljy.tools;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 通用工具，请求报文JSON转换为对应的对象
 */
public class Json2JavaUtils {

    @Autowired
    private ObjectMapper ObjectMapper;

    public static <T> T json2Object(JSONObject json, Class<T> model) {
        try{
            T obj = model.newInstance();
            // 首先得到pojo所定义的字段
            Field[] fields = model.getDeclaredFields();
            // 根据传入的Class动态生成pojo对象
            for (Field field : fields) {
                // 设置字段可访问(必须，否则报错)
                field.setAccessible(true);
                // 得到字段的属性名
                String name = field.getName();
                // 这一段的作用是如果字段在JSONObject中不存在会抛出异常，如果出异常，则跳过。
                try {
                    json.get(name);
                } catch (Exception ex) {
                    continue;
                }
                if (json.get(name) != null && !"".equals(json.getString(name))) {
                    // 根据字段的类型将值转化为相应的类型，并设置到生成的对象中。
                    if (field.getType().equals(Long.class) || field.getType().equals(long.class)) {
                        field.set(obj, Long.parseLong(json.getString(name)));
                    } else if (field.getType().equals(String.class)) {
                        field.set(obj, json.getString(name));
                    } else if (field.getType().equals(Double.class) || field.getType().equals(double.class)) {
                        field.set(obj, Double.parseDouble(json.getString(name)));
                    } else if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        field.set(obj, Integer.parseInt(json.getString(name)));
                    } else if (field.getType().equals(java.util.Date.class)) {
                        field.set(obj, Date.parse(json.getString(name)));
                    } else {
                        continue;
                    }
                }
            }
            return obj;
        }catch(Exception e){
            throw  new RuntimeException("格式化数据错误");
        }
    }

    public static <T> T obj2Json(JSONObject json, Class<T> model) {
        return null;
    }

}
