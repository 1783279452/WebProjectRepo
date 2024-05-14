package com.niitjava.mapper;

import com.niitjava.Bean.Sysuser;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SysuserMapper {


//    @Update("update sysuser set username = #{} , name = #{} , password={} where id = #{}")
//    void ces();//测试sql语句备用方法

    @Select("select id, username, password, name, image, gender, status, entrydate, create_time createTime, update_time updateTime from sysuser")
    List<Sysuser> getSysu();

    @Insert("INSERT INTO sysuser (username, password, name, image, gender, status, entrydate, create_time , update_time ) VALUE (#{username} , #{password} , #{name} , #{image} , #{gender} , #{status} , #{entrydate} , #{createTime} , #{updateTime})")
    void addSysuser(Sysuser sysuser);

    @Delete("delete from sysuser where id = #{id}")
    void deleteById(Integer id);

    void update(Sysuser sysuser);//动态sql语句交由Xml文件进行管理

    @Select("select * from sysuser where id = #{id}")
    Sysuser getSysuById(Integer id);

    @Select("select * from sysuser")//分页查询（插件版）
    List<Sysuser> getPage();


    /*条件查询，分页展示--动态sql*/
    List<Sysuser> getQueryPage(String name, Short gender, Short status, LocalDateTime begin, LocalDateTime end);
}
