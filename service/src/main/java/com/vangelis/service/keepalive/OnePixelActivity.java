package com.vangelis.service.keepalive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vangelis.service.R;
import com.vangelis.support.util.FjLogUtil;

/**
 * Function：1像素保活方案
 * Created on 2023/3/20.
 * Comment：
 *  方案：
 *      1.在锁屏的时候启动这个页面
 *      2.在开屏的时候关闭
 *      3.监听系统锁屏广播
 *
 *  相关知识点：
 *      adj顺序
 *
 * @author Wangpei
 */
public class OnePixelActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_pixel_layout);

//        Window window = getWindow();
//        //放在左上角
//        window.setGravity(Gravity.START | Gravity.TOP);
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        //宽高设计为1个像素
//        attributes.width = 1;
//        attributes.height = 1;
//        //起始坐标
//        attributes.x = 0;
//        attributes.y = 0;
//        window.setAttributes(attributes);
//
//        ScreenManager.getInstance(this).setActivity(this);


        final ScreenManager screenManager = ScreenManager.getInstance(OnePixelActivity.this);
        ScreenBroadcastListener listener = new ScreenBroadcastListener(this);
        listener.registerListener(new ScreenBroadcastListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                FjLogUtil.getInstance().d("1像素保活 屏幕开启-->结束OnePixelActivity");
                screenManager.finishActivity();
            }

            @Override
            public void onScreenOff() {
                FjLogUtil.getInstance().d("1像素保活 屏幕关闭-->开启OnePixelActivity");
                screenManager.startActivity();
            }
        });
    }

    /**
     * 启动
     */
    public static void actionToLiveActivity(Context pContext) {
        Intent intent = new Intent(pContext, OnePixelActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pContext.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        FjLogUtil.getInstance().d("1像素保活 onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        FjLogUtil.getInstance().d("1像素保活 onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d("1像素保活 onDestroy");
    }
}