package com.vangelis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * 1.注解的定义：
 *  在interface前面加一个@符号。
 *  在注解中只有成员变量，没有方法。
 * 2.广义的理解注解：
 *  想像代码具有生命，注解就是对于代码中某些鲜活个体的贴上去的一张标签。简化来讲，注解如同一张标签。
 * 3.元注解：
 *  给注解表明属性的注解。
 *  @Retention（标明注解的保留期）:SOURCE（源码阶段）、CLASS（编译阶段）、RUNTIME（运行阶段）
 *  @Target（标明注解的使用范围）:
 *      ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *      ElementType.CONSTRUCTOR 可以给构造方法进行注解
 *      ElementType.FIELD 可以给属性进行注解
 *      ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 *      ElementType.METHOD 可以给方法进行注解
 *      ElementType.PACKAGE 可以给一个包进行注解
 *      ElementType.PARAMETER 可以给一个方法内的参数进行注解
 *      ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 *  @Inherited（标明被该注解被使用时，假如被注解的类有继承关系，则注解向下一直继承）
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInterface {
    /**
     * 这个方法名字，则为成员变量的名字，返回的类型，则为成员变量的类型
     *
     * 意思就是，在注解使用的时候是这样来使用的：
     *
     * 多个属性用逗号隔开
     * @LogInterface(logMsg="test",logTime=1111)
     * public void test(){
     *
     * }
     */
    String logMsg() default "";

    /**
     * 注解中可以含有默认值，当使用注解的时候，不指定值，则使用默认值
     *
     * 当定义注解的时候，只有一个参数的时候，则可以这样写
     * @LogInterface(1111)
     * public void test(){
     *
     * }
     */
    int logTime() default 0;

    /**
     * 如果注解中没有任何属性，则可以将括号都省略
     *
     * @LogInterface
     * public void test(){
     *
     * }
     */
}