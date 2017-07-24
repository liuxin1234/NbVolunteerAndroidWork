package com.example.renhao.wevolunteer;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.renhao.wevolunteer.adapter.FragmentSwitchAdapter;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.fragment.ProjectFragmentV4;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/8 11:35
 * 修改备注：
 */
public class ProjectActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {
    private static final String TAG = "ProjectActivity";
    public static final int ACTIVITY = 1;
    public static final int JOBS = 0;
    @Bind(R.id.changrTv_project_homepage)
    ChangeColorIconWithTextView mHomepage;
    @Bind(R.id.changrTv_project_find)
    ChangeColorIconWithTextView mFind;
    @Bind(R.id.changrTv_project_signin)
    ChangeColorIconWithTextView mSignin;
    @Bind(R.id.changrTv_project_myself)
    ChangeColorIconWithTextView mMyself;
    @Bind(R.id.linaerlayout_project_bottombar)
    LinearLayout mLinaerlayoutProjectBottombar;
    private int page = JOBS ;
    @Bind(R.id.viewpager_project_switch)
    ViewPager switchPage;
    ImageView indicatorImg;
    RadioButton activityBtn;
    RadioButton jobsBtn;
    RadioGroup switchGroup;
    ImageView magnifierImg;

    private View mCustomView;//actionbar的自定义视图
    private ProjectFragmentV4 mActivityFragment;
    private ProjectFragmentV4 mProjectFragment;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentSwitchAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        ButterKnife.bind(this);
        switchPage.addOnPageChangeListener(this);
        mActivityFragment = new ProjectFragmentV4();
        mActivityFragment.setProjectFragmentType(ProjectFragmentV4.ACTIVITY);
        mProjectFragment = new ProjectFragmentV4();
        mProjectFragment.setProjectFragmentType(ProjectFragmentV4.JOBS);
        mFragments.add(mActivityFragment);
        mFragments.add(mProjectFragment);
        mAdapter = new FragmentSwitchAdapter(getSupportFragmentManager(), mFragments);
        switchPage.setAdapter(mAdapter);

        initActionbar();

        page = getIntent().getIntExtra("page", JOBS);
        if (page == ACTIVITY) {
            switchPage.setCurrentItem(page);
            jobsBtn.setTextColor(getResources().getColor(R.color.colorCyan));
            activityBtn.setTextColor(getResources().getColor(R.color.colorWhite));
            jobsBtn.setChecked(true);
        }

        mHomepage.setIconColor(getResources().getColor(R.color.colorCyan));
    }


    private void initActionbar() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_project, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);

        indicatorImg = (ImageView) mCustomView.findViewById(R.id.imageview_project_indicator);
        indicatorImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switchGroup = (RadioGroup) mCustomView.findViewById(R.id.radiogroup_project_projectswitch);
        switchGroup.setOnCheckedChangeListener(this);
        activityBtn = (RadioButton) mCustomView.findViewById(R.id.radiobutton_project_activity);
        jobsBtn = (RadioButton) mCustomView.findViewById(R.id.radiobutton_project_jobs);
        magnifierImg = (ImageView) mCustomView.findViewById(R.id.imageview_project_magnifier);
        magnifierImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radiobutton_project_jobs:
                jobsBtn.setTextColor(getResources().getColor(R.color.colorCyan));
                activityBtn.setTextColor(getResources().getColor(R.color.colorWhite));
                switchPage.setCurrentItem(ACTIVITY);
                page = ACTIVITY;
                break;
            case R.id.radiobutton_project_activity:
                activityBtn.setTextColor(getResources().getColor(R.color.colorCyan));
                jobsBtn.setTextColor(getResources().getColor(R.color.colorWhite));
                switchPage.setCurrentItem(JOBS);
                page = JOBS;
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == ACTIVITY && page != ACTIVITY) {
            jobsBtn.setChecked(true);
            jobsBtn.setTextColor(getResources().getColor(R.color.colorCyan));
            activityBtn.setTextColor(getResources().getColor(R.color.colorWhite));
            page = ACTIVITY;
        } else if (position == JOBS && page != JOBS) {
            activityBtn.setChecked(true);
            activityBtn.setTextColor(getResources().getColor(R.color.colorCyan));
            jobsBtn.setTextColor(getResources().getColor(R.color.colorWhite));
            page = JOBS;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.changrTv_project_homepage, R.id.changrTv_project_find, R.id.changrTv_project_signin, R.id.changrTv_project_myself})
    public void onClick(View view) {
        Logger.v(TAG, "click");
        switch (view.getId()) {
            case R.id.changrTv_project_homepage:
                setResult(IndexActivity.HOME);
                break;
            case R.id.changrTv_project_find:
                setResult(IndexActivity.FIND);
                break;
            case R.id.changrTv_project_signin:
                setResult(IndexActivity.SIGNIN);
                break;
            case R.id.changrTv_project_myself:
                setResult(IndexActivity.MYSELF);
                break;
        }
        finish();
    }
}
