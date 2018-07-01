package com.example.renhao.wevolunteer.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.content.ContentViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.handler.MxgsaTagHandler;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.Util;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 关于我们界面
 */
public class AboutUsActivity extends BaseActivity {
    private static final String TAG = "AboutUsActivity";
    private static final String contentid = "6ff27aa5-bc50-4dfa-9b8c-292fee63bfa2"; //承办方的详情ID
    @Bind(R.id.tv_Organizer)
    TextView tvOrganizer;
    @Bind(R.id.tv_chengbang)
    TextView tvChengbang;
    @Bind(R.id.tv_url)
    TextView tvUrl;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,getResources().getString(R.string.title_about_us));


//        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AboutUsActivity.this.finish();
//            }
//        });

        TextView versionTv = (TextView) findViewById(R.id.about_us_version);
        versionTv.setText("版本号 : " + Util.getAppVersion(this));
        getDetails();
    }

    /**
     * 主办方的详细信息
     */
    public void getDetails() {
        AppActionImpl.getInstance(this).contentDetails(contentid, new ActionCallbackListener<ContentViewDto>() {
            @Override
            public void onSuccess(ContentViewDto data) {
                if (data == null) {
                    return;
                }
                tvOrganizer.setText(Html.fromHtml(data.getContents(),null, new MxgsaTagHandler(AboutUsActivity.this)));

            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });

    }


}
