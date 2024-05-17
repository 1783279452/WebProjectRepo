package com.niitjava.service.Impl;

import com.niitjava.Bean.Course;
import com.niitjava.mapper.CourseMapper;
import com.niitjava.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Override
    public void addCourse(Course course) {//添加课程
        courseMapper.addCourse(course);
    }

    @Override
    public void deleteCourse(Integer id) {//删除课程
        courseMapper.deleCourse(id);
    }

    @Override
    public void updateCourse(Course course) {//动态更新课程信息
        courseMapper.updateCourse(course);
    }


}
