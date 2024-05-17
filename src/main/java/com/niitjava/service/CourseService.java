package com.niitjava.service;

import com.niitjava.Bean.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    void addCourse(Course course);


    void deleteCourse(Integer id);

    void updateCourse(Course course);
}
