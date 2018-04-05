package com.stupidwind.myaccounting.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 统计页面
 * Created by 蠢风 on 2018/4/5.
 */

public class StatisticFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected void initData() {

    }

    @Override
    protected void setFragName() { this.frag_name = "统计"; }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        textView = new TextView(getContext());
        textView.setText(getFragName());
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
}
