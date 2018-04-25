package com.stupidwind.myaccounting.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ListView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.activity.AccountingActivity;
import com.stupidwind.myaccounting.adapter.AccoutingItemAdapter;
import com.stupidwind.myaccounting.constant.AccountConstant;
import com.stupidwind.myaccounting.dao.AccountEventDao;
import com.stupidwind.myaccounting.dao.AccountLogDao;
import com.stupidwind.myaccounting.model.AccountEvent;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 记账页面
 * Created by 蠢风 on 2018/4/5.
 */

public class AccountingFragment extends BaseFragment {
    private static final String TAG = AccountingFragment.class.getSimpleName();

    private ListView lv_accouting_log;
    private FloatingActionButton fab_accounting;
    private AccoutingItemAdapter adapter;
    private AccountLogDao accountLogDao;
    private AccountEventDao accountEventDao;

    private List<AccountingLog> accountingLogList = new ArrayList<AccountingLog>();
    private Map<Integer, AccountEvent> accountEventMap = new HashMap<Integer, AccountEvent>();

    private static final int RESULT_CODE_ACCOUNT = 101;

    public AccountingFragment(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        return null;
    }

    /**
     * 初始化数据
     * @author StupidWind
     * created at 2018/4/25 21:40
     */
    @Override
    protected void initData() {
        accountLogDao = new AccountLogDao(mContext);
        accountEventDao = new AccountEventDao(mContext);
        accountingLogList = accountLogDao.listAllByUserId(AccountConstant.user_id);
        accountEventMap = accountEventDao.getAllByUserId(AccountConstant.user_id);

        setEventName(accountingLogList);
        adapter = new AccoutingItemAdapter(mContext, accountingLogList);
    }

    /**
     * 设置事件名称
     * @author StupidWind
     * created at 2018/4/25 22:51
     */
    private void setEventName(List<AccountingLog> ac_log_list) {
        Integer event_id;
        String event_name;
        for (AccountingLog ac_log : ac_log_list) {
            event_id = ac_log.getAccounting_event_id();
            event_name = accountEventMap.get(event_id).getAccount_event_name();
            ac_log.setAccounting_event_name(event_name);
        }
    }

    @Override
    protected void setFragName() { this.frag_name = "记账"; }

    /**
     * 显示悬浮球
     * @author StupidWind
     * created at 2018/4/25 21:52
     */
    public void showFab() {
        int visibility = fab_accounting.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            // 渐变显示
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            fab_accounting.startAnimation(alphaAnimation);
            fab_accounting.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏悬浮球
     * @author StupidWind
     * created at 2018/4/25 21:52
     */
    public void hideFab() {
        int visibility = fab_accounting.getVisibility();
        if (visibility == View.VISIBLE) {
            // 渐变消失
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(true);
            fab_accounting.startAnimation(alphaAnimation);
            fab_accounting.setVisibility(View.INVISIBLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.ll_acounting, container, false);
        }

        initData();

        fab_accounting = (FloatingActionButton) rootView.findViewById(R.id.fab_accounting);
        showFab();

        lv_accouting_log = (ListView) rootView.findViewById(R.id.lv_accounting_log);
        lv_accouting_log.setAdapter(adapter);

        initListener();

        return rootView;
    }

    /**
     * 初始化监听器
     * @author StupidWind
     * created at 2018/4/25 21:40
     */
    private void initListener() {
        fab_accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountingActivity.class);
                intent.putExtra("user_id", AccountConstant.user_id);
                startActivityForResult(intent, RESULT_CODE_ACCOUNT);
            }
        });
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE_ACCOUNT) { // 记账活动返回记账信息
            Bundle bundle = data.getExtras();
            if (null != bundle) {
                AccountingLog ac_log = bundle.getParcelable("account_log");
                accountingLogList.add(ac_log);
                accountLogDao.add(ac_log);
                adapter.notifyDataSetChanged();
                Log.i(TAG, "onActivityResult: 添加记账信息->" + ac_log.toString());
            }
        }
    }
}
