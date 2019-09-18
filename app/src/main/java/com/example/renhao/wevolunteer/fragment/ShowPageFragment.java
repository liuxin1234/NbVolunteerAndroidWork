package com.example.renhao.wevolunteer.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.share.ShareListDto;
import com.example.model.share.ShareQueryOptionDto;
import com.example.model.sharePraise.SharepraiseDto;
import com.example.model.show.Data;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.activity.LoginActivity;
import com.example.renhao.wevolunteer.activity.show.CommentActivity;
import com.example.renhao.wevolunteer.activity.show.ShowActivity;
import com.example.renhao.wevolunteer.adapter.ShowAdapter;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.example.renhao.wevolunteer.common.Constants;
import com.example.renhao.wevolunteer.utils.Utils;
import com.example.renhao.wevolunteer.view.CustomLoadingUIProvider2;
import com.example.renhao.wevolunteer.view.DecorationLayout;
import com.example.renhao.wevolunteer.view.GlideSimpleLoader;
import com.example.renhao.wevolunteer.view.MessagePicturesLayout;
import com.example.renhao.wevolunteer.view.SpaceItemDecoration;
import com.github.ielse.imagewatcher.ImageWatcherHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/7 21:20
 * 修改备注：
 * 正式地址：https://appapi.zyh365.com/zyz-sign/index?source=ningbo&mobileunique=手机唯一码&idcard=身份证号
 * 测试地址：http://apitest.zyh365.com/api/web/zyz-sign/index?source=ningbo&mobileunique=手机唯一码&idcard=身份证号
 */
public class ShowPageFragment extends BaseFragment implements MessagePicturesLayout.Callback{
    private static final String TAG = "SigninPageFragment";
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ShowAdapter mShowAdapter;
    private List<Data> mList = new ArrayList<>();
    //朋友圈的展示数据
    private List<ShareListDto> mShareListDtos = new ArrayList<>();
    //点赞数据

    private ImageWatcherHelper iwHelper;

    private DecorationLayout layDecoration;

    private String volunteerId;
    private String volunteerName;

    private int pageIndex = 0;
    private static final int pageSize = 6;
    //    private boolean isNext = true; //默认首次下拉时有下一页，进行第一次数据加载
    private boolean HasNextPage = true;//(boolean, optional): 是否有下一页


    public static final int REFRESH = 0;
    public static final int ADD = 1;

    private int isPraise = 0; //是否点赞


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //适配5.0以下 因为矢量图导致的报错
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        ButterKnife.bind(this, view);
        mList.addAll(Data.getGif());
        initView();

        initAdapter();
        initRefreshLayout();

