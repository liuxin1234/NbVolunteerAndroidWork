package com.example.renhao.wevolunteer.activity.evaluate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.activity.evaluate
 * 项目日期：2019/5/5
 * 作者：liux
 * 功能：签退成功界面 （H5签退成功后的界面）
 *
 * @author 750954283(qq)
 */

public class SignOffActivity extends BaseActivity {

//   @Bind(R.id.img_back)
//    ImageView imgBack;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;
    @Bind(R.id.layout_evaluate)
    LinearLayout layoutEvaluate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_off);
        ButterKnife.bind(this);
        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,getResources().getString(R.string.tv_app_title));

        initView();
    }

    private void initView() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Intent intent = new Intent(SignOffActivity.this,EvaluateActivity.class);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.layout_evaluate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_evaluate:
                Intent intent = new Intent(this,EvaluateActivity.class);
                startActivity(intent);
                break;
        }
    }
}
