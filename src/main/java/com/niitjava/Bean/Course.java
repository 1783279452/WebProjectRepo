package com.niitjava.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Course {
    private Integer id;
    private String courseName;//课程名称
    private String term;//学期
}
