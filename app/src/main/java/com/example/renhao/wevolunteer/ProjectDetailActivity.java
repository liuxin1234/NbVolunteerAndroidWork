package com.example.renhao.wevolunteer;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.DeviceUtils;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.activityRecruit.ActivityRecruitDto;
import com.example.model.activityRecruit.ActivityRecruitListDto;
import com.example.model.activityRecruit.ActivityRecruitQueryOptionDto;
import com.example.model.activityTime.ActivityTimeListDto;
import com.example.model.activityTime.ActivityTimeQueryOptionDto;
import com.example.model.activityattention.ActivityAttentionDto;
import com.example.model.activityattention.ActivityAttentionListDto;
import com.example.model.activityattention.ActivityAttentionQueryOptionDto;
import com.example.model.jobActivity.JobActivityViewDto;
import com.example.model.volunteer.VolunteerTopViewDto;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.handler.MxgsaTagHandler;
import com.example.renhao.wevolunteer.utils.GsonUtils;
import com.example.renhao.wevolunteer.utils.Util;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;
import com.orhanobut.logger.Logger;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 16:09
 * 修改备注：
 */
public class ProjectDetailActivity extends BaseActivity {
    private static final String TAG = "ProjectDetailActivity";
    @Bind(R.id.btn_projectdetail_share)
    Button mBtnShare;
    @Bind(R.id.btn_projectdetail_apply)
    Button mBtnApply;
    @Bind(R.id.btn_projectdetail_focuson)
    Button mBtnFocuson;
    @Bind(R.id.bottombar_projectdetail)
    LinearLayout mBottombar;
    @Bind(R.id.imageview_projectdetail_itemImage)
    ImageView mImageview;
    @Bind(R.id.textview_projectdetail_tilte)
    TextView mTvTilte;
    @Bind(R.id.tv_projectdetail_numName)
    TextView mTvNumName;
    @Bind(R.id.tv_projectdetail_num)
    TextView mTvNum;
    @Bind(R.id.tv_projectdetail_timeName)
    TextView mTvTimeName;
    @Bind(R.id.tv_projectdetail_time)
    TextView mTvTime;
//    @Bind(R.id.tv_projectdetail_state)
//    TextView mTvState;
    @Bind(R.id.relative_projectdetail)
    RelativeLayout mRelative;
    @Bind(R.id.relative_projectdetail_item)
    RelativeLayout mRelativeItem;
    @Bind(R.id.tv_projectDetail_typeName)
    TextView mTvTypeName;
    @Bind(R.id.tv_projectDetail_type)
    TextView mTvType;
    @Bind(R.id.tv_projectDetail_effectiveTimeName)
    TextView mTvEffectiveTimeName;
    @Bind(R.id.tv_projectDetail_effectiveTime)
    TextView mTvEffectiveTime;
    @Bind(R.id.tv_projectDetail_locationName)
    TextView mTvLocationName;
    @Bind(R.id.tv_projectDetail_location)
    TextView mTvLocation;
    @Bind(R.id.tv_projectDetail_foundersName)
    TextView mTvFoundersName;
    @Bind(R.id.tv_projectDetail_founders)
    TextView mTvFounders;
    @Bind(R.id.tv_projectDetail_contactName)
    TextView mTvContactName;
    @Bind(R.id.tv_projectDetail_contact)
    TextView mTvContact;
    @Bind(R.id.tv_projectDetail_skillName)
    TextView mTvSkillName;
    @Bind(R.id.tv_projectDetail_skill)
    TextView mTvSkill;
    @Bind(R.id.tv_projectDetail_infuse)
    TextView mTvInfuse;
    @Bind(R.id.tv_projectDetail_detailName)
    TextView mTvDetailName;
    @Bind(R.id.relative_projectDetail_detailName)
    RelativeLayout mRelativeDetailName;
    @Bind(R.id.tv_projectDetail_detail)
    TextView mTvDetail;
    @Bind(R.id.tv_projectDetail_signedName)
    TextView mTvSignedName;
    @Bind(R.id.imageview_projectDetail_more)
    ImageView mImageviewMore;
    @Bind(R.id.tv_projectDetail_maxNum)
    TextView mTvMaxNum;
    @Bind(R.id.tv_projectDetail_haveSignedNum)
    TextView mTvSignedNum;
    @Bind(R.id.relative_projectDetail_signed)
    RelativeLayout mRelativeSigned;
    @Bind(R.id.imageview_projectDetail_signedPeople_1)
    ImageView mImageviewPeople1;
    @Bind(R.id.imageview_projectDetail_signedPeople_2)
    ImageView mImageviewPeople2;
    @Bind(R.id.imageview_projectDetail_signedPeople_3)
    ImageView mImageviewPeople3;
    @Bind(R.id.imageview_projectDetail_signedPeople_4)
    ImageView mImageviewPeople4;
    @Bind(R.id.imageview_projectDetail_signedPeople_5)
    ImageView mImageviewPeople5;
    @Bind(R.id.relative_projectDetail_signedPeople)
    LinearLayout mRelativeSignedPeople;
    @Bind(R.id.scrollview_projectdetail)
    ScrollView mScrollview;
    @Bind(R.id.tv_projectDetail_registerTimeName)
    TextView mTvRegisterTimeName;
    @Bind(R.id.tv_projectDetail_registerTime)
    TextView mTvRegisterTime;
    @Bind(R.id.projectdetail_border)
    View mProjectdetailBorder;
    @Bind(R.id.relative_projectDetail_skill)
    LinearLayout mRelativeSkill;
    @Bind(R.id.relative_projectDetail_skill_border)
    View mSkillBorder;
    @Bind(R.id.tv_projectDetail_infuse_border)
    View mInfuseBorder;
    @Bind(R.id.projectdetail_parent)
    RelativeLayout mParent;
    @Bind(R.id.tv_projectClaim_detailName)
    TextView mTvClaimName;
    @Bind(R.id.relative_projectClain_detailName)
    RelativeLayout relativeProjectClain;
    @Bind(R.id.tv_projectClaim_detail)
    TextView mTvClaim;
    @Bind(R.id.tv_projectDetail_serviceTimeName)
    TextView mTvServiceTimeName;
    @Bind(R.id.tv_projectDetail_serviceTime)
    TextView mTvServiceTime;
    @Bind(R.id.imageview_state)
    ImageView mImageViewState;

