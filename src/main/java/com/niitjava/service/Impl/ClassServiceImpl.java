package com.niitjava.service.Impl;

import com.niitjava.Bean.Class;
import com.niitjava.mapper.ClassMapper;
import com.niitjava.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override//查询全部部门信息
    public List<Class> getClassList() {
        return classMapper.Classlist();
    }

    @Override//添加班级
    public void addClass(Class classobject) {
        classobject.setCreateTime(LocalDateTime.now());//因为新建的班级无新建日期和更新日期，故此处先设置再往mapper传递
        classobject.setUpdateTime(LocalDateTime.now());
        classMapper.add(classobject);
    }

    @Override//删除班级
    public void deleteClass(Integer id) {
        classMapper.deleteById(id);
    }
}
