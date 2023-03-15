package com.vangelis.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：IntentService讲解
 * Created on 2023/3/14.
 * Comment：
 *  IntentService是简化版的Service，它不在需要手动关闭，每次执行完成后，会自己关闭
 *  主要在onHandleIntent中执行相关逻辑
 *
 *  也有两种启动方式：bind和start
 *      start的生命周期：（但是如果先bind再start，则只会执行onStartCommand和onHandleIntent）
 *          构造方法
 *          onCreate
 *          onStartCommand
 *          onHandleIntent
 *          onDestroy（自己就销毁了）
 *
 *      bind的生命周期：
 *          构造方法
 *          onCreate
 *          onBind
 *          onUnbind
 *          onDestroy
 *
 * @author Wangpei
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
        FjLogUtil.getInstance().d("MyIntentService 生命周期 构造方法");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onHandleIntent");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onCreate");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onUnbind");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onBind");
        return super.onBind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        FjLogUtil.getInstance().d("MyIntentService 生命周期 onRebind");
    }
}