package com.vangelis.activitylifecyclecallbacks;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Function：/
 * Created on 2023/3/23.
 * Comment：/
 *
 * @author Wangpei
 */
public class MyActivityTestB extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_layout);
    }
}