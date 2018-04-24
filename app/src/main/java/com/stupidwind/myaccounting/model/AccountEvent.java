package com.stupidwind.myaccounting.model;

/**
 * 记账事件类型VO
 * Created by 蠢风 on 2018/4/24.
 */
public class AccountEvent {

    /**
     * 用户id
     */
    private Integer _id;

    /**
     * 图片id
     */
    private int image_id;

    /**
     * 事件id
     */
    private Integer account_event_id;

    /**
     * 事件名称
     */
    private String account_event_name;

    /**
     * 记账类型 (income / output)
     */
    private String account_type;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public Integer getAccount_event_id() {
        return account_event_id;
    }

    public void setAccount_event_id(Integer account_event_id) {
        this.account_event_id = account_event_id;
    }

    public String getAccount_event_name() {
        return account_event_name;
    }

    public void setAccount_event_name(String account_event_name) {
        this.account_event_name = account_event_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}
