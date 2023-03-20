package com.vangelis.service.keepalive;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：1像素保活的Service做法
 * Created on 2023/3/20.
 * Comment：
 *  为啥有Service的做法：
 *      因为Service占用的内存比较小，同时能提高本应用的优先级
 *
 * @author Wangpei
 */
public class OnePixelService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void toLiveService(Context pContext) {
        Intent intent = new Intent(pContext, OnePixelService.class);
        pContext.startService(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FjLogUtil.getInstance().d("OnePixelService onStartCommand");

        final ScreenManager screenManager = ScreenManager.getInstance(OnePixelService.this);
        ScreenBroadcastListener listener = new ScreenBroadcastListener(this);
        listener.registerListener(new ScreenBroadcastListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                FjLogUtil.getInstance().d("OnePixelService onScreenOn");
                screenManager.finishActivity();
            }

            @Override
            public void onScreenOff() {
                FjLogUtil.getInstance().d("OnePixelService onScreenOff");
                screenManager.startActivity();
            }
        });
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d("OnePixelService onDestroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FjLogUtil.getInstance().d("OnePixelService onCreate");
    }
}