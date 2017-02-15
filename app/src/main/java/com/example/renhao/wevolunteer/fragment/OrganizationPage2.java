package com.example.renhao.wevolunteer.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.base.BaseFragmentV4;
import com.example.renhao.wevolunteer.event.OrganizationDetailEvent;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 9:34
 * 修改备注：
 */
public class OrganizationPage2 extends BaseFragmentV4 {
    private static final String TAG = "OrganizationPage2";

    public static final int REFRESH = 0;
    public static final int ADD = 1;

    public static final int RECUITMENT = 1;
    public static final int ONGOING = 2;
    public static final int FINISH = 3;
    public static final int ALL = -1;

    private int typeSelect = RECUITMENT;

    private Integer PageIndex;//(integer, optional): 当前页码
    private Integer PageSize;//(integer, optional): 每页条数
    private Integer TotalCount;// (integer, optional): 总共记录数
    private Integer TotalPages;//(integer, optional): 总共分页数
    private Integer StartPosition;// (integer, optional): 记录开始位置
    private Integer EndPosition;//(integer, optional): 记录结束位置
    private Boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private Boolean HasNextPage=true;//(boolean, optional): 是否有下一页

    @Bind(R.id.listvew_organization_projectItem)
    PullToRefreshListView mListvew;
    @Bind(R.id.tv_orginazation_state_1)
    TextView mTvState1;
    @Bind(R.id.tv_orginazation_state_2)
    TextView mTvState2;
    @Bind(R.id.tv_orginazation_state_3)
    TextView mTvState3;
    @Bind(R.id.tv_orginazation_state_4)
    TextView mTvState4;

    private String companyId = "";

    private HomePageAdapter adapter;
    private List<HomePageListItem> list = new ArrayList<>();
    private List<ActivityListDto> dates = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_organization_page2, container, false);
        ButterKnife.bind(this, v);
        EventBus.getDefault().register(this);
        initListView();
        return v;
    }

    @Subscribe
    public void onEventMainThread(OrganizationDetailEvent event) {
        companyId = event.getCompanyViewDto().getId();
        initDate(REFRESH);
       /* List<ActivityViewDto> dates = event.getCompanyViewDto().getActivitys();
        if (dates == null)
            return;
        list = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            HomePageListItem item = new HomePageListItem();
            item.setType(dates.get(i).getType());
            item.setState(dates.get(i).getOperationState());
            item.setTitle(dates.get(i).getActivityName());
            item.setNum(dates.get(i).getRecruited());
            item.setMaxNum(dates.get(i).getRecruitNumber());
            item.setTime(dates.get(i).getLengthTime().toString());
            item.setImg(dates.get(i).getAppImgUrl());
            list.add(item);
        }

        adapter.setDate(list);*/

    }

    private void initListView() {
        mListvew.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initDate(REFRESH);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HasNextPage) {
                    initDate(ADD);
                } else {
                    showToast("到底了");
                    new FinishRefresh().execute();
                }

            }
        });
        mListvew.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mListvew.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mListvew.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        adapter = new HomePageAdapter(getActivity(), list);
        mListvew.setAdapter(adapter);
        mListvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                intent.putExtra("id", dates.get(position - 1).getId());
                intent.putExtra("type", dates.get(position - 1).getType());
                startActivity(intent);
            }
        });
    }

    private void initDate(final int type) {
        if (TextUtils.isEmpty(companyId))
            return;
        ActivityQueryOptionDto dto = new ActivityQueryOptionDto();
        if (typeSelect > -1) {
            dto.setActivityState(typeSelect + "");
        }
        dto.setCompanyId(companyId);
        if (type == ADD) {
            dto.setPageIndex(PageIndex + 1);
        }

        AppActionImpl.getInstance(getActivity()).activityQuery(dto,
                new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                        mListvew.onRefreshComplete();
                        if (type == REFRESH) {
                            list = new ArrayList<HomePageListItem>();
                            dates = new ArrayList<ActivityListDto>();
                        }
                        for (int i = 0; i < data.getRows().size(); i++) {
                            dates.add(data.getRows().get(i));
                            ActivityListDto listDto = data.getRows().get(i);
                            HomePageListItem item = new HomePageListItem();
                            item.setType(listDto.getType());
                            item.setState(listDto.getOperationState());
                            item.setTitle(listDto.getActivityName());
                            item.setNum(listDto.getRecruited());
                            item.setMaxNum(listDto.getRecruitNumber());
                            item.setTime(listDto.getLengthTime());
                            item.setImg(listDto.getAppLstUrl());
                            list.add(item);
                        }
                        PageIndex = data.getPageIndex();
                        PageSize = data.getPageSize();
                        TotalCount = data.getTotalCount();
                        TotalPages = data.getTotalPages();
                        StartPosition = data.getStartPosition();
                        EndPosition = data.getEndPosition();
                        HasPreviousPage = data.getHasPreviousPage();
                        HasNextPage = data.getHasNextPage();

                        adapter.setDate(list);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        mListvew.onRefreshComplete();
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_orginazation_state_1, R.id.tv_orginazation_state_2, R.id.tv_orginazation_state_3, R.id.tv_orginazation_state_4})
    public void onClick(View view) {

        mTvState1.setBackgroundResource(R.color.colorWhite);
        mTvState2.setBackgroundResource(R.color.colorWhite);
        mTvState3.setBackgroundResource(R.color.colorWhite);
        mTvState4.setBackgroundResource(R.color.colorWhite);

        switch (view.getId()) {
            case R.id.tv_orginazation_state_1:
                mTvState1.setBackgroundResource(R.color.colorLightGray);
                typeSelect = RECUITMENT;
                break;
            case R.id.tv_orginazation_state_2:
                mTvState2.setBackgroundResource(R.color.colorLightGray);
                typeSelect = ONGOING;
                break;
            case R.id.tv_orginazation_state_3:
                mTvState3.setBackgroundResource(R.color.colorLightGray);
                typeSelect = FINISH;
                break;
            case R.id.tv_orginazation_state_4:
                mTvState4.setBackgroundResource(R.color.colorLightGray);
                typeSelect = ALL;
                break;
        }
        initDate(REFRESH);
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
            mListvew.onRefreshComplete();
        }
    }
}
