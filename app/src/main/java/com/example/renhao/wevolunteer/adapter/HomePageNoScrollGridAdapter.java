package com.example.renhao.wevolunteer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.items.HomePageGridItem;
import com.example.renhao.wevolunteer.R;

import java.util.ArrayList;

public class HomePageNoScrollGridAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    private Context ctx;

    private ArrayList<HomePageGridItem> items;

    private LayoutInflater layoutInflater;

    public HomePageNoScrollGridAdapter(Context ctx, ArrayList<HomePageGridItem> items) {
        this.ctx = ctx;
        this.items = items;
        this.layoutInflater = layoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_homepage_gridbutton, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.iamgeview_gridebutton_logo);
            viewHolder.bigTv = (TextView) convertView.findViewById(R.id.textview_gridebutton_titleBig);
            viewHolder.smallTv = (TextView) convertView.findViewById(R.id.textview_gridebutton_titleSmall);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        HomePageGridItem item = items.get(position);
        viewHolder.img.setImageResource(item.getImagUrl());
        viewHolder.bigTv.setText(item.getTitleBig());
        viewHolder.bigTv.setTextColor(ctx.getResources().getColor(item.getTitleColor()));
        if (item.getTitleSmall() != null) {
            viewHolder.smallTv.setText(item.getTitleSmall());
            viewHolder.smallTv.setTextColor(ctx.getResources().getColor(item.getTitleColor()));
        } else {
            viewHolder.smallTv.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView bigTv, smallTv;
    }

}
