package com.example.renhao.wevolunteer.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.utils
 * 项目日期：2018/6/26
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ActionBarUtils {

    public static void setTvTitlet(View actionBar,String titlet) {
        TextView tvTitle = (TextView) actionBar.findViewById(R.id.tv_title_name);
        tvTitle.setText(titlet);
    }

    public static void setImgBack(View actionBar,View.OnClickListener listener){
        ImageView imgBack = (ImageView) actionBar.findViewById(R.id.imageView_btn_back);
        imgBack.setOnClickListener(listener);
    }

    public static void setTvSubmit(View actionBar,String text, View.OnClickListener listener){
        TextView tvRigth = (TextView) actionBar.findViewById(R.id.tv_submit);
        tvRigth.setText(text);
        tvRigth.setOnClickListener(listener);
    }

    public static void setImgRightIcon(View actionBar,int resId,View.OnClickListener listener){
        ImageView imgRightIcon = (ImageView) actionBar.findViewById(R.id.img_right_icon);
        imgRightIcon.setImageResource(resId);
        imgRightIcon.setOnClickListener(listener);
    }
}
