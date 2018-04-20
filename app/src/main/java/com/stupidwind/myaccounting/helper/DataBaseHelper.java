package com.stupidwind.myaccounting.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 蠢风 on 2018/4/5.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context mContext = null;

    private static final String name = "accounting"; // 数据库名称

    private static final int version = 1; // 数据库版本

    private static final String CREATE_ACCOUNT = "";

    /**
     * 创建记账明细表
     */
    private static final String CREATE_ACCOUNTING_LOG = "CREATE TABLE accounting_log (" +
            "accounting_log_id integer primary key autoincrement, " +
            "user_id integer, " +
            "account_id integer, " +
            "accounting_type varchar(10), " +
            "accounting_event_id integer, " +
            "accounting_value integer, " +
            "accounting_time datetime, " +
            "remark varchar(100) )";

    private static final String CREATE_ACOUNTING_TYPE = "";

    public DataBaseHelper(Context context) {
        super(context, name, null, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNTING_LOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
