package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：NbVolunteerAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/9/18 10:33
 * 修改备注：
 */
public class FAQDescActivity extends BaseActivity {
    private static final String TAG = "FAQDescActivity";
    @Bind(R.id.tv_faq_desc)
    TextView mTvFaqDesc;
    @Bind(R.id.faqdesc_imageView_btn_back)
    ImageView mBack;
    @Bind(R.id.faqdesc_textView)
    TextView mTitle;
    @Bind(R.id.faqdesc_relativeLayout)
    RelativeLayout mFaqdescRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqdesc);
        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");

        if (title != null)
            mTitle.setText(title);

        if (desc != null) {
            mTvFaqDesc.setText(desc);
        }

    }

    @OnClick(R.id.faqdesc_imageView_btn_back)
    public void onClick() {
        finish();
    }
}
