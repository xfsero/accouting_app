package com.stupidwind.myaccounting.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.adapter.AccoutingItemAdapter;
import com.stupidwind.myaccounting.model.AccoutingDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账页面
 * Created by 蠢风 on 2018/4/5.
 */

public class AccountingFragment extends BaseFragment {

    private List<AccoutingDetail> accoutingDetailList = new ArrayList<AccoutingDetail>();

    private ListView lv_accouting_detail;

    public AccountingFragment(Context context) {
        super(context);
    }

    private void initList() {
        for (int i = 0; i < 3; i++) {
            AccoutingDetail accoutingDetail = new AccoutingDetail();
            accoutingDetail.setAccoutingName("记账" + i);
            accoutingDetailList.add(accoutingDetail);
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
                R.layout.ll_item_accouting_detail, accoutingDetailList);

        lv_accouting_detail = (ListView) rootView.findViewById(R.id.lv_accouting_detail);

        lv_accouting_detail.setAdapter(adapter);

        return rootView;
    }

}
