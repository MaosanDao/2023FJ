package com.vangelis.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vangelis.activity.BaseFragment;
import com.vangelis.support.util.FjLogUtil;


public class MainFragment extends BaseFragment {

    private Context mContext;

    @Override
    protected int setRootView() {
        return R.layout.fragment_main;
    }

    @Override
    protected String setClassName() {
        return "MainFragment";
    }

    public static MainFragment newInstance(String msg){
        MainFragment mainFragment = new MainFragment();
        /**
         * 从Activity传入数据过来
         */
        Bundle bundle = new Bundle();
        bundle.putString("content",msg);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        FjLogUtil.getInstance().d("传递的数据为："+arguments.getString("content"));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /**
         * 这里来持有context对象，如果getActivity()，可能有以下问题：
         *      1.activity宿主被销毁了，fragment仍然引用，可能报空。
         *      2.内存泄露问题。
         */
        this.mContext = context;
    }
}