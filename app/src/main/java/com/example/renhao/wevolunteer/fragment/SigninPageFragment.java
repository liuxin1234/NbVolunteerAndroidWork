package com.example.renhao.wevolunteer.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.core.local.LocalDate;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.activity.webview.WebSettingUtils;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.example.renhao.wevolunteer.common.Constants;
import com.example.renhao.wevolunteer.utils.DeviceUtils;

import com.example.renhao.wevolunteer.utils.RandomUntil;

import com.orhanobut.logger.Logger;

import static android.app.Activity.RESULT_OK;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/7 21:20
 * 修改备注：
 * 正式地址：https://appapi.zyh365.com/zyz-sign/index?source=ningbo&mobileunique=手机唯一码&idcard=身份证号
 * 测试地址：http://apitest.zyh365.com/api/web/zyz-sign/index?source=ningbo&mobileunique=手机唯一码&idcard=身份证号
 *
 */
public class SigninPageFragment extends BaseFragment {
    private static final String TAG = "SigninPageFragment";
    //正式地址
    private String url = "https://appapi.zyh365.com/zyz-sign/index?source=ningbo";
    //测试地址
//    private String url = "http://apitest.zyh365.com/api/web/quick-sign/index.html?";

    private WebView mWebView;
    private ProgressBar progressBar;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private int RESULT_CODE = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        mWebView = (WebView) view.findViewById(R.id.webView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        initWebView();
        return view;
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        //获取Mac地址
//        String mobileunique = LocalDate.getInstance(getActivity()).getLocalDate(Constants.MAC,"");
        String mobileunique = DeviceUtils.getMacAddress();
        Logger.e("mac: "+mobileunique);

        if (mobileunique == null || mobileunique.equals("") || mobileunique.isEmpty()){
            mobileunique = RandomUntil.getNumSmallLetter(16);
            showToast("mac地址为空");
            return;
        }

        String idcard = LocalDate.getInstance(getActivity()).getLocalDate(Constants.USER_NAME,"");
        if (idcard == null || idcard.equals("")){
            showToast("请先登录");
            return;
        }
        String urlResult = url + "&mobileunique=" + mobileunique + "&idcard=" + idcard ;
        Logger.e(urlResult);
        //WebView加载web资源
        mWebView.loadUrl(urlResult);

        //去除滚动条
        mWebView.setHorizontalScrollBarEnabled(false);//水平不显示
        mWebView.setVerticalScrollBarEnabled(false); //垂直不显示

        //启用支持javascript和webSetting
        WebSettingUtils.setWebSettingsInfo(getActivity(),mWebView);


        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
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


        mWebView.setWebChromeClient(new WebChromeClient() {

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
        if (resultCode == RESULT_OK) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
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


    //销毁Webview
    @Override
    public void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

}
