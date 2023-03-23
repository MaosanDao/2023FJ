# Activity

## 启动方式

### standard

```java
默认的启动方式，每次都会创建一个新的实例，进入栈的时候在栈顶
```

### singleTop

```java
如果在启动的时候，该activity在栈顶，则复用，否则直接创建一个新的实例，并调用onNewIntent
```

### singleTask

```java
几种跳转方式：
    A-B => default - singleTask
        taskAffinity
            如果相同，则直接在A的栈中，将B放在栈顶
            如果不同，则B会新开一个栈，且放在栈顶
   A-B-C => default - singleTask - singleTask
        针对于B和C的taskAffinity：
            如果相同，那么当B跳转到C的时候，C会在B的栈的栈顶，否则会新开一个栈
            此时，如果BC是相同taskAffinity的话，那么再加一级：
            A-B-C-B
                最后一级，C回到B的时候，C会被B从栈顶挤掉，因为系统会给B赋予一个CLEAR_TOP的标志（因为B在之前就有这个实例了，要保证这个实例只有一个在栈内）
```

### singleInstance

```java
启动的时候，每次都会创建一个新的实例，且会开启一个新的栈。必定开启。

扩展知识：
    为什么当service跳转到activity的时候，会报错：Calling startActivity() from outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag.Is this really what you want?
    因为，虽然service是context的扩展，也可以在service内部使用startactivity进行跳转，但是其实service并没有
    所存在的task栈，所以，系统并不知道新跳转的这个activity放入哪个栈中，所以就会报错这个。
        当然，解决办法就是：在intent的flag中新增：FLAG_ACTIVITY_NEW_TASK，意味着，这是个新的栈，放入即可。
```

## 参考文档

[[译] Android 开发中 Activity 的正确打开方式](https://juejin.cn/post/6844903442066964494)

