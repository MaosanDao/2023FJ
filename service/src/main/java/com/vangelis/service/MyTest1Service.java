package com.vangelis.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：Service生命周期展示
 * Created on 2023/3/14.
 * Comment：
 *  1.启动service的方式有3种
 *      startService
 *      bindService
 *      同时开启上面2个
 *
 * @author Wangpei
 */
public class MyTest1Service extends Service {

    /**
     * 可以通过继承Binder和绑定的组件传输数据
     *
     * 这里的IBinder可以自定义很多个，这也是一个服务可以和多个组件进行绑定，并通信的做法
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        FjLogUtil.getInstance().d("MyTest1Service 生命周期 onBind intent:"+intent.toString());
        FjLogUtil.getInstance().d("MyTest1Service 生命周期 onBind intent:"+intent.getStringExtra("test"));
        ServiceMainActivity.MyBinder myBinder = new ServiceMainActivity.MyBinder();
        myBinder.myMsg = "这是service传递的消息";
        return myBinder;
    }

    /**
     * oncreate只是在第一次启动服务的时候调用
     */
    @Override
    public void onCreate() {
        super.onCreate();
        FjLogUtil.getInstance().d("MyTest1Service 生命周期 onCreate");
    }

    /**
     * onStartCommand这个方法，在startService首次和每一次都会触发
     *
     * @return 返回值有3种：
     * Service.START_STICKY=1：当service被kill了，系统会保留开始状态，当服务被重启，传过来的Intent可能为空
     * Service.START_NOT_STICKY=2：被kill了，系统不会重启
     * Service.START_REDELIVER_INTENT=3：系统自动重启了，并会传递之前的intent
     *
     * 默认返回为：Service.START_STICKY
     *
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FjLogUtil.getInstance().d("MyTest1Service 生命周期 onStartCommand intent:"
                +intent.toString()+",flags:"+flags+",startId:"+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        FjLogUtil.getInstance().d("MyTest1Service 生命周期 onUnbind");
        return super.onUnbind(intent);
    }

    /**
     * 如果同时被两种启动方式启动，则：
     * 只有当stopService和unBindService都被调用了，才会执行该回调
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d("MyTest1Service 生命周期 onDestroy");
    }

}