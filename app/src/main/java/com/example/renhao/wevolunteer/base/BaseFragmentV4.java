package com.example.renhao.wevolunteer.base;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 10:33
 * 修改备注：
 */
public class BaseFragmentV4 extends Fragment {
    private static final String TAG = "BaseFragment";

    public static final int ACTIVITY = 1;
    public static final int JOBS = 0;

    public static final int REFRESH = 0;
    public static final int ADD = 1;

    protected boolean isFragmentExist = false;
    private ProgressDialog normalDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFragmentExist = true;
        normalDialog = new ProgressDialog(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        isFragmentExist = false;
        super.onDestroy();
    }

    protected void showToast(String msg) {
        if (isFragmentExist)
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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
}
