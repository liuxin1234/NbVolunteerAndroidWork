package com.example.renhao.wevolunteer.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.utils
 * 项目日期：2019/9/9
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */
public class MyCountDownTimer extends CountDownTimer {
    private TextView mCountDownTextView;

    /**
     * @param millisInFuture
     *      表示以「 毫秒 」为单位倒计时的总数
     *      例如 millisInFuture = 1000 表示1秒
     *
     * @param countDownInterval
     *      表示 间隔 多少微秒 调用一次 onTick()
     *      例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
     *
     */

    public MyCountDownTimer(long millisInFuture, long countDownInterval,TextView mCountDownTextView) {
        super(millisInFuture, countDownInterval);
        this.mCountDownTextView = mCountDownTextView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mCountDownTextView.setText( millisUntilFinished / 1000 + "s 跳过");
    }

    @Override
    public void onFinish() {
        mCountDownTextView.setText("0s 跳过");
    }
}
