package com.example.renhao.wevolunteer.activity;
/*
 *
 * Created by Ge on 2016/8/15  9:41.
 * 我的项目和我的关注共用此activity
 *
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activityRecruit.ActivityRecruitListDto;
import com.example.model.activityRecruit.ActivityRecruitQueryOptionDto;
import com.example.model.activityattention.ActivityAttentionListDto;
import com.example.model.activityattention.ActivityAttentionQueryOptionDto;
import com.example.model.items.MyProjectItem;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.SearchActivity;
import com.example.renhao.wevolunteer.adapter.MyProjectAdapter;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyProjectActivity extends BaseActivity {

    public static final int MY_PROJECT = 0;
    public static final int MY_ATTENTION = 1;
    private int activitySelect = MY_PROJECT;

    @Bind(R.id.imageView_myproject_btn_back)
    ImageView mBack;
    @Bind(R.id.imageview_myproject_magnifier)
    ImageView mMagnifier;
    @Bind(R.id.tv_myProject_title)
    TextView mTvTitle;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.listview_myproject)
    PullToRefreshListView mMyproject;

    /*    private ListView lv_myProject;
        private TextView tv_title;*/
    private MyProjectAdapter myProjectAdapter;
    private List<MyProjectItem> lists = new ArrayList<>();
    private List<ActivityRecruitListDto> dates = new ArrayList<>();
    private List<ActivityAttentionListDto> attentions = new ArrayList<>();

    private String volunteerId;

    private int PageIndex;//(integer, optional): 当前页码
    private int PageSize;//(integer, optional): 每页条数
    private int TotalCount;// (integer, optional): 总共记录数
    private int TotalPages;//(integer, optional): 总共分页数
    private int StartPosition;// (integer, optional): 记录开始位置
    private int EndPosition;//(integer, optional): 记录结束位置
    private boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private boolean HasNextPage = true;//(boolean, optional): 是否有下一页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproject);
        ButterKnife.bind(this);

        activitySelect = getIntent().getIntExtra("activityType", 0);

        volunteerId = LocalDate.getInstance(this).getLocalDate("volunteerId", "");

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        mTvTitle.setText(title);

        initPtrListView();

        initDate(REFRESH);
       /* //设置我的项目的适配器
        lv_myProject = (ListView) findViewById(R.id.lv_myproject);
        myProjectAdapter = new MyProjectAdapter(this);
        lv_myProject.setAdapter(myProjectAdapter);

        //设置标题
        tv_title = (TextView) findViewById(R.id.tv_myProject_title);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv_title.setText(title);


        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyProjectActivity.this.finish();
            }
        });*/
    }

    private void initDate(int type) {
        if (activitySelect == MY_PROJECT)
            getMyProject(type);
        else if (activitySelect == MY_ATTENTION) {
            getMyAttention(type);
        }

    }

    private void getMyAttention(final int type) {
        ActivityAttentionQueryOptionDto dto = new ActivityAttentionQueryOptionDto();
        HashMap<String,String> sorts_map = new HashMap<>();
        sorts_map.put("AttentionTime","desc");
        dto.setUserId(volunteerId);
        dto.setSorts(sorts_map);
        if (type == ADD) {
            dto.setPageIndex(PageIndex + 1);
        }

        AppActionImpl.getInstance(this).activityAttentionQuery(dto,
                new ActionCallbackListener<PagedListEntityDto<ActivityAttentionListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityAttentionListDto> data) {
                        mMyproject.onRefreshComplete();
                        if (type == REFRESH) {
                            lists = new ArrayList<MyProjectItem>();
                            attentions = new ArrayList<>();
                        }
                        for (int i = 0; i < data.getRows().size(); i++) {
                            attentions.add(data.getRows().get(i));
                            ActivityAttentionListDto listDto = data.getRows().get(i);
                            MyProjectItem item = new MyProjectItem();
                            item.setNeme(listDto.getActivityActivityName());
                            item.setState(listDto.getActivityState());
                            item.setTime(listDto.getAttentionTime());
                            item.setPic(listDto.getAppLstUrl());
                            lists.add(item);
                        }
                        PageIndex = data.getPageIndex();
                        PageSize = data.getPageSize();
                        TotalCount = data.getTotalCount();
                        TotalPages = data.getTotalPages();
                        StartPosition = data.getStartPosition();
                        EndPosition = data.getEndPosition();
                        HasPreviousPage = data.getHasPreviousPage();
                        HasNextPage = data.getHasNextPage();

                        myProjectAdapter.setDate(lists);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        mMyproject.onRefreshComplete();
                    }
                });
    }

    private void getMyProject(final int type) {
        ActivityRecruitQueryOptionDto dto = new ActivityRecruitQueryOptionDto();
        HashMap<String,String> sorts_map = new HashMap<>();
        sorts_map.put("BaoMingDate","desc");
        dto.setVolunteerId(volunteerId);
        dto.setSorts(sorts_map);
        if (type == ADD) {
            dto.setPageIndex(PageIndex + 1);
        }
        showNormalDialog("正在加载数据...");
        AppActionImpl.getInstance(this).activityRecruitQuery(dto,
                new ActionCallbackListener<PagedListEntityDto<ActivityRecruitListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityRecruitListDto> data) {
                        dissMissNormalDialog();
                        mMyproject.onRefreshComplete();
                        if (type == REFRESH) {
                            lists = new ArrayList<MyProjectItem>();
                            dates = new ArrayList<ActivityRecruitListDto>();
                        }
                        for (int i = 0; i < data.getRows().size(); i++) {
                            dates.add(data.getRows().get(i));
                            ActivityRecruitListDto listDto = data.getRows().get(i);
                            MyProjectItem item = new MyProjectItem();
                            item.setNeme(listDto.getActivityActivityName());
                            item.setState(listDto.getActivityState());
                            item.setTime(listDto.getActivityTimeSTime());
                            item.setPic(listDto.getAppLstUrl());
                            lists.add(item);
                        }
                        Logger.v("------", dates.size() + "");
                        PageIndex = data.getPageIndex();
                        PageSize = data.getPageSize();
                        TotalCount = data.getTotalCount();
                        TotalPages = data.getTotalPages();
                        StartPosition = data.getStartPosition();
                        EndPosition = data.getEndPosition();
                        HasPreviousPage = data.getHasPreviousPage();
                        HasNextPage = data.getHasNextPage();

                        myProjectAdapter.setDate(lists);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        dissMissNormalDialog();
                        mMyproject.onRefreshComplete();
                    }
                });
    }

    private void initPtrListView() {

        mMyproject.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final Picasso picasso = Picasso.with(getApplicationContext());

                if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                    picasso.resumeTag("Ptr");
                } else {
                    picasso.pauseTag("Ptr");
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mMyproject.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initDate(REFRESH);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HasNextPage) {
                    initDate(ADD);
                } else {
                    showToast("已经是最后一页");
                    new FinishRefresh().execute();

                }

            }
        });
        mMyproject.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mMyproject.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mMyproject.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        myProjectAdapter = new MyProjectAdapter(this, lists);
        mMyproject.setAdapter(myProjectAdapter);

        mMyproject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.v("-----", "click  " + position);
                Intent intent = new Intent(MyProjectActivity.this, ProjectDetailActivity.class);
                if (activitySelect == MY_ATTENTION) {
                    intent.putExtra("id", attentions.get(position - 1).getActivityId());
                    if (attentions.get(position - 1).getActivityType().equals("岗位"))
                        intent.putExtra("type", 1);
                    else
                        intent.putExtra("type", 0);
                } else if (activitySelect == MY_PROJECT) {
                    intent.putExtra("id", dates.get(position - 1).getActivityId());
                    if (dates.get(position - 1).getActivityType().equals("岗位"))
                        intent.putExtra("type", 1);
                    else
                        intent.putExtra("type", 0);
                }
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.imageView_myproject_btn_back, R.id.imageview_myproject_magnifier})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_myproject_btn_back:
                MyProjectActivity.this.finish();
                break;
            case R.id.imageview_myproject_magnifier:
                Intent intent = new Intent(MyProjectActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    //测试用方法
    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
//          adapter.notifyDataSetChanged();
            mMyproject.onRefreshComplete();
        }
    }
}
