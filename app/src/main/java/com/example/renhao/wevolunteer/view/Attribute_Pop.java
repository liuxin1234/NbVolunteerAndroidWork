package com.example.renhao.wevolunteer.view;
/*
 *
 * Created by Ge on 2016/8/10  17:32.
 *
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.renhao.wevolunteer.R;

import static com.example.renhao.wevolunteer.R.id.btn_attribute_on_job;
import static com.example.renhao.wevolunteer.R.id.btn_attribute_student;
import static com.example.renhao.wevolunteer.activity.PersonalDataActivity.PDactivity;

public class Attribute_Pop extends PopupWindow {
    private Button btn_sutdent, btn_on_job, btn_retire;
    private View mMenuView;


    public Attribute_Pop(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow_attribute, null);
        btn_sutdent = (Button) mMenuView.findViewById(btn_attribute_student);
        btn_on_job = (Button) mMenuView.findViewById(btn_attribute_on_job);
        btn_retire = (Button) mMenuView.findViewById(R.id.btn_attribute_retire);


        //设置按钮监听
        btn_sutdent.setOnClickListener(itemsOnClick);
        btn_on_job.setOnClickListener(itemsOnClick);
        btn_retire.setOnClickListener(itemsOnClick);

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupAnimation);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        backgroundAlpha((float) 0.5);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int top = mMenuView.findViewById(R.id.attribute_pop_layout).getTop();
                int height = mMenuView.findViewById(R.id.attribute_pop_layout).getHeight();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < top || y > top + height) {
                        dismiss();//销毁弹出框，恢复背景
                    }
                }
                return true;
            }
        });

    }


    //重写，在dismiss的同时，恢复底部activity
    @Override
    public void dismiss() {
        backgroundAlpha(1);
        super.dismiss();
    }


    //背景透明度设置
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = PDactivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        PDactivity.getWindow().setAttributes(lp);
    }
}
