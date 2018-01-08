package com.example.renhao.wevolunteer.application;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

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


}
