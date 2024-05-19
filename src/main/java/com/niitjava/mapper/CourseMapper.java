package com.niitjava.mapper;

import com.niitjava.Bean.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO course (course_name, term) VALUE (#{courseName},#{term})")
    void addCourse(Course course);

    @Delete("delete from course where id = #{id}")
    void deleCourse(Integer id);

    void updateCourse(Course course);


    @Select("select id, course_name courseName, term from course")
    List<Course> getCourse();
}
