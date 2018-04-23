package com.stupidwind.myaccounting.dao;

import com.stupidwind.myaccounting.model.AccountingLog;

/**
 * Created by 蠢风 on 2018/4/21.
 */

public interface AccountingLogDao {

    public int add(AccountingLog vo);

    public void update(int _id, AccountingLog vo);

    public int delete(int _id);

}
