package com.niitjava.controller;

import com.niitjava.Bean.Result;
import com.niitjava.Bean.Student;
import com.niitjava.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
