package com.vangelis.activitylifecyclecallbacks;

import android.app.Application;

/**
 * Function：/
 * Created on 2023/3/23.
 * Comment：/
 *
 * @author Wangpei
 */
public class MyBaseActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new MyActivityLifecycle());
    }
}