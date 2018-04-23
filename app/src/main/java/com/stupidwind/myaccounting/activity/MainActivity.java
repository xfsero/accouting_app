package com.stupidwind.myaccounting.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.fragment.AccountFragment;
import com.stupidwind.myaccounting.fragment.AccountingFragment;
import com.stupidwind.myaccounting.fragment.BaseFragment;
import com.stupidwind.myaccounting.fragment.StatisticFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab_accounting;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<BaseFragment> fragments;
    private ContentPagerAdapter contentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    /**
     * 初始化界面
     * @author StupidWind
     * created at 2018/4/5 10:20
     */
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tl_tab);
        viewPager = (ViewPager) findViewById(R.id.vp_content);
        fab_accounting = (FloatingActionButton) findViewById(R.id.fab_accounting);
        fab_accounting.setVisibility(View.GONE);

        fab_accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountingActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        initFragment();
        initTab();
    }

    private void initFragment() {
        AccountFragment frag_account = new AccountFragment(this);
        AccountingFragment frag_accounting = new AccountingFragment(this);
        StatisticFragment frag_statistic = new StatisticFragment(this);

        fragments = new ArrayList<BaseFragment>();

        fragments.add(frag_account);
        fragments.add(frag_accounting);
        fragments.add(frag_statistic);

        contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(contentAdapter);
    }

    /**
     * 初始化导航栏
     * @author StupidWind
     * created at 2018/4/5 10:21
     */
    private void initTab() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(tabLayout, 10);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab itemTab = tabLayout.getTabAt(i);
            if (null != itemTab) {
                itemTab.setCustomView(R.layout.item_tl_custom);
                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_tl_item);
                itemTv.setGravity(Gravity.CENTER);
                itemTv.setText(fragments.get(i).getFragName());
            }
        }

        // 设置底部导航栏的选中监听器
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == tabLayout.getTabAt(1)) {
                    fab_accounting.setVisibility(View.VISIBLE);
                } else {
                    fab_accounting.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

    private class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getFragName();
        }
    }

}
