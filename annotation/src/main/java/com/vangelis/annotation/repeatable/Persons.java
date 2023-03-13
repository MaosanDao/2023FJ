package com.vangelis.annotation.repeatable;

/**
 * Function：Repeatable元注解
 * Created on 2023/3/13.
 * Comment：这个注解，主要是存储Person注解的注解
 *
 * @author Wangpei
 */

public @interface Persons {
    Person[] value();
}
