package com.vangelis.annotation;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Function：注解的使用演示
 * Created on 2023/3/13.
 * Comment：下面演示了注解的使用
 *
 * @author Wangpei
 */
@LogInterface(logMsg = "这个是类")
public class AnnotationMainActivity extends Activity {

    @LogInterface(logMsg = "这个是成员变量")
    private String test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogInterfaceUtil.init(AnnotationMainActivity.class);
        setContentView(R.layout.annotation_main_layout);
    }

    @LogInterface(logMsg = "这个是方法")
    public void test(){

    }
}