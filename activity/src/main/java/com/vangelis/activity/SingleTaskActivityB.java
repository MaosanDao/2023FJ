package com.vangelis.activity;

import android.content.Intent;
import android.view.View;

/**
 * Function：SingleTask启动模式
 * Created on 2023/3/22.
 * Comment：
 *  本Activity的注册文件为：
 *      <activity android:name=".SingleTaskActivityB"
 *             android:launchMode="singleTask" android:taskAffinity="task2"/>
 *
 *
 * @author Wangpei
 */
public class SingleTaskActivityB extends BaseActivity{
    @Override
    protected int setLayoutId() {
        return R.layout.single_task_b_layout;
    }

    @Override
    protected String setActivityName() {
        return "SingleTaskActivityC";
    }

    public void gotoC(View view){
        startActivity(new Intent(this,SingleTaskActivityC.class));
    }
}