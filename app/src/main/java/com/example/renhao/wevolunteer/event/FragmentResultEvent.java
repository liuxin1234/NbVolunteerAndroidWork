package com.example.renhao.wevolunteer.event;

import android.content.Intent;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 10:30
 * 修改备注：
 */
public class FragmentResultEvent {
    private static final String TAG = "FragmentResultEvent";

    private int requestCode;
    private int resultCode;
    private Intent date;

    public FragmentResultEvent() {
    }

    public FragmentResultEvent(int requestCode, int resultCode, Intent date) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.date = date;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getDate() {
        return date;
    }

    public void setDate(Intent date) {
        this.date = date;
    }
}
