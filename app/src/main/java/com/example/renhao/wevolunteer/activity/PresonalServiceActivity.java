package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.SearchActivity;
import com.example.renhao.wevolunteer.adapter.PresonalServiceAdapter;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.OrganizationDetailEvent;
import com.example.renhao.wevolunteer.event.PresonalServiceEvent;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.view.SlidingTabLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.activity
 * 项目日期：2018/7/20
 * 作者：liux
 * 功能：个人服务时长（在校，在职，退休）的查看界面
 *
 * @author 750954283(qq)
 */

public class PresonalServiceActivity extends BaseActivity {

    @Bind(R.id.slidTab_presonal)
    SlidingTabLayout slidTabPresonal;
    @Bind(R.id.viewPage_presonal)
    ViewPager viewPagePresonal;

    private CharSequence titles[] = {" 志愿服务时间", "历史时长补录"};
    private PresonalServiceAdapter adapter;
    private int numberOfTabs = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presonal_service);
        ButterKnife.bind(this);
        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setImgRightIcon(actionBar, R.drawable.magnifier, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresonalServiceActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ActionBarUtils.setTvTitlet(actionBar,title);
        initView();
        EventBus.getDefault().post(new PresonalServiceEvent(-2));
    }

    private void initView() {
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new PresonalServiceAdapter(getSupportFragmentManager(),this, titles, numberOfTabs);
        viewPagePresonal.setAdapter(adapter);
        slidTabPresonal.setDistributeEvenly(true);
        slidTabPresonal.setViewPager(viewPagePresonal);
    }
}