    private View mCustomView;//actionbar的自定义视图
    private ImageView indicatorImg;
    private TextView titleTv;
    private ImageView magnifierImg;

    private int origin;

    private ActivityViewDto mActivityViewDto;
    private JobActivityViewDto mJobActivityViewDto;
    private String periodType;  //活动/岗位的（日期）周期
    private List<String> periodTypeLists = new ArrayList<>();

    private List<ActivityTimeListDto> times = new ArrayList<>();
    private List<String> sTime = new ArrayList<>();

    private String id;
    private int type;
    private Map<String, String> notes = new HashMap<>();
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private boolean isAttention = false;
    private boolean isInitAttention = false;
    private String volunteerId;
    private String attentionId;

    private String activityName;

    String imagUrl = "";

    String activityOrJob_Url = "";
    private String mOperationState; //当前活动状态

    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;

    public void setBasIn(BaseAnimatorSet bas_in) {
        this.mBasIn = bas_in;
    }

    public void setBasOut(BaseAnimatorSet bas_out) {
        this.mBasOut = bas_out;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetail);
        ButterKnife.bind(this);

        volunteerId = LocalDate.getInstance(this).getLocalDate("volunteerId", "");

        type = getIntent().getIntExtra("type", 0);
        origin = getIntent().getIntExtra("origin", 0);

        initActionBar();

        id = getIntent().getStringExtra("id");
        initView();
        getDetail();

