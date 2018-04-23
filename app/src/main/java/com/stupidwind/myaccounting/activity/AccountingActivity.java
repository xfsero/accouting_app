package com.stupidwind.myaccounting.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.adapter.AccountGridViewAdapter;
import com.stupidwind.myaccounting.adapter.AccountingPagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加记账记录Activity
 * @author StupidWind
 * created at 2018/4/23 13:52
 */
public class AccountingActivity extends AppCompatActivity {

    private MagicIndicator magicIndicator;
    private ViewPager mViewPager;
    private Button btn_back;
    private Button btn_accounting_add;

    private final String[] accoutTypeTitles = {"收入", "支出"};
    
    private List<String> incomeEventList;
    private List<String> payEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);
        
        initData();
        initView();
    }
    
    /**
     * 初始化数据
     * @author StupidWind
     * created at 2018/4/23 19:03
     */
    private void initData() {
        incomeEventList = new ArrayList<String>();
        payEventList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            incomeEventList.add("收入事件" + i);
            payEventList.add("支出事件" + i);
        }
    }

    /**
     * 初始化视图
     * @author StupidWind
     * created at 2018/4/23 13:43
     */
    private void initView() {
        btn_back = (Button) findViewById(R.id.btn_accounting_back);
        btn_accounting_add = (Button) findViewById(R.id.btn_accounting_add);

        initViewPager();
        initIndicator();
        setListener();

        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }

    /**
     * 初始化ViewPager
     * @author StupidWind
     * created at 2018/4/23 20:12
     */
    private void initViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.vp_accouting);

        List<View> views = new ArrayList<View>();

        GridView gv_income = getGridView(this, incomeEventList);
        GridView gv_pay = getGridView(this, payEventList);

        views.add(gv_income);
        views.add(gv_pay);

        mViewPager.setAdapter(new AccountingPagerAdapter(views));
        mViewPager.setCurrentItem(1);
    }

    /**
     * 初始化网格View
     * @author StupidWind
     * created at 2018/4/23 20:08
     */
    public GridView getGridView(Context context, List<String> dataList) {
        GridView gridView = new GridView(context);
        gridView.setNumColumns(5);
        gridView.setAdapter(new AccountGridViewAdapter(context, dataList));
        return gridView;
    }

    /**
     * 设置顶部指示器
     * @author StupidWind
     * created at 2018/4/23 15:05
     */
    private void initIndicator() {
        magicIndicator = (MagicIndicator) findViewById(R.id.mi_top_account_type);
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.35f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return null == accoutTypeTitles ? 0 : accoutTypeTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(accoutTypeTitles[index]);
                simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#e94220"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });

                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator wrapPagerIndicator = new WrapPagerIndicator(context);
                wrapPagerIndicator.setFillColor(Color.parseColor("#ebe4e3"));

                return wrapPagerIndicator;
            }
        });

        commonNavigator.setLeftPadding(140);
        magicIndicator.setNavigator(commonNavigator);
        magicIndicator.onPageSelected(1);
    }

    /**
     * 设置监听器
     * @author StupidWind
     * created at 2018/4/23 13:43
     */
    private void setListener() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_accounting_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 设置ViewPager的监听器
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

}
