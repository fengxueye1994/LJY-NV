//package com.lsljy.tools.logUtil;
//
//import com.sun.deploy.net.HttpUtils;
//import org.apache.commons.lang.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
//@Aspect
//@Component
//public class WebLogAcpect {
//    private final static Logger orderLog = LoggerFactory.getLogger("order");
//    //定义切点
//    @Pointcut("execution(* com.qszhuang.backstage.service.OrderService.updateOrder*(..))")
//    public void webLog() {
//    }
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) {
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (!StringUtils.isEmpty(attributes)) {
//            HttpServletRequest request = attributes.getRequest();
//            // 记录下请求内容
//            orderLog.info("URL : " + request.getRequestURL().toString());
//            // orderLog.info("HTTP_METHOD : " + request.getMethod());
//            orderLog.info("IP : " + HttpUtil.getIp(request));
//            //获取操作人
//            if (!StringUtils.isEmpty(request.getCookies())) {
//                String cookieValueByName = HttpUtil.getCookieValueByName(request.getCookies(), WebSecurityConfig.TOKEN_HEADER);
//                Map<String, Claim> stringClaimMap = TokenUtil.verifyToken(cookieValueByName);
//                //方法名
//                // orderLog.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//                orderLog.info("订单号 : {},操作人:{}", Arrays.toString(joinPoint.getArgs()), stringClaimMap.get("adminUsername").asString());
//            }
//        }else {
//            orderLog.info("整点退款");
//        }
//    }
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//        // 处理完请求，返回内容
//        orderLog.info("操作返回值 : " + ret);
//    }
//}