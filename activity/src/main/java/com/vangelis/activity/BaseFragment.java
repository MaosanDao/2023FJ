package com.vangelis.activity;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：/
 * Created on 2023/3/29.
 * Comment：/
 *
 * @author Wangpei
 */
public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        FjLogUtil.getInstance().d(setClassName()+" 构造方法");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FjLogUtil.getInstance().d(setClassName()+" onCreateView");
        return inflater.inflate(setRootView(), container, false);
    }

    protected abstract int setRootView();
    protected abstract String setClassName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FjLogUtil.getInstance().d(setClassName()+" onAttach");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FjLogUtil.getInstance().d(setClassName()+" onViewCreated");
    }

    @Override
    public void onResume() {
        super.onResume();
        FjLogUtil.getInstance().d(setClassName()+" onResume");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FjLogUtil.getInstance().d(setClassName()+" onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        FjLogUtil.getInstance().d(setClassName()+" onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        FjLogUtil.getInstance().d(setClassName()+" onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        FjLogUtil.getInstance().d(setClassName()+" onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FjLogUtil.getInstance().d(setClassName()+" onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d(setClassName()+" onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        FjLogUtil.getInstance().d(setClassName()+" onDetach");
    }
}