        isAttention();

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();
        try {
            getSiginInVolunteer();
        } catch (Exception e) {
        }

    }


    /**
     * 获取已经报名的志愿者，并获取其头像显示
     */
    private void getSiginInVolunteer() {
        String maxSize = "5";
        AppActionImpl.getInstance(this).getMembership(id, maxSize,
                new ActionCallbackListener<List<VolunteerTopViewDto>>() {
                    @Override
                    public void onSuccess(List<VolunteerTopViewDto> data) {
                        setVolunteerPhoto(data, 0);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    /**
     * 利用迭代的方法获取并显示用户的头像
     *
     * @param list
     * @param position
     */
    private void setVolunteerPhoto(final List<VolunteerTopViewDto> list, final int position) {
        if (list == null || list.size() < (position + 1)) {
            return;
        }
        //取得头像
        AppActionImpl.getInstance(this).get_portrait(list.get(position).getUserId(),
                new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        if (data == null) {
                            try {
                                ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(position);
                                view.setVisibility(View.VISIBLE);
                            } catch (Exception ignored) {

                            }
                        } else {
                            try {
                                Bitmap bitmap = Util.byteToBitmap(data);
                                ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(position);
                                view.setVisibility(View.VISIBLE);
                                //设置图片
                                view.setImageBitmap(bitmap);

                            } catch (Exception ignored) {
                                System.out.println(ignored);
                            }
                        }
                        setVolunteerPhoto(list, position + 1);


                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(position);
                        view.setVisibility(View.VISIBLE);
                        //设置图片
                        view.setImageResource(R.drawable.personal_no_portrait);
                        setVolunteerPhoto(list, position + 1);
                    }
                });
    }

    /**
     * 加载数据
     */
    public void getDetail() {
        showNormalDialog("正在加载数据...");
        if (type == 0) {
            getActivityDetail();
            activityOrJob_Url = "Activity/Detail?Id=";
        } else if (type == 1) {
            getJobActivityDetail();
            activityOrJob_Url = "Job/detail?Id=";
        }

    }

    private void getActivityDetail() {
        AppActionImpl.getInstance(this).activityDetail(id, new ActionCallbackListener<ActivityViewDto>() {
            @Override
            public void onSuccess(ActivityViewDto data) {
                dissMissNormalDialog();
                if (data == null) {
                    showToast("找不到该项目");
                    return;
                }
                mActivityViewDto = data;
                activityName = data.getActivityName();
                setActivityDetail(data);
                periodType = data.getPeriodType();
                String[] split = periodType.split(",");
                periodTypeLists.addAll(Arrays.asList(split));
                initTimePicker();

            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast(message);
                dissMissNormalDialog();
            }
        });
    }

    private void setActivityDetail(ActivityViewDto data) {
        mTvTilte.setText(data.getActivityName());

        mTvNum.setText(data.getRecruited() + "/" + data.getRecruitNumber() + "人");

        Number h = data.getLengthTime();
        DecimalFormat df = new DecimalFormat("#.##");
        mTvTime.setText(df.format(h) + "小时");

//        mTvState.setText(data.getOperationState());
        mOperationState = data.getOperationState();
        setOperationStateImg(mOperationState);

        if (!TextUtils.isEmpty(data.getPcLstUrl())) {
            imagUrl = data.getPcLstUrl();
            Picasso.with(getApplicationContext()).load(Util.getRealUrl(imagUrl))
                    .placeholder(R.drawable.img_unload)
                    .error(R.drawable.img_unload)
                    .into(mImageview);
        } else {
            mImageview.setImageResource(R.drawable.img_unload);
        }

        //岗位类型 可能有多个
        String typeName = data.getActivityTypeName();

        mTvType.setText(typeName);//
        String[] sBeginTime = data.getBeginTime().split(" ");
        String[] sEndTime = data.getEndTime().split(" ");
        String[] sStartTime = data.getStartTime().split(" ");
        String[] sFinishTime = data.getFinishTime().split(" ");
        mTvRegisterTime.setText(sBeginTime[0] + "\n" +
                "报名截止到活动开始前"+ data.getLimitDayNum() +"天");

        mTvEffectiveTime.setText(sStartTime[0] + " - " + sFinishTime[0]);

        mTvServiceTime.setText(data.getDaySTime()+" - " + data.getDayETime());

        mTvLocation.setText(data.getAddr());

        mTvFounders.setText(data.getCompanyName());

        String contactStr = "";
        contactStr += TextUtils.isEmpty(data.getLinker()) ? "" : data.getLinker() + "\n";
        contactStr += TextUtils.isEmpty(data.getTel()) ? "" : data.getTel() + "\n";
        contactStr += TextUtils.isEmpty(data.getMobile()) ? "" : data.getMobile();
        mTvContact.setText(contactStr);

        mTvClaim.setText(Html.fromHtml(data.getJobText(), null, new MxgsaTagHandler(this)));
        mTvDetail.setText(Html.fromHtml(data.getText(), null, new MxgsaTagHandler(this)));

        mTvSignedNum.setText(data.getRecruited() + "");

        mTvMaxNum.setText("/" + data.getRecruitNumber());


        /*for (int i = (data.getRecruited() > 5 ? 5 : data.getRecruited()) - 1; i >= 0; i--) {
            ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(i);
            view.setVisibility(View.VISIBLE);
            //设置图片
            view.setImageResource(R.mipmap.ic_launcher);
        }*/
    }

    private void getJobActivityDetail() {
        AppActionImpl.getInstance(this).jobActivityDetail(id, new ActionCallbackListener<JobActivityViewDto>() {
            @Override
            public void onSuccess(JobActivityViewDto data) {
                dissMissNormalDialog();
                if (data == null) {
                    showToast("找不到该项目");
                    return;
                }
                mJobActivityViewDto = data;
                //times = data.getActivityTimes();
                activityName = data.getActivityName();
                setJobActivityDetail(data);
                periodType = data.getPeriodType();
                String[] split = periodType.split(",");
                periodTypeLists.addAll(Arrays.asList(split));
                initTimePicker();
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast(message);
                dissMissNormalDialog();
            }
        });
    }

    private void setJobActivityDetail(JobActivityViewDto data) {
        mTvTilte.setText(data.getActivityName());

        mTvNum.setText(data.getRecruited() + "/" + data.getRecruitNumber() + "人");

        Number h = data.getLengthTime();
        DecimalFormat df = new DecimalFormat("#.##");
        mTvTime.setText(df.format(h) + "小时");

//        mTvState.setText(data.getOperationState());
        mOperationState = data.getOperationState();
        setOperationStateImg(mOperationState);


        if (!TextUtils.isEmpty(data.getPcLstUrl())) {
            imagUrl = data.getPcLstUrl();
            Picasso.with(getApplicationContext()).load(Util.getRealUrl(imagUrl))
                    .placeholder(R.drawable.img_unload)
                    .error(R.drawable.img_unload)
                    .into(mImageview);
        } else {
            mImageview.setImageResource(R.drawable.img_unload);
        }

        //岗位类型 可能有多个
        String typeName =  data.getActivityTypeName();

        mTvType.setText(typeName);//
        String[] sBeginTime = data.getBeginTime().split(" ");
        String[] sEndTime = data.getEndTime().split(" ");
        String[] sStartTime = data.getStartTime().split(" ");
        String[] sFinishTime = data.getFinishTime().split(" ");
        mTvRegisterTime.setText(sBeginTime[0] + "\n" +
                "报名截止到岗位开始前"+ data.getLimitDayNum() +"天");

        mTvEffectiveTime.setText(sStartTime[0] + " - " + sFinishTime[0]);

        mTvServiceTime.setText(data.getDaySTime()+" - " + data.getDayETime());

        mTvLocation.setText(data.getAddr());

        mTvFounders.setText(data.getCompanyName());

        String contactStr = "";
        contactStr += TextUtils.isEmpty(data.getLinker()) ? "" : data.getLinker() + "\n";
        contactStr += TextUtils.isEmpty(data.getTel()) ? "" : data.getTel() + "\n";
        contactStr += TextUtils.isEmpty(data.getMobile()) ? "" : data.getMobile();
        mTvContact.setText(contactStr);

        mTvSkill.setText(data.getSpecialityTypeName());

        mTvClaim.setText(Html.fromHtml(data.getJobText(), null, new MxgsaTagHandler(this)));
        mTvDetail.setText(Html.fromHtml(data.getText(), null, new MxgsaTagHandler(this)));

        mTvSignedNum.setText(data.getRecruited() + "");

        mTvMaxNum.setText("/" + data.getRecruitNumber());

        /*for (int i = (data.getRecruited() > 5 ? 5 : data.getRecruited()) - 1; i >= 0; i--) {
            ImageView view = (ImageView) mRelativeSignedPeople.getChildAt(i);
            view.setVisibility(View.VISIBLE);
            //设置图片
            view.setImageResource(R.mipmap.ic_launcher);
        }*/
    }

    private void initView() {
        if (type == 0) {
            //活动
            mTvSkill.setVisibility(View.GONE);
            mTvInfuse.setVisibility(View.GONE);
            mRelativeSkill.setVisibility(View.GONE);
            mSkillBorder.setVisibility(View.GONE);
            mInfuseBorder.setVisibility(View.GONE);
            mTvTypeName.setText("活动类型");
            mTvRegisterTimeName.setText("报名开始日期");
            mTvEffectiveTimeName.setText("活动起止日期");
            mTvServiceTimeName.setText("服务时间");
            mTvLocationName.setText("活动服务地址");
            mTvFoundersName.setText("活动发起单位");
            mTvClaimName.setText("活动要求");
            mTvDetailName.setText("活动详情");
            mTvSignedName.setText("已报名志愿者");
            mTvNumName.setText("活动招募人数");
            mTvTimeName.setText("活动服务时长");
        } else if (type == 1) {
            //岗位
            mTvTypeName.setText("岗位类型:");
            mTvRegisterTimeName.setText("岗位报名日期:");
            mTvEffectiveTimeName.setText("岗位起止日期:");
            mTvServiceTimeName.setText("单日服务时间");
            mTvLocationName.setText("岗位服务地址:");
            mTvFoundersName.setText("岗位发起单位:");
            mTvClaimName.setText("岗位要求");
            mTvDetailName.setText("岗位详情");
            mTvSignedName.setText("已报名志愿者");
            mTvNumName.setText("岗位招募人数");
            mTvTimeName.setText("岗位服务时长");
        }
    }

    /**
     * 设置右上角的岗位/活动状态
     * @param operationState
     */
    private void setOperationStateImg(String operationState){
        if (operationState.equals("招募中")){
            mImageViewState.setImageResource(R.mipmap.icon_recruiting_right);
        }else if (operationState.equals("进行中")) {
            mImageViewState.setImageResource(R.mipmap.icon_processing_right);
        }else if (operationState.equals("已结束")) {
            mImageViewState.setImageResource(R.mipmap.icon_over_right);
        }
    }

    private void initActionBar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_normal, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);
        titleTv = (TextView) mCustomView.findViewById(R.id.tv_normal_projectswitch);
        if (type == 0) {
            titleTv.setText("活动");
        } else if (type == 1) {
            titleTv.setText("岗位");
        }

        indicatorImg = (ImageView) mCustomView.findViewById(R.id.imageview_normal_indicator);
        indicatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        magnifierImg = (ImageView) mCustomView.findViewById(R.id.imageview_normal_magnifier);
        magnifierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (origin == 1) {
                    finish();
                } else {
                    Intent intent = new Intent(ProjectDetailActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    @OnClick({R.id.btn_projectdetail_share,
            R.id.btn_projectdetail_apply,
            R.id.btn_projectdetail_focuson,
            R.id.imageview_projectdetail_itemImage,
            R.id.tv_projectDetail_contactName,
            R.id.tv_projectDetail_contact})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_projectdetail_share:
                showShare();
                break;
            case R.id.btn_projectdetail_apply:
                siginIn();
                break;
            case R.id.btn_projectdetail_focuson:
                if (TextUtils.isEmpty(volunteerId)) {
                    showToast("请先登录");
                    return;
                }
                if (isInitAttention) {
                    setOrCelAttention();
                } else {
                    isAttention();
                }
                break;
            case R.id.imageview_projectdetail_itemImage:
                break;
            case R.id.tv_projectDetail_contactName:
                break;
            case R.id.tv_projectDetail_contact:
                break;
            default:
                break;
        }
    }

    /**
     * 根据活动的ID和name,来查询activityTime的时间
     */
    private void getActivityTime(final int signUpType) {
        ActivityTimeQueryOptionDto avTimeDto = new ActivityTimeQueryOptionDto();
        avTimeDto.setActivityId(id);
        avTimeDto.setActivityName(activityName);
        avTimeDto.setPageIndex(1);
        avTimeDto.setPageSize(90);  //活动/岗位规定时间为一个季度：90天
        LinkedHashMap<String, String> sorts_map = new LinkedHashMap<>();
        sorts_map.put("STime", "asc");  //desc:降序  asc:升序
        avTimeDto.setSorts(sorts_map);
        AppActionImpl.getInstance(this).activityTimeQuery(avTimeDto, new ActionCallbackListener<PagedListEntityDto<ActivityTimeListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityTimeListDto> data) {
                if (data.getRows().isEmpty()) {
                    showToast("有效的报名时间段已经过期");
                    dissMissNormalDialog();
                } else {
                    sTime = new ArrayList<>();//储存报名的时间，已天为单位
                    notes = new HashMap<String, String>();//日历上的标记  时间 剩余报名人数
                    times = data.getRows();//所有报名时间段的数据
                    for (int i = 0; i < data.getRows().size(); i++) {
                        ActivityTimeListDto dto = data.getRows().get(i);//获取时间段对象
                        try {
                            Date date = format.parse(dto.getSTime());
                            CalendarDay temp = new CalendarDay(date);
                            notes.put(temp.toString(), "余" + (dto.getAllowNum() - dto.getRecruitedNum()));
                            sTime.add(i, temp.getYear() + "-" + (temp.getMonth() + 1) + "-" + temp.getDay());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    if (signUpType == 1){ //1:表示日历报名
                        dissMissNormalDialog();
                        //显示日历选择器
                        showSiginInDialog();
                    }

                    if (signUpType == 2){ //2：表示批量报名
                        dissMissNormalDialog();
                        createMoreSignUpPickerAlertDialog(times);
                    }

                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast(message);
                dissMissNormalDialog();
            }
        });
    }

    private void siginIn() {
        //判断是否已经登录
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
        if (!isLogin || TextUtils.isEmpty(volunteerId)) {
            showToast("请先登录");
            return;
        }
        if (mOperationState.equals("已结束")) {
            showToast("有效的报名时间段已经过期");
        } else {
            createAlertDialog();
        }
    }

    private void showSiginInDialog() {
        Holder holder = new ViewHolder(R.layout.dialog_caldroid);
        final DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setCancelable(true)
                .setGravity(Gravity.BOTTOM)
                .create();
        final TextView selsecTv = (TextView) dialogPlus.getHolderView().findViewById(R.id.tv_dialogCaldroid_dateSelect);
        Button signUpBtn = (Button) dialogPlus.getHolderView().findViewById(R.id.btn_dialogCaldroid_signUp);
        MaterialCalendarView calendarView = (MaterialCalendarView) dialogPlus.getHolderView().findViewById(R.id.calendarView);
        calendarView.setTileHeightDp(44);
        calendarView.setArrowColor(getResources().getColor(R.color.colorCyan));
        //这是一个自定义的set方法,用来存储服务器传过来的活动报名日期，在日期控件上显示日历上的标记  时间 剩余报名人数
        calendarView.setNotes(notes);
        Date date = new Date();
        try {
            date = format.parse(times.get(0).getSTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);

        // 设置日历显示的时间
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(year,month,1))
                .commit();

        //日期选择模式：默认为单选，可设置为多选模式
        //calendarView.setSelectionMode(SELECTION_MODE_MULTIPLE);
        calendarView.setOnClickListener(new MaterialCalendarView.CloseListener() {
            @Override
            public void close(View view) {
                dialogPlus.dismiss();
            }
        });
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                selsecTv.setText(date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay());
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(selsecTv.getText())) {
                    showToast("请选择一个日期");
                    return;
                }
                //筛选出选择了哪个日期
                int select = -1;
                for (int i = 0; i < sTime.size(); i++) {
                    if (sTime.get(i).equals(selsecTv.getText().toString())) {
                        select = i;
                        break;
                    }
                }
                //判断是否还有剩余
                final ActivityTimeListDto dto = times.get(select);
                if (dto.getRecruitedNum().intValue() == dto.getAllowNum().intValue()) {
                    showToast("报名人数已满");
                    return;
                }

                showNormalDialog("请稍后...");
                //判断是否已经报名
                ActivityRecruitQueryOptionDto queryOptionDto = new ActivityRecruitQueryOptionDto();
                queryOptionDto.setActivityId(id);
                queryOptionDto.setVolunteerId(volunteerId);
                queryOptionDto.setActivityTimeId(dto.getId());
                AppActionImpl.getInstance(getApplicationContext()).activityRecruitQuery(queryOptionDto,
                        new ActionCallbackListener<PagedListEntityDto<ActivityRecruitListDto>>() {
                            @Override
                            public void onSuccess(PagedListEntityDto<ActivityRecruitListDto> data) {
                                if (data.getRows() == null || data.getRows().size() == 0) {
                                    //报名
                                    List<ActivityRecruitDto> recruitDtos = new ArrayList<ActivityRecruitDto>();
                                    ActivityRecruitDto recruitDto = new ActivityRecruitDto();
                                    recruitDto.setActivityId(id);
                                    recruitDto.setVolunteerId(volunteerId);
                                    recruitDto.setActivityTimeId(dto.getId());
                                    recruitDto.setBaoMingDate(Util.getNowDate("yyyy-MM-dd HH:mm:ss"));
                                    recruitDto.setExecuteTime(Util.getNowDate(dto.getSTime()));//参加的活动或岗位时间
                                    recruitDto.setAuditStatus(0);
                                    recruitDto.setSign(0);
                                    recruitDto.setSignout(0);
                                    recruitDto.setSource(0);
                                    //获取Mac地址
                                    recruitDto.setIP(DeviceUtils.getMacAddress());
                                    recruitDto.setDeleted(false);
                                    recruitDtos.add(recruitDto);

                                    AppActionImpl.getInstance(getApplicationContext()).activityRecruitCreate(recruitDtos,
                                            new ActionCallbackListener<List<String>>() {
                                                @Override
                                                public void onSuccess(List<String> data) {
                                                    if (data != null) {
//                                                        showToast("报名成功,请留意报名审核结果短信");
                                                        MaterialDialogOneBtn();
                                                        dialogPlus.dismiss();
                                                        try {
                                                            getSiginInVolunteer();
                                                        } catch (Exception e) {
                                                        }
                                                    } else {
                                                        showToast("报名失败,数据异常");
                                                    }
                                                    dissMissNormalDialog();
                                                }

                                                @Override
                                                public void onFailure(String errorEvent, String message) {
                                                    showToast(message);
                                                    dissMissNormalDialog();
                                                }
                                            });
                                } else {
                                    dissMissNormalDialog();
                                    showToast("您已报名,请不要重复报名");
                                }
                            }

                            @Override
                            public void onFailure(String errorEvent, String message) {
                                dissMissNormalDialog();
                            }
                        });
            }
        });

        dialogPlus.show();
    }

    /**
     * 查看用户是否已经关注此项目，是为已关注，否为关注
     */
    private void isAttention() {
        if (TextUtils.isEmpty(volunteerId)) {
            return;
        }
        ActivityAttentionQueryOptionDto queryOptionDto = new ActivityAttentionQueryOptionDto();
        queryOptionDto.setActivityId(id);
        queryOptionDto.setUserId(volunteerId);
        AppActionImpl.getInstance(this).activityAttentionQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<ActivityAttentionListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityAttentionListDto> data) {
                        if (data.getRows() != null && data.getRows().size() > 0) {
                            isAttention = true;
                            mBtnFocuson.setText("已关注");
                            attentionId = data.getRows().get(0).getId();
                        } else {
                            isAttention = false;
                            mBtnFocuson.setText("关注");
                        }
                        isInitAttention = true;
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    /**
     * 关注或取消关注
     */
    private void setOrCelAttention() {
        if (isAttention) {
            if (TextUtils.isEmpty(attentionId)) {
                return;
            }
            List<String> id = new ArrayList<>();
            id.add(attentionId);
            AppActionImpl.getInstance(this).activityAttentionDelete(id, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    mBtnFocuson.setText("关注");
                    isAttention = false;
                }

                @Override
                public void onFailure(String errorEvent, String message) {

                }
            });
        } else {
            List<ActivityAttentionDto> dtos = new ArrayList<>();
            ActivityAttentionDto dto = new ActivityAttentionDto();
            dto.setActivityId(id);
            dto.setUserId(volunteerId);
            dto.setAttentionTime(Util.getNowDate());
            dtos.add(dto);

            AppActionImpl.getInstance(this).activityAttentionCreate(dtos,
                    new ActionCallbackListener<List<String>>() {
                        @Override
                        public void onSuccess(List<String> data) {
                            if (data == null) {
                                return;
                            }
                            attentionId = data.get(0);
                            mBtnFocuson.setText("已关注");
                            isAttention = true;
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("关注失败，请稍后再试");
                        }
                    });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (resultCode == 0)
            finish();*/
    }

    private void showShare() {
        String title = (String) mTvTilte.getText();
        String content_txet = mTvDetail.getText().toString();
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        //oks.setTitle(getString(R.string.ssdk_oks_share));
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.nbzyz.org/" + activityOrJob_Url + id);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(content_txet);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.nbzyz.org/" + activityOrJob_Url + id);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(content_txet);
        // site是分享此内容的网站名称，仅在QQ空间使用
        //oks.setSite(getString(R.string.app_name));
        oks.setSite(content_txet);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.nbzyz.org/" + activityOrJob_Url + id);

        oks.setImageUrl(Util.getRealUrl(imagUrl));

        // 启动分享GUI
        oks.show(this);
    }


    private void createAlertDialog() {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_sign_up_type, null);
        Button btnDialogOnlySignUp = (Button) view.findViewById(R.id.btn_dialog_only_signUp);
        Button btnDialogMoreSignUp = (Button) view.findViewById(R.id.btn_dialog_more_signUp);
        if (btnDialogOnlySignUp != null) {
            btnDialogOnlySignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dismiss();
                    showNormalDialog("正在从服务器获取数据...");
                    getActivityTime(1);//获取到activityTime数据的接口方法 1:表示日历报名
                }
            });
        }
        if (btnDialogMoreSignUp != null) {
            btnDialogMoreSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dismiss();
                    showNormalDialog("正在从服务器获取数据...");
                    getActivityTime(2);//获取到activityTime数据的接口方法 1:表示日历报名
                }
            });
        }

        builder.setView(view);
        builder.show();
    }

    private List<String> mStartDateList = new ArrayList<>(); //活动开始时间的集合
    private List<String> mEndDateList = new ArrayList<>();  //活动结束时间的集合
    private String mStartDate;  //开始时间
    private String mEndDate;    //结束时间

    private List<String> mDatesList = new ArrayList<>(); //选中的时间段

    private void createMoreSignUpPickerAlertDialog(List<ActivityTimeListDto> times) {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = getLayoutInflater();
        final View view =  inflater.inflate(R.layout.dialog_sign_up_more_picker, null);
        TextView tvTitleName = (TextView) view.findViewById(R.id.tv_Title_Name);
        final TextView tvStartDate = (TextView) view.findViewById(R.id.tv_start_date);
        final TextView tvEndDate = (TextView) view.findViewById(R.id.tv_end_date);
        Button btnMoreSignUp = (Button) view.findViewById(R.id.btn_more_signUp);
        tvTitleName.setText(activityName);
        for (int i=0;i<times.size();i++){
            mStartDateList.add(times.get(i).getSTime().split(" ")[0]);
            mEndDateList.add(times.get(i).getETime().split(" ")[0]);
        }
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(tvStartDate);
            }
        });

        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show(tvEndDate);
            }
        });

        btnMoreSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    mStartDate = tvStartDate.getText().toString().split(" ")[0];
                    mEndDate = tvEndDate.getText().toString().split(" ")[0];
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                    Date start = sdf.parse(mStartDate);
                    Date end = sdf.parse(mEndDate);
                    List<Date> dateList = Util.dateSplit(start, end);
                    if (!dateList.isEmpty()) {
                        mDatesList.clear();
                        for (Date date : dateList) {
                            String format = sdf.format(date);
                            mDatesList.add(format);
                        }
                        moreSignUp(builder,mDatesList);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        builder.setView(view);
        builder.show();
    }

    private TimePickerView pvTime;
    private String mStartYear;
    private String mStartMonth;
    private String mStartDay;
    private String mEndYear;
    private String mEndMonth;
    private String mEndDay;
    /**
     * 时间选择器
     */
    private void initTimePicker() {
        if (type == 0){
            String startTime = mActivityViewDto.getStartTime().split(" ")[0];
            String finishTime = mActivityViewDto.getFinishTime().split(" ")[0];
            mStartYear = startTime.split("-")[0];
            mStartMonth = startTime.split("-")[1];
            mStartDay = startTime.split("-")[2];
            mEndYear = finishTime.split("-")[0];
            mEndMonth = finishTime.split("-")[1];
            mEndDay = finishTime.split("-")[2];
        }else if (type == 1){
            String startTime = mJobActivityViewDto.getStartTime().split(" ")[0];
            String finishTime = mJobActivityViewDto.getFinishTime().split(" ")[0];
            mStartYear = startTime.split("-")[0];
            mStartMonth = startTime.split("-")[1];
            mStartDay = startTime.split("-")[2];
            mEndYear = finishTime.split("-")[0];
            mEndMonth = finishTime.split("-")[1];
            mEndDay = finishTime.split("-")[2];
        }

//        Logger.e(mStartYear+"\n"+mStartMonth+"\n"+mStartDay+"\n"+mEndYear+"\n"+mEndMonth+"\n"+mEndDay);

        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(Integer.parseInt(mStartYear), Integer.parseInt(mStartMonth)-1, Integer.parseInt(mStartDay));
        Calendar endDate = Calendar.getInstance();
        endDate.set(Integer.parseInt(mEndYear), Integer.parseInt(mEndMonth)-1, Integer.parseInt(mEndDay));
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                TextView tv = (TextView) v;
                tv.setText(getTime(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .isDialog(true)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
        return format.format(date);
    }

    //批量报名日期数据上传
    private void moreSignUp(final AlertDialog builder, List<String> list){
        //报名
        List<ActivityRecruitDto> recruitDtos = new ArrayList<ActivityRecruitDto>();

        for (int i=0;i<times.size();i++){
            for (int j=0;j<list.size();j++){
                String sTime = times.get(i).getMountguardTime();
                String[] split = sTime.split(" ");
                //活动的时间
                String replaceTime1 = split[0].trim();
                //批量报名的时间
                String replaceTime2 = list.get(j).trim();
                if (replaceTime1.equals(replaceTime2)){
                    ActivityRecruitDto recruitDto = new ActivityRecruitDto();
                    recruitDto.setActivityId(id);
                    recruitDto.setVolunteerId(volunteerId);
                    recruitDto.setActivityTimeId(times.get(i).getId());
                    recruitDto.setBaoMingDate(Util.getNowDate("yyyy-MM-dd HH:mm:ss"));
                    recruitDto.setExecuteTime(Util.getNowDate(times.get(i).getSTime()));//参加的活动或岗位时间
                    recruitDto.setAuditStatus(0);
                    recruitDto.setSign(0);
                    recruitDto.setSignout(0);
                    recruitDto.setSource(0);
                    //获取Mac地址
                    recruitDto.setIP(DeviceUtils.getMacAddress());
                    recruitDto.setDeleted(false);
                    recruitDtos.add(recruitDto);

                }
            }
        }

            AppActionImpl.getInstance(getApplicationContext()).activityRecruitCreate(recruitDtos,
                    new ActionCallbackListener<List<String>>() {
                        @Override
                        public void onSuccess(List<String> data) {
                            builder.dismiss();
                            if (data != null) {
                                showToast("报名成功");
                                try {
                                    getSiginInVolunteer();
                                } catch (Exception e) {
                                }
                            } else {
                                showToast("报名失败");
                            }
                            dissMissNormalDialog();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast(message);
                            dissMissNormalDialog();
                        }
                    });
    }


    private void MaterialDialogOneBtn() {
        final MaterialDialog dialog = new MaterialDialog(this);
        dialog.btnNum(1)
                .contentTextColor(Color.parseColor("#fc9553"))
                .content("报名成功,请留意报名审核结果短信")
                .btnText("确定")
                .showAnim(mBasIn)
                .dismissAnim(mBasOut)
                .show();

        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.dismiss();
            }
        });
    }
}
