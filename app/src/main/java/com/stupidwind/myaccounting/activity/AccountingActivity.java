package com.stupidwind.myaccounting.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.adapter.AccountGridViewAdapter;
import com.stupidwind.myaccounting.adapter.AccountingPagerAdapter;
import com.stupidwind.myaccounting.constant.AccountConstant;
import com.stupidwind.myaccounting.dao.AccountEventDao;
import com.stupidwind.myaccounting.model.AccountEvent;
import com.stupidwind.myaccounting.model.AccountingLog;
import com.stupidwind.myaccounting.util.KeyboardUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加记账记录Activity
 * @author StupidWind
 * created at 2018/4/23 13:52
 */
public class AccountingActivity extends AppCompatActivity {

    private static final String TAG = AccountingActivity.class.getSimpleName();

    private Context ctx;
    private Activity act;
    private MagicIndicator magicIndicator;
    private ViewPager mViewPager;
    private Button btn_back;
    private Button btn_accounting_add;
    private TextView tv_cur_event_name;
    private EditText et_account_value;
    private final String[] accoutTypeTitles = {AccountConstant.Account_Type.INCOME, AccountConstant.Account_Type.OUTPUT};
    private AccountEventDao accountEventDao;

    private List<AccountEvent> incomeEventList;
    private List<AccountEvent> outputEventList;
    private AccountingLog ac_log = new AccountingLog();

    private static final int RESULT_CODE_ACCOUNT = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounting);
        ctx = this;
        act = this;

        initData();
        initView();
    }

    /**
     * 初始化数据
     * @author StupidWind
     * created at 2018/4/23 19:03
     */
    private void initData() {

        initDao();
        incomeEventList = accountEventDao.listByType(AccountConstant.user_id, "income");
        outputEventList = accountEventDao.listByType(AccountConstant.user_id, "output");

        ac_log.setUser_id(getIntent().getIntExtra("user_id", -1));
    }

    /**
     * 初始化dao
     * @author StupidWind
     * created at 2018/4/25 16:18
     */
    private void initDao() {
        accountEventDao = new AccountEventDao(this);
    }

    /**
     * 初始化视图
     * @author StupidWind
     * created at 2018/4/23 13:43
     */
    private void initView() {
        btn_back = (Button) findViewById(R.id.btn_accounting_back);
        btn_accounting_add = (Button) findViewById(R.id.btn_accounting_add);
        tv_cur_event_name = (TextView) findViewById(R.id.tv_cur_event_name);
        et_account_value = (EditText) findViewById(R.id.et_account_value);
        et_account_value.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

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
        GridView gv_output = getGridView(this, outputEventList);

        views.add(gv_income);
        views.add(gv_output);

        mViewPager.setAdapter(new AccountingPagerAdapter(views));
        mViewPager.setCurrentItem(1);
    }

    /**
     * 初始化网格View
     * @author StupidWind
     * created at 2018/4/23 20:08
     */
    public GridView getGridView(Context context, List<AccountEvent> dataList) {
        GridView gridView = new GridView(context);
        gridView.setNumColumns(5);
        AccountGridViewAdapter adapter = new AccountGridViewAdapter(context, dataList, ac_log);
        adapter.attachEventName(tv_cur_event_name);
        gridView.setAdapter(adapter);
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
                simplePagerTitleView.setTextSize(18);
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

        commonNavigator.setLeftPadding(125);
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

                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                // 添加账单
                double ac_value = Double.valueOf(et_account_value.getText().toString());

                if (ac_value <= 0.0) {
                    Toast.makeText(ctx, "记账金额不能为0", Toast.LENGTH_LONG);
                    return ;
                }

                ac_log.setAccounting_value(ac_value);
                bundle.putParcelable("account_log", ac_log);
                intent.putExtras(bundle);
                setResult(RESULT_CODE_ACCOUNT, intent);

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

        // 设置记账金额编辑框的监听器
        et_account_value.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 弹出键盘
                // TODO 设置软键盘
                //KeyboardUtil.showSoftInputFromWindow(act, et_account_value);
                return false;
            }
        });
    }

    private boolean checkAccount() {

        return false;
    }

}
