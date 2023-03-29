package com.vangelis.fragment;

import com.vangelis.activity.BaseFragment;


public class TwoFragment extends BaseFragment {

    @Override
    protected int setRootView() {
        return R.layout.fragment_two;
    }

    @Override
    protected String setClassName() {
        return "TwoFragment";
    }

    public static TwoFragment newInstance(){
        TwoFragment mainFragment = new TwoFragment();
        return mainFragment;
    }
}