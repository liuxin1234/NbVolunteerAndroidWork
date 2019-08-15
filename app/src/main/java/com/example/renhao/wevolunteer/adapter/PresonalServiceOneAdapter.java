package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.signResult.SignResultListDto;
import com.example.renhao.wevolunteer.R;

import java.util.List;
import java.util.Locale;

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

public class PresonalServiceOneAdapter extends BaseAdapter {


    private Context mContext;
    private List<SignResultListDto> mList;
    private LayoutInflater mInflater;

    public PresonalServiceOneAdapter(Context context, List<SignResultListDto> mData) {
        this.mContext = context;
        this.mList = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<SignResultListDto> data) {
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
            convertView = mInflater.inflate(R.layout.item_presonal_service_record, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SignResultListDto listDto = mList.get(position);
        String format = String.format(Locale.getDefault(), "%.1f", listDto.getValidHour().floatValue());
        String serviceTime = format + "（"+ listDto.getSignInTime() + "—" + listDto.getSignOutTime() + "）";
        viewHolder.tvServiceDate.setText(listDto.getSignInTime().split(" ")[0]);
        viewHolder.tvProjectName.setText(listDto.getActivityTimeActivityActivityName());
        viewHolder.tvServiceTime.setText(serviceTime);
        viewHolder.tvDataSources.setText(listDto.getHourType() == 0 ? "APP录入" : "平台录入");
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_Service_Date)
        TextView tvServiceDate;
        @Bind(R.id.tv_Project_Name)
        TextView tvProjectName;
        @Bind(R.id.tv_Service_Time)
        TextView tvServiceTime;
        @Bind(R.id.tv_Data_Sources)
        TextView tvDataSources;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
