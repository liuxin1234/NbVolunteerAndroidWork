package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/9/18  10:29.
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.Util;

import java.util.ArrayList;
import java.util.List;


public class MyEMail extends BaseActivity implements View.OnFocusChangeListener {
    private EditText edit_email;
    private VolunteerViewDto personal_data;
    private AppAction mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        mAction = new AppActionImpl(this);
        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        edit_email = (EditText) findViewById(R.id.edit_myemail);
        edit_email.setText(personal_data.getEmail());

        final TextView tv_submit = (TextView) findViewById(R.id.tv_myEMail_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog("正在尝试提交");
                if (!Util.isEmail(edit_email.getText().toString())) {
                    showToast("请填写正确的邮箱地址");
                    dissMissNormalDialog();
                } else {
                    final String email = edit_email.getText().toString();

                    List<VolunteerViewDto> vl_updates = new ArrayList<>();
                    personal_data.setEmail(email);
                    vl_updates.add(personal_data);

                    mAction.volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            dissMissNormalDialog();
                            Intent result = new Intent();
                            result.putExtra("personal_data", personal_data);
                            setResult(RESULT_OK, result);
                            showToast("修改成功");
                            MyEMail.this.finish();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            dissMissNormalDialog();
                            showToast(message);
                        }
                    });


                    //邮箱重复判断接口
                    /*AppActionImpl.getInstance(getApplicationContext()).existsEmail(email, 1, new ActionCallbackListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            if (data.equals("true") && !email.equals(personal_data.getEmail())) {
                                showToast("该邮箱已存在");
                                dissMissNormalDialog();
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            dissMissNormalDialog();
                        }
                    });*/
                }
            }
        });


        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyEMail.this.finish();
            }
        });
        edit_email.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edit_myemail:
                setHintEt(edit_email,hasFocus);
                break;
            default:
                break;
        }
    }

    private void setHintEt(EditText et,boolean hasFocus){
        String hint;
        if(hasFocus){
            hint = et.getHint().toString();
            et.setTag(hint);
            et.setHint("");
        }else{
            hint = et.getTag().toString();
            et.setHint(hint);
        }
    }
}