package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.durationRecord.DurationRecordListDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.utils.GsonUtils;
import com.orhanobut.logger.Logger;

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
    private List<DurationRecordListDto> mList;
    private LayoutInflater mInflater;

    public PresonalServiceTwoAdapter(Context context, List<DurationRecordListDto> mData) {
        this.mContext = context;
        this.mList = mData;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<DurationRecordListDto> data) {
        mList = data;
        Logger.e(mList.toString());
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
        DurationRecordListDto listDto = mList.get(position);
        String serviceTime = listDto.getAddLenth() + "（" + listDto.getInTime() + "—" + listDto.getOutTime() + "）";
        viewHolder.tvRecordTime.setText(listDto.getDatePart().split(" ")[0]);
        viewHolder.tvProjectName.setText(listDto.getActivityName());
        viewHolder.tvServiceTime.setText(serviceTime);
        viewHolder.tvDataSources.setText(listDto.getSource());
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv_RecordTime)
        TextView tvRecordTime;
        @Bind(R.id.tv_ProjectName)
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
