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

    public static MainFragment newInstance(String msg) {
        MainFragment mainFragment = new MainFragment();
        /**
         * 从Activity传入数据过来
         */
        Bundle bundle = new Bundle();
        bundle.putString("content", msg);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        FjLogUtil.getInstance().d("传递的数据为：" + arguments.getString("content"));
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

    public void testStartActivity() {
        /**
         * 注意：
         *  在fragment中跳转到其他activity中，且从activity中返回数据的时候，比如：
         *      Intent intent = new Intent();
         *      getActivity().setResult(Activity.RESULT_OK,intent)
         *      getActivity.finish
         *
         *      如上的代码，只能在fragment的宿主activity中获取到，fragment的获取，则需要分发一次才行。
         *      如果有多级fragment，则需要自己实现多级的onActivityResult进行手动分发
         */
//        startActivityForResult();
    }

    /**
     * 这个方法，相当于是fragment的隐藏和展示
     * <p>
     * 但是新建立的fragment则不会回调该方法
     * 因为，只有存在的fragment才会有隐藏和显示的功能
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        FjLogUtil.getInstance().d("MainFragment onHiddenChanged hidden:" + hidden);
        if (hidden) {
            //相当于Fragment的onPause()
        } else {
            // 相当于Fragment的onResume()
        }
    }

    /**
     * 我们切换fragment的时候,会调用setUserVisibleHint方法
     * 一般配合ViewPager使用
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        FjLogUtil.getInstance().d("MainFragment setUserVisibleHint isVisibleToUser:" + isVisibleToUser);
        if (isVisibleToUser) {
            // 相当于onResume()方法--获取焦点
        } else {
            // 相当于onpause()方法---失去焦点
        }
    }


}