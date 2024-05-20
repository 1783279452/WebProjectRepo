package com.niitjava.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private Integer id;
    private Integer stuId;//学生sn
    private Integer courseId;//课程id
    private String courseName;//课程名称
    private Integer score;//成绩
    private String term;//学期



}
