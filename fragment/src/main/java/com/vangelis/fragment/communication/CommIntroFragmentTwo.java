package com.vangelis.fragment.communication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.vangelis.activity.BaseFragment;
import com.vangelis.fragment.R;

/**
 * Function：/
 * Created on 2023/4/3.
 * Comment：/
 *
 * @author Wangpei
 */
public class CommIntroFragmentTwo extends BaseFragment {
    @Override
    protected int setRootView() {
        return R.layout.fragment_comm_intro_two;
    }

    @Override
    protected String setClassName() {
        return "CommIntroFragmentTwo";
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /**
         * Fragment和Fragment的通信方式
         */
        if(getTargetFragment() != null){
            Intent intent = new Intent();
            intent.putExtra("TEST","俺们是Fragment Two,Frag 和 Frag的通信哦");
            getTargetFragment().onActivityResult(CommInfoFragment.FRG_FRG_REQ_CODE, Activity.RESULT_OK,intent);
        }
    }
}