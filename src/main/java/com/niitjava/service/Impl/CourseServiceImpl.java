package com.niitjava.service.Impl;

import com.niitjava.Bean.Course;
import com.niitjava.aop.Log;
import com.niitjava.mapper.CourseMapper;
import com.niitjava.service.CourseService;
import com.niitjava.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScoreService scoreService;


    @Override
    public void addCourse(Course course) {//添加课程
        courseMapper.addCourse(course);
    }

    @Override
    @Log
    @Transactional(rollbackFor = Exception.class)//事务，任一异常都会导致事务回滚
    public void deleteCourse(Integer id) {//删除课程
        courseMapper.deleCourse(id);
        scoreService.deteleScoreByCourse(id);

        int i =1/0; //异常测试用例

        System.out.println("测试");
    }

    @Override
    public void updateCourse(Course course) {//动态更新课程信息
        courseMapper.updateCourse(course);
    }

    @Override
    public List<Course> getCourse() {//查询全部课程
        return courseMapper.getCourse();
    }

    public String getNameById(Integer id){//通过id获取课程名
        String cousName = courseMapper.getNameById(id);
        return cousName;
    }

    public String getTermById(Integer id){//通过id获取学期
        String cousTerm = courseMapper.getTermById(id);
        return cousTerm;
    }



}
