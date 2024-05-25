package com.niitjava.controller;

import com.niitjava.Bean.Result;
import com.niitjava.Bean.Sysuser;
import com.niitjava.service.SysuserService;
import com.niitjava.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private SysuserService sysuserService;

    @PostMapping("/signup")//注册功能
    public Result signUp(@RequestBody Sysuser sysuser){
        log.info("控制-注册账号");
        if (sysuserService.isUsernameNull(sysuser.getUsername())){
            return Result.error("账号为空，请重新输入");
        }
        if (sysuserService.isPasswordNull(sysuser.getPassword())){
            return Result.error("密码为空，请重新输入");
        }
        if (sysuser.getUsername().length() < 4 ){
            return Result.error("账号小于4位数，请重新输入");
        }
        if (sysuser.getPassword().length() <6 ){
            return Result.error("密码小于6位数，请重新输入");
        }
        if (sysuserService.isUsername(sysuser.getUsername())){
            return Result.error("账号已存在，请重新输入");
        }
        sysuser.setPassword(DigestUtils.md5DigestAsHex(sysuser.getPassword().getBytes()));//密码md5加密
        sysuserService.addSysuser(sysuser);//同在管理员端内增加账号
        return Result.success();
    }

    @PostMapping("/login")//接收前端json格式账号密码
    public Result login(@RequestBody Sysuser sysuser){//前端登录输入账号密码封装进用户类传递进来
        log.info("控制-员工登录：{}",sysuser);
        if (sysuserService.isUsernameNull(sysuser.getUsername())){
            return Result.error("账号为空，请重新输入");
        }
        if (sysuserService.isPasswordNull(sysuser.getPassword())){
            return Result.error("密码为空，请重新输入");
        }
        if (sysuser.getUsername().length() < 4 ){
            return Result.error("账号小于4位数，请重新输入");
        }
        if (sysuser.getPassword().length() <6 ){
            return Result.error("密码小于6位数，请重新输入");
        }
        /*对前端传来的密码进行md5加密 再查询*/
        String password = sysuser.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());//spring提供的工具类DigestUtils
        sysuser.setPassword(password);

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
