package com.stupidwind.myaccounting.constant;

/**
 * 结果码 枚举
 * Created by 蠢风 on 2018/4/20.
 */

public enum ResultCode {

    SUCCESS(1), FAILURE(0);

    private int code;

    private ResultCode(Integer code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
