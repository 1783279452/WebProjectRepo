package com.niitjava.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {//学生类

    private Integer id;
    private String sn;//学号
    private String name; // 姓名
    private String image; //图像url
    private short gender;//性别 1男、2女
    private short status; //身份 1系统管理员、2老师、3学生、4其他员工
    private String className;//班级名
    private Integer classId;//班级id
    private LocalDate entrydate; //入学日期
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间


}
