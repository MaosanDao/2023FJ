package com.vangelis.otherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vangelis.fj2023.IProcessInfo;
import com.vangelis.fj2023.User;
import com.vangelis.support.util.FjLogUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBindAppRemoteService;
    private Button mUnBindAppRemoteService;
    private ServiceConnection mRemoteServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBindAppRemoteService = findViewById(R.id.bindAppRemoteService);
        mUnBindAppRemoteService = findViewById(R.id.unBindAppRemoteService);
        mBindAppRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 这里需要设置远程Service的Action，方便找到
                 */
                Intent intent = new Intent();
                intent.setAction("com.vangelis.app.service.fj2023");
                intent.setPackage("com.vangelis.fj2023");
                bindService(intent,mRemoteServiceConnection,BIND_AUTO_CREATE);
            }
        });

        mUnBindAppRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(mRemoteServiceConnection);
            }
        });

        mRemoteServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                /**
                 * 转换IBinder使用asInterface这个方法
                 */
                IProcessInfo iProcessInfo = IProcessInfo.Stub.asInterface(iBinder);
                try {
                    FjLogUtil.getInstance().d("otherApp，我从app获取的消息为："+iProcessInfo.getProcessMsg());

                    List<User> result = iProcessInfo.getUsers();
                    for (User user : result) {
                        FjLogUtil.getInstance().d("otherApp，我从app获取的消息为："+user.toString());
                    }
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }
}