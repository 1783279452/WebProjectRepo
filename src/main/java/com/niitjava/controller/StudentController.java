package com.niitjava.controller;

import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Result;
import com.niitjava.Bean.Student;
import com.niitjava.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/stu")
    public Result add(@RequestBody Student student){//添加学生
        log.info("执行控制-add方法-添加学生：" +student);
        studentService.addstudent(student);
        return Result.success();
    }

    @DeleteMapping("/destu/{id}")//删除学生  通过前端返回id删除
    public Result delete(@PathVariable Integer id){//PathVariable：从URL路径中获取路径变量 如这里的id，将其赋给本方法形参id
        log.info("执行控制-delete方法-删除班级：" +id);
        studentService.deleteStudent(id);
        return Result.success();
    }

    @GetMapping("/stu")
    public Result get(){//查询全部学生
        log.info("执行控制-get方法-查询全部学生信息");
        List<Student> list = studentService.getSysuser();
        return Result.success(list);//向前端返回
    }

    @GetMapping("/stu/{id}")//查询学生 -通过id
    public Result getById(@PathVariable Integer id){
        log.info("执行控制-getById方法-通过id查询学生");
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }

    @GetMapping("/stupage")//分页查询（返回的包括总记录数）
    public Result getPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("控制-getPage-分页查询 数据：{},{}",page,pageSize);
        PageBean pageBean = studentService.getPage(page,pageSize);
        return Result.success(pageBean);
    }

    @GetMapping("/stuquepage")//条件查询--分页返回
    public Result getQueryPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                               String sn, String name, Short gender, Short status, String className, Integer classId,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime begin, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime end){
        log.info("控制-getQueryPage-条件 条件查询 分页返回");
        PageBean pageBean = studentService.getQueryPage(page,pageSize,sn,name,gender,status,className,classId,begin,end);
        return Result.success(pageBean);
    }

    @PutMapping("/stu")//动态更新学生信息
    public Result update(@RequestBody Student student){
        log.info("执行控制-update方法-修改学生");
        studentService.updateSysuser(student);
        return Result.success();
    }
}
