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
import com.stupidwind.myaccounting.model.AccoutingDetail;

import java.util.List;

/**
 * Created by 蠢风 on 2018/4/5.
 */

public class AccoutingItemAdapter extends ArrayAdapter<AccoutingDetail> {

    private int resourceId;

    public AccoutingItemAdapter(Context context, int textViewResourceId, List<AccoutingDetail> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AccoutingDetail accoutingDetail = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView textView = (TextView) view.findViewById(R.id.tv_accouting_detail_name);
        textView.setText(accoutingDetail.getAccoutingName());
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);

        return view;
    }
}
