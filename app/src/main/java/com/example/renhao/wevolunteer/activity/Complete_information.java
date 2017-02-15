package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/9/18  11:18.
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.core.AppAction;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;

import java.util.ArrayList;
import java.util.List;

public class Complete_information extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private VolunteerViewDto personal_data;
    private AppAction mAction;
    private ImageView btn_back;

    public static final int ORG_REGISTER = 2;

    private Btn_TimeCountUtil btn_timeCountUtil;//发送验证码的计时器
    private Button btn_verification_code;
    private LinearLayout LL_trueName, LL_Id_type, LL_IDNumber, LL_Email, LL_Phone, LL_verification, LL_ORG;
    private EditText et_phone, et_verification, et_trueName, et_Email, et_idNumber;
    private TextView tv_cardType, tv_ORG_show;
    private String cardCode;
    private String orgName;
    private String orgId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_information);

        mAction = new AppActionImpl(this);
        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");

        initView();
        initViewListener();
        if (personal_data.getTrueName() != null)
            et_trueName.setText(personal_data.getTrueName());

        if (personal_data.getIdNumber() != null) {
            et_idNumber.setText(personal_data.getIdNumber());
        }

        if (personal_data.getEmail() != null)
            et_Email.setText(personal_data.getEmail());

        if (personal_data.getMobile() != null) {
            et_phone.setText(personal_data.getMobile());
        }

        final TextView tv_submit = (TextView) findViewById(R.id.tv_complete_information_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog("正在提交");
                tv_submit.setClickable(false);
                if (!Util.isPhoneNumber(et_phone.getText().toString())) {
                    showToast("请输入正确的电话号码");
                    dissMissNormalDialog();
                } else if (!Util.isEmail(et_Email.getText().toString())) {
                    showToast("请输入正确的邮箱地址");
                } else {
                    final String phone = et_phone.getText().toString();
                    final String email = et_Email.getText().toString();

                    tv_submit.setClickable(true);
                    CompleteSubmit();

                    /*AppActionImpl.getInstance(getApplicationContext()).existsEmail(email, 1, new ActionCallbackListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            if (data.equals("true") && !email.equals(personal_data.getEmail())) {
                                showToast("邮箱地址已被使用");
                                dissMissNormalDialog();
                                tv_submit.setClickable(true);
                            } else {
                                if (!Util.isPhoneNumber(et_phone.getText().toString())) {
                                    showToast("请输入正确的电话号码");
                                } else {
                                    final String phone = et_phone.getText().toString();
                                    AppActionImpl.getInstance(getApplicationContext()).existsMobile(phone, 1, new ActionCallbackListener<String>() {
                                        @Override
                                        public void onSuccess(String data) {
                                            if (data.equals("true") && !phone.equals(personal_data.getMobile())) {
                                                dissMissNormalDialog();
                                                showToast("该手机号码已存在");
                                                return;
                                            } else {
                                                dissMissNormalDialog();
                                                tv_submit.setClickable(true);
                                                CompleteSubmit();
                                            }
                                        }
                                        @Override
                                        public void onFailure(String errorEvent, String message) {
                                            showToast(message);
                                            tv_submit.setClickable(true);
                                            dissMissNormalDialog();
                                        }
                                    });
                                }
                            }
                        }
                        @Override
                        public void onFailure(String errorEvent, String message) {
                            tv_submit.setClickable(true);
                        }
                    });*/
                }
                dissMissNormalDialog();
            }
        });
        //回退按钮
        btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitLogin();
            }
        });

    }

    private void CompleteSubmit() {
        //修改项
        if (et_trueName.getText() == null) {
            showToast("真实姓名为空");
            dissMissNormalDialog();
        } else if (!Util.isEmail(et_Email.getText().toString())) {
            showToast("请填写正确邮箱地址");
            dissMissNormalDialog();
        } else if (et_idNumber.getText() == null) {
            showToast("请填写证件号码");
            dissMissNormalDialog();
        } else if (TextUtils.isEmpty(cardCode)) {
            showToast("请选择证件类型");
            dissMissNormalDialog();
        } else if (et_phone.getText() == null) {
            showToast("请填写电话号码");
            dissMissNormalDialog();
        } else if (orgId == null) {
            showToast("请选择所属机构");
            dissMissNormalDialog();
        } else {
            personal_data.setTrueName(et_trueName.getText().toString());
            personal_data.setEmail(et_Email.getText().toString());
            personal_data.setCardType(Integer.valueOf(cardCode));
            personal_data.setIdNumber(et_idNumber.getText().toString());
            personal_data.setMobile(et_phone.getText().toString());
            personal_data.setOrgIds(orgId);


            List<VolunteerViewDto> vl_updates = new ArrayList<>();
            vl_updates.add(personal_data);
            AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    dissMissNormalDialog();
                    showToast("提交成功");
                    Complete_information.this.finish();
                }

                @Override
                public void onFailure(String errorEvent, String message) {
                    dissMissNormalDialog();
                    showToast(message);
                }
            });
        }
    }

    private void initView() {
        //LinearLayout
        LL_trueName = (LinearLayout) findViewById(R.id.LL_complete_trueName);
        LL_Id_type = (LinearLayout) findViewById(R.id.ll_credentials_type);
        LL_IDNumber = (LinearLayout) findViewById(R.id.LL_complete_IdNumber);
        LL_Email = (LinearLayout) findViewById(R.id.LL_complete_Email);
        LL_Phone = (LinearLayout) findViewById(R.id.LL_complete_phone);
        LL_ORG = (LinearLayout) findViewById(R.id.LL_complete_ORG);


        //textview
        tv_cardType = (TextView) findViewById(R.id.tv_credentials_type);
        tv_ORG_show = (TextView) findViewById(R.id.tv_complete_ORG);

        //EditText
        et_phone = (EditText) findViewById(R.id.et_complete_phone);
        et_trueName = (EditText) findViewById(R.id.et_complete_trueName);
        et_Email = (EditText) findViewById(R.id.et_complete_email);
        et_idNumber = (EditText) findViewById(R.id.et_complete_id_number);


        LL_ORG.setOnClickListener(this);
        LL_Id_type.setOnClickListener(this);

    }


    private void initViewListener(){
        et_trueName.setOnFocusChangeListener(this);
        et_idNumber.setOnFocusChangeListener(this);
        et_Email.setOnFocusChangeListener(this);
        et_phone.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.et_complete_trueName:
                setHintEt(et_trueName,hasFocus);
                break;
            case R.id.et_complete_id_number:
                setHintEt(et_idNumber,hasFocus);
                break;
            case R.id.et_complete_phone:
                setHintEt(et_phone,hasFocus);
                break;
            case R.id.et_complete_email:
                setHintEt(et_Email,hasFocus);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_credentials_type:
                startActivityForResult(new Intent(Complete_information.this, CardTypeActivity.class), 1);
                break;

            case R.id.LL_complete_ORG:
                Intent orgIntent = new Intent(Complete_information.this, OrgSelectActivity.class);
                orgIntent.putExtra("personal_data",personal_data);
                orgIntent.putExtra("type", ORG_REGISTER);
                startActivityForResult(orgIntent, ORG_REGISTER);
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String cardType_text = data.getStringExtra("type_text");
            if (cardType_text != null) {
                tv_cardType.setText(cardType_text);
                cardCode = data.getStringExtra("typeCode");
            }
        }
        if (requestCode == ORG_REGISTER && resultCode == RESULT_OK) {
            personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
            orgName = data.getStringExtra("orgName");
            orgId = data.getStringExtra("orgId");
            tv_ORG_show.setText(orgName);
        }
    }

    @Override
    public void onBackPressed() {
        exitLogin();
        super.onBackPressed();
    }

    /**
     * 退出登录
     */
    public void exitLogin() {
        LocalDate.getInstance(this).setLocalDate("volunteerId", "");
        LocalDate.getInstance(this).setLocalDate("isLogin", false);
        LocalDate.getInstance(this).setLocalDate("access_token", "");
        LocalDate.getInstance(this).setLocalDate("portrait", null);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
