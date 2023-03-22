package com.vangelis.activity;

import android.content.Intent;
import android.view.View;

/**
 * Function：SingleTask启动模式
 * Created on 2023/3/22.
 * Comment：
 *  本Activity的注册文件为：
 *      <activity android:name=".SingleTaskActivityC"
 *             android:launchMode="singleTask" android:taskAffinity="task2"/>
 *
 *
 * @author Wangpei
 */
public class SingleTaskActivityC extends BaseActivity{
    @Override
    protected int setLayoutId() {
        return R.layout.single_task_c_layout;
    }

    @Override
    protected String setActivityName() {
        return "SingleTaskActivityC";
    }

    public void gotoB(View view){
        startActivity(new Intent(this,SingleTaskActivityB.class));
    }
}