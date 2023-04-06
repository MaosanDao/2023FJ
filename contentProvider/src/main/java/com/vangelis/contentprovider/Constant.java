package com.vangelis.contentprovider;

/**
 * Function：常量
 * Created on 2023/4/6.
 * Comment：/
 *
 * @author Wangpei
 */
public class Constant {

    /**
     * 主键
     */
    public static final String COLUMN_ID = "_id";

    /**
     * 字段不能为空
     */
    public static final String COLUMN_NAME = "name";

    /**
     * 表名
     */
    public static final String TABLE_NAME = "user";

    /**
     * 主机名，URI授权的部分，唯一的标识符，用来定位ContentProvider
     */
    public static final String AUTOHORITY = "com.vangelis.fjprovider";
}