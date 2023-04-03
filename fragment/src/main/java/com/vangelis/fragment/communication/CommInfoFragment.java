package com.vangelis.fragment.communication;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.View;

import com.vangelis.activity.BaseFragment;
import com.vangelis.fragment.R;
import com.vangelis.support.util.FjLogUtil;


/**
 * Function：Fragment和Activity通信的演示
 * Created on 2023/4/3.
 * Comment：/
 *
 * @author Wangpei
 */
public class CommInfoFragment extends BaseFragment implements View.OnClickListener {

    public static CommInterface mCommInterface;
    public CommIntroFragmentTwo mCommIntroFragmentTwo;

    public static final int FRG_FRG_REQ_CODE = 111;

    @Override
    protected int setRootView() {
        return R.layout.fragment_comm_intro;
    }

    @Override
    protected String setClassName() {
        return "CommInfoFragment";
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /**
         * 通信方式一：
         *  Handler
         */
        if(context instanceof CommIntroActivity){
            CommIntroActivity activity = (CommIntroActivity) getActivity();
            Message message = Message.obtain();
            message.what = CommIntroActivity.COMM_DATA;
            message.obj = "我发消息给你了！！俺们是CommInfoFragment";
            activity.getCommunicationHandler().dispatchMessage(message);
        }

        /**
         * 通信方式二：
         *  接口回调
         */
        if(context instanceof CommIntroActivity){
            mCommInterface = (CommInterface) context;
            mCommInterface.showMessage("我是CommInfoFragment，我通过回调接口传入信息");
        }

        /**
         * Fragment和Fragment的通信方式
         */
        if(context instanceof CommIntroActivity) {
            mCommIntroFragmentTwo = ((CommIntroActivity) context).mCommIntroFragmentTwo;
            mCommIntroFragmentTwo.setTargetFragment(this, FRG_FRG_REQ_CODE);
        }

    }

    public static interface CommInterface{
        void showMessage(String msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * Fragment和Fragment的通信方式
         */
        if(resultCode != RESULT_OK){
            return;
        }else{
            String test = data.getStringExtra("TEST");
            FjLogUtil.getInstance().d("这里收到了Fragment Two的信息："+test);
        }
    }
}