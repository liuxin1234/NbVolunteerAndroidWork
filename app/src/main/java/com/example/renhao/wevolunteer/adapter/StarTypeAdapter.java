package com.example.renhao.wevolunteer.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.model.dictionary.DictionaryListDto;
import com.example.renhao.wevolunteer.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.adapter
 * 项目日期：2019/5/14
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class StarTypeAdapter extends BaseQuickAdapter<DictionaryListDto,BaseViewHolder> {

    public static List<String> mRatingList = new ArrayList<>();

    public StarTypeAdapter(int layoutResId, @Nullable List<DictionaryListDto> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final DictionaryListDto item) {
        helper.setText(R.id.tv_content,item.getName());
        RatingBar ratingBar = helper.getView(R.id.rb_star);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setRating(rating);
                if (mOnStarClickListener != null){
                    mOnStarClickListener.onItemClick(ratingBar,item,rating,helper.getLayoutPosition());
                }

            }
        });
    }

    private OnStarClickListener mOnStarClickListener;

    public interface OnStarClickListener {
        void onItemClick(View view,DictionaryListDto listDto,float rating,int position);
    }

    public void setOnStarClickListener(OnStarClickListener onItemClickListener) {
        mOnStarClickListener = onItemClickListener;
    }
}
