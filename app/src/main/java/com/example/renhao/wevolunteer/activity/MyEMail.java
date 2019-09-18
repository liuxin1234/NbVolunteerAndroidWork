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

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的邮箱界面
 */
public class MyEMail extends BaseActivity implements View.OnFocusChangeListener {
    private EditText edit_email;
    private VolunteerViewDto personal_data;
    private AppAction mAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        View actionBar = setActionBar();
        ActionBarUtils.setTvTitlet(actionBar,getResources().getString(R.string.title_email));
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvSubmit(actionBar, "提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSubmit();
            }
        });

        mAction = new AppActionImpl(this);
        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        edit_email = (EditText) findViewById(R.id.edit_myemail);
        edit_email.setText(personal_data.getEmail());
        edit_email.setOnFocusChangeListener(this);
    }

    private void initSubmit() {
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
        }
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