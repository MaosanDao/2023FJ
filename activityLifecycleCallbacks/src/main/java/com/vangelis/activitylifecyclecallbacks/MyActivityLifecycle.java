package com.vangelis.activitylifecyclecallbacks;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：/
 * Created on 2023/3/23.
 * Comment：/
 *
 * @author Wangpei
 */
public class MyActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
        /**
         * 这里使用接口的activity，将其辨别，可以使用相关的方法
         * 主要是可以将baseactivity从简，这里可以做一些公共的事情
         */
        if(activity instanceof TestAInterface){
            FjLogUtil.getInstance().d("这个activity是："+activity.getLocalClassName());
            ((TestAInterface) activity).test("测试测试测试测试测试测试测试测试测试测试");

//                    activity.getIntent() 可以使用intent进行传值
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}