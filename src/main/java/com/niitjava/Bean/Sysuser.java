package com.niitjava.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sysuser {//系统用户类

    private Integer id;
    private String username;//用户名
    private String password;//密码
    private String name;//姓名
    private String image; //图像url
    private Short gender; //性别 , 1 男, 2 女
    private short status; //身份 1系统管理员、2老师、3学生、4其他员工
    private LocalDate entrydate; //入职日期
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间


}
