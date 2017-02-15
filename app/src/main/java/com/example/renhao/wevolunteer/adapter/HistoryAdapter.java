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
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/19 11:13
 * 修改备注：
 */
public class HistoryAdapter extends BaseAdapter {
    private static final String TAG = "HistoryAdapter";

    private List<String> dates;
    private Context mContext;

    public HistoryAdapter(Context context, List<String> dates) {
        this.mContext = context;
        this.dates = dates;
    }

    public void setDate(List<String> date) {
        dates = date;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTvHistoryItem.setText(dates.get(position));
/*        viewHolder.mTvHistoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null)
                    mOnClickListener.onClick(v);
            }
        });*/

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_history_item)
        TextView mTvHistoryItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public View.OnClickListener mOnClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }
}
