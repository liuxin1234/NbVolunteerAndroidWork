package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerCreateDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;
import com.github.jjobes.htmldialog.HtmlDialog;
import com.github.jjobes.htmldialog.HtmlDialogListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.ButterKnife;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private static final String TAG = "RegisterActivity";

    public static final int PERSONAL_ATTRIBUTE = 0;
    public static final int CARD_TYPE = 1;
    public static final int AREA_REGISTER = 2;
    public static final int ORG_REGISTER = 3;
    public static final int MY_POLIT = 4;
    public static final int VOLUNTEER_TYPE = 5;

    CheckBox cb_register_agree; //是否同意 选择按钮
//    EditText et_nickname; //昵称
    EditText et_truename;   //真实姓名
    EditText et_id_number;  //身份证号码
    EditText et_password;   //密码
    EditText et_Repassword; //重复密码
//    EditText et_email;    //邮箱
    EditText et_phone;      //输入手机号
    EditText et_verification_code;  //输入验证码
    Button btn_verification_code;   //发送验证码按钮
    Button btn_register_volunteer;  //注册按钮
    Button btn_back_login;  //返回登录界面按钮
//    ImageView img_back;     //头部导航栏返回图标按钮
    LinearLayout ll_area;   // 区域选择
    LinearLayout ll_org;  //机构选择
    LinearLayout ll_credentials_type;   //证件类型
    LinearLayout ll_personal_attribute; //个人属性
//    LinearLayout ll_Political_Attribute; //政治面貌
    TextView tv_cardType;   //证件类型选择后显示的内容
    TextView areaNameTv;    //所属区域名字text
    TextView orgNameTv;   //所属机构名字text
    TextView clause;        //条款text
    LinearLayout ll;        //整个view的父组件
    TextView attribute_show;    //个人属性 text
//    TextView tv_polity_show;
//    @Bind(R.id.tv_volunteer_type)
//    TextView tvVolunteerType;  //平安志愿者类型
//    @Bind(R.id.ll_volunteer_type)
//    LinearLayout llVolunteerType; //平安志愿者选择

    private VolunteerViewDto personal_data;

    private String isCheck_register_agree;
//    private String nickname;  //昵称
    private String truename;    //真实姓名
//    private String cardType;  //证件类型
    private String cardCode = "1";    //证件类型code 默认为 1 表示内地居民身份证
    private String id_number;   //身份证号
    private String phone;       //手机号
    private String password;    //密码
    private String Repassword;  //重复密码
//    private String email;     //邮箱
    private String verification_code;   //短信验证码
    private String areaName;    //所在区域名字
    private String areaId;      //所在区域ID
    private String areaCode;    //所在区域code
    private String orgName;   //所属机构名字
    private String orgId;     //所属机构ID
    private String personalCode;    //个人属性code

