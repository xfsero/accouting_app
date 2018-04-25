package com.stupidwind.myaccounting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.model.AccountEvent;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.List;

/**
 * 记账事件网格Adapter
 * Created by 蠢风 on 2018/4/23.
 */
public class AccountGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private TextView tv_cur_evnet_name;
    private List<AccountEvent> mdatas;
    private AccountingLog ac_log;

    public AccountGridViewAdapter(Context mContext, List<AccountEvent> mdatas, AccountingLog ac_log) {
        this.mContext = mContext;
        this.mdatas = mdatas;
        this.ac_log = ac_log;
    }

    public void attachEventName(TextView event_name) {
        this.tv_cur_evnet_name = event_name;
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder ;

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_account_event_item, null);

            holder = new ViewHolder();
            holder.iv_event_image = (ImageView) convertView.findViewById(R.id.iv_event_image);
            holder.tv_event_name = (TextView) convertView.findViewById(R.id.tv_event_name);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_cur_evnet_name.setText(mdatas.get(position).getAccount_event_name());
                    ac_log.setAccounting_event_name(mdatas.get(position).getAccount_event_name());
                    ac_log.setAccounting_event_id(mdatas.get(position).getAccount_event_id());
                    ac_log.setAccounting_type(mdatas.get(position).getAccount_type());
                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_event_name.setText(mdatas.get(position).getAccount_event_name());

        return convertView;
    }

    private class ViewHolder {
        ImageView iv_event_image;
        TextView tv_event_name;
    }
}
