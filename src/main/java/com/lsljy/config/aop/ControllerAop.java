package com.lsljy.config.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;

/**1定义切面类
 * @功能描述:用于controller层操作的AOP类，打印请求响应报文
 * @author Administrator
 */
@Component // 将对象交由spring进行管理
@Aspect // 代表此类为一个切面类
public class ControllerAop {
    private final static Logger logger = LoggerFactory.getLogger(ControllerAop.class);


    //2.定义切入点表达式：
    @Pointcut("execution(public * com.lsljy.controller..*.*(..))") // 切入点表达式
    public void privilege() {
    }

    //3.定义切面方法
    @Around("privilege()")
    public Object around(ProceedingJoinPoint pjd) throws Throwable {
        // 获取方法名
        String requestClassNm = pjd.getSignature().getDeclaringType().getName()+"."+pjd.getSignature().getName();
        /** 初始化日志打印 */
//        Logger log = LoggerFactory.getLogger(className);
//        String pretty = "";//请求报文接收的字符串
        // 定义返回参数
        Object result = null;//返回报文的接受对象
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 获取方法参数
        Object[] args = pjd.getArgs();
//        String params = "请求报文";
        //获取请求参数集合并进行遍历拼接
//        for (Object object : args) {
//            pretty += object.toString() + ",";
//        }
//        pretty = pretty.substring(0, pretty.length() - 1);
//        Object aa = JSONObject.parseObject(pretty);
        result = args[0];//因为请求参数就一个大的JSONObject对象，不hi有第二个，所以直接取0
        result = JSON.toJSONString((result instanceof Result ? (Result) result : result), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        //打印请求报文
        logger.info(requestClassNm +"\n"+ ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【【【【【【【请求报文】】】】】】】:"+"\n" + result);

        // 执行目标方法
        result = pjd.proceed();


        // 打印响应报文
        result = JSON.toJSONString((result instanceof Result ? (Result) result : result), SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat);
        // 获取执行完的时间
        logger.info(requestClassNm+"\n"+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【【【【【【【响应报文】】】】】】】:"+"\n"+result);
        logger.info(requestClassNm.substring(requestClassNm.lastIndexOf(".")+1) + "请求耗时:" + (System.currentTimeMillis() - start));
        return  JSONObject.parseObject(String.valueOf(result));//将报文返回给前端，需要转换为JsonObject类型
    }
}
