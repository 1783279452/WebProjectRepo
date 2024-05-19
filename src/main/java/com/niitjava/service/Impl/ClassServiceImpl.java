package com.niitjava.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niitjava.Bean.Class;
import com.niitjava.Bean.PageBean;
import com.niitjava.aop.Log;
import com.niitjava.aop.LogNotice;
import com.niitjava.mapper.ClassMapper;
import com.niitjava.mapper.StudentMapper;
import com.niitjava.service.ClassService;
import com.niitjava.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private LogNotice logNotice;

    @Autowired
    private StudentService studentService;

    @Override//查询全部部门信息
    public List<Class> getClassList() {
        return classMapper.Classlist();
    }

    @Override//添加班级
    public void addClass(Class classobject) {
        classobject.setCreateTime(LocalDateTime.now());
        classobject.setUpdateTime(LocalDateTime.now());
        classMapper.add(classobject);//因为新建的班级无新建日期和更新日期，故此处先设置再往mapper传递
    }


    @Log
    @Override//根据id删除班级
    @Transactional(rollbackFor = Exception.class)//事务，任一异常都会导致事务回滚
    public void deleteClass(Integer id) {

        classMapper.deleteById(id);

        /*int i =1/0;//异常测试*/

        studentService.deleteStudentByClassId(id);
        log.info("已删除班级id：{} 及其学生",id);

    }

    @Override//分页查询
    public PageBean getPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Class> list = classMapper.getClassPage();
        Page<Class> ClassPage = (Page<Class>) list;
        PageBean pageBean = new PageBean(ClassPage.getTotal(), ClassPage.getResult());
        return pageBean;
    }
}
