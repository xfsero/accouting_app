package com.stupidwind.myaccounting.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.stupidwind.myaccounting.constant.ResultCode;
import com.stupidwind.myaccounting.helper.DataBaseHelper;
import com.stupidwind.myaccounting.model.AccountingLog;

/**
 * Created by 蠢风 on 2018/4/20.
 */

public class AccountingLogDao {

    private DataBaseHelper helper;

    private SQLiteDatabase db;

    private static final String CREATE_ACCOUNTING_LOG = "CREATE TABLE accounting_log (" +
            "accounting_log_id integer primary key autoincrement, " +
            "user_id integer, " +
            "account_id integer, " +
            "accounting_type varchar(10), " +
            "accounting_event_id integer, " +
            "accounting_value integer, " +
            "accounting_time datetime, " +
            "remark varchar(100) )";

    public AccountingLogDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    /**
     * 添加记账明细记录
     * @author StupidWind
     * created at 2018/4/20 18:04
     */
    public int add(AccountingLog vo) {

        int _id = -1;

        db = helper.getWritableDatabase();

        db.execSQL("insert into accounting_log (user_id, account_id, accounting_type, " +
                "accounting_event_id, accounting_value, accounting_time, remark) " +
                "values (?, ?, ?, ?, ?, ?, ?)", new Object[] {vo.getUser_id(), vo.getAccount_id(),
        vo.getAccounting_type(), vo.getAccounting_event_id(), vo.getAccounting_value(),
        vo.getAccounting_time(), vo.getRemark()});

        Cursor cursor = db.rawQuery("select last_insert_rowid() from accounting_log", null);

        Log.i("DB_AccoutingLog", "add: 插入记录成功, vo : " + vo.toString());

        if (cursor.moveToFirst()) {
            _id = cursor.getInt(0);
        }

        db.close();
        return _id;
    }

    /**
     * 更新记账明细记录
     * @author StupidWind
     * created at 2018/4/20 18:08
     */
    public void update(int _id, AccountingLog vo) {

        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from accounting_log where accounting_log_id = ?",
                new String[] {String.valueOf(_id)});

        if (cursor.moveToFirst()) {
            db.execSQL("update accounting_log set user_id = ?, account_id = ?, accounting_type = ?, " +
                    "accounting_event_id = ?, accounting_value = ?, accounting_time = ?, remark = ? " +
                    "where accounting_log_id = ?", new Object[] {vo.getUser_id(), vo.getAccount_id(),
                    vo.getAccounting_type(), vo.getAccounting_event_id(), vo.getAccounting_value(),
                    vo.getAccounting_time(), vo.getRemark(), String.valueOf(_id)});

            Log.i("DB_AccountingLog", "update: 更新记录成功, vo : " + vo.toString());
        }

        db.close();
    }

    /**
     * 删除记账明细记录
     * @author StupidWind
     * created at 2018/4/20 18:18
     */
    public int delete(int _id) {

        db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from accounting_log where accounting_log_id = ?",
                new String[] {String.valueOf(_id)});

        if (cursor.moveToFirst()) {
            db.execSQL("delete from accounting_log where accounting_log_id = ?", new Object[] {String.valueOf(_id)});
            return ResultCode.SUCCESS.getCode();
        }

        return ResultCode.FAILURE.getCode();
    }

}
