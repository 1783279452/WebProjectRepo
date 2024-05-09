package com.niitjava.mapper;

import com.niitjava.Bean.Class;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassMapper {

    //查询全部班级信息
    @Select("select id, name, create_time createTime, update_time updateTime from classinfo")
    List<Class> Classlist();


    //添加班级
    @Insert("INSERT INTO classinfo (name, create_time, update_time) VALUE (#{name},#{createTime},#{updateTime})")
    void add(Class classobject);

    //删除班级
    @Delete("delete from classinfo where id = #{id}")
    void deleteById(Integer id);
}
