package com.example.renhao.wevolunteer.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;

/**
 * 忘记（找回）密码界面
 */
public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private static final String TAG = "ForgotPasswordActivity";

    Button btn_verification_code;
    EditText et_phone;
    EditText et_verification_code;
    LinearLayout ll_back;
    Button btn_forgot_byMobile;
    Button btn_forgot_byIDNumber;
    LinearLayout ll_layout1;
    LinearLayout ll_layout2;
    LinearLayout ll_layout3;
    LinearLayout ll_layout4;
    EditText et_phone1;
    EditText et_IDNumber;
    Button btn_byMobile;
    Button btn_byIDNumber;
    EditText et_password;
    EditText et_repassword;


    private String idnumber;
    private String phone;
    private String verification_code;
    private String userId;
    private String nPassword;
    private String nRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getAccessToken();
        initView();
        initListener();
    }

    private void initView() {
        btn_verification_code = (Button) findViewById(R.id.btn_forget_verification_code);
        et_phone = (EditText) findViewById(R.id.et_forget_phone);
        et_verification_code = (EditText) findViewById(R.id.et_forget_verification_code);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        btn_forgot_byMobile = (Button) findViewById(R.id.btn_forgot_byMobile);
        btn_forgot_byIDNumber = (Button) findViewById(R.id.btn_forgot_byIDNumber);
        ll_layout1 = (LinearLayout) findViewById(R.id.layout1);
        ll_layout2 = (LinearLayout) findViewById(R.id.layout2);
        ll_layout3 = (LinearLayout) findViewById(R.id.layout3);
        ll_layout4 = (LinearLayout) findViewById(R.id.layout4);
        et_IDNumber = (EditText) findViewById(R.id.et_forget_IDNumber);
        et_phone1 = (EditText) findViewById(R.id.et_forget_phone1);
        btn_byIDNumber = (Button) findViewById(R.id.btn_byIDNumberAndMobile);
        btn_byMobile = (Button) findViewById(R.id.btn_byMobile);
    }

    private void initListener() {
        btn_verification_code.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        btn_forgot_byMobile.setOnClickListener(this);
        btn_forgot_byIDNumber.setOnClickListener(this);
        btn_byMobile.setOnClickListener(this);
        btn_byIDNumber.setOnClickListener(this);
        et_phone.setOnFocusChangeListener(this);
        et_verification_code.setOnFocusChangeListener(this);
        et_IDNumber.setOnFocusChangeListener(this);
        et_phone1.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.et_forget_phone:
                setHintEt(et_phone,hasFocus);
                break;
            case R.id.et_forget_verification_code:
                setHintEt(et_verification_code,hasFocus);
                break;
            case R.id.et_forget_IDNumber:
                setHintEt(et_IDNumber,hasFocus);
                break;
            case R.id.et_forget_phone1:
                setHintEt(et_phone1,hasFocus);
                break;
            case R.id.et_dialog_Password:
                setHintEt(et_password,hasFocus);
                break;
            case R.id.et_dialog_RePassword:
                setHintEt(et_repassword,hasFocus);
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

    private void initInfo() {
        phone = et_phone.getText().toString();
        verification_code = et_verification_code.getText().toString();
    }


    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ll_layout1.setVisibility(View.VISIBLE);
                    ll_layout2.setVisibility(View.VISIBLE);
                    ll_layout3.setVisibility(View.GONE);
                    ll_layout4.setVisibility(View.GONE);
                    btn_forgot_byMobile.setVisibility(View.VISIBLE);
                    btn_forgot_byIDNumber.setVisibility(View.GONE);
                    btn_byMobile.setTextColor(getResources().getColor(R.color.colorCyan));
                    btn_byIDNumber.setTextColor(getResources().getColor(R.color.gray));
                    et_IDNumber.setText("");
                    et_phone1.setText("");
                    break;
                case 2:
                    ll_layout1.setVisibility(View.GONE);
                    ll_layout2.setVisibility(View.GONE);
                    ll_layout3.setVisibility(View.VISIBLE);
                    ll_layout4.setVisibility(View.VISIBLE);
                    btn_forgot_byMobile.setVisibility(View.GONE);
                    btn_forgot_byIDNumber.setVisibility(View.VISIBLE);
                    btn_byMobile.setTextColor(getResources().getColor(R.color.gray));
                    btn_byIDNumber.setTextColor(getResources().getColor(R.color.colorCyan));
                    et_phone.setText("");
                    et_verification_code.setText("");
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_forget_verification_code:
                existsMobile();
                break;
            case R.id.ll_back:
                finish();
                break;
            case R.id.btn_forgot_byMobile:
                verifyCode();
                break;
            case R.id.btn_forgot_byIDNumber:
                existsIDNumber();
                break;
            case R.id.btn_byMobile:
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_byIDNumberAndMobile:
                myHandler.sendEmptyMessage(2);
                break;
            default:
                break;
        }
    }

    //通过手机验证弹出Dialog
    private void byMobileDialogPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.forgot_password, null);
        final Button btn_submit = (Button) view.findViewById(R.id.btn_submit_password);
        et_password = (EditText) view.findViewById(R.id.et_dialog_Password);
        et_repassword = (EditText) view.findViewById(R.id.et_dialog_RePassword);
        et_password.setOnFocusChangeListener(this);
        et_password.setOnFocusChangeListener(this);
        if (btn_submit != null) {
            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nPassword = et_password.getText().toString();
                    nRePassword = et_repassword.getText().toString();
                    newPassword();
                }
            });
        }
        builder.setView(view);
        builder.create().show();
    }

    private Btn_TimeCountUtil btn_timeCountUtil;//发送验证码的计时器

    private void sendCode() {
        //先判断手机号码是否输入正确
        String phoneNumber = et_phone.getText().toString();
        if (Util.isPhoneNumber(phoneNumber)) {
            //开始计时
            btn_timeCountUtil = new Btn_TimeCountUtil(this, 60000, 1000, btn_verification_code);
            btn_timeCountUtil.start();
            //发送短信验证码
            AppActionImpl.getInstance(getApplicationContext()).getVerification(phoneNumber, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    showToast("验证码已发送");
                }

                @Override
                public void onFailure(String errorEvent, String message) {
                    showToast("验证码发送失败,"+message);
                }
            });
        } else {
            showToast("请填写正确的手机号码");
        }
    }


    private void verifyCode() {
        showNormalDialog("正在验证…");
        initInfo();
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号");
        } else if (TextUtils.isEmpty(verification_code)) {
            showToast("请输入验证码");
        } else {
            //验证验证码是否正确
            AppActionImpl.getInstance(getApplicationContext()).getverify(phone, verification_code,
                    new ActionCallbackListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            if (data) {
                                getByMobile();
                            } else
                                showToast("验证码错误");
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("验证码错误");
                        }
                    });
        }
        dissMissNormalDialog();
    }

    //判断是否存在手机号码
    private void existsMobile() {
        phone = et_phone.getText().toString();
        AppActionImpl.getInstance(getApplicationContext()).existsMobile(phone, 1, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                if (data.equals("true")) {
                    sendCode();
                } else {
                    showToast("该手机号码不存在");
                }

            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast(message);
            }
        });
    }

    //根据手机号码获取用户的ID
    private void getByMobile() {
        phone = et_phone.getText().toString();
        AppActionImpl.getInstance(getApplicationContext()).getByMobile(phone, 1, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                userId = data;
                byMobileDialogPassword();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast(message);
            }
        });
    }

    //通过验证手机修改密码，添加新密码
    private void newPassword() {
        if (TextUtils.isEmpty(nPassword)){
            showToast("请输入新密码");
        }else if (nPassword.length() >= 6 ) {
            if (!TextUtils.isEmpty(nRePassword)) {
                if (nPassword.equals(nRePassword)) {
                    AppActionImpl.getInstance(getApplicationContext()).setnew_PSWD(userId, nPassword, new ActionCallbackListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            showToast("修改成功");
                            ForgotPasswordActivity.this.finish();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("网络异常，请检查后重试");
                        }
                    });
                } else {
                    showToast("两次新密码输入不相同！");
                }
            } else {
                showToast("请再次输入密码");
            }
        } else {
                showToast("输入密码不能少于六位");
        }
    }


    //判断是否存在身份证和手机号
    private void existsIDNumber() {
        showNormalDialog("正在验证中…");
        idnumber = et_IDNumber.getText().toString();
        phone = et_phone1.getText().toString();
        if (!TextUtils.isEmpty(idnumber)) {
            if (Util.isPhoneNumber(phone)) {
                AppActionImpl.getInstance(getApplicationContext()).existsIDNumber(idnumber, phone, 1, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data.equals("true")) {
                            getByIDNumber();
                        } else if (data.equals("false")) {
                            showToast("证件号或手机号码错误");
                        }

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast(message);
                    }
                });
            } else {
                showToast("手机号码错误");
            }
        } else {
            showToast("证件号码错误");
        }
        dissMissNormalDialog();

    }

    //通过身份证和手机号获取用户ID
    private void getByIDNumber() {
        idnumber = et_IDNumber.getText().toString();
        phone = et_phone1.getText().toString();
        AppActionImpl.getInstance(getApplicationContext()).getByIDNumber(idnumber, phone, 1, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                userId = data;
                byMobileDialogPassword();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast(message);
            }
        });
    }

}
