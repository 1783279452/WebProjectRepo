package com.niitjava.aop;


import com.google.gson.Gson;
import com.niitjava.Bean.OperateLog;
import com.niitjava.mapper.LogNoticeMapper;
import com.niitjava.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect//aop切面注解
@Slf4j
public class LogNotice {

    @Autowired
    private HttpServletRequest Request;
    @Autowired
    private LogNoticeMapper logNoticeMapper;

    //记录用户操作日志，根据bean类需要获取相关值并封装传回数据库
    @Around("@annotation(com.niitjava.aop.Log)")//环绕通知，通过连接点—@log的方法切面注入（需要运用本类的方法前+注解）
    public Object recordLog(ProceedingJoinPoint proJoin) throws Throwable {

        //思路：可通过jwt令牌获取操作人的信息,
        String token = Request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);//解析jwt令牌
        Integer id = (Integer) claims.get("id");//获取操作人id
        LocalDateTime recordTime = LocalDateTime.now();//操作时间


        String classname = proJoin.getTarget().getClass().getName();//获取类名
//        Signature signature = proJoin.getSignature();//获取方法签名
        String methodname = proJoin.getSignature().getName();//获取方法名
        Object[] args = proJoin.getArgs();//获取方法参数(此方法默认返回数值）
        String methodParms = Arrays.toString(args);


        long begin = System.currentTimeMillis();//记录开始时间
        Object proceed = proJoin.proceed();//执行原始方法，并返回原始方法执行结果
        long end = System.currentTimeMillis();//记录结束时间
        Long runTime = end-begin;//获取操作耗时

        Gson gson = new Gson();
        String returnValue = gson.toJson(proceed);//获取方法返回值 转换json格式

        //将信息封装进记录bean类
        OperateLog logInfo = new OperateLog(null,id,recordTime,classname,methodname,methodParms,returnValue,runTime);
        logNoticeMapper.insert(logInfo);
        log.info("aop操作记录日志：{}",logInfo);

        return proceed;
    }


}
