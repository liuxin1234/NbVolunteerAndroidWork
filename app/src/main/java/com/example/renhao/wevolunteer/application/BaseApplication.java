package com.example.renhao.wevolunteer.application;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/19 11:06
 * 修改备注：
 */
public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";

    public static BaseApplication INSTANCE;

    public static BaseApplication INSTANCE() {
        return INSTANCE;
    }

    public static void setBaseApplication(BaseApplication baseApplication) {
        INSTANCE = baseApplication;
    }

    public void setInstance(BaseApplication baseApplication) {
        setBaseApplication(baseApplication);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        initLogger();

    }

    private void initLogger() {
        Logger.init("We志愿");
    }
}
