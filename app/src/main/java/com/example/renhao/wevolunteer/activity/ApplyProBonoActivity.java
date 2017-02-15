package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.Attachment.AttachmentParaDto;
import com.example.model.Attachment.AttachmentsReturnDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.UpLoadFileEvent;
import com.example.renhao.wevolunteer.view.Btn_TimeCountUtil;
import com.example.renhao.wevolunteer.view.Polity_Pop;
import com.github.jjobes.htmldialog.HtmlDialog;
import com.github.jjobes.htmldialog.HtmlDialogListener;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * 申请专业志愿者界面 （属于我的界面里）
 */
public class ApplyProBonoActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {
    private static final String TAG = "ApplyProBonoActivity";

    private VolunteerViewDto personal_data;

    private Button APPLY;
    private Button btn_getVerify;
    private Btn_TimeCountUtil btn_timeCountUtil;
    private EditText edit_home;
    private EditText edit_work;
    private EditText edit_phone;
    private EditText edit_verification;
    private String my_home;
    private String my_work;
    private String my_phone;
    private String my_verification;
    private int my_AuditStatus;
    private List<AttachmentParaDto> files;
    private Polity_Pop polityPop;
    private TextView tv_clause_click;
    private CheckBox box_isAgree;

    final private int UPDATE_UI = 0;
    final private int APPLY_AGAIN = 1;

    final private int MY_SPECIALITY = 0;
    final private int MY_SERVICETIME = 1;
    final private int MY_SERVICETYPE = 2;
    final private int MY_MAJOR = 3;
    final private int MY_POLIT = 4;

    private boolean Flag_major = false;
    private boolean Flag_time = false;
    private boolean Flag_type = false;
    private boolean Flag_speciality = false;
    private boolean Flag_polity = false;

