package com.vangelis.activity;

/**
 * Function：SingleTop
 * Created on 2023/3/22.
 * Comment：
 * 具体含义：
 * 1.如果当前栈中没有该实例，则直接新建(不管有没有在栈内部)
 * 2.如果该实例在当前的栈顶，则直接复用，且调用onNewIntent
 *
 * @author Wangpei
 */
public class SingleTopActivity extends BaseActivity {

    @Override
    protected int setLayoutId() {
        return R.layout.single_top_layout;
    }

    @Override
    protected String setActivityName() {
        return "SingleTopActivity";
    }
}