package com.niitjava.mapper;

import com.niitjava.Bean.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface LogNoticeMapper {

    //插入日志数据
    @Transactional(propagation = Propagation.REQUIRES_NEW)//无论有没有上级方法有没有事务，调用本方法时，本方法一律开启新事物，不嵌套
    @Insert("insert into operate_log (operate_user, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateUser}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

}
