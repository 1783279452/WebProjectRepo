package com.niitjava.controller;

import com.niitjava.Bean.Result;
import com.niitjava.Bean.Sysuser;
import com.niitjava.service.SysuserService;
import com.niitjava.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private SysuserService sysuserService;

    @PostMapping("/login")//接收前端json格式账号密码
    public Result login(@RequestBody Sysuser sysuser){//前端登录输入账号密码封装进用户类传递进来
        log.info("控制-员工登录：{}",sysuser);
        Sysuser sysuser1 = sysuserService.login(sysuser);//查询

        /*生成jwt令牌，并返回前端（如果调用mapper方法判断账号密码存在 登录成功的话）*/
        if (sysuser1 != null){
            HashMap<String, Object> map1 = new HashMap<>();
//            Map<String, Object> map1 = new HashMap<>();
            map1.put("id",sysuser1.getId());
            map1.put("name",sysuser1.getName());
            map1.put("username",sysuser1.getUsername());
            String jwtNum = JwtUtils.generateJwt(map1);
            /*调用jwt工具类，往里传递map集合，该集合包括要生成的员工的信息,然后借此信息生成jwt令牌*/
            return Result.success(jwtNum);
        }else {
            return Result.error("用户名或密码错误");
        }
    }


}
