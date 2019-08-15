package com.example.renhao.wevolunteer.fragment;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.area.AreaListDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.items.HomePageListItem;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.HomePageAdapter;
import com.example.renhao.wevolunteer.adapter.ListDropDownAdapter;
import com.example.renhao.wevolunteer.base.BaseFragmentV4;
import com.example.renhao.wevolunteer.gps.GetGPS;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/8 13:36
 * 修改备注：
 */
public class ProjectFragmentV4 extends BaseFragmentV4 {
    private static final String TAG = "JobsFragment";


    public int PROJECT_TYPE = ACTIVITY;

    @Bind(R.id.dropDownMenu)
    DropDownMenu mDropDownMenu;

    PullToRefreshListView contentView;

    private ListDropDownAdapter typeAdapter;
    private ListDropDownAdapter stateAdapter;
    private ListDropDownAdapter areaAdapter;
    private ListDropDownAdapter smartAdapter;
    private List<View> popupViews = new ArrayList<>();

    private HomePageAdapter adapter;

    private String headers[] = {"类型", "状态", "区域", "智能筛选"};
    private List<String> type = new ArrayList<>();
    private List<String> typeCode = new ArrayList<>();
    private int typeSelect = -1;
    private String[] state = {"状态", "招募中", "进行中", "已结束"};
    private List<String> stateCode = Arrays.asList("1", "2", "3");
    private int stateSelect = -1;
    private List<String> area = new ArrayList<>();
    private List<String> areaCode = new ArrayList<>();
    private int areaSelect = -1;
    private String[] smart = {"智能筛选", "最新发布", "热门报名", "距离最近"};
    private List<Integer> smartInt = Arrays.asList(0, 1, 2);
    private int smartSelect = -1;

    private List<HomePageListItem> list = new ArrayList<>();
    private List<ActivityListDto> dates = new ArrayList<>();

    private String lat;
    private String lng;
    private int scope = 0;

    //一岗双责序列号
    private int serviceTypeSelect = -1;
    private List<String> serviceType = new ArrayList<>();
    private List<String> serviceTypeCode = new ArrayList<>();
    private boolean service_flag = true;    //判断是否点击了"一员双岗双建功"选项 用来固定第一层的postion
    private boolean TypeSelect_flag = false;//判断是否选中"一员双岗双建功"选项
    private int dropDownMenu_int = 1; //判断是否有2级菜单



    private int PageIndex;//(integer, optional): 当前页码
    private int PageSize;//(integer, optional): 每页条数
    private int TotalCount;// (integer, optional): 总共记录数
    private int TotalPages;//(integer, optional): 总共分页数
    private int StartPosition;// (integer, optional): 记录开始位置
    private int EndPosition;//(integer, optional): 记录结束位置
    private boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private boolean HasNextPage = true;//(boolean, optional): 是否有下一页

    /**
     * 设置此fragment的类型
     *
     * @param type 0 活动，1 岗位
     */
    public void setProjectFragmentType(int type) {
        PROJECT_TYPE = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);

        View v = inflater.inflate(R.layout.view_prtlistview, null);
        //PullToRefreshListView contentView = new PullToRefreshListView(getActivity());
        contentView = ButterKnife.findById(v, R.id.ptrListview_view_list);

        initDropDownMenu();
        initPtrListView(contentView);

        //init dropdownview
        initDropdownview();

