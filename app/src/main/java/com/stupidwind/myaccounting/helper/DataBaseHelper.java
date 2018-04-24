package com.stupidwind.myaccounting.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.stupidwind.myaccounting.constant.AccountConstant;

/**
 * Created by 蠢风 on 2018/4/5.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "database";

    private static final String DB_NAME = "accounting.db"; // 数据库名称

    private static final int version = 3; // 数据库版本

    /**
     * 创建记账明细表
     */
    private static final String CREATE_ACCOUNT_LOG = "create table account_log (" +
            "_id INTEGER PRIMARY KEY NOT NULL, " +
            "account_id INTEGER, " +
            "account_type INTEGER NOT NULL, " +
            "account_event_id INTEGER, " +
            "account_value DECIMAL, " +
            "account_time varchar(15), " +
            "remark varchar(100) )";

    /**
     * 创建记账事件表
     */
    private static final String CREATE_ACOUNT_EVENT = "create table account_event (" +
            "_id INTEGER NOT NULL, " +
            "account_event_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "account_event_name text," +
            "account_type text NOT NULL," +
            "image_id INTEGER )";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        dropTable(db);

        db.execSQL(CREATE_ACCOUNT_LOG);
        db.execSQL(CREATE_ACOUNT_EVENT);

        // 收入类型
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "工资", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "还款", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "股票", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "基金", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "分红", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "利息", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "兼职", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "奖金", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "租金", "income"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "报销款", "income"});

        // 支出类型
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "早餐", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "午餐", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "晚餐", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "生活用品", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "工作用品", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "衣服", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "零食", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "电子产品", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "出行", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "租金", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "应酬", "output"});
        db.execSQL("insert into account_event(_id, account_event_name, account_type) values(?, ?, ?);", new String[]{String.valueOf(100), "旅游", "output"});

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: ");
        onCreate(db);
    }

    private void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS account_log;");
        db.execSQL("DROP TABLE IF EXISTS account_event;");
    }

}
