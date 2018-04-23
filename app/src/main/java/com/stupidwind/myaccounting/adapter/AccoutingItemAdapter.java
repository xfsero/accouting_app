package com.stupidwind.myaccounting.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 记账明细列表Adapter
 * Created by 蠢风 on 2018/4/5.
 */

public class AccoutingItemAdapter extends BaseAdapter {

    private List<AccountingLog> data;

    private Context mContext;

    private LayoutInflater layoutInflater;

    public AccoutingItemAdapter(Context context, List<AccountingLog> data) {
        super();
        this.mContext = context;
        this.data = data;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Nullable
    @Override
    public AccountingLog getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private final class ViewHolder {

        TextView tv_accounting_event_name;
        TextView tv_accounting_value;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AccountingLog accountingLog = getItem(position);
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = layoutInflater.inflate(R.layout.ll_item_accouting_log, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_accounting_event_name = (TextView) convertView.findViewById(R.id.tv_accounting_event_name);
            viewHolder.tv_accounting_value = (TextView) convertView.findViewById(R.id.tv_accounting_value);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_accounting_event_name.setText(accountingLog.getAccounting_event_name());
        viewHolder.tv_accounting_value.setText(String.valueOf(accountingLog.getAccounting_value()));

        return convertView;
    }
}
