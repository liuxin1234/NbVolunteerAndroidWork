package com.example.renhao.wevolunteer.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
import com.orhanobut.logger.Logger;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/15 11:28
 * 修改备注：
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    public static final int REFRESH = 0;
    public static final int ADD = 1;


    private ProgressDialog normalDialog;
    private boolean isActivityExist = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        normalDialog = new ProgressDialog(this);
        isActivityExist = true;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        isActivityExist = false;
        if(normalDialog != null) {
            normalDialog.dismiss();
        }
        super.onDestroy();

    }

    protected void showToast(String msg) {
        if (isActivityExist)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示提示框
     *
     * @param msg
     */
    public void showNormalDialog(String msg) {
        normalDialog.setMessage(msg);
        normalDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        normalDialog.show();
    }

    /**
     * 提示框消失
     */
    public void dissMissNormalDialog() {
        try {
            if (normalDialog.isShowing())
                normalDialog.dismiss();
        }catch (Exception e){
        }
    }

    //文本提示对话框
    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    /**
     * 获得应用接口票据
     */
    protected void getAccessToken() {
        showNormalDialog("正在连接服务器");
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
        String username = isLogin ? LocalDate.getInstance(this).getLocalDate("username", "") : "";
        String password = isLogin ? LocalDate.getInstance(this).getLocalDate("password", "") : "";

        AppActionImpl.getInstance(this).getAccessToken(username, password, new AccessTokenListener() {
            @Override
            public void success(AccessTokenBO accessTokenBO) {
                Logger.v(TAG, "get token success");
                dissMissNormalDialog();
            }

            @Override
            public void fail() {
                Logger.v(TAG, "get token fail");
                dissMissNormalDialog();
            }
        });
    }
}
