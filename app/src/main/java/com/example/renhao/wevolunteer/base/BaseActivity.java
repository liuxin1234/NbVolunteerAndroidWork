package com.example.renhao.wevolunteer.base;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.core.listener.AccessTokenListener;
import com.example.core.local.LocalDate;
import com.example.model.AccessTokenBO;
import com.example.renhao.wevolunteer.R;
import com.orhanobut.logger.Logger;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/15 11:28
 * 修改备注：
 */
public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "BaseActivity";

    public static final int REFRESH = 0;
    public static final int ADD = 1;


    private ProgressDialog normalDialog;
    private boolean isActivityExist = false;

    /**
     * 6.0权限
     */
    private static final String[] LOCATION_AND_CONTACTS =
            {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,};

    private static final int RC_LOCATION_CONTACTS_PERM = 124;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        normalDialog = new ProgressDialog(this);
        isActivityExist = true;

        //请求定位和读写权限
        locationAndContactsTask();
    }



    public View setActionBar(){
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.actionbar_common_layout, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);
        return mCustomView;
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
        if (isActivityExist) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
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
            if (normalDialog.isShowing()) {
                normalDialog.dismiss();
            }
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
        final boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
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
                LocalDate.getInstance(getApplicationContext()).setLocalDate("access_token","");
                if(isLogin){
                    showToast("登录账号异常，请退出后重新登录");
                }
                Logger.v(TAG, "get token fail");
                dissMissNormalDialog();
            }
        });
    }



    /**
     * 注意一个界面只能触发一个权限请求方法，
     * 但是一个请求权限的方法里可以添加多个请求权限的参数。
     * 这样在一个界面里就会有多次的权限请求对话框产生，然后
     * 用于提示用户是否允许打开某某权限功能。
     */
    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    private void locationAndContactsTask() {
        if (hasLocationAndContactsPermissions()){
            // 已经申请过权限，做想做的事
        }else {
            // 没有申请过权限，现在去申请
            EasyPermissions.requestPermissions(
                    this,
                    getString(R.string.rationale_location_contacts),
                    RC_LOCATION_CONTACTS_PERM,
                    LOCATION_AND_CONTACTS
            );
        }
    }


    //判断是否申请过定位，读写,相机权限（还可以添加多个权限）
    private boolean hasLocationAndContactsPermissions() {
        return EasyPermissions.hasPermissions(this, LOCATION_AND_CONTACTS);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 把执行结果的操作给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override //申请成功时调用
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override //申请失败时调用
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

}