//    private String polity;    //政治面貌
//    private boolean Flag_polity = false;    //是否选择了政治面貌

    private String volunteerTagName;    //平安志愿者类型名字
    private Integer volunteerTagCode;   //平安志愿者类型code
    private String userId;  //用户id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,getResources().getString(R.string.title_register));


        personal_data = new VolunteerViewDto();
        initView();
        initViewListener();
        getAccessToken();
        userId = UUID.randomUUID().toString();
    }

    private void initView() {
        cb_register_agree = (CheckBox) findViewById(R.id.cb_register_agree);
//        et_nickname = (EditText) findViewById(R.id.et_register_nickname);
        et_truename = (EditText) findViewById(R.id.et_register_trueName);
        et_id_number = (EditText) findViewById(R.id.et_register_id_number);
        et_phone = (EditText) findViewById(R.id.et_register_phone);
        et_password = (EditText) findViewById(R.id.et_register_password);
        et_Repassword = (EditText) findViewById(R.id.et_register_Repassword);
//        et_email = (EditText) findViewById(R.id.et_register_email);
        et_verification_code = (EditText) findViewById(R.id.et_register_verification_code);
        btn_verification_code = (Button) findViewById(R.id.btn_register_verification_code);
        btn_register_volunteer = (Button) findViewById(R.id.btn_register_volunteer);
        btn_back_login = (Button) findViewById(R.id.btn_back_login);
//        img_back = (ImageView) findViewById(R.id.imageView_btn_back);
        ll_area = (LinearLayout) findViewById(R.id.LL_apply_area);
        ll_org = (LinearLayout) findViewById(R.id.LL_apply_ORG);
        ll_credentials_type = (LinearLayout) findViewById(R.id.ll_credentials_type);
        ll_personal_attribute = (LinearLayout) findViewById(R.id.ll_personal_attribute);
        tv_cardType = (TextView) findViewById(R.id.tv_credentials_type);
        areaNameTv = (TextView) findViewById(R.id.tv_register_areaName);
        orgNameTv = (TextView) findViewById(R.id.tv_register_orgName);
        clause = (TextView) findViewById(R.id.tv_clause);
        ll = (LinearLayout) findViewById(R.id.ll_register);
//        ll_Political_Attribute = (LinearLayout) findViewById(R.id.ll_PoliticalAttribute);
//        tv_polity_show = (TextView) findViewById(R.id.tv_register_pro_bono_polity);
        attribute_show = (TextView) findViewById(R.id.tv_register_attribute);
        //这里证件类型默认显示内地居民身份证
        tv_cardType.setText("内地居民身份证");
    }

    private void initViewListener() {
        cb_register_agree.setOnClickListener(this);
        btn_verification_code.setOnClickListener(this);
        btn_register_volunteer.setOnClickListener(this);
        btn_back_login.setOnClickListener(this);
//        img_back.setOnClickListener(this);
        ll_area.setOnClickListener(this);
        ll_org.setOnClickListener(this);
        ll_credentials_type.setOnClickListener(this);
        ll_personal_attribute.setOnClickListener(this);
        clause.setOnClickListener(this);
//        et_nickname.setOnFocusChangeListener(this);
        et_truename.setOnFocusChangeListener(this);
        et_id_number.setOnFocusChangeListener(this);
        et_phone.setOnFocusChangeListener(this);
        et_password.setOnFocusChangeListener(this);
        et_Repassword.setOnFocusChangeListener(this);
//        et_email.setOnFocusChangeListener(this);
        et_verification_code.setOnFocusChangeListener(this);
        ll.setOnTouchListener(onTouchListener);
//        ll_Political_Attribute.setOnClickListener(this);
    }

    //用户填写的注册的信息
    private void registerInfo() {
//        nickname = et_nickname.getText().toString();
        truename = et_truename.getText().toString();
//        cardType = tv_cardType.getText().toString();
        id_number = et_id_number.getText().toString().trim();
        phone = et_phone.getText().toString().trim();
        password = et_password.getText().toString().trim();
        Repassword = et_Repassword.getText().toString().trim();
//        email = et_email.getText().toString();
        verification_code = et_verification_code.getText().toString().trim();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //证件类型的数据回调
        if (requestCode == CARD_TYPE && resultCode == RESULT_OK) {
            String cardType_text = data.getStringExtra("type_text");
            if (cardType_text != null) {
                tv_cardType.setText(cardType_text);
                cardCode = data.getStringExtra("typeCode");
            }
        }
        //所在区域的数据回调
        if (requestCode == AREA_REGISTER && resultCode == RESULT_OK) {
            areaName = data.getStringExtra("areaName");
            areaId = data.getStringExtra("areaId");
            areaCode = data.getStringExtra("areaCode");
            personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
            areaNameTv.setText(areaName);
            System.out.println("areaCode--------" + areaCode);
        }
        //所属机构的数据回调
        if (requestCode == ORG_REGISTER && resultCode == RESULT_OK) {
            personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
            orgName = data.getStringExtra("orgName");
            orgId = data.getStringExtra("orgId");
            orgNameTv.setText(orgName);
            System.out.println("orgId----------" + orgId);
        }
        //个人属性的数据回调
        if (requestCode == PERSONAL_ATTRIBUTE && resultCode == RESULT_OK) {
            Bundle result = new Bundle();
            result = data.getExtras();
            personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
            if (personal_data != null) {
                try {
                    personalCode = String.valueOf(personal_data.getJobStatus());
                    attribute_show.setText(personal_data.getJobStatusStr());
                    System.out.println("personalCode------" + personalCode);
                } catch (Exception e) {
                }
            }
        }
        //政治面貌的数据回调
//        if (requestCode == MY_POLIT && resultCode == RESULT_OK) {
//            Bundle result = new Bundle();
//            result = data.getExtras();
//            personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
//            String polity_name = result.getString("polity_name");
//            if (personal_data != null) {
//                polity = personal_data.getPolity();
//                System.out.println("polity------" + polity);
//                tv_polity_show.setText(polity_name);
//            }
//            Flag_polity = true;
//        }
        //是否为平安志愿者的数据回调
//        if (requestCode == VOLUNTEER_TYPE && resultCode == RESULT_OK){
//            personal_data = (VolunteerViewDto) data.getSerializableExtra("personal_data");
//            if (personal_data != null) {
//                volunteerTagName = personal_data.getTagName();
//                volunteerTagCode = personal_data.getTag();
//                tvVolunteerType.setText(volunteerTagName);
//            }
//        }
    }

    /**
     * 给布局加触摸监听，当点击EditText之外的地方时
     * EditText失去焦点
     */
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            ll.setFocusable(true);
            ll.setFocusableInTouchMode(true);
            ll.requestFocus();//布局获得焦点
            return false;
        }
    };

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
//            case R.id.et_register_nickname:
//                setHintEt(et_nickname, hasFocus);
//                break;
            case R.id.et_register_trueName:
                setHintEt(et_truename, hasFocus);
                break;
            case R.id.et_register_id_number:
                setHintEt(et_id_number, hasFocus);
                break;
            case R.id.et_register_phone:
                setHintEt(et_phone, hasFocus);
                break;
            case R.id.et_register_password:
                setHintEt(et_password, hasFocus);
                break;
            case R.id.et_register_Repassword:
                setHintEt(et_Repassword, hasFocus);
                break;
