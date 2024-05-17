package com.niitjava.mapper;

import com.niitjava.Bean.Course;
import com.niitjava.Bean.Sysuser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    @Insert("INSERT INTO course (course_name, term) VALUE (#{courseName},#{term})")
    void addCourse(Course course);

    @Delete("delete from course where id = #{id}")
    void deleCourse(Integer id);


    void updateCourse(Course course);


}
