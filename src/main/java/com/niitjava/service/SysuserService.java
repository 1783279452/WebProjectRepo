package com.niitjava.service;

import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Result;
import com.niitjava.Bean.Sysuser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface SysuserService {
    List<Sysuser> getSysuser();

    void addSysuser(Sysuser sysuser);

    void deleteSysuser(Integer id);


    void updateSysuser(Sysuser sysuser);

    Sysuser getSysuserById(Integer id);

    PageBean getPage(Integer page, Integer pageSize);

    PageBean getQueryPage(Integer page, Integer pageSize, String name, Short gender, Short status, LocalDateTime begin, LocalDateTime end);

    Sysuser login(Sysuser sysuser);

    Boolean isPasswordNull(String password);

    boolean isUsernameNull(String username);

    boolean isUsername(String username);

}
