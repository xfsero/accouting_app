package com.stupidwind.myaccounting.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stupidwind.myaccounting.R;
import com.stupidwind.myaccounting.model.AccountingLog;

import java.util.List;

/**
 * Created by 蠢风 on 2018/4/5.
 */

public class AccoutingItemAdapter extends ArrayAdapter<AccountingLog> {

    private int resourceId;

    public AccoutingItemAdapter(Context context, int textViewResourceId, List<AccountingLog> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AccountingLog accountingLog = getItem(position);
        View view;

        if (null == convertView) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_accouting_detail_name);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);

        return view;
    }
}
