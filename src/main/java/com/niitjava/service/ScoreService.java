package com.niitjava.service;

import com.niitjava.Bean.Result;
import com.niitjava.Bean.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService {

    Result addScoreByStuid(Score score);

    Result deleteAllScoreByStuId(Integer stuid);

    List<Score> getScore(Integer stuid);

    int getScoreAver(Integer stuid);

    Boolean isStudent(Integer stuid);

    void updateScore(Score score);

    List<Score> sortScoreDesc(Integer id);
    Boolean isCourse(Integer id);

    List<Score> sortScoreStuDesc(Integer id);

    void deleteScoreByCouId(Score score);

    Boolean isScore(Score score);

    Result deteleScoreByCourse(Integer id);
}
