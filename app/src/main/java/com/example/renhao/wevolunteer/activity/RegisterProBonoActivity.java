package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.Attachment.AttachmentParaDto;
import com.example.model.Attachment.AttachmentsReturnDto;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.UpLoadFileEvent;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;
import com.github.jjobes.htmldialog.HtmlDialog;
import com.github.jjobes.htmldialog.HtmlDialogListener;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.renhao.wevolunteer.R.id.cb_register_agree;

/**
 * 注册专业志愿者界面  （属于登录界面的）
 */
public class RegisterProBonoActivity extends BaseActivity implements View.OnFocusChangeListener {
    private static final String TAG = "RegisterProBonoActivity";

    public static final int PERSONAL_ATTRIBUTE = 0;
    public static final int AREA_REGISTER = 1;
    public static final int ORG_REGISTER = 2;
    public static final int MY_POLIT = 3;
    public static final int MY_MAJOR = 4;
    public static final int MY_SERVICETIME = 5;
    public static final int MY_SERVICETYPE = 6;
    public static final int MY_SPECIALITY = 7;
    public static final int MY_CARDTYPE = 8;


    @Bind(R.id.ll_back)
    LinearLayout llBack;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_register_nickname)
    EditText etRegisterNickname;
    @Bind(R.id.et_register_trueName)
    EditText etRegisterTrueName;
    @Bind(R.id.tv_credentials_type)
    TextView tvCredentialsType;
    @Bind(R.id.ll_credentials_type)
    LinearLayout llCredentialsType;
    @Bind(R.id.et_register_id_number)
    EditText etRegisterIdNumber;
    @Bind(R.id.et_register_email)
    EditText etRegisterEmail;
    @Bind(R.id.et_register_password)
    EditText etRegisterPassword;
    @Bind(R.id.et_register_Repassword)
    EditText etRegisterRepassword;
    @Bind(R.id.LL_apply_area)
    LinearLayout LLApplyArea;
    @Bind(R.id.LL_apply_ORG)
    LinearLayout LLApplyORG;
    @Bind(R.id.ll_PoliticalAttribute)
    LinearLayout llPoliticalAttribute;
    //    @Bind(R.id.ll_speciality_advantages)
//    LinearLayout llSpecialityAdvantages;
    @Bind(R.id.ll_intention_time)
    LinearLayout llIntentionTime;
    @Bind(R.id.ll_intention_type)
    LinearLayout llIntentionType;
    @Bind(R.id.ll_professional_ability)
    LinearLayout llProfessionalAbility;
    @Bind(R.id.et_register_phone)
    EditText etRegisterPhone;
    @Bind(R.id.tv_clause)
    TextView tv_clause_click;
    @Bind(R.id.btn_register_verification_code)
    Button btnRegisterVerificationCode;
    @Bind(R.id.et_register_verification_code)
    EditText etRegisterVerificationCode;
    @Bind(cb_register_agree)
    CheckBox cbRegisterAgree;
    @Bind(R.id.btn_register_volunteer)
    Button btnRegisterVolunteer;
    @Bind(R.id.btn_back_login)
    Button btnBackLogin;
    @Bind(R.id.edit_register_workUnit)
    EditText editRegisterWorkUnit;
    @Bind(R.id.edit_register_Addr)
    EditText editRegisterAddr;
    @Bind(R.id.tv_register_pro_bono_attribute)
    TextView tv_attribute_show;
    @Bind(R.id.tv_register_pro_bono_area)
    TextView tv_area_show;
    @Bind(R.id.tv_register_pro_bono_ORG)
    TextView tv_ORG_show;
    @Bind(R.id.tv_register_pro_bono_polity)
    TextView tv_polity_show;
    //    @Bind(R.id.tv_register_pro_bono_specialty)
