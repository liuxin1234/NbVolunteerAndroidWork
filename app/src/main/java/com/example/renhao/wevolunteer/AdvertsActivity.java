package com.example.renhao.wevolunteer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.core.local.LocalDate;
import com.example.renhao.wevolunteer.activity.webview.WebViewAdvertActivity;
import com.example.renhao.wevolunteer.utils.MyCountDownTimer;
import com.example.renhao.wevolunteer.utils.Util;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on
 * 项目名称：com.example.renhao.wevolunteer
 * 项目日期：2019/9/18
 * 作者：liux
 * 邮箱：750954283@qq.com
 * 公司：nbzhongjing
 * 功能：广告活动页面
 */

public class AdvertsActivity extends Activity {

    @Bind(R.id.img_advert)
    ImageView imgAdvert;
    @Bind(R.id.start_skip_count_down)
    TextView startSkipCountDown;

    private MyCountDownTimer mMyCountDownTimer;
    private Boolean isJumpAdvert = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adverts);
        ButterKnife.bind(this);

        mMyCountDownTimer = new MyCountDownTimer(5000,1000,startSkipCountDown);
        mMyCountDownTimer.start();

        initThread();
    }

    private void initThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    if (!isJumpAdvert){
                        Intent intent = new Intent(AdvertsActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    }

                } catch (InterruptedException e) {
                    finish();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @OnClick(R.id.img_advert)
    public void onViewClicked() {
        isJumpAdvert = true;
        Intent intent = new Intent(AdvertsActivity.this, WebViewAdvertActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (mMyCountDownTimer != null) {
            mMyCountDownTimer.cancel();
        }
        super.onDestroy();
    }
}
