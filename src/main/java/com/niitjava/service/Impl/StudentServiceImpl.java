package com.niitjava.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Student;
import com.niitjava.aop.Log;
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

    @Override
    public PageBean getPage(Integer page, Integer pageSize) {//分页查询
        PageHelper.startPage(page,pageSize);
        List<Student> list = studentMapper.getStuPage();
        Page<Student> listpage= (Page<Student>) list;
        PageBean pageBean = new PageBean(listpage.getTotal(), listpage.getResult());
        return pageBean;
    }

    @Override//条件查询-分页返回
    public PageBean getQueryPage(Integer page, Integer pageSize, String sn, String name, Short gender, Short status, String className, Integer classId, LocalDateTime begin, LocalDateTime end) {
        PageHelper.startPage(page,pageSize);
        List<Student> list = studentMapper.getQueryPage(sn,name,gender,status,className,classId,begin,end);
        Page<Student> listpage= (Page<Student>) list;
        PageBean pageBean = new PageBean(listpage.getTotal(), listpage.getResult());
        return pageBean;
    }

    @Override//修改学生信息
    public void updateSysuser(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Log
    @Override//根据班级id批量删除学生
    public void deleteStudentByClassId(Integer id) {
        studentMapper.deleteByClaaId(id);
    }

}
