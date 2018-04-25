package com.stupidwind.myaccounting.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.stupidwind.myaccounting.helper.DataBaseHelper;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账明细dao
 * Created by 蠢风 on 2018/4/25.
 */
public class AccountLogDao {

    private DataBaseHelper helper;

    private SQLiteDatabase db;

    private static final String TABLE_NAME = "account_log";

    public AccountLogDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    /**
     * 添加记账明细vo
     * @author StupidWind
     * created at 2018/4/25 21:04
     */
    public void add(AccountingLog ac_log) {

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("_id", ac_log.getUser_id());
        cv.put("account_id", ac_log.getAccount_id());
        cv.put("account_type", ac_log.getAccounting_type());
        cv.put("account_event_id", ac_log.getAccounting_event_id());
        cv.put("account_value", ac_log.getAccounting_value());
        cv.put("account_time", ac_log.getAccounting_time());
        cv.put("remark", ac_log.getRemark());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public List<AccountingLog> listAllByUserId(Integer _id) {

        db = helper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"_id", "account_id", "account_type", "account_event_id", "account_value", "account_time", "remark"},
                "_id = ?", new String[] {String.valueOf(_id)}, null, null, null);

        List<AccountingLog> list = new ArrayList<>();

        while(cursor.moveToNext()) {
            AccountingLog vo = new AccountingLog();
            vo.setUser_id(cursor.getInt(cursor.getColumnIndex("_id")));
            vo.setAccount_id(cursor.getInt(cursor.getColumnIndex("account_id")));
            vo.setAccounting_type(cursor.getString(cursor.getColumnIndex("account_type")));
            vo.setAccounting_event_id(cursor.getInt(cursor.getColumnIndex("account_event_id")));
            vo.setAccounting_value(cursor.getDouble(cursor.getColumnIndex("account_value")));
            vo.setAccounting_time(cursor.getLong(cursor.getColumnIndex("account_time")));
            vo.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
            list.add(vo);
        }

        cursor.close();
        db.close();

        return list;
    }

}
