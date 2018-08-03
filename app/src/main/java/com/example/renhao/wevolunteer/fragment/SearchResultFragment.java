package com.example.renhao.wevolunteer.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
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
import com.example.renhao.wevolunteer.SearchActivity;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.example.renhao.wevolunteer.event.SearchHistoryEvent;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/19 9:41
 * 修改备注：
 */
public class SearchResultFragment extends BaseFragment {
    private static final String TAG = "SearchResultFragment";
    public static final int REFRESH = 0;
    public static final int ADD = 1;
    @Bind(R.id.listview_searchResult)
    PullToRefreshListView mSearchResult;
    @Bind(R.id.tv_searchResult)
    TextView mTv;
    @Bind(R.id.img_No_SearchResult)
    ImageView imgNoSearchResult;


    private String keyWords;

    private int PageIndex;//(integer, optional): 当前页码
    private int PageSize = 20;//(integer, optional): 每页条数
    private int TotalCount;// (integer, optional): 总共记录数
    private int TotalPages;//(integer, optional): 总共分页数
    private int StartPosition;// (integer, optional): 记录开始位置
    private int EndPosition;//(integer, optional): 记录结束位置
    private boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private boolean HasNextPage = true;//(boolean, optional): 是否有下一页

    private SearchActivity mSearchActivity;

    private HomePageAdapter mHomePageAdapter;
    private List<HomePageListItem> projectItem = new ArrayList<>();
    private List<ActivityListDto> projectResult = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchActivity = (SearchActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchresult, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        imgNoSearchResult.setVisibility(View.INVISIBLE);
        mTv.setVisibility(View.INVISIBLE);
        mSearchResult.setVisibility(View.VISIBLE);

        initPullToRefreshListView();

        return view;
    }

    /**
     * 初始化下拉刷新上拉加载控件
     */
    private void initPullToRefreshListView() {

        mSearchResult.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final Picasso picasso = Picasso.with(getActivity());

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

        mSearchResult.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getDate(keyWords, REFRESH);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HasNextPage) {
                    getDate(keyWords, ADD);
                } else {
                    showToast("已经是最后一页");
                    new FinishRefresh().execute();

                }

            }
        });
        mSearchResult.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mSearchResult.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mSearchResult.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        mHomePageAdapter = new HomePageAdapter(getActivity(), projectItem);
        mSearchResult.setAdapter(mHomePageAdapter);

        mSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                intent.putExtra("id", projectResult.get(position - 1).getId());
                intent.putExtra("type", projectResult.get(position - 1).getType());
                intent.putExtra("origin", 1);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getSearchResult();
    }

    private void getSearchResult() {
        keyWords = mSearchActivity.getKeyWords();
        getDate(keyWords, REFRESH);
    }

    private void getDate(String keyWords, final int refreshType) {
        getProject(keyWords, refreshType);

    }


    private void getProject(String keyWords, final int refreshType) {
        LinkedHashMap<String, String> sorts_map = new LinkedHashMap<>();
        sorts_map.put("RunStatus", "asc");
        sorts_map.put("Stick", "desc");
        sorts_map.put("StartTime", "desc");
        ActivityQueryOptionDto queryOptionDto = new ActivityQueryOptionDto();
        queryOptionDto.setDeleted(false);
        queryOptionDto.setStatus(1);
        queryOptionDto.setSorts(sorts_map);
        if (refreshType == ADD) {
            queryOptionDto.setPageIndex(PageIndex + 1);
        }
        queryOptionDto.setKeyWord(keyWords);
        queryOptionDto.setPageSize(PageSize);
        AppActionImpl.getInstance(getActivity()).activityQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                        if (data.getRows().size() <= 0 || data.getRows() == null) {
                            noResult();
                            return;
                        }
                        if (mSearchResult == null) {
                            showToast("该类型搜索结果为空，请尝试其他类型搜索");
                            return;
                        }
                        mSearchResult.onRefreshComplete();
                        if (refreshType == REFRESH) {
                            projectItem = new ArrayList<HomePageListItem>();
                            projectResult = new ArrayList<ActivityListDto>();
                        }
                        for (int i = 0; i < data.getRows().size(); i++) {
                            projectResult.add(data.getRows().get(i));
                            ActivityListDto listDto = data.getRows().get(i);
                            HomePageListItem item = new HomePageListItem();
                            item.setType(listDto.getType());
                            item.setState(listDto.getOperationState());
                            item.setTitle(listDto.getActivityName());
                            item.setNum(listDto.getRecruited());
                            item.setMaxNum(listDto.getRecruitNumber());
                            item.setTime(listDto.getLengthTime());
                            item.setImg(listDto.getAppLstUrl());
                            item.setStartTime(listDto.getStartTime());
                            item.setFinishTime(listDto.getFinishTime());
                            projectItem.add(item);
                        }
                        PageIndex = data.getPageIndex();
                        PageSize = data.getPageSize();
                        TotalCount = data.getTotalCount();
                        TotalPages = data.getTotalPages();
                        StartPosition = data.getStartPosition();
                        EndPosition = data.getEndPosition();
                        HasPreviousPage = data.getHasPreviousPage();
                        HasNextPage = data.getHasNextPage();

                        mHomePageAdapter = new HomePageAdapter(getActivity(), projectItem);
                        mSearchResult.setAdapter(mHomePageAdapter);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        if (mSearchResult == null) {
                            return;
                        }
                        mSearchResult.onRefreshComplete();
                    }
                });
    }

    /**
     * 没有返回结果时调用
     */
    private void noResult() {
        imgNoSearchResult.setVisibility(View.VISIBLE);
        mTv.setVisibility(View.VISIBLE);
        mSearchResult.setVisibility(View.INVISIBLE);
    }

    @Subscribe
    public void onEventMainThread(SearchHistoryEvent event) {
        getDate(event.getKeyWords(), event.getRefreshType());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
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
            mSearchResult.onRefreshComplete();
        }
    }
}
