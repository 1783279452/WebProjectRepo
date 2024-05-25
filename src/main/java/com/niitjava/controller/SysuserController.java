package com.niitjava.controller;

import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Result;
import com.niitjava.Bean.Sysuser;
import com.niitjava.aop.Log;
import com.niitjava.service.SysuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController//控制反转的注解
@Slf4j
public class SysuserController {

    @Autowired//依赖注入
    private SysuserService sysuserService;


    @GetMapping("/sysuser")
    public Result get(){//查询全部系统人员信息
        log.info("执行控制-get方法-查询全部系统人员信息");
        List<Sysuser> list = sysuserService.getSysuser();
        return Result.success(list);//向前端返回
    }

    @PostMapping("/sysuser")//添加系统人员信息
    public Result add(@RequestBody Sysuser sysuser){//添加系统人员信息——接受前端返回的json信息并封装成bean类
        log.info("执行控制-add方法-添加系统人员");
        sysuserService.addSysuser(sysuser);
        return Result.success();
    }

    @DeleteMapping("/sysuser/{id}")//删除人员  通过前端返回id删除
    public Result delete(@PathVariable Integer id){//PathVariable：从URL路径中获取路径变量 如这里的id，将其赋给本方法形参id
        log.info("执行控制-delete方法-删除人员：" +id);
        sysuserService.deleteSysuser(id);
        return Result.success();
    }

    @PutMapping("/sysuser")//动态修改人员信息，可修改部分或全部属性版
    public Result update(@RequestBody Sysuser sysuser){
        log.info("执行控制-update方法-修改员工信息");
        sysuserService.updateSysuser(sysuser);
        return Result.success();
    }

    @GetMapping("/sysuser/{id}")//根据ID查询员工,前端返回id属性（实现数据回显）
    public Result getById(@PathVariable Integer id){
        log.info("执行控制-getById-根据ID查询人员：" + id);
        Sysuser sysuser = sysuserService.getSysuserById(id);
        return Result.success(sysuser);
    }

    @Log
    @GetMapping("/sysuserpage")//分页查询，接收开始页码、每页记录数量，返回总记录数、每页多项员工数据
    public Result getPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){//接收前端提供数据
        /*page为分页查询页码，pagesiz为每页记录的数量。@RequestParam用于给形参提供默认值*/
        log.info("控制-getPage-分页查询 数据：{},{}",page,pageSize);
        PageBean pageBean = sysuserService.getPage(page,pageSize);//设PageBean实体类来封装分页数据（总记录数 和 每页多项员工的数据 ）
        return Result.success(pageBean);
    }

    /*本功能预计根据：姓名、性别、职位、入职时间（始末）进行条件查询*/
    @GetMapping("/sysuserquepage")//条件查询-分页显示 接收开始页码、每页记录数量、姓名、性别、职位、入职时间（始末）；返回总记录数、每页多项员工数据
    public Result getQueryPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                               String name, Short gender, Short status, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime begin, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime end){
        /*@DateTimeFormat(pattern = "yyyy-MM-dd")此注解表示接收的前端日期时间的格式*/
        log.info("控制-getQueryPage-条件 条件查询 分页返回");
        PageBean pageBean = sysuserService.getQueryPage(page,pageSize,name,gender,status,begin,end);
        return Result.success(pageBean);
    }

}
