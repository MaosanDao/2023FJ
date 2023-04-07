package com.vangelis.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：ContentProvider基础使用
 * Created on 2023/4/6.
 * Comment：/
 *
 * @author Wangpei
 */
public class FjContentProvider extends ContentProvider {

    FjDBHelper mDbHelper = null;
    SQLiteDatabase db = null;

    public static final int MATCHER_ITEM_CODE = 1;
    public static final int MATCHER_DIR_CODE = 2;

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
        mMather.addURI(Constant.AUTOHORITY, Constant.TABLE_NAME, MATCHER_ITEM_CODE);
        mMather.addURI(Constant.AUTOHORITY, Constant.TABLE_NAME + "/#", MATCHER_DIR_CODE);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new FjDBHelper(getContext());
        /**
         * 他们的区别：
         *      getWritableDatabase：以读写方式打开数据库，一旦数据库的磁盘空间满了，数据库就只能读而不能写，一旦磁盘满了，就会报错
         *      getReadableDatabase：以读写方式打开数据库，如果磁盘空间已满，则以只读方式打开
         */
//        db = mDbHelper.getWritableDatabase();
        db = mDbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        FjLogUtil.getInstance().d("这里执行DB的操作哦 --- query");
        /**
         * 检索的时候，根据MIME类型，进行相应的查询
         */
        switch (mMather.match(uri)) {
            case MATCHER_ITEM_CODE:
                cursor = db.query(Constant.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MATCHER_DIR_CODE:
                cursor = db.query(Constant.TABLE_NAME, projection, Constant.COLUMN_ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }

        /**
         * 观察者通知
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
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
            case MATCHER_ITEM_CODE:
                return "vnd.android.cursor.dir/" + Constant.TABLE_NAME;
            case MATCHER_DIR_CODE:
                return "vnd.android.cursor.item/" + Constant.TABLE_NAME;
            default:
                throw new IllegalArgumentException("UnKnown URI:" + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        /**
         * 先匹配这个插入的记录是否是一条记录，否则抛出异常
         */
        if (mMather.match(uri) != MATCHER_ITEM_CODE) {
            throw new IllegalArgumentException("Unknown URI:" + uri);
        }
        /**
         * 开始插入
         */
        long insert = db.insert(Constant.TABLE_NAME, null, contentValues);

        /**
         * 当ID不是初始值，则使用ContentUris进行追加记录，并返回
         */
        if (insert > 0) {
            Uri uri1 = ContentUris.withAppendedId(Constant.CONTENT_URI, insert);
            getContext().getContentResolver().notifyChange(uri1, null);
            return uri1;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return db.delete(Constant.TABLE_NAME, s, strings);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return db.update(Constant.TABLE_NAME, contentValues, s, strings);
    }
}