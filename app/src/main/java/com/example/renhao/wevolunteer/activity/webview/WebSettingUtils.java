package com.example.renhao.wevolunteer.activity.webview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * Created by
 * 项目名称：com.liux.nbdpcoaandroid.activity.webView
 * 项目日期：2018/3/28
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class WebSettingUtils {

    public static ProgressDialog dialog;

    @SuppressLint("SetJavaScriptEnabled")
    public static void setWebSettingsInfo(Context context, WebView webView){
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //出于安全考虑
        webView.removeJavascriptInterface("searchBoxJavaBridge_");

        settings.setAllowContentAccess(true); //设置WebView是否使用其内置的变焦机制，该机制结合屏幕缩放控件使用，默认是false，不使用内置变焦机制。
        settings.setDatabaseEnabled(true);  //设置是否开启数据库存储API权限，默认false，未开启，可以参考setDatabasePath(String path)
        settings.setDomStorageEnabled(true);    //设置是否开启DOM存储API权限，默认false，未开启，设置为true，WebView能够使用DOM storage API
        settings.setAppCacheEnabled(true);  //设置Application缓存API是否开启，默认false，设置有效的缓存路径参考setAppCachePath(String path)方法
        settings.setSavePassword(false);    //是否保存密码
        settings.setSaveFormData(false);    //设置WebView是否保存表单数据，默认true，保存数据。
        settings.setUseWideViewPort(true);  //设置WebView是否使用viewport，当该属性被设置为false时，加载页面的宽度总是适应WebView控件宽度；当被设置为true，当前页面包含viewport属性标签，在标签中指定宽度值生效，如果页面不包含viewport标签，无法提供一个宽度值，这个时候该方法将被使用。
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //设置定位的数据库路径
        String dir = context.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setGeolocationDatabasePath(dir);

        //启用地理定位
        settings.setGeolocationEnabled(true);

        //设置自适应屏幕，两者合用
        //在三星等部分手机上会出现网页突然变大的情况，因此注释掉
        settings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        settings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(true); //设置可以缩放
        settings.setDisplayZoomControls(false); //隐藏原生的缩放控件
//        //若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
//        settings.setTextZoom(2);//设置文本的缩放倍数，默认为 100

        //不使用缓存，只从网络获取数据.LOAD_NO_CACHE
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    // 设置cookie
    public static void syncCookie(Context context, String url, String[] cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
//        cookieManager.setCookie(Api.BASE_COOKIE_URL + url, cookies[0]);//cookies是在HttpClient中获得的cookie
//        cookieManager.setCookie(Api.BASE_COOKIE_URL + url, cookies[1]);//cookies是在HttpClient中获得的cookie
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieManager.getInstance().flush();
        }
    }

    // 清除cookie即可彻底清除缓存,这里需要清除cookie
    public static void clearWebViewCache(Context context) {
        // 清除cookie即可彻底清除缓存,这里需要清除cookie，否则一直为上一次用户的登录状态，导致header刷新了也无法，获取当前用户的东西
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().removeAllCookie();

    }



    public static void openDialog(Context context, int newProgress) {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("正在加载......");
            dialog.setProgress(ProgressDialog.STYLE_SPINNER);
            dialog.setProgress(newProgress);
            dialog.show();
        } else {
            dialog.setProgress(newProgress);
        }
    }

    private void closeDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
