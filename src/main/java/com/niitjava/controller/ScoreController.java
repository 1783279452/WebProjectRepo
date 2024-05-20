package com.niitjava.controller;

import com.niitjava.Bean.Result;
import com.niitjava.Bean.Score;
import com.niitjava.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    //根据学生id增加学生成绩  入参学生id、课程id、分数，课程名、学期自动获取。
    @PostMapping("/score")
    public Result addScoreByStuid(@RequestBody Score score){
        log.info("控制-addScoreByStuid-为学生添加成绩");
        return scoreService.addScoreByStuid(score);
    }

    //根据学生ID删除该学生所有成绩
    @DeleteMapping("/score/{stuid}")
    public Result deleteAllScoreByStuId(@PathVariable Integer stuid){
        log.info("控制-deleteAllScoreByStuId-删除该学生所有成绩");
        return scoreService.deleteAllScoreByStuId(stuid);
    }

    //删除x学生的x课程成绩
    @DeleteMapping("/scorecourse")
    public Result deleteScoreByCouId(@RequestBody Score score){
        log.info("控制-deleteScoreByCouId-删除学生的单科成绩");
        scoreService.deleteScoreByCouId(score);
        return Result.success();
    }

    //删除x课程所有成绩
    @DeleteMapping("/scorecouallsco")
    public Result deteleScoreByCourse(@PathVariable Integer id){
        log.info("控制-deteleScoreByCourse-删除x课程下所有成绩");
        return scoreService.deteleScoreByCourse(id);
    }

    //根据id查询学生成绩
    @GetMapping("/score/{stuid}")
    public Result getScore(@PathVariable Integer stuid){
        log.info("控制-getScore-根据id查询学生成绩");
        if (scoreService.isStudent(stuid)){//判断是否存在学生
            return Result.error("无此学生，请重新查询");
        }
        List<Score> list = scoreService.getScore(stuid);
        scoreService.isStudent(stuid);
        return Result.success(list);
    }


    //查询x学生所有科目平均成绩  （入参 学生id）
    @GetMapping("/scoreavg/{stuid}")
    public Result getScoreAver(@PathVariable Integer stuid){
        log.info("控制-getScoreAver-根据id查询学生平均成绩");
        if (scoreService.isStudent(stuid)){//判断是否存在学生
            return Result.error("无此学生，请重新查询");
        }
        int scoreAver = scoreService.getScoreAver(stuid);
        return Result.success(scoreAver);
    }

    //更新学生成绩-根据学生id和课程id修改
    @PutMapping("/score")
    public Result updateScore(@RequestBody Score score){
        log.info("控制-updateScore-根据id更新学生成绩");
        if (scoreService.isStudent(score.getStuId())){//判断是否存在学生
            return Result.error("无此学生，请重新查询");
        }
        scoreService.updateScore(score);
        return Result.success();
    }

    //查询x课程成绩 从高到低排序
    @PostMapping("/scoredesc/{id}")
    public Result sortScoreDesc(@PathVariable Integer id){
        log.info("控制-sortScoreDesc-课程成绩从高到低排序");
        if (scoreService.isCourse(id)){//判断是否存在课程
            return Result.error("无此课程，请重新输入");
        }
        List<Score> list = scoreService.sortScoreDesc(id);
        return Result.success(list);
    }

    //查询学生所有科目成绩 从高到低排列
    @PostMapping("/scorebystu/{id}")
    public Result sortScoreStuDesc(@PathVariable Integer id){
        log.info("控制-sortScoreStuDesc-学生成绩从高到低排序");
        if (scoreService.isStudent(id)){//判断是否存在学生
            return Result.error("无此学生，请重新查询");
        }
        List<Score> list = scoreService.sortScoreStuDesc(id);
        return Result.success(list);
    }





}
