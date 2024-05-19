package com.niitjava.exception;


import com.niitjava.Bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice//全局异常处理类，注解包括一个@ResponseBody用来将对象以json格式响应返回给前端
@Slf4j
public class ExceptionHandler {

    //TODO 异常分类别进行拦截，sql报错拦截模块未完成
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result handSql(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();//获取异常信息
        ex.printStackTrace();
        return Result.error("失败，数据库异常：" + message);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Result hand(Exception e){
        e.printStackTrace();//打印异常的堆栈信息
        log.info("——————捕获到全局异常——————");
        return Result.error("失败，操作出现异常，请联系管理员");
    }

}


