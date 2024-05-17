package com.niitjava.service;

import com.niitjava.Bean.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {


    void addstudent(Student student);

    void deleteStudent(Integer id);

    List<Student> getSysuser();

    Student getStudentById(Integer id);
}
