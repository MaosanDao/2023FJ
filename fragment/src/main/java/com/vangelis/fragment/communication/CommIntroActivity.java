package com.vangelis.fragment.communication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;

import com.vangelis.activity.BaseActivity;
import com.vangelis.fragment.R;
import com.vangelis.support.util.FjLogUtil;

/**
 * Function：Fragment和Activity通信的演示
 * Created on 2023/4/3.
 * Comment：
 * 通信方式：
 * 1.Handler
 * 2.广播（or EventBus)
 * 3.接口回调
 * 4.读写文件
 * Fragment和Fragment的通信：
 * 通过setTargetFragment和getTargetFragment进行通信
 *
 * @author Wangpei
 */
public class CommIntroActivity extends BaseActivity implements View.OnClickListener, CommInfoFragment.CommInterface {

    private Handler mCommunicationHandler;
    public static final int COMM_DATA = 1;
    public boolean checkFrag;
    public CommIntroFragmentTwo mCommIntroFragmentTwo;
    public CommInfoFragment mCommInfoFragment;

    public Handler getCommunicationHandler() {
        return mCommunicationHandler;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_comm_intro;
    }

    @Override
    protected String setActivityName() {
        return "CommIntroActivity";
    }

    @Override
    public void init() {
        super.init();

        mCommInfoFragment = new CommInfoFragment();
        mCommIntroFragmentTwo = new CommIntroFragmentTwo();

        /**
         * 通信方式一：
         *  Handler
         */
        mCommunicationHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                switch (message.what) {
                    case COMM_DATA:
                        FjLogUtil.getInstance().d("Activity收到了数据：" + message.obj);
                        break;
                    default:
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        if (view.getId() == R.id.show) {
            fragmentTransaction.add(mCommInfoFragment, "CommInfoFragment");
        } else if (view.getId() == R.id.interfaceTrigger) {
            if (checkFrag) {
                checkFrag = false;
                fragmentTransaction.replace(R.id.frag_content, mCommInfoFragment);
            } else {
                checkFrag = true;
                fragmentTransaction.replace(R.id.frag_content, mCommIntroFragmentTwo);
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public void showMessage(String msg) {
        FjLogUtil.getInstance().d("我是Activity，我收到了回调接口传入的信息：" + msg);
    }
}