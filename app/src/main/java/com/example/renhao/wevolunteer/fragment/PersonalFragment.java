package com.example.renhao.wevolunteer.fragment;
/*
 *
 * Created by Ge on 2016/8/8  10:03.
 *
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.activity.AboutUsActivity;
import com.example.renhao.wevolunteer.activity.ApplyProBonoActivity;
import com.example.renhao.wevolunteer.activity.Complete_information;
import com.example.renhao.wevolunteer.activity.FAQActivity;
import com.example.renhao.wevolunteer.activity.LoginActivity;
import com.example.renhao.wevolunteer.activity.MajorAbilityActivity;
import com.example.renhao.wevolunteer.activity.MyORGActivity;
import com.example.renhao.wevolunteer.activity.MyProjectActivity;
import com.example.renhao.wevolunteer.activity.MyRecuritJobActivity;
import com.example.renhao.wevolunteer.activity.PersonalDataActivity;
import com.example.renhao.wevolunteer.activity.ReportProblemActivity;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.example.renhao.wevolunteer.utils.DataCleanManager;
import com.example.renhao.wevolunteer.utils.Util;
import com.orhanobut.logger.Logger;

import static android.app.Activity.RESULT_OK;
import static com.example.renhao.wevolunteer.R.drawable.star;

public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "PersonalFragment";

    private View mainview;
    private boolean isCreat = false;

    private VolunteerViewDto personal_data;
    private View bottomView;
    private LinearLayout professional_true;
    private LinearLayout Professional_false;
    private TextView tv_true_name, tv_specialty, tv_integral;
    private View middleView;
    private TextView tv_AllTime, tv_SchoolTime, tv_InJobTime, tv_RetireTime;
    private TextView tv_cache_show, tv_rank_show;
    private LinearLayout group;
    private ImageView image_portrait;
    private Button exit;
    private TextView tv_specialty_show;
    private BaseActivity mBaseActivity;
    private LinearLayout job, attention, ORG, rank, Information, aboutUS, WIPE_CACHE, REPORT_PROBLEM, FAQ,My_Recruit_Job;


    private boolean toSpecialRegister_connect_again = true;
    private boolean toInformation_connect_again = true;
    private boolean getVolunteerDate_connect_again = true;

    private final int UPDATE_UI = 0;
    private final int UPDATE_PORTRAIT = 1;

    //onclick TAG
    private final int PROFESSIONAL_SELECTION = 0;
    private final int APPLY_PROFESSIONAL = 1;
    private final int TO_MYPROJECT = 2;
    private final int TO_ATTENTION = 3;
    private final int TO_ORG = 4;
    private final int TO_RANK = 5;
    private final int TO_INFORMATION = 6;
    private final int TO_ABOUTUS = 7;
    private final int TO_WIPE_CACHE = 8;
    private final int TO_REPORT_PROBLEM = 9;
    private final int TO_FAQ = 10;
    private final int LOGIN = 11;
    private final int TO_MYRECRUIT = 12;

    private boolean isSpeciality;
    private int AuditStatus;
    private boolean isShowTrueName;
    private boolean isInit = false;//为了让第一次显示时用户未登录进入登录界面
    private String true_name;
    private String nick_name;
    private String specialty;
    private String integral;
    private int StarType;
    private String AllServiceTime;
    private String WorkServiceTime;
    private String SchoolServiceTime;
    private String RetireServiceTime;
    private String my_portrait;

    private void repeat_update() {
        //判断是否为专业志愿志愿者，显示不同按钮
        if (isSpeciality && AuditStatus == 1) {
            //专业志愿者
            tv_specialty.setVisibility(View.VISIBLE);
            tv_specialty.setText(specialty);
            professional_true.setVisibility(View.VISIBLE);
            Professional_false.setVisibility(View.GONE);
        } else {
            //普通志愿者
            tv_specialty.setVisibility(View.GONE);
            professional_true.setVisibility(View.GONE);
            Professional_false.setVisibility(View.VISIBLE);
        }
        //头部
        if (isShowTrueName) {
            tv_true_name.setText(true_name);
        } else {
            tv_true_name.setText(nick_name);
        }
        tv_integral.setText(integral + " 分");
        SetStars();//根据level显示星级


        //中部
        tv_AllTime.setText(AllServiceTime);
        tv_SchoolTime.setText(WorkServiceTime);
        tv_InJobTime.setText(SchoolServiceTime);
        tv_RetireTime.setText(RetireServiceTime);


        //底部
        if (personal_data.getRankingName() != null)
            tv_rank_show.setText(personal_data.getRankingName());

        isCreat = true;
    }


    //根据Level显示星星
    private void SetStars() {
        if (StarType != 0) {
            group = (LinearLayout) mainview.findViewById(R.id.LL_personal_StarsGroup);
            group.removeAllViews();
            ImageView[] imageViews = new ImageView[StarType];
            int width = (int) getActivity().getResources().getDimension(R.dimen.imageview_stars_width);
            int heigth = (int) getActivity().getResources().getDimension(R.dimen.imageview_stars_height);
            for (int i = 0; i < StarType; i++) {
                ImageView imageView = new ImageView(getActivity());
                imageView.setImageResource(star);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, heigth);
                imageView.setLayoutParams(params);
                group.addView(imageView);
                imageViews[i] = imageView;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInit = true;
        mainview = inflater.inflate(R.layout.fragment_personal, container, false);

        mBaseActivity = (BaseActivity) getActivity();
        /*头部*/
        image_portrait = (ImageView) mainview.findViewById(R.id.image_personal_portrait);

        /*find UI界面组件
        include的middle_view*/
        middleView = mainview.findViewById(R.id.middle_part);
        tv_AllTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceAllTime);
        tv_SchoolTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceSchoolTime);
        tv_InJobTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceWorkTime);
        tv_RetireTime = (TextView) middleView.findViewById(R.id.tv_personal_ServiceRetireTime);
        /*include的bottom_view*/
        bottomView = mainview.findViewById(R.id.bottom_part);
        tv_true_name = (TextView) mainview.findViewById(R.id.tv_LL_true_name);
        tv_specialty = (TextView) mainview.findViewById(R.id.tv_LL_specialty);
        tv_integral = (TextView) mainview.findViewById(R.id.tv_LL_integral);
        professional_true = (LinearLayout) bottomView.findViewById(R.id.LL_PF_Professional_true);
        Professional_false = (LinearLayout) bottomView.findViewById(R.id.LL_PF_Professional_false);
        tv_cache_show = (TextView) bottomView.findViewById(R.id.tv_personal_clear);
        tv_rank_show = (TextView) bottomView.findViewById(R.id.tv_personal_rank_show);


        exit = (Button) mainview.findViewById(R.id.btn_personalData_quit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitLogin();
            }
        });

        initViewsEven();//设置点击事件的监听以及初始化组件
        setClickFalse();

        return mainview;
    }

    /**
     * 退出登录
     */
    public void exitLogin() {
        LocalDate.getInstance(getActivity()).setLocalDate("volunteerId", "");
        LocalDate.getInstance(getActivity()).setLocalDate("isLogin", false);
        LocalDate.getInstance(getActivity()).setLocalDate("access_token", "");
        LocalDate.getInstance(getActivity()).setLocalDate("portrait", null);
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    private void initViewsEven() {
        //初始化组件
        job = (LinearLayout) bottomView.findViewById(R.id.LL_PF_job);
        attention = (LinearLayout) bottomView.findViewById(R.id.LL_PF_attention);
        ORG = (LinearLayout) bottomView.findViewById(R.id.LL_PF_ORG);
        rank = (LinearLayout) bottomView.findViewById(R.id.LL_PF_rank);
        Information = (LinearLayout) bottomView.findViewById(R.id.LL_PF_information);
        aboutUS = (LinearLayout) bottomView.findViewById(R.id.LL_PF_aboutUS);
        WIPE_CACHE = (LinearLayout) bottomView.findViewById(R.id.LL_PF_WIPE_CACHE);
        REPORT_PROBLEM = (LinearLayout) bottomView.findViewById(R.id.LL_PF_REPORT_PROBLEM);
        FAQ = (LinearLayout) bottomView.findViewById(R.id.LL_PF_FAQ);
        My_Recruit_Job = (LinearLayout) bottomView.findViewById(R.id.LL_PF_remove);



        //添加点击监听
        professional_true.setOnClickListener(this);
        Professional_false.setOnClickListener(this);
        job.setOnClickListener(this);
        attention.setOnClickListener(this);
        ORG.setOnClickListener(this);
        rank.setOnClickListener(this);
        Information.setOnClickListener(this);
        aboutUS.setOnClickListener(this);
        WIPE_CACHE.setOnClickListener(this);
        REPORT_PROBLEM.setOnClickListener(this);
        FAQ.setOnClickListener(this);
        My_Recruit_Job.setOnClickListener(this);

        //添加点击标签
        professional_true.setTag(PROFESSIONAL_SELECTION);
        Professional_false.setTag(APPLY_PROFESSIONAL);
        job.setTag(TO_MYPROJECT);
        attention.setTag(TO_ATTENTION);
        ORG.setTag(TO_ORG);
        rank.setTag(TO_RANK);
        Information.setTag(TO_INFORMATION);
        aboutUS.setTag(TO_ABOUTUS);
        WIPE_CACHE.setTag(TO_WIPE_CACHE);
        REPORT_PROBLEM.setTag(TO_REPORT_PROBLEM);
        FAQ.setTag(TO_FAQ);
        My_Recruit_Job.setTag(TO_MYRECRUIT);

    }

    public void setClickFalse() {
//        professional_true.setClickable(false);
        Professional_false.setClickable(false);
//        job.setClickable(false);
//        attention.setClickable(false);
        Information.setClickable(false);
//        REPORT_PROBLEM.setClickable(false);
//        FAQ.setClickable(false);
    }

    public void setClickTrue() {
        professional_true.setClickable(true);
        Professional_false.setClickable(true);
        job.setClickable(true);
        attention.setClickable(true);
        Information.setClickable(true);
        REPORT_PROBLEM.setClickable(true);
        FAQ.setClickable(true);
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        final Intent intent = new Intent();

        boolean isLigon = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (!isLigon) {
            showToast("请先登录");
            return;
        }

        switch (tag) {
            case PROFESSIONAL_SELECTION:
                if (personal_data == null) {
                    showToast("登录异常");
                    return;
                }
                intent.setClass(getActivity(), MajorAbilityActivity.class);
                intent.putExtra("personal_data", personal_data);
                startActivityForResult(intent, PROFESSIONAL_SELECTION);
                break;

            case APPLY_PROFESSIONAL:
                intent.putExtra("personal_data", personal_data);
                intent.setClass(getActivity(), ApplyProBonoActivity.class);
                startActivity(intent);
                break;

            case TO_MYPROJECT:
                intent.putExtra("title", this.getResources().getString(R.string.title_myproject));
                intent.putExtra("activityType", MyProjectActivity.MY_PROJECT);
                intent.setClass(getActivity(), MyProjectActivity.class);
                startActivity(intent);
                break;

            case TO_ATTENTION:
                intent.putExtra("title", this.getResources().getString(R.string.title_myattention));
                intent.putExtra("activityType", MyProjectActivity.MY_ATTENTION);
                intent.setClass(getActivity(), MyProjectActivity.class);
                startActivity(intent);
                break;

            case TO_MYRECRUIT:
                intent.setClass(getActivity(), MyRecuritJobActivity.class);
                startActivity(intent);
                break;

            case TO_ORG:
                if (personal_data == null) {
                    showToast("登录异常");
                    return;
                }
                intent.setClass(getActivity(), MyORGActivity.class);
                intent.putExtra("personal_data", personal_data);
                startActivity(intent);
                break;

            case TO_RANK:
                break;

            case TO_INFORMATION:
                intent.putExtra("data", personal_data);
                intent.setClass(getActivity(), PersonalDataActivity.class);
                startActivityForResult(intent, TO_INFORMATION);
                break;

            case TO_ABOUTUS:
                intent.setClass(getActivity(), AboutUsActivity.class);
                startActivity(intent);
                break;

            case TO_WIPE_CACHE:
                try {
                    DataCleanManager.clearAllCache(getActivity());
                    Toast.makeText(mBaseActivity, "清除成功", Toast.LENGTH_SHORT).show();
                } catch (Exception localException) {
                }
               /* LocalDate.getInstance(getActivity()).setLocalDate("isLogin", false);*/
                break;

            case TO_REPORT_PROBLEM:
                intent.setClass(getActivity(), ReportProblemActivity.class);
                startActivity(intent);
                break;

            case TO_FAQ:
                intent.setClass(getActivity(), FAQActivity.class);
                startActivity(intent);
                break;

        }
    }


    @Override
    public void onResume() {
        setClickFalse();
        //注册登录时需写的
        super.onResume();
        if (!LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false)) {
            image_portrait.setImageResource(R.drawable.personal_no_portrait);
            tv_true_name.setText("");
        }
        /*getVolunteerDate_connect_again = true;*/
        getVolunteerDate();

    }

    private void getVolunteerDate() {
        final String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (isInit) {
            isInit = false;
            if (TextUtils.isEmpty(volunteerId) || !isLogin) {
                //如果用户未登录则跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, LOGIN);
                return;
            }
        }

        if (TextUtils.isEmpty(volunteerId))//如果id为空就不获取志愿者的信息
        {
            return;
        }
        //获取志愿者的信息
        AppActionImpl.getInstance(getActivity()).get_volunteerDetail(volunteerId,
                new ActionCallbackListener<VolunteerViewDto>() {

                    @Override
                    public void onSuccess(VolunteerViewDto data) {
                        if (data == null) {
                            System.out.println("data:"+data);
                            System.out.println("isInit"+isInit);
                            //如果用户登录失败则跳转到登录界面
                            if (isInit) {
                                isInit = false;
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivityForResult(intent, LOGIN);
                            }
                            return;
                        }
                        getVolunteerPhoto(volunteerId);
                        //将data传到另一个activity中备用
                        personal_data = data;
                        setClickTrue();
                        System.out.println("trueName:"+personal_data.getTrueName());
                        System.out.println("IdNumber:"+personal_data.getIdNumber());
                        System.out.println("Email:"+personal_data.getEmail());
                        System.out.println("Mobile:"+personal_data.getMobile());
                        if (personal_data.getTrueName() == null
                                || personal_data.getIdNumber() == null
                                || personal_data.getEmail() == null
                                || personal_data.getMobile() == null
                                || personal_data.getTrueName().equals("")
                                || personal_data.getIdNumber().equals("")
                                || personal_data.getEmail().equals("")
                                || personal_data.getMobile().equals("")) {
                            Intent must_write = new Intent();
                            must_write.putExtra("personal_data", personal_data);
                            must_write.setClass(getActivity(), Complete_information.class);
                            startActivity(must_write);
                        } else {
                            isSpeciality = data.getSpeciality();
                            AuditStatus = data.getAuditStatus();

                            isShowTrueName = data.getShowTrueName() == null ? false : data.getShowTrueName();

                            //头部
                            try {
                                true_name = data.getTrueName();
                                nick_name = data.getNickName();
                                integral = data.getScore().toString();
                                specialty = data.getSpecialty();
                            } catch (Exception e) {
                            }


                            //服务时长
                            double schoolTime = data.getSchoolservicetime() == null && data.getSchoolservicetime1() == null
                                    ? 0 : data.getSchoolservicetime() + data.getSchoolservicetime1();
                            SchoolServiceTime = schoolTime + "小时";
                            double workTime = data.getWorkservicetime() == null && data.getWorkunit() == null
                                    ? 0 : data.getWorkservicetime() + data.getWorkservicetime1();
                            WorkServiceTime = workTime + "小时";
                            double retireTime = data.getRetireservicetime() == null && data.getRetireservicetime1() == null
                                    ? 0 : data.getRetireservicetime() + data.getRetireservicetime1();
                            RetireServiceTime = retireTime + "小时";

                            double historyTime = data.getHistorytime() == null && data.getHistorytime() == null
                                    ? 0 : data.getHistorytime();

                            AllServiceTime = schoolTime + workTime + retireTime + historyTime + "小时";
                            Double timeLenth = schoolTime + workTime + retireTime;


                            AppActionImpl.getInstance(getActivity()).query_star_level(timeLenth, new ActionCallbackListener<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        StarType = Integer.parseInt(data);
                                    } catch (Exception e) {
                                    }
                                    repeat_update();
                                }

                                @Override
                                public void onFailure(String errorEvent, String message) {
                                    repeat_update();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                       /* if (getVolunteerDate_connect_again) {
                            getVolunteerDate_connect_again = false;
                            getVolunteerDate();
                        } else*/
                        showToast("网络异常，请检查后重试");
                    }
                });
    }

    private void getVolunteerPhoto(String volunteerId) {
        //获取头像
        AppActionImpl.getInstance(getActivity()).get_portrait(volunteerId,
                new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data == null){
                            image_portrait.setImageResource(R.drawable.personal_no_portrait);
                        }
                        if (!TextUtils.isEmpty(data)) {
                            my_portrait = data;
                            LocalDate.getInstance(getActivity()).setLocalDate("portrait", my_portrait);
                            image_portrait.setImageBitmap(Util.byteToBitmap(data));
                        }
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    @Override
    public void onDestroy() {
        if (group != null)
            group.removeAllViews();
        isCreat = false;
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TO_INFORMATION && resultCode == RESULT_OK) {
            Logger.v(TAG, "onActivityResult");
            getVolunteerDate();
        }
        if (requestCode == PROFESSIONAL_SELECTION && resultCode == RESULT_OK) {
            Bundle result = new Bundle();
            result = data.getExtras();
            VolunteerViewDto personal_data = (VolunteerViewDto) result.getSerializable("personal_data");
            try {
                tv_specialty_show.setText(personal_data.getSkilled());
            } catch (Exception e) {
            }

        }
    }
}