        volunteerId = LocalDate.getInstance(getActivity()).getLocalDate(Constants.VOLUNTEER_ID, "");
        volunteerName = LocalDate.getInstance(getActivity()).getLocalDate(Constants.TRUE_NAME, "");
        return view;
    }

    private void initRefreshLayout() {
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if (HasNextPage){
                    initData(ADD);
                    refreshlayout.finishLoadmore(3000);
                }else {
                    showToast("已经是最后一页");
                    refreshlayout.finishLoadmore();
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                initData(REFRESH);
                refreshlayout.finishRefresh(3000);
            }
        });
        refreshLayout.autoRefresh();
    }

    private void initData(int flag) {
        getData(flag);
    }

    private void initView() {
        boolean isTranslucentStatus = false;
        layDecoration = new DecorationLayout(getActivity());
        //  **************  动态 addView   **************

        iwHelper = ImageWatcherHelper.with(getActivity(), new GlideSimpleLoader()) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setTranslucentStatus(!isTranslucentStatus ? Utils.calcStatusBarHeight(getActivity()) : 0) // 如果不是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
                .setErrorImageRes(R.mipmap.error_picture) // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
//                .setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
//                    @Override
//                    public void onPictureLongPress(ImageView v, Uri uri, int pos) {
//                        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
////                        Toast.makeText(v.getContext().getApplicationContext(), "长按了第" + (pos + 1) + "张图片", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setOnStateChangedListener(new ImageWatcher.OnStateChangedListener() {
//                    @Override
//                    public void onStateChangeUpdate(ImageWatcher imageWatcher, ImageView clicked, int position, Uri uri, float animatedValue, int actionTag) {
//                        Log.e("IW", "onStateChangeUpdate [" + position + "][" + uri + "][" + animatedValue + "][" + actionTag + "]");
//                    }
//
//                    @Override
//                    public void onStateChanged(ImageWatcher imageWatcher, int position, Uri uri, int actionTag) {
////                        if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
////                            Toast.makeText(getApplicationContext(), "点击了图片 [" + position + "]" + uri + "", Toast.LENGTH_SHORT).show();
////                        } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
////                            Toast.makeText(getApplicationContext(), "退出了查看大图", Toast.LENGTH_SHORT).show();
////                        }
//                    }
//                })

                .setLoadingUIProvider(new CustomLoadingUIProvider2()); // 自定义LoadingUI


        layDecoration.attachImageWatcher(iwHelper);
    }

    private void initAdapter() {
        mShowAdapter = new ShowAdapter(R.layout.item_show_layout, mShareListDtos, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(getActivity()).setSpace(14).setSpaceColor(0xFFECECEC));
        recyclerView.setAdapter(mShowAdapter);
        mShowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_like:
                        if (TextUtils.isEmpty(volunteerId)){
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
//                        Boolean like = mShareListDtos.get(position).isLike();
                        Integer praise = mShareListDtos.get(position).getIsPraise();
                        if (praise == 1) {
                            mShareListDtos.get(position).setIsPraise(0);
                            postSharePraiseOperation(mShareListDtos.get(position).getId(),0);
                        } else {
                            mShareListDtos.get(position).setIsPraise(1);
                            postSharePraiseOperation(mShareListDtos.get(position).getId(),1);
                        }
                        mShowAdapter.notifyDataSetChanged();
                        //这里是局部组件数据刷新
//                        ShowAdapter.setIsPraise(mShareListDtos);
//                        mShowAdapter.notifyItemChanged(position,"isPraise");
                        break;
                    case R.id.ll_evaluate:
                        if (TextUtils.isEmpty(volunteerId)){
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            return;
                        }
                        Intent intent = new Intent(getActivity(), CommentActivity.class);
                        intent.putExtra("ShareId",mShareListDtos.get(position).getId());
                        intent.putExtra("VolunteerId",volunteerId);
                        intent.putExtra("VolunteerName",volunteerName);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

    }



    @Override
    public void onThumbPictureClick(ImageView i, SparseArray<ImageView> imageGroupList, List<Uri> urlList) {
        iwHelper.show(i, imageGroupList, urlList);

        fitsSystemWindow(getActivity(), layDecoration);
    }

    private void fitsSystemWindow(Activity activity, View otherView) {
        final View content = activity.findViewById(android.R.id.content);
        boolean adjustByRoot = false;
        if (content instanceof ViewGroup) {
            final View root = ((ViewGroup) content).getChildAt(0);
            if (root != null) {
                boolean fitsSystemWindows = ViewCompat.getFitsSystemWindows(root);
                if (fitsSystemWindows) {
                    otherView.setPadding(root.getPaddingLeft(), root.getPaddingTop(), root.getPaddingRight(), root.getPaddingBottom());
                    adjustByRoot = true;
                }
            }
        }
        if (!adjustByRoot) {
            ViewCompat.requestApplyInsets(otherView);
        }
    }

    //获取list列表数据集合
    private void getData(int flag) {
        if (flag == ADD){
            pageIndex++;
        }
        ShareQueryOptionDto queryOptionDto = new ShareQueryOptionDto();
        queryOptionDto.setPageIndex(pageIndex);
        queryOptionDto.setPageSize(pageSize);
        queryOptionDto.setIsDeleted(0);
        queryOptionDto.setStatu(1);
        queryOptionDto.setVolunteerId(volunteerId);
        AppActionImpl.getInstance(getActivity()).postShareQuery(queryOptionDto, new ActionCallbackListener<PagedListEntityDto<ShareListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ShareListDto> data) {
                if (data != null && data.getRows().size() > 0){
                    HasNextPage = data.getHasNextPage();
                    if (pageIndex == 1){
                        mShareListDtos.clear();
                    }
                    mShareListDtos.addAll(data.getRows());
                    mShowAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("获取数据失败，"+message);
            }
        });
    }

    //新增点赞数据
    private void postSharePraiseOperation(String id,int statu) {
        List<SharepraiseDto> mSharePraiseDtoList = new ArrayList<>();
        SharepraiseDto sharepraiseDto = new SharepraiseDto();
        sharepraiseDto.setShareId(id);
        sharepraiseDto.setVolunteerId(volunteerId);
        sharepraiseDto.setVolunteerName(volunteerName);
        sharepraiseDto.setStatu(statu); //是否点赞状态 0：取消 1：点赞
        mSharePraiseDtoList.add(sharepraiseDto);
        AppActionImpl.getInstance(getActivity()).postSharePraiseOperation(mSharePraiseDtoList, new ActionCallbackListener<String>() {
            @Override
            public void onSuccess(String data) {

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
}
