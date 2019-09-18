package com.example.renhao.wevolunteer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.items.MyProjectItem;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.utils.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyProjectAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<MyProjectItem> lists;

    public MyProjectAdapter(Context context, List<MyProjectItem> lists) {
        mContext = context;
        this.lists = lists;
        this.mInflater = LayoutInflater.from(context);

    }

    public void setDate(List<MyProjectItem> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public final class ViewHolder {
        public TextView name;
        public TextView TIME;
        public TextView STATE;
        public ImageView PIC;
        public TextView tvSignInTiem;
        public TextView tvSignOutTime;
        public TextView tvLengthTime;
        public TextView tvEvaluate;
        public TextView tvSendShow;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_myproject, null);//根据布局产生一个view
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_myProject_projectName);
            holder.STATE = (TextView) convertView.findViewById(R.id.tv_myProject_state);
            holder.PIC = (ImageView) convertView.findViewById(R.id.iv_myProject_item);
            holder.TIME = (TextView) convertView.findViewById(R.id.tv_myProject_projectTime);
            holder.tvSignInTiem = (TextView) convertView.findViewById(R.id.tv_SignInTime);
            holder.tvSignOutTime = (TextView) convertView.findViewById(R.id.tv_SignOutTime);
            holder.tvLengthTime = (TextView) convertView.findViewById(R.id.tv_LengthTime);
            holder.tvEvaluate = (TextView) convertView.findViewById(R.id.tv_myProject_evaluate);
            holder.tvSendShow = (TextView) convertView.findViewById(R.id.tv_myProject_send_show);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        if (lists.get(position).getType() == 1){
            if (lists.get(position).getSignInTime() == null){
                holder.tvSignInTiem.setText("签到时间：");
            }else {
                holder.tvSignInTiem.setText("签到时间："+lists.get(position).getSignInTime());
            }
            if (lists.get(position).getSignInTime() == null){
                holder.tvSignOutTime.setText("签退时间：");
            }else {
                holder.tvSignOutTime.setText("签退时间："+lists.get(position).getSignOutTime());
            }
            if (lists.get(position).getSignInTime() == null){
                holder.tvLengthTime.setText("本次服务时长：");
            }else {
                holder.tvLengthTime.setText("本次服务时长："+lists.get(position).getComputerHour());
            }
            holder.TIME.setVisibility(View.GONE);
        }else if (lists.get(position).getType() == 2){
            holder.tvSignInTiem.setVisibility(View.GONE);
            holder.tvSignOutTime.setVisibility(View.GONE);
            holder.tvLengthTime.setVisibility(View.GONE);
        }

        if(lists.get(position).getState().equals("0")){
            holder.STATE.setText("招募中");
        }else if (lists.get(position).getState().equals("1")){
            holder.STATE.setText("进行中");
        }else if (lists.get(position).getState().equals("2")){
            holder.STATE.setText("已结束");
        }else {
            holder.STATE.setText(lists.get(position).getState());
        }

        holder.name.setText(lists.get(position).getNeme());
        holder.TIME.setText(lists.get(position).getTime());
        holder.tvEvaluate.setText("待评价");
        holder.tvEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemButtonClickListener != null){
                    mOnItemButtonClickListener.onItemBtnEvaluateClickListener(lists,position);
                }
            }
        });

        holder.tvSendShow.setText("秀一秀");
        holder.tvSendShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemButtonClickListener != null){
                    mOnItemButtonClickListener.onItemBtnSendShowClickListener(lists,position);
                }
            }
        });

        if (TextUtils.isEmpty(lists.get(position).getPic())) {
            holder.PIC.setImageResource(R.drawable.img_unload);
        } else {
            Picasso.with(mContext).load(Util.getRealUrl(lists.get(position).getPic()))
                    .fit().tag("Ptr")
                    .placeholder(R.drawable.img_unload)
                    .error(R.drawable.img_unload)
                    .into(holder.PIC);
        }

        return convertView;
    }


    public interface OnItemButtonClickListener {
        void onItemBtnEvaluateClickListener(List<MyProjectItem>  data,int position);
        void onItemBtnSendShowClickListener(List<MyProjectItem>  data,int position);
    }

    private OnItemButtonClickListener mOnItemButtonClickListener;

    public void setOnItemButtonClickListener(OnItemButtonClickListener onItemButtonClickListener){
        this.mOnItemButtonClickListener = onItemButtonClickListener;
    }

}
