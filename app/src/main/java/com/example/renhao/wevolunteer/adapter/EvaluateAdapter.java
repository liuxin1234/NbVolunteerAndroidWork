package com.example.renhao.wevolunteer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.model.evaluate.EvaluateBean;
import com.example.renhao.wevolunteer.R;

import java.util.List;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.adapter
 * 项目日期：2019/5/6
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class EvaluateAdapter extends BaseQuickAdapter<EvaluateBean,BaseViewHolder> {

    private Context mContext;

    public EvaluateAdapter(Context context,int layoutResId, @Nullable List<EvaluateBean> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final EvaluateBean item) {
        helper.setText(R.id.btn_item_evaluate,item.getName())
        .addOnClickListener(R.id.btn_item_evaluate);

        if (item.getCheck()){
            helper.setTextColor(R.id.btn_item_evaluate,mContext.getResources().getColor(R.color.color));
            helper.setBackgroundRes(R.id.btn_item_evaluate,R.drawable.btn_pressed_border);
        }else {
            helper.setTextColor(R.id.btn_item_evaluate,mContext.getResources().getColor(R.color.gray));
            helper.setBackgroundRes(R.id.btn_item_evaluate,R.drawable.btn_unpressed_border);
        }



    }
}
