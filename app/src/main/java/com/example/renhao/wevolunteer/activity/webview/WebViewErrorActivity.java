package com.example.renhao.wevolunteer.activity.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.activity.webview
 * 项目日期：2019/1/29
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class WebViewErrorActivity extends BaseActivity {


    @Bind(R.id.webView_Error)
    WebView webViewError;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private int RESULT_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_error);
        ButterKnife.bind(this);
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {

        //WebView加载web资源
        webViewError.loadUrl("http://demo.nbzyz.org/SysError");

        //去除滚动条
        webViewError.setHorizontalScrollBarEnabled(false);//水平不显示
        webViewError.setVerticalScrollBarEnabled(false); //垂直不显示

        //启用支持javascript和webSetting
        WebSettingUtils.setWebSettingsInfo(this,webViewError);

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webViewError.setWebViewClient(new WebViewClient() {
            //setWebClient就是帮助WebView处理各种通知、请求事件

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //设定加载开始的操作
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //设定加载结束的操作
//                dissMissNormalDialog();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                //设定加载资源的操作
            }

        });


        webViewError.setWebChromeClient(new WebChromeClient() {

            //For Android  >= 4.1
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                this.openFileChooser(uploadMsg);
            }

            // For Android  >= 3.0
            @SuppressWarnings("unused")
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                this.openFileChooser(uploadMsg);
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                pickFile();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                pickFile();
                return true;
            }



            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin,true,false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            //setWebChromeClient辅助WebView处理Javascript的对话框，网站图标，网站title，加载进度等
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //网页加载完毕
//                    dissMissNormalDialog();
                    progressBar.setVisibility(View.GONE);
                } else {
                    if (progressBar.getVisibility() == View.GONE) {
                        progressBar.setVisibility(View.GONE);
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                    //网页正在加载
//                    showNormalDialog("正在加载数据.....");
                }
            }
        });
    }


    public void pickFile() {
        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "*/*");
        startActivityForResult(chooserIntent, RESULT_CODE);
    }


    /**
     * 5.0+以后的文件上传的细节 如相机选择图片等等
     * ValueCallback参数里包裹着不再是Uri,而是Uri数组，因此我们必须为5.0+的机器做适配
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_CODE) {
            if (null == mUploadMessage && null == uploadMessageAboveL){
                return;
            }
            Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, intent);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }

        }
    }


    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (webViewError.canGoBack()) {
//                webViewError.goBack();//返回上一页面
//                return true;
//            } else {
//                finish();
//            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


//    @Override
//    public void onBackPressed() {
//        if (!mBackKeyPressed){
//            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//            mBackKeyPressed = true;
//            new Timer().schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    mBackKeyPressed = false;
//                }
//            },2000);
//        }else {
//            this.finish();//退出程序
//        }
//    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (webViewError != null) {
            webViewError.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webViewError.clearHistory();
            ((ViewGroup) webViewError.getParent()).removeView(webViewError);
            webViewError.destroy();
            webViewError = null;
        }
        super.onDestroy();
    }
}
