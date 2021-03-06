package com.example.renhao.wevolunteer.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.Utils;
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

    private static Context context;

    public static Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        context = getApplicationContext();
        initLogger();
        Utils.init(INSTANCE);
    }

    private void initLogger() {
        Logger.init("We志愿");
        //logger2.1.1版本必需添加的适配器，否则打印不出日志信息，设置在这里供整个APP调用
//        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    /**
     * 使其系统更改字体大小无效,若用户将字体改大这里强制该成默认的标准字体
     * 注意：这里网上有人说联想手机不行，还有7.0中设置手机系统字体方法过时了不可用
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
        {
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
