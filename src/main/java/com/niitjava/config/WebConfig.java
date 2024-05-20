package com.niitjava.config;

import com.niitjava.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {//注册拦截器

    @Autowired
    private LoginInterceptor loginInterceptor;//引入拦截器对象

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //调用注册拦截器的方法api
        //TODO
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
        //设置拦截路径和不拦截的路径

    }
}
