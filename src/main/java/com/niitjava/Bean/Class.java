package com.niitjava.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class {//班级类

    private Integer id;
    private String name; //班级名
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

}
