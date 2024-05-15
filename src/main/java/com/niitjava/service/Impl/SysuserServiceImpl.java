package com.niitjava.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niitjava.Bean.PageBean;
import com.niitjava.Bean.Sysuser;
import com.niitjava.mapper.SysuserMapper;
import com.niitjava.service.SysuserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SysuserServiceImpl implements SysuserService {

    @Autowired
    private SysuserMapper sysuserMapper;

    @Override//查询全部人员信息
    public List<Sysuser> getSysuser() {
        return sysuserMapper.getSysu();
    }

    @Override//添加人员
    public void addSysuser(Sysuser sysuser) {
        sysuser.setCreateTime(LocalDateTime.now());
        sysuser.setUpdateTime(LocalDateTime.now());
        sysuserMapper.addSysuser(sysuser);
    }

    @Override//根据ID删除人员
    public void deleteSysuser(Integer id) {
        sysuserMapper.deleteById(id);
    }

    @Override//修改人员部分或全部信息
    public void updateSysuser(Sysuser sysuser) {
        sysuser.setUpdateTime(LocalDateTime.now());
        sysuserMapper.update(sysuser);

    }

    @Override//根据ID查询员工
    public Sysuser getSysuserById(Integer id) {
        return sysuserMapper.getSysuById(id);
    }

    @Override//分页查询
    public PageBean getPage(Integer page, Integer pageSize) {
        /*原理：在mapper层查询全部数据，在本层调PageHelper内方法来 单独查询内部总记录数count(*)和分页数据limit #{start} , #{pageSize}*/
        PageHelper.startPage(page,pageSize);//设置分页参数
        List<Sysuser> sysusers = sysuserMapper.getPage();//用list集合封装 分页查询返回的:总记录数、多个员工数据
        Page<Sysuser> sysuerpage = (Page<Sysuser>) sysusers;//再强转List集合进page类
        PageBean pageBean = new PageBean(sysuerpage.getTotal(), sysuerpage.getResult());//调用PageBean里的两方法获取 总记录数、多个员工数据
        return pageBean;
    }

    @Override//条件查询-分页显示     未完成
    public PageBean getQueryPage(Integer page, Integer pageSize, String name, Short gender, Short status, LocalDateTime begin, LocalDateTime end) {
        PageHelper.startPage(page,pageSize);
        List<Sysuser> sysusers = sysuserMapper.getQueryPage(name,gender,status,begin,end);
        Page<Sysuser> page1 = (Page<Sysuser>) sysusers;
        PageBean pageBean = new PageBean(page1.getTotal(),page1.getResult());
        return pageBean;
    }

    @Override//登录校验
    public Sysuser login(Sysuser sysuser) {
        return sysuserMapper.login(sysuser);
    }


}
