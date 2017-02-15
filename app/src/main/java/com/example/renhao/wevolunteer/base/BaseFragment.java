package com.example.renhao.wevolunteer.base;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/26 18:29
 * 修改备注：
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    protected boolean isFragmentExist = false;
    private ProgressDialog normalDialog;
    private String str;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFragmentExist = true;
        normalDialog = new ProgressDialog(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
    protected void showNormalDialog(String msg) {
        normalDialog.setMessage(msg);
        normalDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        normalDialog.show();
    }

    /**
     * 提示框消失
     */
    protected void dissMissNormalDialog() {
        try {
            if (normalDialog.isShowing())
                normalDialog.dismiss();
        } catch (Exception e) {
        }

    }


    /**
     * 取子字符串
     *
     * @param oriStr     原字符串
     * @param beginIndex 取子串的起始位置
     * @param len        取子串的长度
     * @return 子字符串
     */
    public String subString(String oriStr, int beginIndex, int len) {
        int strlen = oriStr.length();
        beginIndex = beginIndex - 1;
        if (strlen <= beginIndex) {
            System.out.println("out of " + oriStr + "'s length, please recheck!");
        } else if (strlen <= beginIndex + len) {
            str = oriStr.substring(beginIndex);
        } else {
            str = oriStr.substring(beginIndex, beginIndex + len);
        }
        return str;
    }
}
