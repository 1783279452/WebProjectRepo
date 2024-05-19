package com.niitjava.controller;


import com.niitjava.Bean.Course;
import com.niitjava.Bean.Result;
import com.niitjava.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    public Result add(@RequestBody Course course){//添加课程
        log.info("执行控制-add-添加课程信息");
        courseService.addCourse(course);
        return Result.success();
    }

    @DeleteMapping("/delecourse/{id}")
    public Result delete(@PathVariable Integer id){//根据id删除课程
        log.info("执行控制-delete-根据id删除课程");
        courseService.deleteCourse(id);
        return Result.success();
    }

    @PutMapping("/course")
    public Result update(@RequestBody Course course){//更新课程-后端根据ID进行更新
        log.info("执行控制-update-更新课程");
        courseService.updateCourse(course);
        return Result.success();
    }

    @GetMapping("/course")
    private Result get(){//查询全部的课程信息
        log.info("执行控制-get-查询全部课程");
        List<Course> list = courseService.getCourse();
        return Result.success(list);
    }


}
