package com.vangelis.service.foreground;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.vangelis.service.R;
import com.vangelis.service.ServiceMainActivity;
import com.vangelis.support.util.FjLogUtil;

/**
 * Function：前台服务
 * Created on 2023/3/20.
 * Comment：
 *  运用场景：最常见的是音乐播放器，可以在通知栏中暂停、播放之类的操作
 *  为啥用前台：因为后台运行的Service优先级很低，当内存不足的时候。系统会将后台Service进行回收
 *
 *
 * @author Wangpei
 */
public class MyForeGroundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FjLogUtil.getInstance().d("MyForeGroundService onStartCommand");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            /**
             * Android 8.0以上需要指定channelID，否则将不能开启
             */
            NotificationChannel foreGround_service_test1
                    = new NotificationChannel("ForeGround1", "ForeGround Service Test1", NotificationManager.IMPORTANCE_DEFAULT);
            foreGround_service_test1.setDescription("HAHAHAHA");
            notificationManager.createNotificationChannel(foreGround_service_test1);
        }

        Intent intent1 = new Intent(this, ServiceMainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification build = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setContentTitle("YOUYOUYOY")
                .setChannelId("ForeGround1")
                .setContentText("HAHAHAHA")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        startForeground(1,build);
        /**
         * 下面这个方法，是指：
         *      停止前台，但是不退出整个服务。 这个boolean表示是否取消掉前台服务的通知。false表示保留通知。
         *      此时服务变成了后台服务
         */
//        stopForeground(false);
        notificationManager.notify(1,build);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d("MyForeGroundService onDestroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 只有使用stopService，才能触发onDestory
         */
        FjLogUtil.getInstance().d("MyForeGroundService onCreate");
    }
}