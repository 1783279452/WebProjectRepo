package com.niitjava.service;

import com.niitjava.Bean.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    void addCourse(Course course);


    void deleteCourse(Integer id);

    void updateCourse(Course course);

    List<Course> getCourse();
}
