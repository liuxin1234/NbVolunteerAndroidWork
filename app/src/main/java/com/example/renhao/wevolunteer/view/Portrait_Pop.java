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

import static com.example.renhao.wevolunteer.activity.PersonalDataActivity.PDactivity;

public class Portrait_Pop extends PopupWindow {
    private Button btn_take_photo, btn_pick_photo, btn_cancel;
    private View mMenuView;


    public Portrait_Pop(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow_portrait, null);
        btn_take_photo = (Button) mMenuView.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button) mMenuView.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancel);

        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                //恢复底部activity的透明度
                backgroundAlpha(1);
            }
        });

        //设置按钮监听
        btn_pick_photo.setOnClickListener(itemsOnClick);
        btn_take_photo.setOnClickListener(itemsOnClick);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        mMenuView.setFocusable(true);
        mMenuView.setFocusableInTouchMode(true);

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

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                System.out.println(height + "|" + y);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();//销毁弹出框,恢复底部activity
                    }
                }

                return true;
            }
        });



    }


    //重写，在dismiss的同时，恢复底部activity
    @Override
    public void dismiss(){
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
