package com.vangelis.service.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：AIDL的远程服务
 * Created on 2023/3/15.
 * Comment：
 *  1.该服务定义在另外的一个APP A内部，且需要在注册文件中标明这个service的action
 *  2.从APP B可以使用intent的setAction指定service，并将Intent的内容传递过来，交给这个service进行处理
 *  3.处理完成后使用AIDL的机制，将这个实例的IBinder返回出去即可
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