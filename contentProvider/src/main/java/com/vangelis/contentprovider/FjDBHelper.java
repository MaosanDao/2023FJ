package com.vangelis.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Function：DBHelper
 * Created on 2023/4/6.
 * Comment：/
 *
 * @author Wangpei
 */
public class FjDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fjtest.db";
    private static final int DATABASE_VERSION = 1;

    public FjDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /**
         * 创建表，并指定主键，和不为空的字段名
         */
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_NAME
                + "(" + Constant.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Constant.COLUMN_NAME + " VARCHAR NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /**
         * 更新数据库的操作，先删除表，然后创建
         */
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Constant.TABLE_NAME+";");
        onCreate(sqLiteDatabase);
    }
}