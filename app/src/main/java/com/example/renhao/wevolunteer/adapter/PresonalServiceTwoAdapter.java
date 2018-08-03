package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.adapter
 * 项目日期：2018/7/23
 * 作者：liux
 * 功能：个人服务累计的时间 Adapter
 *
 * @author 750954283(qq)
 */

public class PresonalServiceTwoAdapter extends BaseAdapter {


    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;

    public PresonalServiceTwoAdapter(Context context, List<String> mData) {
        this.mContext = context;
        this.mList = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> data) {
        mList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_presonal_service_time, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvStartTime.setText(mList.get(position));
        viewHolder.tvEndTime.setText(mList.get(position));
        viewHolder.tvServiceTime.setText(mList.get(position));
        viewHolder.tvDataSources.setText(mList.get(position));
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.tv_StartTime)
        TextView tvStartTime;
        @Bind(R.id.tv_EndTime)
        TextView tvEndTime;
        @Bind(R.id.tv_Data_Sources)
        TextView tvDataSources;
        @Bind(R.id.tv_Service_Time)
        TextView tvServiceTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
