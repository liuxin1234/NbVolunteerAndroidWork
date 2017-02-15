package com.example.renhao.wevolunteer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
import com.example.renhao.wevolunteer.utils.Util;
import com.orhanobut.logger.Logger;

import static com.example.model.volunteer.VolunteerDto.TAG;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getAccessToken();
        initView();
    }

    public void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    String nowVersion = "V" + Util.getAppVersion(WelcomeActivity.this);
                    String LastVersion = LocalDate.getInstance(WelcomeActivity.this).getLocalDate("LastVersion", null);

                    if (nowVersion.equals(LastVersion)) {
                        Intent intent = new Intent(WelcomeActivity.this, IndexActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                        WelcomeActivity.this.finish();
                    }
                    LocalDate.getInstance(WelcomeActivity.this).setLocalDate("LastVersion", nowVersion);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 获得应用接口票据
     */
    protected void getAccessToken() {
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
        String username = isLogin ? LocalDate.getInstance(this).getLocalDate("username", "") : "";
        String password = isLogin ? LocalDate.getInstance(this).getLocalDate("password", "") : "";

        AppActionImpl.getInstance(this).getAccessToken(username, password, new AccessTokenListener() {
            @Override
            public void success(AccessTokenBO accessTokenBO) {
                Logger.v(TAG, "get token success");
            }

            @Override
            public void fail() {
                Logger.v(TAG, "get token fail");
            }
        });
    }
}
