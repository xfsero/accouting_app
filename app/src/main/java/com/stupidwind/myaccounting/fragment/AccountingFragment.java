package com.stupidwind.myaccounting.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.adapter.AccoutingItemAdapter;
import com.stupidwind.myaccounting.dao.AccountingLogDao;
import com.stupidwind.myaccounting.dao.AccountingLogDaoImpl;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账页面
 * Created by 蠢风 on 2018/4/5.
 */

public class AccountingFragment extends BaseFragment {

    private List<AccountingLog> accountingLogList = new ArrayList<AccountingLog>();

    private ListView lv_accouting_log;

    // 记账明细记录Dao
    AccountingLogDao accountingLogDao = new AccountingLogDaoImpl(getContext());

    private Button btn_add_accounting;

    public AccountingFragment(Context context) {
        super(context);
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

        final AccoutingItemAdapter adapter = new AccoutingItemAdapter(mContext, accountingLogList);

        lv_accouting_log = (ListView) rootView.findViewById(R.id.lv_accounting_log);
        lv_accouting_log.setAdapter(adapter);

        btn_add_accounting = (Button) rootView.findViewById(R.id.btn_add_accounting);

        // 设置按钮监听器
        btn_add_accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 新增数据项
                AccountingLog vo = new AccountingLog();
                vo.setUser_id(1);
                vo.setAccounting_event_id(101);
                vo.setAccounting_event_name("生活费");
                vo.setAccount_id(10);
                vo.setAccounting_type("out");
                vo.setAccounting_value(100);
                vo.setRemark("备注");
                accountingLogList.add(vo);
                Log.i("添加账单项", "onClick: add 成功! list : " + accountingLogList.toString());
                adapter.notifyDataSetChanged();
                //accountingLogDao.add(vo);
            }
        });

        return rootView;
    }

}
