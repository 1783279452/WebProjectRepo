package com.niitjava.mapper;

import com.niitjava.Bean.Score;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ScoreMapper {

    @Insert("INSERT INTO score (stu_id, course_id, course_name, score, term) VALUE (#{stuId},#{courseId},#{courseName},#{score},#{term})")
    void addScoreByStuid(Score score);

    @Delete("delete from score where stu_id = #{stuid}")
    void deleteAllScoreByStuId(Integer stuid);

    @Select("select id, stu_id stuID, course_id courseId, course_name courseName, score, term from score where stu_id = #{stuid}")
    List<Score> getScore(Integer stuid);

    @Select("select AVG(score) from score where stu_id = #{stuid}")
    int getScoreAver(Integer stuid);

    @Select("select count(*) from score where stu_id = #{stuid}")
    int isStudent(Integer stuid);

    @Update("update score set score = #{score} where stu_id = #{stuId} and course_id = #{courseId} ")
    void updateScore(Score score);

    @Select("select id, stu_id stuID, course_id courseId, course_name courseName, score, term from score where course_id = #{id} order by score desc")
    List<Score> sortScoreDesc(Integer id);

    @Select("select count(*) from score where course_id = #{id}")
    int isCourse(Integer id);

    @Select("select id, stu_id stuID, course_id courseId, course_name courseName, score, term from score where stu_id = #{stuid} ORDER by score desc")
    List<Score> sortScoreStuDesc(Integer id);

    @Delete("delete from score where stu_id = #{stuId} and course_id = #{courseId} ")
    void deleteScoreByCouId(Score score);

    @Select("SELECT count(*) FROM score WHERE stu_id = #{stuId} AND course_id = #{courseId};")
    int isScore(Score score);

    @Delete("delete from score where course_id = #{id}")
    void deteleScoreByCourse(Integer id);
}
