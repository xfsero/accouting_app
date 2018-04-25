package com.stupidwind.myaccounting.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.stupidwind.myaccounting.helper.DataBaseHelper;
import com.stupidwind.myaccounting.model.AccountEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账事件类型Dao
 * Created by 蠢风 on 2018/4/24.
 */
public class AccountEventDao {

    private DataBaseHelper helper;

    private SQLiteDatabase db;

    private static final String TABLE_NAME = "account_event";

    public AccountEventDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    /**
     * 添加事件类型
     * @author StupidWind
     * created at 2018/4/24 17:19
     */
    public void add(AccountEvent vo) {

        db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("_id", vo.get_id());
        cv.put("account_event_name", vo.getAccount_event_name());
        cv.put("account_type", vo.getAccount_type());
        cv.put("image_id", vo.getImage_id());

        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    /**
     * 获取事件类型列表 （根据记账类型）
     * @author StupidWind
     * created at 2018/4/24 17:23
     */
    public List<AccountEvent> listByType(Integer _id, String type) {

        db = helper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] {"_id", "account_event_id", "account_event_name", "image_id", "account_type"},
                "_id=? and account_type=?", new String[]{String.valueOf(_id), type}, null, null, null);

        List<AccountEvent> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            AccountEvent vo = new AccountEvent();
            vo.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            vo.setAccount_event_id(cursor.getInt(cursor.getColumnIndex("account_event_id")));
            vo.setAccount_event_name(cursor.getString(cursor.getColumnIndex("account_event_name")));
            vo.setImage_id(cursor.getInt(cursor.getColumnIndex("image_id")));
            vo.setAccount_type(cursor.getString(cursor.getColumnIndex("account_type")));

            list.add(vo);
        }

        cursor.close();
        db.close();

        return list;
    }

}
