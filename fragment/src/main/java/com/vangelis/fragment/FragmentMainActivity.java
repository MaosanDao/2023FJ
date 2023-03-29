package com.vangelis.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

import com.vangelis.activity.BaseActivity;
import com.vangelis.support.util.FjLogUtil;

/**
 * Function：Fragment大体使用
 * Created on 2023/3/29.
 * Comment：
 *      1.add 和 replace的区别
 *          add 不会清空之前的fragment的，但是会检测是否已经有这个fragment了
 *          replace 则会清空之前的fragment，且替换为最新的。
 *      2.其实Fragment就是一个高级的View，这个view在Activity中使用，也可以互相嵌套
 *
 * @author Wangpei
 */
public class FragmentMainActivity extends BaseActivity implements View.OnClickListener {

    private MainFragment mMainFragment;
    private TwoFragment mTwoFragment;

    private boolean isSwitch = false;
    private boolean isAddFragment = false;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_main_layout;
    }

    @Override
    protected String setActivityName() {

        return "FragmentMainActivity";
    }

    @Override
    public void init() {
        super.init();
        FjLogUtil.getInstance().d("FragmentMainActivity setName");
        mMainFragment = MainFragment.newInstance("数据哦");
        mTwoFragment = TwoFragment.newInstance();
    }

    @Override
    public void onClick(View view) {//动态方式加入fragment到activity中
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /**
         * Fragment 的动态添加、删除等操作都需要借助于 FragmentTransaction 类来完成：
         *      add() 系列：添加 Fragment 到 Activity 界面中；
         *      remove()：移除 Activity 中的指定 Fragment；
         *      replace() 系列：通过内部调用 remove() 和 add() 完成 Fragment 的修改；（fragment被replace的时候，不会保存view的状态，而hide和show则可以）
         *      hide() 和 show()：隐藏和显示 Activity 中的 Fragment；
         *      commit()：提交事务，所有通过上述方法对 Fragment 的改动都必须通过调用 commit() 方法完成提交；
         *
         */
        if (view.getId() == R.id.add_fragment) {
            if (!isAddFragment) {
                isAddFragment = true;
                fragmentTransaction.add(R.id.fragment_layout, mMainFragment);
            } else {
                toastMsg("已经添加了Fragment，不能再添加了");
            }
        } else if (view.getId() == R.id.hide_fragment) {
            fragmentTransaction.hide(mMainFragment);
            fragmentTransaction.hide(mTwoFragment);
        } else if (view.getId() == R.id.show_fragment) {
            fragmentTransaction.show(mMainFragment);
        } else if (view.getId() == R.id.remove_fragment) {
            fragmentTransaction.remove(mMainFragment);
            fragmentTransaction.remove(mTwoFragment);
            isAddFragment = false;
        } else if (view.getId() == R.id.replce_fragment) {
            if (isSwitch) {
                isSwitch = false;
                fragmentTransaction.replace(R.id.fragment_layout, mMainFragment);
                /**
                 * 当切换到目标fragment的时候，需要将跳转之前的fragment退回到栈中，否则系统可能会移除或者替换fragment
                 * 比喻：
                 *      Fragment A -> B -> C ,如果都入退回栈，则当用户返回的时候，会是C -> B -> A -> 退出当前界面
                 *                          如果没有入退回栈，则直接从C -> 退出当前界面
                 */
//                fragmentTransaction.addToBackStack(null);
            } else {
                isSwitch = true;
                fragmentTransaction.replace(R.id.fragment_layout, mTwoFragment);
//                fragmentTransaction.addToBackStack(null);
            }
        }

        fragmentTransaction.commit();
    }
}