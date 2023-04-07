package com.vangelis.contentprovider;

import android.annotation.SuppressLint;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vangelis.support.util.FjLogUtil;

public class ContentProviderMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_main);

        getContentResolver().registerContentObserver(Constant.CONTENT_URI, true
                , new FjContentObserver(new Handler() {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what) {
                            case 1:
                                FjLogUtil.getInstance().d(msg.obj.toString());
                                break;
                            default:
                        }
                    }
                }));
    }

    /**
     * ContentObserver(内容观察者)，目的是观察特定Uri引起的数据库的变化，继而做一些相应的处理，
     * 它类似于数据库技术中的触发器(Trigger)，当ContentObserver所观察的Uri发生变化时，便会触发它.
     */
    private class FjContentObserver extends ContentObserver {

        private Handler mHandler;

        public FjContentObserver(Handler handler) {
            super(handler);
            mHandler = handler;
        }

        @SuppressLint("Range")
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            Cursor query = getContentResolver().query(Constant.CONTENT_URI, null, null, null, null);
            StringBuilder stringBuilder = new StringBuilder();
            while (query.moveToNext()) {
                stringBuilder.append("name=").append(query.getString(query.getColumnIndex("name")));
                FjLogUtil.getInstance().d("数据库发生了变化：" + stringBuilder);
                mHandler.obtainMessage(1, "发生了变化：" + stringBuilder);
            }
        }
    }
}