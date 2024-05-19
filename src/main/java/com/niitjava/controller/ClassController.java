package com.niitjava.controller;


import com.niitjava.Bean.Class;
import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Result;
import com.niitjava.aop.Log;
import com.niitjava.service.ClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController//控制反转
public class ClassController {

    @Autowired//依赖注入
    private ClassService classService;

    @GetMapping("/classpage")//分页查询班级信息
    public Result getPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("控制-getPage-分页查询 数据：{},{}",page,pageSize);
        PageBean pageBean = classService.getPage(page,pageSize);
        return Result.success(pageBean);
    }


    @GetMapping("/classes")//查询全部班级信息
    public Result get(){
        log.info("执行控制-get方法-查询部门信息");//日志记录
        List<Class> classList = classService.getClassList();
        return Result.success(classList);//返回带对象，方便前端调用
    }

    @PostMapping("/classes")//添加班级，POST方法测试时，数据在body中！且为json格式！
    public Result add(@RequestBody Class classobject){//前端返回json数据，如需封装 要在封装的bean类前加@RequestBody
        log.info("执行控制-add方法-添加班级：" +classobject);
        classService.addClass(classobject);
        return Result.success();
    }

    @DeleteMapping("/declass/{id}")//删除班级  通过前端返回id删除
    public Result deleteClass(@PathVariable Integer id){//PathVariable：从URL路径中获取路径变量 如这里的id，将其赋给本方法形参id
        log.info("执行控制-delete方法-删除班级：" +id);
        classService.deleteClass(id);
        return Result.success();
    }



}
