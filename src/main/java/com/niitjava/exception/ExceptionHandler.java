package com.niitjava.exception;


import com.niitjava.Bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//全局异常处理类，注解包括一个@ResponseBody用来将对象以json格式响应返回给前端
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public Result hand(Exception e){
        e.printStackTrace();
        log.info("——————捕获到全局异常！——————");
        return Result.error("失败，操作出现异常");

    }
}
