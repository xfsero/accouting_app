package com.stupidwind.myaccounting.model;

import java.sql.Date;

/**
 * 记账明细VO
 * Created by 蠢风 on 2018/4/20.
 */

public class AccountingLog {

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
    private Integer accounting_value;

    // 记账时间
    private Date accounting_time;

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

    public Integer getAccounting_value() {
        return accounting_value;
    }

    public void setAccounting_value(Integer accounting_value) {
        this.accounting_value = accounting_value;
    }

    public Date getAccounting_time() {
        return accounting_time;
    }

    public void setAccounting_time(Date accounting_time) {
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
}
