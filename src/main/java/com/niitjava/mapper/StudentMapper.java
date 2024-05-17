package com.niitjava.mapper;

import com.niitjava.Bean.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from sysuser where id = #{id}")
    Student getStudentById(Integer id);
}
