package com.example.renhao.wevolunteer.fragment;

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
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.example.model.durationRecord.DurationRecordListDto;
import com.example.model.durationRecord.DurationRecordQueryOptionDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.PresonalServiceTwoAdapter;
import com.example.renhao.wevolunteer.base.BaseFragmentV4;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 9:34
 * 修改备注：
 */
public class PresonalServicePage2 extends BaseFragmentV4 {
    private static final String TAG = "OrganizationPage2";
    @Bind(R.id.list_Presonal_Service_DurationRecordItem)
    PullToRefreshListView mListView;
    @Bind(R.id.tv_NoData)
    TextView tvNoData;

    private Integer PageIndex = 1;//(integer, optional): 当前页码
    private Integer PageSize = 6;//(integer, optional): 每页条数
    private Integer TotalCount;// (integer, optional): 总共记录数
    private Integer TotalPages;//(integer, optional): 总共分页数
    private Integer StartPosition;// (integer, optional): 记录开始位置
    private Integer EndPosition;//(integer, optional): 记录结束位置
    private Boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private Boolean HasNextPage = true;//(boolean, optional): 是否有下一页


    private PresonalServiceTwoAdapter adapter;
    private List<DurationRecordListDto> list = new ArrayList<>();
    private Integer tag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_presonal_service_page2, container, false);
        ButterKnife.bind(this, v);
        tag = getArguments().getInt("tag", -1);
        initListView();
        initDate(PageIndex);
        return v;
    }


    private void initListView() {
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                PageIndex = 1;
                initDate(PageIndex);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HasNextPage) {
                    PageIndex++;
                    initDate(PageIndex);
                } else {
                    showToast("到底了");
                    new FinishRefresh().execute();
                }

            }
        });
        mListView.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mListView.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mListView.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        adapter = new PresonalServiceTwoAdapter(getActivity(), list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
//                intent.putExtra("id", dates.get(position - 1).getId());
//                intent.putExtra("type", dates.get(position - 1).getType());
//                startActivity(intent);
            }
        });
    }

    private void initDate(final int pageIndex) {
        final String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        if (TextUtils.isEmpty(volunteerId))
            return;
        HashMap<String, String> map = new HashMap<>();
        map.put("CreateOperation.CreateTime", "desc");
        DurationRecordQueryOptionDto durationRecordQueryOptionDto = new DurationRecordQueryOptionDto();
        durationRecordQueryOptionDto.setPageIndex(pageIndex);
        Logger.e("" + tag);
        if (tag != -1) {
            durationRecordQueryOptionDto.setJobStatus(""+tag);
        }
        durationRecordQueryOptionDto.setPageSize(PageSize);
        durationRecordQueryOptionDto.setStatus(1);
        durationRecordQueryOptionDto.setVolunteerId(volunteerId);
        durationRecordQueryOptionDto.setSorts(map);
        AppActionImpl.getInstance(getActivity()).postDurationRecordQuery(durationRecordQueryOptionDto, new ActionCallbackListener<PagedListEntityDto<DurationRecordListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<DurationRecordListDto> data) {
                mListView.onRefreshComplete();
                if (data == null) {
                    tvNoData.setVisibility(View.VISIBLE);
                    return;
                }
                HasNextPage = data.getHasNextPage();
                List<DurationRecordListDto> rows = data.getRows();
                if (rows != null && rows.size() > 0) {
                    tvNoData.setVisibility(View.INVISIBLE);
                    if (pageIndex == 1) {
                        list.clear();
                    }
                    list.addAll(rows);
                    adapter.setData(list);
                } else {
                    tvNoData.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                tvNoData.setVisibility(View.VISIBLE);
                mListView.onRefreshComplete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
            mListView.onRefreshComplete();
        }
    }
}