//    TextView tv_specialty_show;
    @Bind(R.id.tv_register_pro_bono_serviceTime)
    TextView tv_serviceTime_show;
    @Bind(R.id.tv_register_pro_bono_serviceType)
    TextView tv_serviceType_show;
    @Bind(R.id.tv_register_pro_bono_major)
    TextView tv_major_show;


    private String isCheck_register_agree;
    private String nickname;
    private String truename;
    private String cardType;
    private String cardCode;
    private String id_number;
    private String password;
    private String Repassword;
    private String email;
    private String personalCode;
    private String areaName;
    private String areaCode;
    private String orgName;
    private String orgId;
    private String polity;
    private String specialtyType;
    private String serviceTimeIntention;
    private String serviceIntention;
    private String serviceIntentionOther;
    private String skill;
    private String workUnlt;
    private String address;
    private String phone;
    private String verification_code;
    private String CertificatePic;

    private VolunteerViewDto personal_data;
    private List<AttachmentParaDto> files;

    private boolean Flag_major = false;
    private boolean Flag_time = false;
    private boolean Flag_type = false;
    private boolean Flag_speciality = false;
    private boolean Flag_polity = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_register_pro_bono);
        getAccessToken();
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        personal_data = new VolunteerViewDto();
        initViewListener();
    }

    /**
     * 1.不用 Bundle 封装数据   接收数据activity：intent.getSerializableExtra
     * 2. 用Bundle封装数据    接收activity：bundle.getSerializable
     * 3. VolunteerViewDto数据类里实现serializable的接口 可序列化  所以personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle result = new Bundle();
            switch (requestCode) {
                case PERSONAL_ATTRIBUTE:
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    if (personal_data != null) {
                        try {
                            personalCode = String.valueOf(personal_data.getJobStatus());
                            System.out.println("personalCode------" + personalCode);
                            tv_attribute_show.setText(personal_data.getJobStatusStr());
                        } catch (Exception e) {
                        }
                    }
                    break;
                case MY_POLIT:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    String polity_name = result.getString("polity_name");
                    if (personal_data != null) {
                        polity = personal_data.getPolity();
                        System.out.println("polity------" + polity);
                        tv_polity_show.setText(polity_name);
                    }
                    Flag_polity = true;
                    break;
              /*  case MY_MAJOR:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (personal_data != null) {
                        specialtyType = personal_data.getSpecialtyType();
                        System.out.println("specialtyType------" + specialtyType);
                        tv_specialty_show.setText(personal_data.getSpecialty());
                    }
                    Flag_major = true;
                    break;*/
                case MY_SERVICETIME:
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    if (personal_data != null) {
                        serviceTimeIntention = personal_data.getServiceTimeIntention();
                        tv_serviceTime_show.setText("已选");
                    }
                    Flag_time = true;
                    break;
                case MY_SERVICETYPE:
                    result = data.getExtras();
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    String serviceIntentionStr = result.getString("serviceIntentionStr");
                    if (personal_data != null) {
                        serviceIntention = personal_data.getServiceIntention();
                        tv_serviceType_show.setText(serviceIntentionStr);
                    }
                    if (personal_data != null) {
                        serviceIntentionOther = personal_data.getServiceIntentionOther();
                        tv_serviceType_show.setText(serviceIntentionStr);
                    }
                    Flag_type = true;
                    break;
                case MY_SPECIALITY:
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    if (personal_data != null) {
                        skill = personal_data.getSkilled();
                        tv_major_show.setText(skill);
                    }
                    if (personal_data != null) {
                        CertificatePic = personal_data.getCertificatePic();
                        System.out.println("CertificatePic-----" + CertificatePic);
                    }
                    Flag_speciality = true;
                    break;
                case AREA_REGISTER:
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    areaName = data.getStringExtra("areaName");
                    areaCode = data.getStringExtra("areaCode");
                    tv_area_show.setText(areaName);
                    break;
                case ORG_REGISTER:
                    personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
                    orgName = data.getStringExtra("orgName");
                    orgId = data.getStringExtra("orgId");
                    tv_ORG_show.setText(orgName);
                    break;
                case MY_CARDTYPE:
                    String cardType_text = data.getStringExtra("type_text");
                    if (cardType_text != null) {
                        tvCredentialsType.setText(cardType_text);
                        cardCode = data.getStringExtra("typeCode");
                    }