//            case R.id.et_register_email:
//                setHintEt(et_email, hasFocus);
//                break;
            case R.id.et_register_verification_code:
                setHintEt(et_verification_code, hasFocus);
                break;
            default:
                break;
        }
    }

    private void setHintEt(EditText et, boolean hasFocus) {
        String hint;
        if (hasFocus) {
            hint = et.getHint().toString();
            et.setTag(hint);
            et.setHint("");
        } else {
            hint = et.getTag().toString();
            et.setHint(hint);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //证件类型
            case R.id.ll_credentials_type:
                startActivityForResult(new Intent(RegisterActivity.this, CardTypeActivity.class), 1);
                break;
            //个人属性
            case R.id.ll_personal_attribute:
                Intent intent = new Intent();
                intent.putExtra("personal_data", personal_data);
                intent.setClass(this, AttributeAtivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, PERSONAL_ATTRIBUTE);
                break;
            //所属区域
            case R.id.LL_apply_area:
                Intent areaIntent = new Intent(RegisterActivity.this, AreaSelectAction2.class);
                areaIntent.putExtra("personal_data", personal_data);
                areaIntent.putExtra("type", AREA_REGISTER);
                startActivityForResult(areaIntent, AREA_REGISTER);
                break;
            //所在机构
            case R.id.LL_apply_ORG:
                Intent orgIntent = new Intent(RegisterActivity.this, OrgSelectActivity.class);
                orgIntent.putExtra("orgnames", orgName);
                orgIntent.putExtra("personal_data", personal_data);
                orgIntent.putExtra("type", ORG_REGISTER);
                startActivityForResult(orgIntent, ORG_REGISTER);
                break;
            //政治面貌
//            case R.id.ll_PoliticalAttribute:
//                Intent PoliticalAttributeintent = new Intent();
//                PoliticalAttributeintent.putExtra("personal_data", personal_data);
//                PoliticalAttributeintent.putExtra("type", 0);
//                PoliticalAttributeintent.setClass(this, PoliticsstatusActivity.class);
//                startActivityForResult(PoliticalAttributeintent, MY_POLIT);
//                break;
            //条款是否同意按钮
            case R.id.cb_register_agree:
                if (((CheckBox) v).isChecked()) {
                    isCheck_register_agree = cb_register_agree.getText().toString();
                    showToast("已同意");
                } else {
                    isCheck_register_agree = null;
                    showToast("已取消");
                }
                break;
            //发生手机验证码按钮
            case R.id.btn_register_verification_code:
                sendCode();
                break;
            //注册按钮
            case R.id.btn_register_volunteer:
                informationJudge();
                break;
            //已有账号返回登录页按钮
            case R.id.btn_back_login:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
            //返回图标按钮
//            case R.id.imageView_btn_back:
//                finish();
//                break;
            //条款查看按钮
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
                                cb_register_agree.setChecked(false);
                            }

                            @Override
                            public void onPositiveButtonPressed() {
                                isCheck_register_agree = cb_register_agree.getText().toString();
                                cb_register_agree.setChecked(true);
                            }
                        })
                        .build()
                        .show();
                break;
            default:
                break;
        }
    }

    private Btn_TimeCountUtil btn_timeCountUtil;//发送验证码的计时器

    private void sendCode() {
        //先判断手机号码是否输入正确
        String phoneNumber = et_phone.getText().toString();
        if (Util.isPhoneNumber(phoneNumber)) {
            //开始计时
            btn_timeCountUtil = new Btn_TimeCountUtil(RegisterActivity.this, 60000, 1000, btn_verification_code);
            btn_timeCountUtil.start();
            //发送短信验证码
            AppActionImpl.getInstance(getApplicationContext()).getVerification(phoneNumber, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    showToast("验证码已发送");
                }

                @Override
                public void onFailure(String errorEvent, String message) {
                    showToast("验证码发送失败," + message);
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
        vl_create.setUserId(userId);
        vl_create.setLoginUserName(id_number);
        vl_create.setTrueName(truename);
        vl_create.setUserPassword(password);
        vl_create.setReUserPassword(Repassword);
        vl_create.setIdNumber(id_number);
        vl_create.setMobile(phone);
        vl_create.setAreaCode(areaCode);
        vl_create.setAreaCodes(areaCode);
        vl_create.setOrgIds(orgId);  //所在机构  这里默认注册到ae14862e-6383-4d23-9a5d-cc3caaad7e99
//        vl_create.setPolity(polity);//政治面貌
//        vl_create.setEmail(email);    //邮箱
//        vl_create.setTag(volunteerTagCode);   //是否为平安志愿者
        vl_create.setJobStatus(Integer.valueOf(personalCode));  //个人属性
        vl_create.setCardType(Integer.parseInt(cardCode));
        vl_create.setIsSpeciality(0);
        vl_create.setAuditStatus(0);
        List<VolunteerCreateDto> vl_creates = new ArrayList<>();
        vl_creates.add(vl_create);
        AppActionImpl.getInstance(getApplicationContext()).volunteerCreate(vl_creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                dissMissNormalDialog();
                if (data == null || data.size() < 1) {
                    showToast("注册失败");
                    return;
                }
                showToast("注册成功");
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                dissMissNormalDialog();
                showToast("注册失败  " + message);
            }
        });
    }

    /**
     * 判断用户注册信息是否为空
     */
    private void informationJudge() {
        registerInfo();
//        if (TextUtils.isEmpty(nickname)) {
//            showToast("昵称不能为空");
//        } else
        if (TextUtils.isEmpty(truename)) {
            showToast("真实姓名不能为空");
        } else if (TextUtils.isEmpty(cardCode)) {
            showToast("证件类型不能为空");
        } else if (cardCode.equals("1")) {
            if (!Util.isID(id_number)) {
                showToast("证件号码错误");
            } else {
                againInformation();
            }
        } else {
            if (TextUtils.isEmpty(id_number)) {
                showToast("证件号码不能为空");
            } else {
                againInformation();
            }
        }


    }


    /**
     * 该方法是： 验证身份证后继续对下面信息的验证
     */
    private void againInformation() {
//        if (!Util.isEmail(email)) {
//            showToast("请输入正确的电子邮箱");
//        } else
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            showToast("密码需要不小于六位");
        } else if (TextUtils.isEmpty(Repassword)) {
            showToast("确认密码不能为空");
        } else if (!Repassword.equals(password)) {
            showToast("两次密码不同，请重新确认");
        } else if (TextUtils.isEmpty(personalCode)) {
            showToast("请选择个人属性");
        } else if (TextUtils.isEmpty(areaCode)) {
            showToast("请选择所在区域");
        }
//        else if (TextUtils.isEmpty(orgId)) {
//            showToast("请选择所属机构");
//        }
//        else if (!Flag_polity) {
//            showToast("请选择政治面貌");
//        }else if (TextUtils.isEmpty(volunteerTagName)) {
//            showToast("请选择是否为平安志愿者");
//        }
        else if (!Util.isPhoneNumber(phone)) {
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
                                RegisterSubmit();
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

//    @OnClick(R.id.ll_volunteer_type)
//    public void onViewClicked() {
//        //是否为平安志愿者
//        Intent intentVolunteerType = new Intent();
//        intentVolunteerType.putExtra("personal_data", personal_data);
//        intentVolunteerType.putExtra("type", VOLUNTEER_TYPE);
//        intentVolunteerType.setClass(RegisterActivity.this, VolunteerTagActivity.class);
//        startActivityForResult(intentVolunteerType, VOLUNTEER_TYPE);
//    }
}
