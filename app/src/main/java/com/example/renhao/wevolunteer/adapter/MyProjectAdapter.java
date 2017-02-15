package com.example.renhao.wevolunteer.adapter;

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
    }


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
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        holder.name.setText(lists.get(position).getNeme());
        holder.STATE.setText(lists.get(position).getState());
        holder.TIME.setText(lists.get(position).getTime());

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

}
