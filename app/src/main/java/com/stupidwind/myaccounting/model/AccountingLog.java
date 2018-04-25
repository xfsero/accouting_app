package com.stupidwind.myaccounting.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.stupidwind.myaccounting.util.IntegerUtil;

import java.sql.Date;

/**
 * 记账明细VO
 * Created by 蠢风 on 2018/4/20.
 */

public class AccountingLog implements Parcelable {

    // 用户id
    private Integer user_id;

    // 账户id
    private Integer account_id;

    // 记账明细记录id
    private Integer accounting_log_id;

    // 记账类型
    private String accounting_type;

    // 记账事件id
    private Integer accounting_event_id;

    // 记账事件名称
    private String accounting_event_name;

    // 记账金额值
    private double accounting_value;

    // 记账时间
    private long accounting_time;

    // 备注
    private String remark;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public Integer getAccounting_log_id() {
        return accounting_log_id;
    }

    public void setAccounting_log_id(Integer accounting_log_id) {
        this.accounting_log_id = accounting_log_id;
    }

    public String getAccounting_type() {
        return accounting_type;
    }

    public void setAccounting_type(String accounting_type) {
        this.accounting_type = accounting_type;
    }

    public Integer getAccounting_event_id() {
        return accounting_event_id;
    }

    public void setAccounting_event_id(Integer accounting_event_id) {
        this.accounting_event_id = accounting_event_id;
    }

    public String getAccounting_event_name() {
        return accounting_event_name;
    }

    public void setAccounting_event_name(String accounting_event_name) {
        this.accounting_event_name = accounting_event_name;
    }

    public double getAccounting_value() {
        return accounting_value;
    }

    public void setAccounting_value(double accounting_value) {
        this.accounting_value = accounting_value;
    }

    public long getAccounting_time() {
        return accounting_time;
    }

    public void setAccounting_time(long accounting_time) {
        this.accounting_time = accounting_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AccountingLog{" +
                "user_id=" + user_id +
                ", account_id=" + account_id +
                ", accounting_log_id=" + accounting_log_id +
                ", accounting_type='" + accounting_type + '\'' +
                ", accounting_event_id=" + accounting_event_id +
                ", accounting_event_name='" + accounting_event_name + '\'' +
                ", accounting_value=" + accounting_value +
                ", accounting_time=" + accounting_time +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static final Parcelable.Creator<AccountingLog> CREATOR = new Creator<AccountingLog>() {
        @Override
        public AccountingLog createFromParcel(Parcel source) {
            AccountingLog ac_log = new AccountingLog();
            ac_log.setUser_id(source.readInt());
            ac_log.setAccount_id(source.readInt());
            ac_log.setAccounting_log_id(source.readInt());
            ac_log.setAccounting_type(source.readString());
            ac_log.setAccounting_event_id(source.readInt());
            ac_log.setAccounting_event_name(source.readString());
            ac_log.setAccounting_value(source.readDouble());
            ac_log.setAccounting_time(source.readLong());
            ac_log.setRemark(source.readString());
            return ac_log;
        }

        @Override
        public AccountingLog[] newArray(int size) {
            return new AccountingLog[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IntegerUtil.toInt(user_id));
        dest.writeInt(IntegerUtil.toInt(account_id));
        dest.writeInt(IntegerUtil.toInt(accounting_log_id));
        dest.writeString(accounting_type);
        dest.writeInt(IntegerUtil.toInt(accounting_event_id));
        dest.writeString(accounting_event_name);
        dest.writeDouble(accounting_value);
        dest.writeLong(accounting_time);
        dest.writeString(remark);

    }
}
