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

import java.util.List;

/**
 * 记账事件网格Adapter
 * Created by 蠢风 on 2018/4/23.
 */
public class AccountGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<AccountEvent> mdatas;

    public AccountGridViewAdapter(Context mContext, List<AccountEvent> mdatas) {
        this.mContext = mContext;
        this.mdatas = mdatas;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;

        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_account_event_item, null);

            holder = new ViewHolder();
            holder.iv_event_image = (ImageView) convertView.findViewById(R.id.iv_event_image);
            holder.tv_event_name = (TextView) convertView.findViewById(R.id.tv_event_name);
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