//            personalCode = data.getStringExtra("personCode");
                    break;
                default:
                    break;
            }
        }

    }

    //用户填写的注册的信息
    private void registerInfo() {
        nickname = etRegisterNickname.getText().toString();
        truename = etRegisterTrueName.getText().toString();
        cardType = tvCredentialsType.getText().toString();
        id_number = etRegisterIdNumber.getText().toString();
        phone = etRegisterPhone.getText().toString();
        password = etRegisterPassword.getText().toString();
        Repassword = etRegisterRepassword.getText().toString();
        email = etRegisterEmail.getText().toString();
        workUnlt = editRegisterWorkUnit.getText().toString();
        address = editRegisterAddr.getText().toString();
        verification_code = etRegisterVerificationCode.getText().toString();
    }

    private void initViewListener(){
        etRegisterNickname.setOnFocusChangeListener(this);
        etRegisterTrueName.setOnFocusChangeListener(this);
        etRegisterIdNumber.setOnFocusChangeListener(this);
        etRegisterEmail.setOnFocusChangeListener(this);
        etRegisterPassword.setOnFocusChangeListener(this);
        etRegisterRepassword.setOnFocusChangeListener(this);
        editRegisterWorkUnit.setOnFocusChangeListener(this);
        editRegisterAddr.setOnFocusChangeListener(this);
        etRegisterPhone.setOnFocusChangeListener(this);
        etRegisterVerificationCode.setOnFocusChangeListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.et_register_nickname:
                setHintEt(etRegisterNickname,hasFocus);
                break;
            case R.id.et_register_trueName:
                setHintEt(etRegisterTrueName,hasFocus);
                break;
            case R.id.et_register_id_number:
                setHintEt(etRegisterIdNumber,hasFocus);
                break;
            case R.id.et_register_phone:
                setHintEt(etRegisterPhone,hasFocus);
                break;
            case R.id.et_register_password:
                setHintEt(etRegisterPassword,hasFocus);
                break;
            case R.id.et_register_Repassword:
                setHintEt(etRegisterRepassword,hasFocus);
                break;
            case R.id.et_register_email:
                setHintEt(etRegisterEmail,hasFocus);
                break;
            case R.id.edit_register_workUnit:
                setHintEt(editRegisterWorkUnit,hasFocus);
                break;
            case R.id.edit_register_Addr:
                setHintEt(editRegisterAddr,hasFocus);
                break;
            case R.id.et_register_verification_code:
                setHintEt(etRegisterVerificationCode,hasFocus);
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


    @OnClick({R.id.ll_back, R.id.ll_credentials_type, R.id.ll_personal_attribute, R.id.LL_apply_area, R.id.LL_apply_ORG, R.id.ll_PoliticalAttribute/*, R.id.ll_speciality_advantages*/, R.id.ll_intention_time, R.id.ll_intention_type, R.id.ll_professional_ability, R.id.btn_register_verification_code, cb_register_agree, R.id.btn_register_volunteer, R.id.btn_back_login, R.id.tv_clause})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_credentials_type:
                startActivityForResult(new Intent(RegisterProBonoActivity.this, CardTypeActivity.class), MY_CARDTYPE);
                break;
            case R.id.ll_personal_attribute:
                Intent intent = new Intent();
                intent.putExtra("personal_data", personal_data);
                intent.setClass(RegisterProBonoActivity.this, AttributeAtivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, PERSONAL_ATTRIBUTE);
                break;
            case R.id.LL_apply_area:
                Intent areaintent = new Intent();
                areaintent.putExtra("personal_data", personal_data);
                areaintent.setClass(RegisterProBonoActivity.this, AreaSelectAction2.class);
                areaintent.putExtra("type", AREA_REGISTER);
                startActivityForResult(areaintent, AREA_REGISTER);
                break;
            case R.id.LL_apply_ORG:
                Intent orgIntent = new Intent(RegisterProBonoActivity.this, OrgSelectActivity.class);
                orgIntent.putExtra("personal_data", personal_data);
                orgIntent.putExtra("orgnames", orgName);
                orgIntent.putExtra("type", ORG_REGISTER);
                startActivityForResult(orgIntent, ORG_REGISTER);
                break;
            case R.id.ll_PoliticalAttribute:
                Intent PoliticalAttributeintent = new Intent();
                PoliticalAttributeintent.putExtra("personal_data", personal_data);
                PoliticalAttributeintent.putExtra("type", 0);
                PoliticalAttributeintent.setClass(this, PoliticsstatusActivity.class);
                startActivityForResult(PoliticalAttributeintent, MY_POLIT);
                break;
          /*  case R.id.ll_speciality_advantages:
                intent.setClass(this, SpecilaAbilityActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, MY_MAJOR);
                break;*/
            case R.id.ll_intention_time:
                Intent intention_time_intent = new Intent();
                intention_time_intent.putExtra("personal_data", personal_data);
                intention_time_intent.setClass(this, ApplyServiceTimeActivity.class);
                startActivityForResult(intention_time_intent, MY_SERVICETIME);
                break;
            case R.id.ll_intention_type:
                Intent intention_type_intent = new Intent();
                intention_type_intent.putExtra("personal_data", personal_data);
                intention_type_intent.setClass(this, ServiceCategoryActivity.class);
                intention_type_intent.putExtra("type", 0);
                startActivityForResult(intention_type_intent, MY_SERVICETYPE);
                break;
            case R.id.ll_professional_ability:
                Intent professional_ability_intent = new Intent();
                professional_ability_intent.putExtra("personal_data", personal_data);
                professional_ability_intent.setClass(this, ApplyMajorAbilityActivity.class);
                startActivityForResult(professional_ability_intent, MY_SPECIALITY);
                break;
            case R.id.btn_register_verification_code:
                sendCode();
                break;
            case cb_register_agree:
                if (((CheckBox) view).isChecked()) {
                    isCheck_register_agree = cbRegisterAgree.getText().toString();
                } else {
                    isCheck_register_agree = null;
                }
                break;
            case R.id.tv_clause:
                new HtmlDialog.Builder(getFragmentManager())
                        .setHtmlResId(R.raw.clause_volunteer)
                        .setTitle("宁波市注册志愿者管理办法")
                        .setShowNegativeButton(true)
                        .setNegativeButtonText("我拒绝")
                        .setShowPositiveButton(true)
                        .setPositiveButtonText("我同意")
                        .setListener(new HtmlDialogListener() {
                            @Override
                            public void onNegativeButtonPressed() {
                                isCheck_register_agree = null;
                                cbRegisterAgree.setChecked(false);
                            }

                            @Override
                            public void onPositiveButtonPressed() {
                                isCheck_register_agree = cbRegisterAgree.getText().toString();
                                cbRegisterAgree.setChecked(true);
                            }
                        })
                        .build()
                        .show();
                break;
            case R.id.btn_register_volunteer:
                informationJudge();
                break;
            case R.id.btn_back_login:
                startActivity(new Intent(RegisterProBonoActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    private Btn_TimeCountUtil btn_timeCountUtil;//发送验证码的计时器

    private void sendCode() {
        //先判断手机号码是否输入正确
        String phoneNumber = etRegisterPhone.getText().toString();
        if (Util.isPhoneNumber(phoneNumber)) {
            //开始计时
            btn_timeCountUtil = new Btn_TimeCountUtil(RegisterProBonoActivity.this, 60000, 1000, btnRegisterVerificationCode);
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

    //提交注册信息的接口
    public void RegisterSubmit() {
        showNormalDialog("正在提交数据，请稍后....");
        VolunteerCreateDto vl_create = new VolunteerCreateDto();
        vl_create.setLoginUserName(nickname);
        vl_create.setTrueName(truename);
        vl_create.setCardType(Integer.parseInt(cardCode));
        vl_create.setIdNumber(id_number);
        vl_create.setEmail(email);
        vl_create.setUserPassword(password);
        vl_create.setReUserPassword(Repassword);
        vl_create.setJobStatus(Integer.valueOf(personalCode));
        vl_create.setAreaCode(areaCode);
        vl_create.setOrgIds(orgId);
        vl_create.setPolity(polity);//政治面貌
        vl_create.setWorkunit(workUnlt);//工作单位
        vl_create.setDomicile(address);//地址
        vl_create.setServiceTimeIntention(serviceTimeIntention);//服务时间意向
        vl_create.setServiceIntention(serviceIntention);//服务意向
        vl_create.setSkilled(skill);//专业类型文字输入
//        vl_create.setCertificatePic(CertificatePic);//专业证书
        vl_create.setMobile(phone);
        vl_create.setAuditStatus(3);//审核状态  0未审核 1审核通过 2审核不通过 ,3待审核
        vl_create.setIsSpeciality(true);

        List<VolunteerCreateDto> vl_creates = new ArrayList<>();
        vl_creates.add(vl_create);
        AppActionImpl.getInstance(getApplicationContext()).volunteerCreate(vl_creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                dissMissNormalDialog();
                if (data == null || data.size() < 1)
                    return;
                showToast("注册成功，请耐心等待审核");
                startActivity(new Intent(RegisterProBonoActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dissMissNormalDialog();
                showToast("注册失败 " + message);
            }
        });
    }

    /**
     * 判断用户注册信息是否为空
     */
    private void informationJudge() {
        registerInfo();
        if (TextUtils.isEmpty(nickname)) {
            showToast("昵称不能为空");
        } else if (TextUtils.isEmpty(truename)) {
            showToast("姓名不能为空");
        } else if (TextUtils.isEmpty(cardCode)) {
            showToast("证件类型不能为空");
        } else if (TextUtils.isEmpty(id_number)) {
            showToast("证件号码错误");
        } else if (!Util.isEmail(email)) {
            showToast("请输入正确的电子邮箱");
        } else if (TextUtils.isEmpty(password) || password.length() < 6) {
            showToast("密码不能为空");
        } else if (TextUtils.isEmpty(Repassword)) {
            showToast("确认密码不能为空");
        } else if (!Repassword.equals(password)) {
            showToast("两次密码不同，请重新确认");
        } else if (TextUtils.isEmpty(personalCode)) {
            showToast("请选择个人属性");
        } else if (TextUtils.isEmpty(areaCode)) {
            showToast("请选择所在区域");
        } else if (TextUtils.isEmpty(orgId)) {
            showToast("请选择所属机构");
        } else if (!Flag_polity) {
            showToast("请选择政治面貌");
        } /*else if (!Flag_major) {
            showToast("请填写专业特长");
        }*/ else if (!Flag_time) {
            showToast("请选择意向服务时间");
        } else if (!Flag_type) {
            showToast("请选择意向服务类型");
        } else if (!Flag_speciality) {
            showToast("请选择专业能力");
        } else if (TextUtils.isEmpty(workUnlt)) {
            showToast("工作单位不能为空");
        } else if (TextUtils.isEmpty(address)) {
            showToast("家庭地址不能为空");
        } else if (!Util.isPhoneNumber(phone)) {
            showToast("请输入正确的手机号");
        } else if (TextUtils.isEmpty(verification_code)) {
            showToast("请输入验证码");
        } else if (TextUtils.isEmpty(isCheck_register_agree)) {
            showToast("请同意《宁波市注册志愿者管理法办》");
        } else {
            //验证验证码是否正确
            AppActionImpl.getInstance(getApplicationContext()).getverify(phone, verification_code,
                    new ActionCallbackListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            if (data) {
                                AppActionImpl.getInstance(getApplicationContext()).update_major_attachment(files,
                                        new ActionCallbackListener<List<AttachmentsReturnDto>>() {
                                            @Override
                                            public void onSuccess(List<AttachmentsReturnDto> data) {
                                                //申请资料上传
                                                RegisterSubmit();
                                            }

                                            @Override
                                            public void onFailure(String errorEvent, String message) {
                                                showToast("证书上传失败");
                                            }
                                        });
                            } else {
                                showToast("验证码错误");
                            }
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("验证码错误");
                        }
                    });

        }
    }

    @Subscribe
    public void onEventMainThread(UpLoadFileEvent event) {
        files = event.getFiles();
        System.out.println("files----------" + files);
        Logger.v("----", "get File");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