        initDate(REFRESH);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initDropdownview() {
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        mDropDownMenu.setPopupViewListener(new DropDownMenu.PopupViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                setDropDownMenuList(position);
            }
        });
    }

    public void setDropDownMenuList(int position) {
        if (position == 0) {//类型
            AppActionImpl.getInstance(getActivity()).dictionaryQueryDefault("ActivityType", "",
                    new ActionCallbackListener<List<DictionaryListDto>>() {
                        @Override
                        public void onSuccess(List<DictionaryListDto> data) {
                            type = new ArrayList<String>();
                            type.add("类型");
                            typeCode = new ArrayList<String>();
                            for (int i = 0; i < data.size(); i++) {
                                type.add(data.get(i).getName());
                                typeCode.add(data.get(i).getCode());
                            }
                            typeAdapter.setDate(type);
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {

                        }
                    });
        } else if (position == 2) {//区域
            AppActionImpl.getInstance(getActivity()).AreaQuery("ac689592-5a3e-4015-8609-cdeed42df6ab",
                    new ActionCallbackListener<List<AreaListDto>>() {
                        @Override
                        public void onSuccess(List<AreaListDto> data) {
                            if (data == null)
                                return;
                            area = new ArrayList<String>();
                            area.add("区域");
                            areaCode = new ArrayList<String>();
                            for (int i = 0; i < data.size(); i++) {
                                area.add(data.get(i).getName());
                                areaCode.add(data.get(i).getCode());
                            }
                            areaAdapter.setDate(area);
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {

                        }
                    });
        }
    }

    private void initDate(final int type) {
        showNormalDialog("正在加载中....");
        ActivityQueryOptionDto dto = new ActivityQueryOptionDto();
        LinkedHashMap<String, String> sorts_map = new LinkedHashMap<>();
        sorts_map.put("RunStatus", "asc");
        sorts_map.put("Stick", "desc");
        sorts_map.put("StartTime", "desc");

        dto.setLanguageType("");
        dto.setType(PROJECT_TYPE);
        if (typeSelect > -1) {
            if (TypeSelect_flag){ // 判断是否有2级菜单
                dto.setActivityType(typeCode.get(serviceTypeSelect));
//                System.out.println("执行到了。。。。");
                if (serviceTypeCode != null && serviceTypeCode.size() > 0){
                    dto.setServiceType(Integer.valueOf(serviceTypeCode.get(typeSelect)));
                }

            }else {
                dto.setActivityType(typeCode.get(typeSelect));
            }

        }
        if (stateSelect > -1) {
            dto.setActivityState(stateCode.get(stateSelect));
        }
        if (areaSelect > -1) {
            dto.setAreaCode(areaCode.get(areaSelect));
        }
        if (type == ADD) {
            dto.setPageIndex(PageIndex + 1);
        }
        switch (smartSelect) {
            case 0:
                sorts_map.put("CreateTime", "Asc");
                dto.setDeleted(0);
                dto.setActivityState("1");
                dto.setStatus(1);
                System.out.println("0");
                break;
            case 1:
                sorts_map.put("JoinNum", "Desc");
                dto.setDeleted(0);
                dto.setActivityState("1");
                dto.setStatus(1);
                System.out.println("1");
                break;
            case 2:
                dto.setDeleted(0);
                dto.setActivityState("1");
                dto.setStatus(1);
                dto.setLat(lat);
                dto.setLng(lng);
                dto.setScope(scope);
                System.out.println("2");
                break;
        }
        dto.setDeleted(0);//false 为正常在用的
        dto.setStatus(1);
        dto.setSorts(sorts_map);
        AppActionImpl.getInstance(getActivity()).activityQuery(dto,
                new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                        dissMissNormalDialog();

                        if (data != null && data.getRows() != null && data.getRows().size()>0){
                            contentView.onRefreshComplete();
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
                                item.setImg(listDto.getPcLstUrl());
                                item.setStartTime(listDto.getStartTime());
                                item.setFinishTime(listDto.getFinishTime());
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

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        contentView.onRefreshComplete();
                        dissMissNormalDialog();
                    }
                });

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initPtrListView(final PullToRefreshListView mPtr) {
        mPtr.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final Picasso picasso = Picasso.with(getContext());

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

        mPtr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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
        mPtr.setMode(PullToRefreshBase.Mode.BOTH);//设置头部下拉刷新
        //设置刷新时显示的文本
        ILoadingLayout startLayout = mPtr.getLoadingLayoutProxy(true, false);//开始头部的layout
        startLayout.setPullLabel("正在下拉刷新....");
        startLayout.setRefreshingLabel("正在玩命加载....");
        startLayout.setReleaseLabel("放开刷新");

        ILoadingLayout endLayout = mPtr.getLoadingLayoutProxy(false, true);//开始底部的layout
        endLayout.setPullLabel("正在下拉刷新....");
        endLayout.setRefreshingLabel("正在玩命加载....");
        endLayout.setReleaseLabel("放开刷新");

        adapter = new HomePageAdapter(getActivity(), list);
        mPtr.setAdapter(adapter);

        mPtr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                intent.putExtra("id", dates.get(position - 1).getId());
                intent.putExtra("type", dates.get(position - 1).getType());
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化下拉菜单
     */
    private void initDropDownMenu() {
        //类型
        type.add("类型");
        final ListView typeView = (ListView) LayoutInflater.from(getActivity()).inflate(R.layout.view_listview, null);
        typeAdapter = new ListDropDownAdapter(getActivity(), type);
        typeView.setDividerHeight(0);
        typeView.setAdapter(typeAdapter);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeSelect = position - 1;
                typeAdapter.setCheckItem(position);

                //设置这个判断主要防止typeCode的position的值为0的时候，position-1为负数，导致APP闪退
                if (typeSelect > -1){

                    //判断用户点击的是第一级菜单 并且点击的不是“一员双岗双建功”
                    if (!typeCode.get(position-1).equals("00024") && dropDownMenu_int == 1){
                        TypeSelect_flag = false;    //未选择“一员双岗双建功”
                    }

                    if (typeCode.get(position-1).equals("00024") && service_flag){
                        serviceTypeSelect = position - 1;
                        TypeSelect_flag = true;
                        dropDownMenu_int = 2;
                        againData();
                    } else {
                        mDropDownMenu.setTabText(position == 0 ? headers[0] : (dropDownMenu_int == 2 ? serviceType.get(position):type.get(position)));
                        mDropDownMenu.closeMenu();
                        service_flag = true;
                        dropDownMenu_int = 1;
                        initDate(REFRESH);

                    }
                }else {  //当用户点击下拉菜单的是“类型”字段时候，即postion=0时
                    TypeSelect_flag = false;    //未选择“一员双岗双建功”
                    mDropDownMenu.setTabText(position == 0 ? headers[0] : (dropDownMenu_int == 2 ? serviceType.get(position):type.get(position)));
                    mDropDownMenu.closeMenu();
                    service_flag = true;
                    dropDownMenu_int = 1;
                    initDate(REFRESH);
                }

            }
        });
        //状态
        final ListView stateView = new ListView(getActivity());
        stateAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(state));
        stateView.setDividerHeight(0);
        stateView.setAdapter(stateAdapter);
        stateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stateSelect = position - 1;
                initDate(REFRESH);
                stateAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : state[position]);
                mDropDownMenu.closeMenu();
            }
        });
        //区域
        area.add("区域");
        final ListView areaView = new ListView(getActivity());
        areaAdapter = new ListDropDownAdapter(getActivity(), area);
        areaView.setDividerHeight(0);
        areaView.setAdapter(areaAdapter);
        areaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaSelect = position - 1;
                initDate(REFRESH);
                areaAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[2] : area.get(position));
                mDropDownMenu.closeMenu();
            }
        });
        //智能筛选
        final ListView smartView = new ListView(getActivity());
        smartView.setDividerHeight(300);
        smartAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(smart));
        smartView.setDividerHeight(0);
        smartView.setAdapter(smartAdapter);
        smartView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                smartSelect = position - 1;
                if (smartSelect < 2)
                    initDate(REFRESH);
                else {
            GetGPS getGPS = new GetGPS(getActivity());
            getGPS.setGPSListener(new GetGPS.GPSListener() {
                @Override
                public void OnGPSChanged(AMapLocation aMapLocation) {
                    lat = aMapLocation.getLatitude() + "";
                    lng = aMapLocation.getLongitude() + "";
                    initDate(REFRESH);
                }
            });
            getGPS.startGPSListener();
        }
        smartAdapter.setCheckItem(position);
        mDropDownMenu.setTabText(position == 0 ? headers[3] : smart[position]);
        mDropDownMenu.closeMenu();
    }
});

        //init popupViews
        popupViews.add(typeView);
        popupViews.add(stateView);
        popupViews.add(areaView);
        popupViews.add(smartView);
    }


    /**
     * 一元双建双功
     */
    public void againData(){
        AppActionImpl.getInstance(getActivity()).dictionaryQueryDefault("YYSGSJG", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        serviceType = new ArrayList<String>();
                        serviceType.add("类型");
                        serviceTypeCode = new ArrayList<String>();
                        for (int i = 0; i < data.size(); i++) {
                            serviceType.add(data.get(i).getName());
                            serviceTypeCode.add(data.get(i).getCode());
                        }

                        typeAdapter.setDate(serviceType);
                        service_flag = false;
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    //测试用方法,延迟 1秒左右，在调用onRefreshComplete 方法,防止刷新获取数据时候，时间太短，就会出现问题
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
            contentView.onRefreshComplete();//停止 刷新操作
        }
    }
}
