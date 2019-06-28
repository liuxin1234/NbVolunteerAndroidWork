package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/8/9  13:50.
 *
 */

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.user.UserPhotoDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.common.Constants;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.FileProviderUtils;
import com.example.renhao.wevolunteer.utils.Util;
import com.example.renhao.wevolunteer.view.Attribute_Pop;
import com.example.renhao.wevolunteer.view.Portrait_Pop;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心  信息展示界面
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {

    private Portrait_Pop portrait_PPwindow;
    private Attribute_Pop attribute_PPwindow;
    private int ppwindow_flag;

    public static Activity PDactivity;
    private VolunteerViewDto Personal_Data;
    private TextView tv_myNickName, tv_myPhone, tv_myUserName, tv_myTrueName, tv_myIDNumber, tv_myAttribute, tv_myEMail;
    private TextView tv_polity_show, tv_area_show, tv_ORG_show, tv_specialty_show, tv_work_show,
            tv_address_show, tv_serviceType, tv_serviceTime,tv_VolunteerType;

    private TextView tv_MySex;   //性别 tv
    private TextView tv_Education;   //最高学历 tv


    //需要更改的值 头像暂无
    private boolean IsShowTrueName;
    private String MyNickNmae;
    private String MyPassWord;
    private String MYMobile;
    private String MyTrueName;
    private String MyIDNumber;
    private Integer MyAttribute_code;
    private String MyPolity;
    private String MyArea;
    private String MySkilled;
    private String MyWorkunit;
    private String MyAddr;
    private String MyServiceTimeIntention;
    private String MyServiceIntention;

    //字典
    private List<String> names;


    //头像二进制流
    private byte[] send_portrait;

    private ImageView portrait;
    /* 头像文件名 */
    private static final String IMAGE_FILE_NAME = "nbvolunteer_myportrait.jpg";

    // 裁剪后图片的宽(X)和高(Y),100 X 100的正方形。
    private static int output_X = 100;
    private static int output_Y = 100;

    /* 拍照.图库请求识别码 */
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    //6.0申请相机权限
    private static final int TAKE_PHOTO_REQUEST_CODE = 1;

    //onclick TAG
    private final int BACK = -1;
    private final int MY_PORTRAIT = 0;
    private final int MY_NICKNAME = 1;
    private final int CHANGE_PASSWORD = 2;
    private final int MY_PHONENUMBER = 3;
    private final int MY_SEX = 4;
    private final int MY_EDUCATION = 5;

    private final int MY_ATTRIBUTE = 7;
    private final int MY_POLITICSSTATUS = 8;
    private final int MY_AREA = 9;
    private final int MY_ORG = 10;
    private final int MY_MAJOR = 11;
    private final int MY_NOWCOMPANY = 12;
    private final int MY_HOMEADDRESS = 13;
    private final int MY_IMAGETIME = 14;
    private final int MY_IMAGETYPE = 15;
    private final int MY_EMAIL = 16;
    private final int MY_VOLUNTEERTYPE = 17;

    private final int UPDATE_UI = 0;

//    private ImageView top_btn_back;
    private LinearLayout myPortrait;
    private LinearLayout myNickName;
    private LinearLayout changePassword;
    private LinearLayout myPhoneNumber;
    private LinearLayout myUserName;
    private LinearLayout myTrueName;
    private LinearLayout myIDNumber;
    private LinearLayout myAttribute;
    private LinearLayout myPoliticsStatus;
    private LinearLayout myArea;
    private LinearLayout myORG;
    private LinearLayout myMajor;
    private LinearLayout myNowCompany;
    private LinearLayout myHomeAddress;
    private LinearLayout myImageTime;
    private LinearLayout myImageType;
    private LinearLayout myEMail;
    private LinearLayout myVolunteerType;
    private LinearLayout mySex;
    private LinearLayout myEducation;

    private String volunteerId;

    /**
     * 更新用户数据方法
     */
    private void repeat_update() {
        volunteerId = LocalDate.getInstance(this).getLocalDate("volunteerId", "");
        //用户昵称
        if (Personal_Data.getNickName() != null) {
            IsShowTrueName = Personal_Data.getShowTrueName();
            MyNickNmae = Personal_Data.getNickName();
            tv_myNickName.setText(MyNickNmae);
        }
        //手机号
        if (Personal_Data.getMobile() != null) {
            MYMobile = Personal_Data.getMobile();
            tv_myPhone.setText(MYMobile);
        }
        //真实姓名
        if (Personal_Data.getTrueName() != null) {
            MyTrueName = Personal_Data.getTrueName();
            tv_myTrueName.setText(MyTrueName);
        }
        //身份证号码
        if (Personal_Data.getIdNumber() != null) {
            MyIDNumber = Personal_Data.getIdNumber();
            tv_myIDNumber.setText(MyIDNumber);
        }
        //个人属性
        if (Personal_Data.getJobStatus() != null) {
            tv_myAttribute.setText(Personal_Data.getJobStatusStr());
//            query_dictionary("PersonAttribute");
        }
        //政治面貌
        if (Personal_Data.getPolity() != null) {
            query_dictionary("PoliticalAttribute");
        }
        //所在区域
        if (Personal_Data.getAreaName() != null) {
            tv_area_show.setText(Personal_Data.getAreaName());
        }
        //所在机构
        if (Personal_Data.getOrganizationName() != null) {
            tv_ORG_show.setText(Personal_Data.getOrganizationName());
        }
        //专业 这里可能会与下面的专业类型重复 但是应该也是没问题
        if (Personal_Data.getSpecialty() != null) {
            tv_specialty_show.setText(Personal_Data.getSpecialty());
        }
        //现在工作单位
        if (Personal_Data.getWorkunit() != null) {
            tv_work_show.setText(Personal_Data.getWorkunit());
        }
        //现在居住地
        if (Personal_Data.getDomicile() != null) {
            tv_address_show.setText(Personal_Data.getDomicile());
        }
        //邮箱
        if (Personal_Data.getEmail() != null) {
            tv_myEMail.setText(Personal_Data.getEmail());
        }
        //服务时间
        if (Personal_Data.getServiceTime() != null) {
            tv_serviceTime.setText("已选");
        }
        //服务意向类别
        if (Personal_Data.getServiceIntention() != null){
            query_dictionary("ActivityType");
        }
        //专业类型
        if(Personal_Data.getSpecialtyType() != null){
            query_dictionary("SPECIALITY");
        }
        //是否为平安志愿者
        if (Personal_Data.getTag() != null){
            query_dictionary("VlteTag");
        }
        //性别
        if (Personal_Data.getSex() != null){
            query_dictionary("Sex");
        }
        //最高学历
        if (Personal_Data.getDegree() != null){
//            query_dictionary("Education");
            tv_Education.setText(Personal_Data.getDegree());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,getResources().getString(R.string.title_personal_data));

        Intent intent = getIntent();
        Personal_Data = (VolunteerViewDto) intent.getSerializableExtra("data");

        PDactivity = PersonalDataActivity.this;//用于非本activity控制此activity
        //需要更改展示的UI组件
        portrait = (ImageView) findViewById(R.id.imageview_item_myPortrait);
        tv_myNickName = (TextView) findViewById(R.id.tv_PD_myNickName);
        tv_myPhone = (TextView) findViewById(R.id.tv_PD_myPhoneNumber);
        tv_myUserName = (TextView) findViewById(R.id.tv_PD_myUserName);
        tv_myTrueName = (TextView) findViewById(R.id.tv_PD_myTrueName);
        tv_myIDNumber = (TextView) findViewById(R.id.tv_PD_myIDNumber);
        tv_myAttribute = (TextView) findViewById(R.id.tv_PD_myAttribute);
        tv_myEMail = (TextView) findViewById(R.id.tv_PD_myEMail);


        initViewsEven();
        //更改显示的内容
        repeat_update();

        if (Personal_Data.getAuditStatus() != 1 || !Personal_Data.getSpeciality()) {
            myPoliticsStatus.setVisibility(View.GONE);
            myMajor.setVisibility(View.GONE);
            myNowCompany.setVisibility(View.GONE);
            myHomeAddress.setVisibility(View.GONE);
            myImageType.setVisibility(View.GONE);
            myImageTime.setVisibility(View.GONE);
        }


    }

    private void query_dictionary(final String type) {
        //获取字典选项备用
        AppActionImpl.getInstance(this).dictionaryQueryDefault(type, "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        if (data == null || data.size() < 1) {
                            return;
                        }
                        List<String> codes = new ArrayList<String>();
                        List<String> names = new ArrayList<String>();
                        LinkedHashMap<String,String> serverType = new  LinkedHashMap<String, String>();
                        LinkedHashMap<String,String> specialtyType = new LinkedHashMap<String, String>();
                        int temp = -1;
                        for (int i = 0; i < data.size(); i++) {
                            String name = data.get(i).getName();
                            String code = data.get(i).getCode();
                            codes.add(code);
                            names.add(name);
                        }
                        //个人属性
                        if (type.equals("PersonAttribute")) {
                            try {
                                for (int i = 0; i < data.size(); i++) {
                                    if (codes.get(i).equals(Personal_Data.getJobStatus().toString())) {
                                        tv_myAttribute.setText(names.get(i));
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        //政治面貌
                        if (type.equals("PoliticalAttribute")) {
                            try {
                                for (int i = 0; i < data.size(); i++) {
                                    if (codes.get(i).equals(Personal_Data.getPolity())) {
                                        tv_polity_show.setText(names.get(i));
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        //专业能力
                        if (type.equals("SPECIALITY")){
                            try{
                                String specialtyTypeName = "";
                                for (int i=0;i<data.size();i++){
                                    specialtyType.put(data.get(i).getCode(),data.get(i).getName());
                                }
                                System.out.println(Personal_Data.getSpecialtyType());
                                String[] SpecialtyTypeCode = Personal_Data.getSpecialtyType().split(",");
                                for (String aSpecialtyTypeCode : SpecialtyTypeCode) {
                                    specialtyTypeName += specialtyType.get(aSpecialtyTypeCode)+",";
                                }
                                tv_specialty_show.setText(specialtyTypeName);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //服务类型
                        if (type.equals("ActivityType")) {
                            try {
                                String serviceTypeName = "";
                                for (int i = 0; i < data.size(); i++) {
                                    serverType.put(data.get(i).getCode(), data.get(i).getName());
                                }
                                String[] ServiceTypeCode = Personal_Data.getServiceIntention().split(",");
                                for (String aServiceTypeCode : ServiceTypeCode) {
                                    serviceTypeName += serverType.get(aServiceTypeCode) + ",";
                                }
                                tv_serviceType.setText(serviceTypeName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        //是否为平安志愿者
                        if (type.equals("VlteTag")){
                            try {
                                for (int i=0;i<data.size();i++){
                                    if (codes.get(i).equals(Personal_Data.getTag().toString())) {
                                        tv_VolunteerType.setText(names.get(i));
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        //性别
                        if (type.equals("Sex")){
                            try {
                                for (int i=0;i<data.size();i++){
                                    if (codes.get(i).equals(Personal_Data.getSex().toString())){
                                        tv_MySex.setText(names.get(i));
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                        //最高学历
                        if (type.equals("Education")){
                            try {
                                for (int i = 0; i < data.size(); i++) {
                                    if (codes.get(i).equals(Personal_Data.getDegree())) {
                                        tv_Education.setText(names.get(i));
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }


                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
        dissMissNormalDialog();
    }


    //给组件添加监听
    private void initViewsEven() {

        //find组件
//        top_btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        myPortrait = (LinearLayout) findViewById(R.id.LL_PD_myPortrait);
        myNickName = (LinearLayout) findViewById(R.id.LL_PD_myNickName);
        changePassword = (LinearLayout) findViewById(R.id.LL_PD_changePassword);
        myPhoneNumber = (LinearLayout) findViewById(R.id.LL_PD_myPhoneNumber);
        myUserName = (LinearLayout) findViewById(R.id.LL_PD_myUserName);
        myTrueName = (LinearLayout) findViewById(R.id.LL_PD_myTrueName);
        myIDNumber = (LinearLayout) findViewById(R.id.LL_PD_myIDNumber);
        myAttribute = (LinearLayout) findViewById(R.id.LL_PD_myAttribute);
        myPoliticsStatus = (LinearLayout) findViewById(R.id.LL_PD_myPoliticsStatus);
        myArea = (LinearLayout) findViewById(R.id.LL_PD_myArea);
        myORG = (LinearLayout) findViewById(R.id.LL_PD_myORG);
        myMajor = (LinearLayout) findViewById(R.id.LL_PD_myMajor);
        myNowCompany = (LinearLayout) findViewById(R.id.LL_PD_myNowCompany);
        myHomeAddress = (LinearLayout) findViewById(R.id.LL_PD_myHomeAddress);
        myImageTime = (LinearLayout) findViewById(R.id.LL_PD_myImageTime);
        myImageType = (LinearLayout) findViewById(R.id.LL_PD_myImageType);
        myEMail = (LinearLayout) findViewById(R.id.LL_PD_myEMail);
        myVolunteerType = (LinearLayout) findViewById(R.id.ll_volunteer_type);
        mySex = (LinearLayout) findViewById(R.id.LL_PD_mySex);
        myEducation = (LinearLayout) findViewById(R.id.LL_PD_myEducation);

        tv_polity_show = (TextView) findViewById(R.id.tv_personal_data_polity);
        tv_area_show = (TextView) findViewById(R.id.tv_personal_data_area);
        tv_ORG_show = (TextView) findViewById(R.id.tv_personal_data_ORG);
        tv_specialty_show = (TextView) findViewById(R.id.tv_personal_data_specialty);
        tv_work_show = (TextView) findViewById(R.id.tv_personal_data_work);
        tv_address_show = (TextView) findViewById(R.id.tv_personal_data_address);
        tv_serviceType = (TextView) findViewById(R.id.tv_personal_data_serviceType);
        tv_serviceTime = (TextView) findViewById(R.id.tv_personal_data_serviceTime);
        tv_VolunteerType = (TextView) findViewById(R.id.tv_volunteer_type);
        tv_MySex = (TextView) findViewById(R.id.tv_PD_mySex);
        tv_Education = (TextView) findViewById(R.id.tv_PD_myEducation);

        //设置监听
//        top_btn_back.setOnClickListener(this);
        myPortrait.setOnClickListener(this);
        myNickName.setOnClickListener(this);
        changePassword.setOnClickListener(this);
        myPhoneNumber.setOnClickListener(this);
        myUserName.setOnClickListener(this);
//        myTrueName.setOnClickListener(this);
//        myIDNumber.setOnClickListener(this);
        myAttribute.setOnClickListener(this);
        myPoliticsStatus.setOnClickListener(this);
        myArea.setOnClickListener(this);
        myORG.setOnClickListener(this);
        myMajor.setOnClickListener(this);
        myNowCompany.setOnClickListener(this);
        myHomeAddress.setOnClickListener(this);
        myImageTime.setOnClickListener(this);
        myImageType.setOnClickListener(this);
        myEMail.setOnClickListener(this);
        myVolunteerType.setOnClickListener(this);
        mySex.setOnClickListener(this);
        myEducation.setOnClickListener(this);

        //添加标签
//        top_btn_back.setTag(BACK);
        myPortrait.setTag(MY_PORTRAIT);
        myNickName.setTag(MY_NICKNAME);
        changePassword.setTag(CHANGE_PASSWORD);
        myPhoneNumber.setTag(MY_PHONENUMBER);
        mySex.setTag(MY_SEX);
        myEducation.setTag(MY_EDUCATION);
        myAttribute.setTag(MY_ATTRIBUTE);
        myPoliticsStatus.setTag(MY_POLITICSSTATUS);
        myArea.setTag(MY_AREA);
        myORG.setTag(MY_ORG);
        myMajor.setTag(MY_MAJOR);
        myNowCompany.setTag(MY_NOWCOMPANY);
        myHomeAddress.setTag(MY_HOMEADDRESS);
        myImageTime.setTag(MY_IMAGETIME);
        myImageType.setTag(MY_IMAGETYPE);
        myEMail.setTag(MY_EMAIL);
        myVolunteerType.setTag(MY_VOLUNTEERTYPE);
    }


    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        Intent intent = new Intent();
        intent.putExtra("personal_data", Personal_Data);
        switch (tag) {
//            case BACK:
//                this.finish();
//                break;
            //政治面貌
            case MY_PORTRAIT:
                portrait_PPwindow = new Portrait_Pop(this, itemsOnClick);
                portrait_PPwindow.showAtLocation(this.findViewById(R.id.SV_PD),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
                ppwindow_flag = 0;
                break;
            //昵称
            case MY_NICKNAME:
                intent.setClass(this, NickNameActivity.class);
                startActivityForResult(intent, MY_NICKNAME);
                break;
            //修改密码
            case CHANGE_PASSWORD:
                intent.putExtra("ID", volunteerId);
                intent.setClass(this, SetPasswordActivity.class);
                startActivity(intent);
                break;
            //修改手机号
            case MY_PHONENUMBER:
                intent.setClass(this, MobilePhoneActivity.class);
                startActivityForResult(intent, MY_PHONENUMBER);
                break;
            //修改性别
            case MY_SEX:
                intent.setClass(this, SexAtivity.class);
                startActivityForResult(intent, MY_SEX);
                break;
            //修改最高学历
            case MY_EDUCATION:
                intent.setClass(this, EducationActivity.class);
                startActivityForResult(intent, MY_EDUCATION);
                break;
            //修改个人属性
            case MY_ATTRIBUTE:
                intent.setClass(this, AttributeAtivity.class);
                startActivityForResult(intent, MY_ATTRIBUTE);
                break;
            //修改政治面貌
            case MY_POLITICSSTATUS:
                intent.setClass(this, PoliticsstatusActivity.class);
                startActivityForResult(intent, MY_POLITICSSTATUS);
                break;
            //修改所属区域
            case MY_AREA:
                intent.setClass(this, AreaSelectAction2.class);
                startActivityForResult(intent, MY_AREA);
                break;
            //修改所属机构
            case MY_ORG:
                intent.setClass(this, OrgSelectActivity.class);
                startActivityForResult(intent, MY_ORG);
                break;
            //修改业能力/特长
            case MY_MAJOR:
                intent.setClass(this, SpecilaAbilityActivity.class);
                startActivityForResult(intent, MY_MAJOR);
                break;
            //修改现工作单位
            case MY_NOWCOMPANY:
                intent.setClass(this, CompanyActivity.class);
                startActivityForResult(intent, MY_NOWCOMPANY);
                break;
            //修改居住地
            case MY_HOMEADDRESS:
                intent.setClass(this, ResidenceActivity.class);
                startActivityForResult(intent, MY_HOMEADDRESS);
                break;
            //修改服务时间
            case MY_IMAGETIME:
                intent.setClass(this, ServiceTimeActivity.class);
                startActivityForResult(intent, MY_IMAGETIME);
                break;
            //修改意向志愿服务类别
            case MY_IMAGETYPE:
                intent.setClass(this, ServiceCategoryActivity.class);
                startActivityForResult(intent, MY_IMAGETYPE);
                break;
            //修改我的邮箱
            case MY_EMAIL:
                intent.setClass(this, MyEMail.class);
                startActivityForResult(intent, MY_EMAIL);
                break;
            //修改是否为平安志愿者
            case MY_VOLUNTEERTYPE:
                intent.setClass(this, VolunteerTagActivity.class);
                startActivityForResult(intent, MY_VOLUNTEERTYPE);
                break;
            default:
                break;
        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //点击以后销毁pop框，将背景恢复
            if (ppwindow_flag == 0) {
                portrait_PPwindow.dismiss();
            }
            if (ppwindow_flag == 1) {
                attribute_PPwindow.dismiss();
            }
            backgroundAlpha(1);

            switch (v.getId()) {
                case R.id.btn_take_photo:
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (ContextCompat.checkSelfPermission(PersonalDataActivity.this,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(PersonalDataActivity.this, Manifest.permission.CAMERA)) {
                                showMessageOKCancel("您需要在应用权限设置中对本应用使用摄像头进行授权才能正常使用该功能",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ActivityCompat.requestPermissions(PersonalDataActivity.this, new String[]{Manifest.permission.CAMERA},
                                                        TAKE_PHOTO_REQUEST_CODE);
                                            }
                                        });
                                return;
                            }
                            ActivityCompat.requestPermissions(PersonalDataActivity.this, new String[]{Manifest.permission.CAMERA},
                                    TAKE_PHOTO_REQUEST_CODE);
                            return;
                        }
                        choseHeadImageFromCameraCapture();

                    } else {
                        choseHeadImageFromCameraCapture();
                    }
                    break;
                case R.id.btn_pick_photo:
                    chosePortraitFromGallery();
                    break;
                default:
                    break;
            }


        }

    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (ContextCompat.checkSelfPermission(PersonalDataActivity.this,
                    Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                choseHeadImageFromCameraCapture();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //各个页面的resultCode不可为0，此为系统back键的resultCode,代表用户没有进行任何操作
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (resultCode == RESULT_OK) {
            Bundle result = new Bundle();
            switch (requestCode) {
                //修改个人资料后更新展示的内容
                case MY_NICKNAME:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    repeat_update();
                    break;

                case CHANGE_PASSWORD:
                    //无需回调
                    break;
                //修改手机号码的数据回调
                case MY_PHONENUMBER:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    repeat_update();
                    break;
                //修改性别的数据回调
                case MY_SEX:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    repeat_update();
                    break;
                //修改最高学历的数据回调
                case MY_EDUCATION:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    repeat_update();
                    break;
                //修改个人属性的数据回调
                case MY_ATTRIBUTE:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    repeat_update();
                    break;
                //修改政治面貌的数据回调
                case MY_POLITICSSTATUS:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    String polity_name = result.getString("polity_name");
                    tv_polity_show.setText(polity_name);
                    break;
                //修改我的专业类型的数据回调
                case MY_MAJOR:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    tv_specialty_show.setText(Personal_Data.getSpecialty());
                    break;
                //修改我的现工作单位的数据回调
                case MY_NOWCOMPANY:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    tv_work_show.setText(Personal_Data.getWorkunit());
                    break;
                //修改现居住地的数据回调
                case MY_HOMEADDRESS:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    tv_address_show.setText(Personal_Data.getDomicile());
                    break;
                //修改我的服务时间的数据回调
                case MY_IMAGETIME:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    if (Personal_Data != null && Personal_Data.getServiceTime() != null) {
                        tv_serviceTime.setText("已选");
                    }
                    break;
                //修改我的所在区域的数据回调
                case MY_AREA:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    String areaCodes = result.getString("AreaCodes");
//                    Logger.e(areaCodes);
                    Personal_Data.setAreaCode(areaCodes);
                    tv_area_show.setText(Personal_Data.getAreaName());
                    getVolunteerDetails();
                    break;
                //修改我的所在机构的数据回调
                case MY_ORG:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    tv_ORG_show.setText(Personal_Data.getOrganizationName());
                    break;
                //修改我的服务意向类型的数据回调
                case MY_IMAGETYPE:
                    try {
                        result = data.getExtras();
                        Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                        String serverTypeName = result.getString("serviceIntentionStr");
                        if (Personal_Data != null) {
                            System.out.println("勾选提交的数据 = "+Personal_Data.getServiceIntention());
                        }
                        String temp = null;
                        if (Personal_Data.getServiceIntention() != null) {
                            temp = serverTypeName;
                        }
                        if (Personal_Data.getServiceIntentionOther() != null) {
                            temp = serverTypeName + Personal_Data.getServiceIntentionOther();
                        }
                        tv_serviceType.setText(temp);
                    } catch (Exception e) {
                    }
                    break;
                //修改我的邮箱数据回调
                case MY_EMAIL:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    tv_myEMail.setText(Personal_Data.getEmail());
                    break;
                //修改我的是否为平安志愿者的数据回调
                case MY_VOLUNTEERTYPE:
                    result = data.getExtras();
                    Personal_Data = (VolunteerViewDto) result.getSerializable("personal_data");
                    tv_VolunteerType.setText(Personal_Data.getTagName());
                    break;

                //照片的返回结果处理
                case CODE_GALLERY_REQUEST:
                    if (Util.hasSDcard()) {
                        File tempFile = new File(Environment.getExternalStorageDirectory(),
                                IMAGE_FILE_NAME);
                        cropRawPhoto(data.getData(),tempFile);
                    } else {
                        Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                    }
                    break;

                case CODE_CAMERA_REQUEST:
                    if (Util.hasSDcard()) {
                        File tempFile = new File(Environment.getExternalStorageDirectory(),
                                IMAGE_FILE_NAME);
                        cropRawPhoto(Uri.fromFile(tempFile),tempFile);
                    } else {
                        Toast.makeText(getApplication(), "没有SDCard!", Toast.LENGTH_LONG).show();
                    }
                    break;

                case CODE_RESULT_REQUEST:
                    if (data != null) {
                        //图片裁切完成，显示裁切后的图片
                        try {
                            File tempFile = new File(Environment.getExternalStorageDirectory(),
                                    IMAGE_FILE_NAME);
                            Uri uri = Uri.fromFile(tempFile);
//                            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//                            UpdatePortrait(bitmap);
                            Logger.e(uri.getPath());
                            upDateHeadImagePhoto(uri);

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
//                        setImageToHeadView(data);
                    }
                    break;
                default:
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    //从本地相册选择图片作为头像
    private void chosePortraitFromGallery() {
        Intent intentFromGallery = new Intent();
        // 设置文件类型
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction("android.intent.action.PICK");
        intentFromGallery.addCategory("android.intent.category.DEFAULT");
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST);
    }

    // 启动手机相机拍摄照片作为头像
    private void choseHeadImageFromCameraCapture() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        intent.addCategory("android.intent.category.DEFAULT");
        Uri uri = FileProviderUtils.uriFromFile(this, new File(Environment
                .getExternalStorageDirectory(), IMAGE_FILE_NAME));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CODE_CAMERA_REQUEST);
    }


    /**
     * 裁剪原始的图片
     */
    public void cropRawPhoto(Uri uri,File outputFile) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        FileProviderUtils.setIntentDataAndType(this, intent, "image/*", uri, true);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        //return-data为true时，直接返回bitmap，可能会很占内存，不建议，小米等个别机型会出异常！！！
        //所以适配小米等个别机型，裁切后的图片，不能直接使用data返回，应使用uri指向
        //裁切后保存的URI，不属于我们向外共享的，所以可以使用fill://类型的URI
        Uri outputUri = Uri.fromFile(outputFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 提取保存裁剪之后的图片数据，并设置头像部分的View
     */
    private void setImageToHeadView(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            UpdatePortrait(photo);
        }
    }

    private void upDateHeadImagePhoto(final Uri url){
        Map<String,String> map = new HashMap<>();
        map.put("Id",Personal_Data.getId());
        Logger.e(Personal_Data.getId());
        List<String> imageUrl = new ArrayList<>();
        imageUrl.add(url.getPath());
        AppActionImpl.getInstance(this).postShareUploadPhoto(map, imageUrl, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                if (data != null){
                    showToast(data);
                }
                portrait.setImageURI(url);
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }


    private void UpdatePortrait(final Bitmap bitmap) {

        byte[] send_portrait = getBitmapByte(bitmap);
        UserPhotoDto vl_updates = new UserPhotoDto();
        vl_updates.setUserId(volunteerId);
        vl_updates.setPhoto(Base64.encodeToString(send_portrait, Base64.DEFAULT));

        AppActionImpl.getInstance(this).update_portrait(vl_updates, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {
                portrait.setImageBitmap(bitmap);
                repeat_update();
                showToast("修改成功");
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("网络异常，请检查后重试");
            }
        });
    }


    //背景透明度设置
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = PDactivity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        PDactivity.getWindow().setAttributes(lp);
    }


    //图片转换成二进制流
    public byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //参数1转换类型，参数2压缩质量，参数3字节流资源
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            /**
             * 我们服务端是每次上传的个人头像只是替换原图，路径并不变。
             * 这就导致glide加载时会使用缓存的图片，导致页面图片显示不同步。
             * 所以这里需要进行缓存的清除
             * .skipMemoryCache(true)
             * .diskCacheStrategy(DiskCacheStrategy.NONE)
             *
             */
            String headImageUrl = LocalDate.getInstance(this).getLocalDate(Constants.HEADIMAGE, "");
            Glide.with(this)
                    .load(headImageUrl)
                    .asBitmap()
                    .error(R.drawable.personal_no_portrait)
                    .placeholder(R.drawable.personal_no_portrait)
                    .into(portrait);
        } catch (Exception e) {

        }
    }

    //获取个人详情的接口
    private void getVolunteerDetails(){
        AppActionImpl.getInstance(this).get_volunteerDetail(volunteerId, new ActionCallbackListener<VolunteerViewDto>() {
            @Override
            public void onSuccess(VolunteerViewDto data) {
                if (data == null){
                    return;
                }
                Personal_Data = data;
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }
}
