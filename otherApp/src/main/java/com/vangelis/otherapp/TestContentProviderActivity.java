package com.vangelis.otherapp;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.vangelis.support.util.FjLogUtil;
import com.vangelis.support.util.RandomUtil;

public class TestContentProviderActivity extends AppCompatActivity implements View.OnClickListener {

    public static final Uri USER_CONTENT_PROVIDER_URI = Uri.parse("content://" + "com.vangelis.fjprovider" + "/user");
    private static final String TAG = "使用Content Provider";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_provider);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.insert:
                insertUser();
                break;
            case R.id.delete:
                deleteUser();
                break;
            case R.id.query:
                queryUser();
                break;
            case R.id.update:
                updateUser();
                break;
            default:
        }
    }

    private void updateUser() {
        String selectionClause = "name=?";//过滤字段
        String[] args = {"946322139"};//传递的具体参数
        ContentValues c =new ContentValues();
        c.put("name","111111111");
        getContentResolver().update(USER_CONTENT_PROVIDER_URI,c,selectionClause,args);
    }

    private void deleteUser() {
        String selectionClause = "name=?";//过滤字段
        String[] args = {"196568455"};//传递的具体参数
        int delete = getContentResolver().delete(USER_CONTENT_PROVIDER_URI,
                selectionClause,
                args);
        FjLogUtil.getInstance().d(TAG+",删除了:"+delete);
    }

    @SuppressLint("Range")
    private void queryUser() {
        Cursor query = getContentResolver().query(USER_CONTENT_PROVIDER_URI, null, null, null, null);
        while (query.moveToNext()){
            FjLogUtil.getInstance().d(TAG+",从数据库获取的数据为："+query.getString(query.getColumnIndex("name")));
        }
    }

    private void insertUser(){
        String name = RandomUtil.getNumRandomString(9);
        FjLogUtil.getInstance().d(TAG+",开始插入测试数据：Name："+name);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        Uri insert = getContentResolver().insert(
                USER_CONTENT_PROVIDER_URI//插入的表
                , contentValues);//数据
        getContentResolver().notifyChange(insert,null);
        long id = ContentUris.parseId(insert);
        if(id>0){
            FjLogUtil.getInstance().d(TAG+",插入成功");
        }
    }
}