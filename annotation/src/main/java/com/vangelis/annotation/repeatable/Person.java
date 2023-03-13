package com.vangelis.annotation.repeatable;


import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Function：Repeatable元注解讲解
 * Created on 2023/3/13.
 * Comment：
 *      @Repeatable(Persons.class)：Persons是一个有Person属性的注解
 *
 * @author Wangpei
 */
@Repeatable(Persons.class)
@Retention(RetentionPolicy.CLASS)
public @interface Person {
    String role() default "";
}
