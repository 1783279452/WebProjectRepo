package com.niitjava.service;

import com.niitjava.Bean.Class;
import com.niitjava.Bean.PageBean;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassService {

    List<Class> getClassList();

    void addClass(Class classobject);

    void deleteClass(Integer id);

    PageBean getPage(Integer page, Integer pageSize);
}
