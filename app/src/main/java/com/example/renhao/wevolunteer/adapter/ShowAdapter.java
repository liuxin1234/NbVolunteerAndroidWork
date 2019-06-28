package com.example.renhao.wevolunteer.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.api.Api;
import com.example.api.net.HttpEngine;
import com.example.model.share.ShareListDto;
import com.example.model.show.Data;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.MessagePicturesLayout;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.adapter
 * 项目日期：2019/5/7
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ShowAdapter extends BaseQuickAdapter<ShareListDto,BaseViewHolder> {

    private MessagePicturesLayout.Callback mCallback;
    public static List<ShareListDto> mListDtos = new ArrayList<>();

    public ShowAdapter(int layoutResId, @Nullable List<ShareListDto> data,MessagePicturesLayout.Callback callback) {
        super(layoutResId, data);
        this.mCallback = callback;
    }

    public static void setIsPraise(List<ShareListDto> dtos){
        mListDtos = dtos;
    }

    //刷新部分组件数据时 需要用到的重写该方法
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
//        if (payloads.isEmpty()){
//            super.onBindViewHolder(holder, position, payloads);
//            return;
//        }
//        String sPayload = (String)payloads.get(0);
//        Logger.e(sPayload);
//        Integer praise = mListDtos.get(position).getIsPraise();
//        Logger.e("是否点赞："+praise);
//        if (praise == 1){
//            holder.setBackgroundRes(R.id.img_like,R.mipmap.icon_likegood_press);
//            holder.setTextColor(R.id.tv_like,mContext.getResources().getColor(R.color.colorPrimary));
//        } else {
//            holder.setBackgroundRes(R.id.img_like,R.mipmap.icon_likegood_unpresss);
//            holder.setTextColor(R.id.tv_like,mContext.getResources().getColor(R.color.colorTransparentDark));
//        }
//    }

    @Override
    protected void convert(BaseViewHolder helper, ShareListDto item) {
        ImageView imageAvatar = helper.getView(R.id.img_avatar);
        MessagePicturesLayout lPictures = helper.getView(R.id.l_pictures);

        String headUrl =  Util.getRealUrl(item.getImageUrl());
        Logger.e(headUrl);
        /**
         * 我们服务端是每次上传的个人头像只是替换原图，路径并不变。
         * 这就导致glide加载时会使用缓存的图片，导致页面图片显示不同步。
         * 所以这里需要进行缓存的清除
         */
        Glide.with(mContext).load(headUrl)
                .placeholder(R.mipmap.icon_default_user_head)
                .error(R.mipmap.icon_default_user_head)
                .into(imageAvatar);
        helper.setText(R.id.tv_nickname,item.getVolunteerName())
                .setText(R.id.tv_time,item.getCreateTime())
                .setText(R.id.tv_activity_name,item.getActivityName())
                .setText(R.id.t_content,item.getDescription())
                .addOnClickListener(R.id.ll_like)
                .addOnClickListener(R.id.ll_evaluate);
        lPictures.setCallback(mCallback);

        String st = item.getImageUrl1();
        String[] split = st.split("\\|");

        List<Uri> list = new ArrayList<>();
        for (String s : split){
            Uri uri = Uri.parse(Util.getRealUrl(s));
            list.add(uri);
        }
//        Logger.e(list.toString());

        lPictures.set(list,list);

        Integer praise = item.getIsPraise();
//        Logger.e("是否点赞："+praise);
        if (praise == 1){
            helper.setBackgroundRes(R.id.img_like,R.mipmap.icon_likegood_press);
            helper.setTextColor(R.id.tv_like,mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            helper.setBackgroundRes(R.id.img_like,R.mipmap.icon_likegood_unpresss);
            helper.setTextColor(R.id.tv_like,mContext.getResources().getColor(R.color.colorTransparentDark));
        }

    }
}
