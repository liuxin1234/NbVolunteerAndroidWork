package com.example.renhao.wevolunteer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.area.AreaListDto;
import com.example.model.company.CompanyListDto;
import com.example.model.company.CompanyQueryOptionDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.items.OrganizationListItem;
import com.example.renhao.wevolunteer.adapter.ListDropDownAdapter;
import com.example.renhao.wevolunteer.adapter.OrganizationAdapter;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 11:29
 * 修改备注：
 */
public class OrganizationActivity extends BaseActivity {
    private static final String TAG = "Organization";
    @Bind(R.id.changrTv_view_homepage)
    ChangeColorIconWithTextView mHomepage;
    @Bind(R.id.changrTv_view_find)
    ChangeColorIconWithTextView mFind;
    @Bind(R.id.changrTv_view_signin)
    ChangeColorIconWithTextView mSignin;
    @Bind(R.id.changrTv_view_myself)
    ChangeColorIconWithTextView mMyself;
    @Bind(R.id.dropDownMenu_organization_menu)
    DropDownMenu mDropDownMenu;

    PullToRefreshListView contentView;
    private View mCustomView;//actionbar的自定义视图
    ImageView indicatorImg;
    TextView titleTv;
    ImageView magnifierImg;

    private ListDropDownAdapter typeAdapter;
    private ListDropDownAdapter areaAdapter;
    private OrganizationAdapter adapter;

    private List<View> popupViews = new ArrayList<>();

    private String[] headers = {"类型", "区域"};
    private List<String> type = new ArrayList<>();
    private List<String> typeCode = new ArrayList<>();
    private int typeSelect = -1;
    private List<String> area = new ArrayList<>();
    private List<String> areaCode = new ArrayList<>();
    private int areaSelect = -1;

    private ArrayList<OrganizationListItem> list = new ArrayList<>();
    private List<CompanyListDto> dates = new ArrayList<>();
    private Integer PageIndex;//(integer, optional): 当前页码
    private Integer PageSize;//(integer, optional): 每页条数
    private Integer TotalCount;// (integer, optional): 总共记录数
    private Integer TotalPages;//(integer, optional): 总共分页数
    private Integer StartPosition;// (integer, optional): 记录开始位置
    private Integer EndPosition;//(integer, optional): 记录结束位置
    private Boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private Boolean HasNextPage = true;//(boolean, optional): 是否有下一页


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        ButterKnife.bind(this);
       /* mHomepage = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_homepage);
        mFind = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_find);
        mSignin = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_signin);
        mMyself = (ChangeColorIconWithTextView) findViewById(R.id.changrTv_view_myself);
        mDropDownMenu= (DropDownMenu) findViewById(R.id.dropDownMenu_organization_menu);*/
        initActionBar();

        initDropDownMenu();

        View v = LayoutInflater.from(this).inflate(R.layout.view_prtlistview, null);
        contentView = ButterKnife.findById(v, R.id.ptrListview_view_list);
        initPtrListView(contentView);
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
        mDropDownMenu.setPopupViewListener(new DropDownMenu.PopupViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                setDropDownMenuList(position);
            }
        });

        mHomepage.setIconColor(getResources().getColor(R.color.colorCyan));

        initDate(REFRESH);
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    /**
     * 获取dropdownmenu的下拉的list
     *
     * @param position
     */
    private void setDropDownMenuList(int position) {
        if (position == 0) {//类型
            AppActionImpl.getInstance(this).dictionaryQueryDefault("ActivityType", "",
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
        } else if (position == 1) {//区域
            AppActionImpl.getInstance(this).AreaQuery("ac689592-5a3e-4015-8609-cdeed42df6ab",
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

    /**
     * 初始化actionBar
     */
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
        titleTv.setText("组织");
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
                Intent intent = new Intent(OrganizationActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDate(final int type) {
        showNormalDialog("正在加载中.....");
        CompanyQueryOptionDto companyQueryOptionDto = new CompanyQueryOptionDto();
        if (type == ADD) {
            companyQueryOptionDto.setPageIndex(PageIndex + 1);
        }
        if (typeSelect > -1) {
            companyQueryOptionDto.setServiceType(typeCode.get(typeSelect));
        }
        if (areaSelect > -1) {
            companyQueryOptionDto.setAreaCode(areaCode.get(areaSelect));
        }
        AppActionImpl.getInstance(this).companyQuery(companyQueryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<CompanyListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<CompanyListDto> data) {
                        dissMissNormalDialog();
                        contentView.onRefreshComplete();
                        if (type == REFRESH) {
                            list = new ArrayList<OrganizationListItem>();
                            dates = new ArrayList<CompanyListDto>();
                        }
                        for (int i = 0; i < data.getRows().size(); i++) {
                            dates.add(data.getRows().get(i));
                            OrganizationListItem item = new OrganizationListItem();
                            item.setName(data.getRows().get(i).getCompanyName());
                            item.setAddress(data.getRows().get(i).getAddr());
                            item.setIconUrl(data.getRows().get(i).getAppLstUrl());
                            item.setContactName(data.getRows().get(i).getPerson());
                            item.setContactPhone(data.getRows().get(i).getMobile());
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
                        new FinishRefresh().execute();
                        dissMissNormalDialog();
                    }
                });
    }

    private void initPtrListView(final PullToRefreshListView mPtr) {

        mPtr.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                final Picasso picasso = Picasso.with(getBaseContext());

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
                    showToast("到底了");
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

        adapter = new OrganizationAdapter(this, list);
        mPtr.setAdapter(adapter);
        mPtr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrganizationActivity.this, OrganizationDetailActivity.class);
                intent.putExtra("id", dates.get(position - 1).getId());
                startActivity(intent);
            }
        });
    }

    private void initDropDownMenu() {
        //类型
        type.add("类型");
        final ListView typeView = new ListView(this);
        typeAdapter = new ListDropDownAdapter(this, type);
        typeView.setDividerHeight(0);
        typeView.setAdapter(typeAdapter);
        typeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeSelect = position - 1;
                initDate(REFRESH);
                typeAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : type.get(position));
                mDropDownMenu.closeMenu();
            }
        });
        //区域
        area.add("区域");
        final ListView areaView = new ListView(this);
        areaAdapter = new ListDropDownAdapter(this, area);
        areaView.setDividerHeight(0);
        areaView.setAdapter(areaAdapter);
        areaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                areaSelect = position - 1;
                initDate(REFRESH);
                areaAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : area.get(position));
                mDropDownMenu.closeMenu();
            }
        });

        //init popupViews
        popupViews.add(typeView);
        popupViews.add(areaView);
    }

    @OnClick({R.id.changrTv_view_homepage,
            R.id.changrTv_view_find,
            R.id.changrTv_view_signin,
            R.id.changrTv_view_myself})
    public void onClick(View view) {
        Logger.v(TAG, "onclick");
        switch (view.getId()) {
            case R.id.changrTv_view_homepage:
                setResult(0);
                finish();
                break;
            case R.id.changrTv_view_find:
                setResult(1);
                finish();
                break;
            case R.id.changrTv_view_signin:
                setResult(2);
                finish();
                break;
            case R.id.changrTv_view_myself:
                setResult(3);
                finish();
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
            contentView.onRefreshComplete();
        }
    }
}
