package com.vangelis.activity;

import android.content.Intent;
import android.view.View;

/**
 * Function：SingleTask启动模式
 * Created on 2023/3/22.
 * Comment：
 *  本Activity的注册文件为：
 *      <activity android:name=".SingleTaskActivityA"
 *             android:launchMode="standard"/>
 *
 *  几种跳转方式：
 *      A-B => default - singleTask
 *          taskAffinity
 *              如果相同，则直接在A的栈中，将B放在栈顶
 *              如果不同，则B会新开一个栈，且放在栈顶
 *     A-B-C => default - singleTask - singleTask
 *          针对于B和C的taskAffinity：
 *              如果相同，那么当B跳转到C的时候，C会在B的栈的栈顶，否则会新开一个栈
 *              此时，如果BC是相同taskAffinity的话，那么再加一级：
 *              A-B-C-B
 *                  最后一级，C回到B的时候，C会被B从栈顶挤掉，因为系统会给B赋予一个CLEAR_TOP的标志（因为B在之前就有这个实例了，要保证这个实例只有一个在栈内）
 *
 * @author Wangpei
 */
public class SingleTaskActivityA extends BaseActivity{
    @Override
    protected int setLayoutId() {
        return R.layout.single_task_a_layout;
    }

    @Override
    protected String setActivityName() {
        return "SingleTaskActivityA";
    }

    public void gotoB(View view){
        startActivity(new Intent(this,SingleTaskActivityB.class));
    }
}