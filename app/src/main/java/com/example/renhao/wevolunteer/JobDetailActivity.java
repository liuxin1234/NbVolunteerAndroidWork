package com.example.renhao.wevolunteer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.handler.MxgsaTagHandler;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 19:18
 * 修改备注：
 */
public class JobDetailActivity extends BaseActivity {
    private static final String TAG = "JobDetailActivity";
    @Bind(R.id.tv_jobactivity_detail)
    TextView mTvDetail;

    private View mCustomView;//actionbar的自定义视图
    private ImageView indicatorImg;
    private TextView titleTv;
    private ImageView magnifierImg;

    private String jobText;
    private String text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);
        ButterKnife.bind(this);

        initActionBar();

        jobText = getIntent().getStringExtra("jobText");
        text = getIntent().getStringExtra("text");

        String detailText = "";

        detailText += "岗位要求";
        detailText += "\n";
        detailText += TextUtils.isEmpty(jobText) ? "" : Html.fromHtml(jobText, null, new MxgsaTagHandler(this));
        detailText += "\n";
        detailText += "\n";
        detailText += "岗位详情";
        detailText += "\n";
        detailText += TextUtils.isEmpty(text) ? "" : Html.fromHtml(text, null, new MxgsaTagHandler(this));

        mTvDetail.setText(detailText);
    }

    private void initActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_normal, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);
        titleTv = (TextView) mCustomView.findViewById(R.id.tv_normal_projectswitch);
        titleTv.setText("岗位详情");

        indicatorImg = (ImageView) mCustomView.findViewById(R.id.imageview_normal_indicator);
        indicatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        magnifierImg = (ImageView) mCustomView.findViewById(R.id.imageview_normal_magnifier);
        magnifierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int origin = getIntent().getIntExtra("origin", 0);
                if (origin == 1) {
                    setResult(0);
                    finish();
                } else {
                    Intent intent = new Intent(JobDetailActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
