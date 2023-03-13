# 注解的使用方式和自定义注解

## 注解的含义

```java
想像代码具有生命，注解就是对于代码中某些鲜活个体的贴上去的一张标签。简化来讲，注解如同一张标签。
```

## 注解的作用

> 注解是一系列元数据，它提供数据用来解释程序代码，但是注解并非是所解释的代码本身的一部分。注解对于代码的运行效果没有直接影响。 注解有许多用处，主要如下：

- 提供信息给编译器： 编译器可以利用注解来探测错误和警告信息
- 编译阶段时的处理： 软件工具可以用来利用注解信息来生成代码、Html文档或者做其它相应处理。
- 运行时的处理： 某些注解可以在程序运行的时候接受代码的提取 值得注意的是，注解不是代码本身的一部分。

## 注解的使用方式

```java
在interface前面加一个@符号。
在注解中只有成员变量，没有方法。
```

## 自定义注解

### 元注解

```java
给注解表明属性的注解。
@Retention（标明注解的保留期）:SOURCE（源码阶段）、CLASS（编译阶段）、RUNTIME（运行阶段）
@Target（标明注解的使用范围）:
    ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
    ElementType.CONSTRUCTOR 可以给构造方法进行注解
    ElementType.FIELD 可以给属性进行注解
    ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
    ElementType.METHOD 可以给方法进行注解
    ElementType.PACKAGE 可以给一个包进行注解
    ElementType.PARAMETER 可以给一个方法内的参数进行注解
    ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
@Inherited（标明被该注解被使用时，假如被注解的类有继承关系，则注解向下一直继承）
```

## 参考文献
* [Android基础系列篇（一）：注解的那些事儿](https://zhuanlan.zhihu.com/p/575272303 )

