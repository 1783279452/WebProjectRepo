package com.niitjava.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//指定当前注解什么时候有效，此处设置为运行时有效
@Target(ElementType.METHOD)//指定当前注解作用在何处，此处为方法上method
public @interface Log {
}
