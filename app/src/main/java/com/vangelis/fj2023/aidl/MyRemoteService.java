package com.vangelis.fj2023.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;


/**
 * Function：/
 * Created on 2023/3/15.
 * Comment：/
 *
 * @author Wangpei
 */
public class MyRemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IProcessInfoImpl iProcessInfo = new IProcessInfoImpl();
        FjLogUtil.getInstance().d("MyRemoteService onBind");
        return iProcessInfo;
    }
}