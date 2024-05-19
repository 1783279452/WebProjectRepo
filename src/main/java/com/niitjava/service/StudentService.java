package com.niitjava.service;

import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface StudentService {


    void addstudent(Student student);

    void deleteStudent(Integer id);

    List<Student> getSysuser();

    Student getStudentById(Integer id);

    PageBean getPage(Integer page, Integer pageSize);

    PageBean getQueryPage(Integer page, Integer pageSize, String sn, String name, Short gender, Short status, String className, Integer classId, LocalDateTime begin, LocalDateTime end);

    void updateSysuser(Student student);

    void deleteStudentByClassId(Integer id);
}
