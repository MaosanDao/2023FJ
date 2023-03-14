package com.vangelis.fj2023;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.vangelis.annotation.AnnotationMainActivity;
import com.vangelis.service.ServiceMainActivity;

/**
 * Function: #todo
 * Created on 2023/3/10.
 *
 * @author Wangpei
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button mAnnotation;
    private Button mService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mAnnotation = findViewById(R.id.button_annotation);
        mService = findViewById(R.id.button_service);
        mAnnotation.setOnClickListener(this);
        mService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_annotation:
                startActivity(new Intent(this, AnnotationMainActivity.class));
                break;
            case R.id.button_service:
                startActivity(new Intent(this, ServiceMainActivity.class));
                break;
            default:
        }
    }
}