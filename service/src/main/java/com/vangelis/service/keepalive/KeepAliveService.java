package com.vangelis.service.keepalive;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.vangelis.service.R;
import com.vangelis.support.util.FjLogUtil;

/**
 * Function：使用前台服务进行保活
 * Created on 2023/3/21.
 * Comment：
 * 具体做法：
 *  两个服务同时启动前台服务，取消前一个，通知里面会不显示，但是会保持服务
 *      ！！！经过测试已经无效！！！
 *
 * @author Wangpei
 */
public class KeepAliveService extends Service {

    public static final int NOTIFICATION_ID = 0x11;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FjLogUtil.getInstance().d("KeepAliveService onCreate");
        //API 18以下，直接发送Notification并将其置为前台
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startForeground(NOTIFICATION_ID, new Notification());
        } else {
            //API 18以上，发送Notification并将其置为前台后，启动InnerService
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(NOTIFICATION_ID, builder.build());
            FjLogUtil.getInstance().d("KeepAliveService 开始启动InnerService");
            startService(new Intent(this, InnerService.class));
        }
    }

    public static class InnerService extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            FjLogUtil.getInstance().d("KeepAliveService InnerService onCreate");
            //发送与KeepLiveService中ID相同的Notification，然后将其取消并取消自己的前台显示
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            startForeground(NOTIFICATION_ID, builder.build());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FjLogUtil.getInstance().d("KeepAliveService InnerService stopForeground");
                    stopForeground(true);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.cancel(NOTIFICATION_ID);
                    stopSelf();
                }
            }, 100);

        }
    }
}