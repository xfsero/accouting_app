package com.stupidwind.myaccounting.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by 蠢风 on 2018/4/5.
 */

public abstract class BaseFragment extends Fragment {

    protected String frag_name = "NULL";

    protected Context mContext;

    protected View rootView;

    public BaseFragment(Context context) {
        mContext = context;
        setFragName();
        rootView = initView();
        initData();
    }

    protected abstract View initView();

    /**
     * 初始化数据
     * @author StupidWind
     * created at 2018/4/5 10:30
     */
    protected abstract void initData();

    /**
     * 设置页面名称
     * @author StupidWind
     * created at 2018/4/5 10:31
     */
    protected abstract void setFragName();

    public String getFragName() { return frag_name; }
}
