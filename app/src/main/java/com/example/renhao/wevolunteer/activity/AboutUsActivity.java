package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.Util;

/**
 * 关于我们界面
 */
public class AboutUsActivity extends BaseActivity {
    private static final String TAG = "AboutUsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsActivity.this.finish();
            }
        });

        TextView versionTv = (TextView) findViewById(R.id.about_us_version);
        versionTv.setText("版本号 : " + Util.getAppVersion(this));
    }

}
