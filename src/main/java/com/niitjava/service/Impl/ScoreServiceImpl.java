package com.niitjava.service.Impl;

import com.niitjava.Bean.Course;
import com.niitjava.Bean.Result;
import com.niitjava.Bean.Score;
import com.niitjava.aop.Log;
import com.niitjava.mapper.ScoreMapper;
import com.niitjava.service.CourseService;
import com.niitjava.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private CourseService courseService;

    @Override
    public Boolean isStudent(Integer stuid) {//是否有xx学生
        int isNum = scoreMapper.isStudent(stuid);
        if (isNum <= 0){
            return true;//不存在
        }
        return false;
    }

    public Boolean isCourse(Integer id) {//否存在xx课程
        int isNum = scoreMapper.isCourse(id);
        if (isNum <= 0){
            return true;//不存在
        }
        return false;
    }

    public Boolean isScore(Score score){//是否存在相同科目成绩
        int isNum = scoreMapper.isScore(score);
        if (isNum >0){
            return true;//已经存在成绩
        }
        return false;
    }

    @Override
    public Result deteleScoreByCourse(Integer id) {//删除x课程所有成绩
        if (isCourse(id)){
            return Result.error("不存在能删除的成绩");
        }
        scoreMapper.deteleScoreByCourse(id);
        return Result.success();
    }

    @Override
    public Result addScoreByStuid(Score score) {//根据学生id添加成绩
        if (isScore(score)){
            return Result.error("已经存在成绩，成绩重复，请重新输入");
        }
        if (score.getScore()>100 | score.getScore()<0){
            return Result.error("打分超出范围，请重新输入");
        }
        Integer couId = score.getCourseId();//获取课程id
        String couName = courseService.getNameById(couId);//通过课程id获取课程名字
        String couTerm = courseService.getTermById(couId);//通过课程id获取课程学期
        score.setCourseName(couName);
        score.setTerm(couTerm);
        scoreMapper.addScoreByStuid(score);
        return Result.success();
    }


    @Override
    public Result deleteAllScoreByStuId(Integer stuid) {//根据学生ID删除该学生所有成绩
        if (isStudent(stuid)){//判断是否存在学生
            return Result.error("无此学生，请重新查询");
        }
        scoreMapper.deleteAllScoreByStuId(stuid);
        return Result.success();
    }

    @Override
    public List<Score> getScore(Integer stuid) {//根据学生id查询成绩
        List<Score> score1 = scoreMapper.getScore(stuid);
        return score1;
    }

    @Override
    public int getScoreAver(Integer stuid) {//根据学生id查询平均成绩
        int scoreAver = scoreMapper.getScoreAver(stuid);
        return scoreAver;
    }

    @Override
    public void updateScore(Score score) {//更新x学生成绩
        scoreMapper.updateScore(score);

    }

    @Override
    public List<Score> sortScoreDesc(Integer id) {//将x课程成绩从高到低排序
        return scoreMapper.sortScoreDesc(id);
    }

    @Override
    public List<Score> sortScoreStuDesc(Integer id) {//查询x学生所有成绩 从高到低排序
        return scoreMapper.sortScoreStuDesc(id);
    }

    @Override
    public void deleteScoreByCouId(Score score) {//删除x学生的x课程成绩
        scoreMapper.deleteScoreByCouId(score);
    }


}
