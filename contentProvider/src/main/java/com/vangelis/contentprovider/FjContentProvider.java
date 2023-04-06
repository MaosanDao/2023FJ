package com.vangelis.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Function：/
 * Created on 2023/4/6.
 * Comment：/
 *
 * @author Wangpei
 */
public class FjContentProvider extends ContentProvider {

    FjDBHelper mDbHelper = null;
    SQLiteDatabase db = null;

    /**
     * UriMather用法介绍：
     * 这个就是匹配器，只有当匹配到相应的记录的时候，会返回结果
     */
    private static final UriMatcher mMather;

    static {
        mMather = new UriMatcher(UriMatcher.NO_MATCH);
        /**
         * 后面的Code就是匹配码，使用matcher的match来匹配，如果返回相应的code，则匹配成功
         *
         * 比如：第一条记录是表的数据，匹配成功则会返回1
         */
        mMather.addURI(Constant.AUTOHORITY, Constant.TABLE_NAME, 1);
        mMather.addURI(Constant.AUTOHORITY, Constant.TABLE_NAME + "/#", 2);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new FjDBHelper(getContext());
        /**
         * 他们的区别：
         *      getWritableDatabase：以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，一旦磁盘满了，就会报错
         *      getReadableDatabase：以读写方式打开数据库，如果磁盘空间已满，则以只读方式打开
         */
        db = mDbHelper.getWritableDatabase();
//        db = mDbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        /**
         * 如果操作集合，则必须以vnd.android.cursor.dir开头
         * 如果操作非集合，则必须以vnd.android.cursor.item开头
         *
         *
         */
        Uri parse = Uri.parse("content://" + Constant.AUTOHORITY + "/" + Constant.TABLE_NAME);
        switch (mMather.match(parse)) {
            case 1:
                return "vnd.android.cursor.dir/" + Constant.TABLE_NAME;
            case 2:
                return "vnd.android.cursor.item/" + Constant.TABLE_NAME;
            default:
                throw new IllegalArgumentException("UnKnown URI:"+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}