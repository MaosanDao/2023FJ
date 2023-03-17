package com.vangelis.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.vangelis.service.aidl.MyRemoteService;
import com.vangelis.service.service.MyIntentService;
import com.vangelis.service.service.MyTest1Service;
import com.vangelis.support.util.FjLogUtil;


/**
 * service的讲解：
 * 1.特征点：
 * service不是一个单独的进程（除非在注册文件中指定进程名字），否则它所在的进程就是应用的进程。
 * 2.service的停止
 * 除非service手动调用stopService和内部主动调起stopSelf，否则service一直运行。
 * 3.bindService
 * bindService的生命周期和其绑定的组件相关联。
 */
public class ServiceMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartService;
    private Button mStopService;
    private Button mBindService;
    private Button mUnBindService;
    private Button mStartIntentService;
    private Button mBindIntentService;
    private Button mBindRemoteService;

    private ServiceConnection mServiceConnection;
    private ServiceConnection mRemoteServiceConnection;

    /**
     * 这个继承纸Binder，可以在service中的OnBind回调中传输数据给绑定组件
     *
     * bind的时候使用intent传过去，传回来用这个binder
     */
    public static class MyBinder extends Binder{
        public String myMsg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_main);

        mStartService = findViewById(R.id.startService);
        mStopService = findViewById(R.id.stopService);
        mBindService = findViewById(R.id.bindService);
        mUnBindService = findViewById(R.id.unBindService);
        mStartIntentService = findViewById(R.id.startIntentService);
        mBindIntentService = findViewById(R.id.bindIntentService);
        mBindRemoteService = findViewById(R.id.bindRemoteService);

        mStartService.setOnClickListener(this);
        mStopService.setOnClickListener(this);
        mBindService.setOnClickListener(this);
        mUnBindService.setOnClickListener(this);
        mStartIntentService.setOnClickListener(this);
        mBindIntentService.setOnClickListener(this);
        mBindRemoteService.setOnClickListener(this);

        mServiceConnection = new ServiceConnection(){

            /**
             *  和服务绑定成功后，服务会回调该方法
             *  服务异常中断后重启，也会重新调用改方法
             */
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                FjLogUtil.getInstance().d("onServiceConnected componentName:"+componentName.toString()+",iBinder:"+iBinder.toString());
                MyBinder myBinder = (MyBinder) iBinder;
                FjLogUtil.getInstance().d("onServiceConnected myBinder msg:"+myBinder.myMsg);
            }

            /**
             * 当服务异常终止时会调用。
             * 注意，unbindService时不会调用
             */
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                FjLogUtil.getInstance().d("onServiceDisconnected componentName:"+componentName);
            }
        };

        mRemoteServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                ServiceIProcessInfo processInfo = ServiceIProcessInfo.Stub.asInterface(iBinder);
                try {
                    FjLogUtil.getInstance().d("mRemoteServiceConnection myBinder msg:"+processInfo.getProcessMsg());
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                FjLogUtil.getInstance().d("mRemoteServiceConnection onServiceDisconnected");
            }
        };
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.startService) {
            startService(new Intent(this, MyTest1Service.class));
        } else if (id == R.id.stopService) {
            //一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁
            stopService(new Intent(this, MyTest1Service.class));
        } else if (id == R.id.bindService) {
            Intent intent = new Intent(this, MyTest1Service.class);
            intent.putExtra("test","传入到service中的数据");
            bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
        }else if(id == R.id.unBindService){
            /**
             * 一个Service必须要在既没有和任何Activity关联又处理停止状态的时候才会被销毁
             * 注意当此时如果没有service被bind的话，则会报错
             */
            unbindService(mServiceConnection);
        }else if(id == R.id.startIntentService){
            startService(new Intent(this, MyIntentService.class));
        }else if(id == R.id.bindIntentService){
            Intent intent = new Intent(this, MyIntentService.class);
            bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
        }else if(id == R.id.bindRemoteService){
            bindService(new Intent(this, MyRemoteService.class),mRemoteServiceConnection,BIND_AUTO_CREATE);
        }
    }
}