package com.niitjava.Interceptor;

import com.google.gson.Gson;
import com.niitjava.Bean.Result;
import com.niitjava.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {//拦截器设置

    @Override //目标资源方法运行前运行
    /*return true代表放行，false不放行*/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI().toString();//获取请求地址并转换成string
        log.info("请求的url：{}",url);
        if (url.contains("login")){
            log.info("登录操作，放行");
            return true;
        }
        /*此前在登录时已通过jwt工具类生成jwt令牌，并返回给前端请求头heard，（前端设置后续每次请求jwt都位于请求头中传递），因此这里直接从request中拿jwt数据*/
        String jwt = request.getHeader("token");

        //如果jwt为空或null：（调用工具类判断是否为null或空）
        if (!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，未登录");
            /*下面是向前端返回错误信息*/
            Result not_login = Result.error("Not_Login");
            Gson gson = new Gson();
            String notlogin = gson.toJson(not_login);//用gson转换为json格式
            response.getWriter().write(notlogin);//调用write流将字符串响应回浏览器
            return false;
        }
        /*不为空，开始解析jwt*/
        try {
            JwtUtils.parseJWT(jwt);//解析jwt看是否合法
        }catch (Exception e){
            e.printStackTrace();//打印异常的堆栈信息
            log.info("令牌解析失败");
            /*下面是向前端返回错误信息*/
            Result not_login = Result.error("Not_Login");
            Gson gson = new Gson();
            String notlogin = gson.toJson(not_login);//用gson转换为json格式
            response.getWriter().write(notlogin);//调用write流将字符串响应回浏览器
            return false;
        }

        log.info("jwt合法，放行");
        return true;
    }

    /*！注意，添加登录校验后，后续postman测试，需在请求头中手动添加“token”与值，否则将被拦截！*/

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
