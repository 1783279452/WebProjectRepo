package com.niitjava.service.Impl;

import com.niitjava.Bean.Student;
import com.niitjava.mapper.StudentMapper;
import com.niitjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override//添加学生
    public void addstudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.addStudent(student);
    }

    @Override//删除学生
    public void deleteStudent(Integer id) {
        studentMapper.deleStudent(id);
    }

    @Override//查询全部学生
    public List<Student> getSysuser() {
        List<Student> list = studentMapper.getAllStudent();
        return list;
    }

    @Override//查询学生byid
    public Student getStudentById(Integer id) {
        Student student = studentMapper.getStudentById(id);
        return student;
    }

}
