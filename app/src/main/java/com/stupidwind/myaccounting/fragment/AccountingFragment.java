package com.stupidwind.myaccounting.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.adapter.AccoutingItemAdapter;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账页面
 * Created by 蠢风 on 2018/4/5.
 */

public class AccountingFragment extends BaseFragment {

    private List<AccountingLog> accountingLogList = new ArrayList<AccountingLog>();

    private ListView lv_accouting_detail;

    public AccountingFragment(Context context) {
        super(context);
    }

    /**
     * 初始化账单列表
     */
    private void initList() {
        for (int i = 0; i < 3; i++) {
            AccountingLog accountingLog = new AccountingLog();
            // TODO 自定义记账明细列表，测试用
            accountingLogList.add(accountingLog);
        }
    }

    @Override
    protected View initView() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setFragName() { this.frag_name = "记账"; }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.ll_acounting, container, false);
        }

        initList();

        AccoutingItemAdapter adapter = new AccoutingItemAdapter(mContext,
                R.layout.ll_item_accouting_log, accountingLogList);

        lv_accouting_detail = (ListView) rootView.findViewById(R.id.lv_accouting_detail);

        lv_accouting_detail.setAdapter(adapter);

        return rootView;
    }

}
