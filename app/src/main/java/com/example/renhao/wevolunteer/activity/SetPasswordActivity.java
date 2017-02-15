package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.api.utils.EncryptUtil;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

/**
 * 设置密码界面
 */
public class SetPasswordActivity extends BaseActivity implements View.OnFocusChangeListener {
    private static final String TAG = "SetPasswordActivity";

    private String ID;
    private String PSWD_OLD, PSWD_NEW, PSWD_AGAIN;

    private EditText edit_old, edit_new, edit_again;
    private TextView tv_submit;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");

        //QueryOldPSWD();
        edit_old = (EditText) findViewById(R.id.edit_password_old);
        edit_new = (EditText) findViewById(R.id.edit_password_new);
        edit_again = (EditText) findViewById(R.id.edit_password_again);

        tv_submit = (TextView) findViewById(R.id.tv_password_change);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryOldPSWD();
            }
        });
        back = (ImageView) findViewById(R.id.imageView_setpassword_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit_old.setOnFocusChangeListener(this);
        edit_new.setOnFocusChangeListener(this);
        edit_again.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edit_password_old:
                setHintEt(edit_old,hasFocus);
                break;
            case R.id.edit_password_new:
                setHintEt(edit_new,hasFocus);
                break;
            case R.id.edit_password_again:
                setHintEt(edit_again,hasFocus);
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



    private void changePassword() {
        if (!TextUtils.isEmpty(edit_old.getText())) {
            if (!TextUtils.isEmpty(edit_new.getText())) {
                if (!TextUtils.isEmpty(edit_again.getText())) {
                    if (EncryptUtil.makeMD5(edit_old.getText().toString()).toUpperCase().
                            equals(PSWD_OLD)) {
                        PSWD_NEW = edit_new.getText().toString();
                        PSWD_AGAIN = edit_again.getText().toString();
                        if (PSWD_NEW.length() < 6){
                            showToast("输入新密码不能少于六位");
                        }else if (PSWD_NEW.equals(PSWD_AGAIN)) {
                            AppActionImpl.getInstance(getApplicationContext()).setnew_PSWD(ID, PSWD_NEW, new ActionCallbackListener<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    //修改成功后保存密码
                                    LocalDate.getInstance(getApplicationContext()).setLocalDate("password", PSWD_NEW);
                                    showToast("修改成功");
                                    dissMissNormalDialog();
                                    SetPasswordActivity.this.finish();
                                }

                                @Override
                                public void onFailure(String errorEvent, String message) {
                                    dissMissNormalDialog();
                                    showToast("网络异常，请检查后重试");
                                }
                            });
                        } else {
                            showToast("两次新密码输入不相同！");
                        }
                    } else {
                        showToast("原密码错误");
                    }
                } else {
                    showToast("请再次输入密码");
                }
            } else {
                showToast("请输入新密码");
            }
        } else {
            showToast("请输入原密码");
        }
        dissMissNormalDialog();
    }


    private void QueryOldPSWD() {
        showNormalDialog("正在提交");
        //获取校验
        AppActionImpl.getInstance(this).getold_PSWD(ID, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                PSWD_OLD = data;
                changePassword();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("网络异常，请检查后重试");
                dissMissNormalDialog();
            }
        });
    }

}
