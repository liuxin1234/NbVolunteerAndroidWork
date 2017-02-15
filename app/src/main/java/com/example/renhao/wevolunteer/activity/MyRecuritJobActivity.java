package com.example.renhao.wevolunteer.activity;

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
import com.example.model.items.MyProjectItem;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.SearchActivity;
import com.example.renhao.wevolunteer.adapter.MyRecruitJobAdapter;
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

public class MyRecuritJobActivity extends BaseActivity {
    @Bind(R.id.imageView_myRecruitJob_btn_back)
    ImageView mBack;
    @Bind(R.id.imageview_myRecruitJob_magnifier)
    ImageView mMagnifier;
    @Bind(R.id.tv_myRecruitJob_title)
    TextView mTvTitle;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.listview_myRecruitJob)
    PullToRefreshListView mMyproject;

    private MyRecruitJobAdapter myProjectAdapter;
    private List<MyProjectItem> lists = new ArrayList<>();
    private List<ActivityRecruitListDto> dates = new ArrayList<>();

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
        setContentView(R.layout.activity_my_recurit_job);
        ButterKnife.bind(this);
        volunteerId = LocalDate.getInstance(this).getLocalDate("volunteerId", "");
        initPtrListView();
        initDate(REFRESH);
    }

    private void initDate(int type) {
        getMyProject(type);

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
                            item.setActivityRecruitId(listDto.getId());
                            item.setAuditStatus(listDto.getAuditStatus());
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
                    new MyRecuritJobActivity.FinishRefresh().execute();

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

        myProjectAdapter = new MyRecruitJobAdapter(this, lists);
        myProjectAdapter.setOnItemRemoveClickListener(new MyRecruitJobAdapter.OnItemRemoveClickListener() {
            @Override
            public void onItemRemoveClickListener(List<MyProjectItem> data, int position) {
                showNormalDialog("正在取消报名....");
                List<String> activityRecruitId = new ArrayList<>();
                activityRecruitId.add(data.get(position).getActivityRecruitId());
                AppActionImpl.getInstance(getApplicationContext()).activityRecruitRemove(activityRecruitId, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        initDate(REFRESH);
                        dissMissNormalDialog();
                        showToast("取消报名成功");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        dissMissNormalDialog();
                        showToast("取消报名失败，"+message);
                    }
                });
            }

        });
        mMyproject.setAdapter(myProjectAdapter);


        mMyproject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logger.v("-----", "click  " + position);
                Intent intent = new Intent(MyRecuritJobActivity.this, ProjectDetailActivity.class);

                    intent.putExtra("id", dates.get(position - 1).getActivityId());
                    if (dates.get(position - 1).getActivityType().equals("岗位"))
                        intent.putExtra("type", 1);
                    else
                        intent.putExtra("type", 0);

                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.imageView_myRecruitJob_btn_back, R.id.imageview_myRecruitJob_magnifier})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_myRecruitJob_btn_back:
                MyRecuritJobActivity.this.finish();
                break;
            case R.id.imageview_myRecruitJob_magnifier:
                Intent intent = new Intent(MyRecuritJobActivity.this, SearchActivity.class);
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
