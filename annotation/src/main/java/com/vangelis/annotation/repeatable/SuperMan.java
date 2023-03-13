package com.vangelis.annotation.repeatable;

/**
 * Function：Repeatable注解
 * Created on 2023/3/13.
 * Comment：
 *  下面的使用方式就是需要@Repeatable(Persons.class)这样的注解来进行标识，
 *  否则无法在使用注解的时候，多次使用
 *
 * @author Wangpei
 */
@Person(role = "doctor")
@Person(role = "coder")
@Person(role = "PM")
public class SuperMan {
}