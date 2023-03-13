package com.vangelis.annotation;

import com.vangelis.support.util.FjLogUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Function: 注解提取演示类
 * Created on 2023/3/10.
 *
 * 1.注解的提取：
 *  想要从类、方法、成员变量等获取注解的值，则需要使用反射机制。
 *
 * @author Wangpei
 */
public class LogInterfaceUtil {
    public static void init(Class clz) {
        //isAnnotationPresent：首先判断这个Class对象是否含有某个注解
        boolean isAnnotation = clz.isAnnotationPresent(LogInterface.class);
        FjLogUtil.getInstance().d("是否有注解：" + isAnnotation);
        if (isAnnotation) {
            //getAnnotation：获取这个注解
            LogInterface annotation = (LogInterface) clz.getAnnotation(LogInterface.class);
            //getAnnotations:获取这个元素上所有的注解，这里指这个类（元素）
            Annotation[] annotations = clz.getAnnotations();
            //这里直接就获取注解上的值
            String msg = annotation.logMsg();
            FjLogUtil.getInstance().d("这个是类上的注解：" + msg);
        }

        try {
            Method[] methods = clz.getDeclaredMethods();
            FjLogUtil.getInstance().d("methods：" + methods.length);
            for (Method method : methods) {
                LogInterface annotation1 = method.getAnnotation(LogInterface.class);
                if (annotation1 != null) {
                    FjLogUtil.getInstance().d("这个是方法上的注解：" + annotation1.logMsg() + ",运行时间为：" + System.currentTimeMillis());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FjLogUtil.getInstance().d("Exception：" + e);
        }

        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            LogInterface annotation = declaredField.getAnnotation(LogInterface.class);
            if(annotation!=null){
                FjLogUtil.getInstance().d("这个是成员变量上的注解：" + annotation.logMsg() + ",运行时间为：" + System.currentTimeMillis());
            }
        }
    }
}