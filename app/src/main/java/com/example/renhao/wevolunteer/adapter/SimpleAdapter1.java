package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/30 0:00
 * 修改备注：
 */
public class SimpleAdapter1 extends BaseAdapter {
    private static final String TAG = "SimpleAdapter1";
    public static final int SINGLE = 0;
    public static final int MULTIPLY = 1;
    private int choseModle = MULTIPLY;
    private boolean canSelect = true;

    public int getChoseModle() {
        return choseModle;
    }

    public void setChoseModle(int choseModle) {
        this.choseModle = choseModle;
    }

    private Context mContext;
    private List<ItemDate> dates;

    public SimpleAdapter1(Context context, List<ItemDate> dates) {
        this.mContext = context;
        this.dates = dates;
    }

    public void setDate(List<ItemDate> dates) {
        this.dates = dates;
        notifyDataSetChanged();
    }

    public void setDate(List<ItemDate> dates, boolean canSelect) {
        this.dates = dates;
        this.canSelect = canSelect;
        notifyDataSetChanged();
    }

    public void setChecked(int position, boolean isChecked) {
        if (choseModle == SINGLE) {
            for (int i = 0; i < getCount(); i++) {
                dates.get(i).setChecked(false);
            }
        }
        dates.get(position).setChecked(isChecked);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_simple_chose_1, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        final ItemDate date = dates.get(position);
        if (canSelect) {
            viewHolder.mCb.setVisibility(View.VISIBLE);
            viewHolder.mCb.setChecked(dates.get(position).isChecked());
            viewHolder.mCb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCheckedChangeListener != null) {
                        mOnCheckedChangeListener.onCheckedChanged(date, !date.isChecked());
                    }
                    setChecked(position, !date.isChecked());
                }
            });
        } else {
            viewHolder.mCb.setVisibility(View.GONE);
        }
        viewHolder.mTv.setText(date.getTitle());
        viewHolder.mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClickListener(date, position);
                }
            }
        });

        return convertView;
    }

    public interface OnCheckedChangeListener {
        public void onCheckedChanged(ItemDate date, boolean isChecked);
    }

    private OnCheckedChangeListener mOnCheckedChangeListener;

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public interface OnItemClickListener {
        public void onItemClickListener(ItemDate date, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder {
        @Bind(R.id.cb_spcs_1)
        CheckBox mCb;
        @Bind(R.id.iv_spcs_1)
        ImageView mIv;
        @Bind(R.id.tv_spcs_1)
        TextView mTv;
        @Bind(R.id.rl_spcs_1)
        RelativeLayout mRl;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public static class ItemDate {
        private boolean isChecked;
        private String title;
        private Object value;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

}
