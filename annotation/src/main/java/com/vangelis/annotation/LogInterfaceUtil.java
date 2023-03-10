package com.vangelis.annotation;

import android.util.Log;

import com.vangelis.support.util.FjLogUtil;

import java.lang.reflect.Method;

/**
 * Function: 注解工具类
 * Created on 2023/3/10.
 *
 * @author Wangpei
 */
public class LogInterfaceUtil {
    public static void init(Class clz) {
        boolean isAnnotation = clz.isAnnotationPresent(LogInterface.class);
        FjLogUtil.getInstance().d("是否有注解：" + isAnnotation);
        if (isAnnotation) {
            LogInterface annotation = (LogInterface) clz.getAnnotation(LogInterface.class);
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
    }
}