    /*Handler更新UI*/
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            my_AuditStatus = personal_data.getAuditStatus();
            ScrollView ALL = (ScrollView) findViewById(R.id.Scroll_pro_bono_ALL);
            TextView tip = (TextView) findViewById(R.id.tv_pro_bono_apply_tip);
            Button again = (Button) findViewById(R.id.btn_pro_bono_apply_again);
            if (personal_data.getSpeciality())
                switch (msg.what) {
                    case UPDATE_UI:
                        switch (my_AuditStatus) {
                            case 2:
                                ALL.setVisibility(View.GONE);
                                tip.setText("您的审核未通过");
                                tip.setVisibility(View.VISIBLE);
                                again.setVisibility(View.VISIBLE);
                                again.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SendHandlerMsg(APPLY_AGAIN);
                                    }
                                });
                                break;
                            case 3:
                                ALL.setVisibility(View.GONE);
                                tip.setVisibility(View.VISIBLE);
                                break;
                        }
                        break;
                    case APPLY_AGAIN:
                        ALL.setVisibility(View.VISIBLE);
                        tip.setVisibility(View.GONE);
                        again.setVisibility(View.GONE);
                        break;
                }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_pro_bono);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        if (personal_data.getAuditStatus() != null)
            SendHandlerMsg(UPDATE_UI);

        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyProBonoActivity.this.finish();
            }
        });


        //手机验证码
        edit_verification = (EditText) findViewById(R.id.edit_Verification);
        edit_phone = (EditText) findViewById(R.id.edit_myphone);
        //设置监听
        initViewsEven();
        btn_getVerify = (Button) findViewById(R.id.btn_phone_getVerify);
        btn_getVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_phone = edit_phone.getText().toString();
                if (!my_phone.equals("")) {
                    //获取验证码按钮倒计时
                    btn_timeCountUtil = new Btn_TimeCountUtil(ApplyProBonoActivity.this,
                            60000, 1000, btn_getVerify);
                    btn_timeCountUtil.start();

                    AppActionImpl.getInstance(getApplicationContext()).getVerification(my_phone, new ActionCallbackListener<String>() {

                        @Override
                        public void onSuccess(String data) {
                            showToast("验证码已发送");
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("验证码发送失败");
                        }
                    });

                } else {
                    showToast("请输入正确的手机号码");
                }
            }
        });
        //申请按钮
        APPLY = (Button) findViewById(R.id.btn_pro_bono_apply);
        APPLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!box_isAgree.isChecked()) {
                    showToast("请阅读并同意管理办法");
                } else if (edit_verification.getText().toString().equals("")) {
                    showToast("验证码不能为空");
                } else if (edit_phone.getText().toString().equals("")) {
                    showToast("手机号码不能为空");
                } else if (edit_work.getText().toString().equals("")) {
                    showToast("工作单位不能为空");
                } else if (edit_home.getText().toString().equals("")) {
                    showToast("家庭住址不能为空");
                } else if (!Flag_time) {
                    showToast("请选择意向服务时间");
                } else if (!Flag_type) {
                    showToast("请选择意向服务类型");
                } else if (!Flag_speciality) {
                    showToast("请选择意向专业类型");
                } else if (!Flag_polity) {
                    showToast("请选择政治面貌");
                } else {
                    showNormalDialog("正在提交申请");
                    my_verification = edit_verification.getText().toString();
                    my_phone = edit_phone.getText().toString();
                    my_work = edit_work.getText().toString();
                    my_home = edit_home.getText().toString();
                    //手机验证码验证
                    AppActionImpl.getInstance(getApplicationContext()).getverify(my_phone, my_verification, new ActionCallbackListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean data) {
                            if (data) {
                                if (files != null)
                                    AppActionImpl.getInstance(getApplicationContext()).update_major_attachment(files,
                                            new ActionCallbackListener<List<AttachmentsReturnDto>>() {
                                                @Override
                                                public void onSuccess(List<AttachmentsReturnDto> data) {
                                                    //修改项
                                                    personal_data.setWorkunit(my_work);
                                                    personal_data.setDomicile(my_home);
                                                    personal_data.setMobile(my_phone);
                                                    personal_data.setAuditStatus(3);//审核状态改变
                                                    personal_data.setSpeciality(true);
                                                    List<VolunteerViewDto> vl_updates = new ArrayList<>();
                                                    vl_updates.add(personal_data);
                                                    //申请资料上传
                                                    AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                                                        @Override
                                                        public void onSuccess(String data) {
                                                            dissMissNormalDialog();
                                                            showToast("申请成功，请耐心等待审核");
                                                            ApplyProBonoActivity.this.finish();
                                                        }

                                                        @Override
                                                        public void onFailure(String errorEvent, String message) {
                                                            dissMissNormalDialog();
                                                            showToast("请求失败  " + message);
                                                        }
                                                    });


                                                }

                                                @Override
                                                public void onFailure(String errorEvent, String message) {
                                                    dissMissNormalDialog();
                                                    showToast("证书上传失败");
                                                }
                                            });
                                else {
                                    //修改项
                                    personal_data.setWorkunit(my_work);
                                    personal_data.setAddr(my_home);
                                    personal_data.setMobile(my_phone);
                                    personal_data.setAuditStatus(3);//审核状态改变
                                    personal_data.setSpeciality(true);
                                    List<VolunteerViewDto> vl_updates = new ArrayList<>();
                                    vl_updates.add(personal_data);
                                    //申请资料上传
                                    AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                                        @Override
                                        public void onSuccess(String data) {
                                            dissMissNormalDialog();
                                            showToast("申请成功，请耐心等待审核");
                                            ApplyProBonoActivity.this.finish();
                                        }

                                        @Override
                                        public void onFailure(String errorEvent, String message) {
                                            dissMissNormalDialog();
                                            showToast("请求失败  " + message);
                                        }
                                    });

                                }
                            } else {
                                dissMissNormalDialog();
                                showToast("验证码错误");
                            }
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            dissMissNormalDialog();
                        }
                    });
                }
            }
        });
    }


    private void initViewsEven() {
        edit_work = (EditText) findViewById(R.id.edit_apply_Workunit);
        edit_home = (EditText) findViewById(R.id.edit_apply_Addr);
        LinearLayout Polity = (LinearLayout) findViewById(R.id.LL_apply_Polity);
//        LinearLayout Major = (LinearLayout) findViewById(R.id.LL_apply_Major);
        LinearLayout intentionTime = (LinearLayout) findViewById(R.id.LL_apply_intentionTime);
        LinearLayout intentionType = (LinearLayout) findViewById(R.id.LL_apply_intentionType);
        LinearLayout specialty = (LinearLayout) findViewById(R.id.LL_apply_specialty);
        tv_clause_click = (TextView) findViewById(R.id.tv_clause);
        box_isAgree = (CheckBox) findViewById(R.id.box_pro_bono_isAgree);

        tv_clause_click.setOnClickListener(this);
        Polity.setOnClickListener(this);
//        Major.setOnClickListener(this);
        intentionTime.setOnClickListener(this);
        intentionType.setOnClickListener(this);
        specialty.setOnClickListener(this);
        edit_work.setOnFocusChangeListener(this);
        edit_home.setOnFocusChangeListener(this);
        edit_phone.setOnFocusChangeListener(this);
        edit_verification.setOnFocusChangeListener(this);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edit_apply_Workunit:
                setHintEt(edit_work,hasFocus);
                break;
            case R.id.edit_apply_Addr:
                setHintEt(edit_home,hasFocus);
                break;
            case R.id.edit_myphone:
                setHintEt(edit_phone,hasFocus);
                break;
            case R.id.edit_Verification:
                setHintEt(edit_verification,hasFocus);
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
        Intent intent = new Intent();
        intent.putExtra("personal_data", personal_data);
        switch (v.getId()) {
            case R.id.LL_apply_Polity:
               /* polityPop = new Polity_Pop(this, itemsOnClick);
                polityPop.showAtLocation(this.findViewById(R.id.Scroll_pro_bono_ALL),
                        Gravity.CENTER, 0, 0); //设置layout在PopupWindow中显示的位置*/
                intent.putExtra("type", 0);
                intent.setClass(ApplyProBonoActivity.this, PoliticsstatusActivity.class);
                startActivityForResult(intent, MY_POLIT);
                break;
//            case R.id.LL_apply_Major:
//                intent.setClass(this, SpecilaAbilityActivity.class);
//                intent.putExtra("type", 0);
//                startActivityForResult(intent, MY_SPECIALITY);
//                break;
            case R.id.LL_apply_intentionTime:
                intent.setClass(this, ApplyServiceTimeActivity.class);
                startActivityForResult(intent, MY_SERVICETIME);
                break;
            case R.id.LL_apply_intentionType:
                intent.setClass(this, ApplyServiceCategoryActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, MY_SERVICETYPE);
                break;
            case R.id.LL_apply_specialty:
                intent.setClass(this, ApplyMajorAbilityActivity.class);
                startActivityForResult(intent, MY_MAJOR);
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
                                box_isAgree.setChecked(false);
                            }

                            @Override
                            public void onPositiveButtonPressed() {
                                box_isAgree.setChecked(true);
                            }
                        })
                        .build()
                        .show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        Bundle result = new Bundle();
        switch (requestCode) {
           /* case MY_MAJOR:
                result = data.getExtras();
                files = (List<AttachmentParaDto>) result.getSerializable("files");
                personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                Flag_major = true;
                break;*/
           /* case MY_SPECIALITY:
                result = data.getExtras();
                personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                TextView tv_major_show = (TextView) findViewById(R.id.tv_apply_pro_bono_specialty);
                tv_major_show.setText(personal_data.getSpecialty());
                Flag_major = true;
                break;*/
            case MY_SERVICETIME:
                result = data.getExtras();
                personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                TextView tv_setviceTime_show = (TextView) findViewById(R.id.tv_apply_pro_bono_serviceTime);
                tv_setviceTime_show.setText("已选");
                Flag_time = true;
                break;
            case MY_SERVICETYPE:
                result = data.getExtras();
                personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                TextView tv_serviceType = (TextView) findViewById(R.id.tv_apply_pro_bono_serviceType);
                tv_serviceType.setText(result.getString("serviceIntentionStr"));
                Flag_type = true;
                break;
            case MY_MAJOR:
                result = data.getExtras();
                //files = (List<AttachmentParaDto>) result.getSerializable("files");
                personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                TextView tv_specialty_show = (TextView) findViewById(R.id.tv_apply_pro_bono_major);
                tv_specialty_show.setText(personal_data.getSkilled());
                Flag_speciality = true;
                break;
            case MY_POLIT:
                result = data.getExtras();
                personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
                TextView tv_polity_show = (TextView) findViewById(R.id.tv_apply_pro_bono_polity);
                tv_polity_show.setText(result.getString("polity_name"));
                Flag_polity = true;
                break;

        }
    }

    @Subscribe
    public void onEventMainThread(UpLoadFileEvent event) {
        files = event.getFiles();
        Logger.v("----", "get File");
    }


    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (polityPop.isShowing())
                polityPop.dismiss();
            backgroundAlpha(1);
            switch (v.getId()) {
                case R.id.btn_CPC:
                    personal_data.setPolity(getResources().getString(R.string.btn_CPC_string));
                    Flag_polity = true;
                    break;
                case R.id.btn_CYL:
                    personal_data.setPolity(getResources().getString(R.string.btn_CYL_string));
                    Flag_polity = true;
                    break;
                case R.id.btn_democratic:
                    personal_data.setPolity(getResources().getString(R.string.btn_democratic_string));
                    Flag_polity = true;
                    break;
                case R.id.btn_masses:
                    personal_data.setPolity(getResources().getString(R.string.btn_masses_string));
                    Flag_polity = true;
                    break;
            }
        }
    };

    //发送更新UI请求
    private void SendHandlerMsg(int msg) {
        Message message = new Message();
        message.what = msg;
        myHandler.sendMessage(message);
    }

    //背景透明度设置
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        this.getWindow().setAttributes(lp);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
