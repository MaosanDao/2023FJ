package com.vangelis.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：基类方便打印
 * Created on 2023/3/22.
 * Comment：
 * abstract关键字：{@link com.vangelis.activity.AbstractTest}
 *
 * @author Wangpei
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        FjLogUtil.getInstance().d(setActivityName() + " onCreate");
    }

    protected abstract int setLayoutId();

    protected abstract String setActivityName();

    @Override
    protected void onResume() {
        super.onResume();
        FjLogUtil.getInstance().d(setActivityName() + " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        FjLogUtil.getInstance().d(setActivityName() + " onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FjLogUtil.getInstance().d(setActivityName() + " onStart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        FjLogUtil.getInstance().d(setActivityName() + " onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d(setActivityName() + " onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        FjLogUtil.getInstance().d(setActivityName() + " onRestart");
    }
}