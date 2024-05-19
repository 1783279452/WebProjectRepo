package com.niitjava.mapper;

import com.niitjava.Bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO student (sn, name, image, gender, status, class_name, class_id, entrydate, create_time, update_time) " +
            "value (#{sn},#{name},#{image},#{gender},#{status},#{className},#{classId},#{entrydate},#{createTime},#{updateTime})")
    void addStudent(Student student);

    @Delete("delete from student where id = #{id}")
    void deleStudent(Integer id);

    @Select("select id, sn, name, image, gender, status, class_name className, class_id classId, entrydate, create_time createTime, update_time updateTime from student")
    List<Student> getAllStudent();

    @Select("select * from student where id = #{id}")
    Student getStudentById(Integer id);

    @Select("select * from student")//分页查询（插件版）
    List<Student> getStuPage();

    //条件查询，分页返回
    List<Student> getQueryPage(String sn, String name, Short gender, Short status, String className, Integer classId, LocalDateTime begin, LocalDateTime end);


    //动态更新学生
    //TODO 注意，更新学生信息sql语句未完全测试
    void update(Student student);

    @Delete("delete from student where class_id = #{id}")
    void deleteByClaaId(Integer id);
}
