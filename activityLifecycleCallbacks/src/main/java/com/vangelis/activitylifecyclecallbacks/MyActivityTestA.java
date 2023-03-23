package com.vangelis.activitylifecyclecallbacks;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：/
 * Created on 2023/3/23.
 * Comment：/
 *
 * @author Wangpei
 */
public class MyActivityTestA extends Activity implements TestAInterface{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_layout);
    }

    @Override
    public void test(String msg) {
        FjLogUtil.getInstance().d("MyActivityTestA TestAInterface test:"+msg);
    }
}