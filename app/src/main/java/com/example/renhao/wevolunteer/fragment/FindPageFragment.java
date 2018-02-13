package com.example.renhao.wevolunteer.fragment;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.MrL.qrcodescan.MipcaActivityCapture;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.blankj.utilcode.util.DeviceUtils;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.activity.ActivityListDto;
import com.example.model.activity.ActivityQueryOptionDto;
import com.example.model.activity.ActivityViewDto;
import com.example.model.signRecord.SignInOutDto;
import com.example.model.volunteer.VolunteerBaseListDto;
import com.example.model.volunteer.VolunteerBaseQueryOptionDto;
import com.example.renhao.wevolunteer.ProjectDetailActivity;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.example.renhao.wevolunteer.event.QRCodeResultEvent;
import com.example.renhao.wevolunteer.utils.Util;
import com.jungly.gridpasswordview.GridPasswordView;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 发现地图界面
 * A simple {@link Fragment} subclass.
 * @author 75095
 */
public class FindPageFragment extends BaseFragment implements LocationSource,
        AMapLocationListener, AMap.OnMapLoadedListener, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, View.OnClickListener {
    private static final String TAG = "FindPageFragment";

    public static final int MAP_ALL = -1;
    public static final int MAP_JOB = 1;
    public static final int MAP_ACTIVITY = 0;
    public static final int MAP_CENTER = 3;
    public static final int MAP_STATION = 4;

    //6.0申请存储权限
    private static final int LOCATION_REQUEST_CODE = 1;
    private static final int TAKE_PHOTO_REQUEST_CODE = 2;

    //签到签退的两种方式：二维码：1，签到码：2
    private int SIGN_IN_QR = 1;
    private int SIGN_IN_NUM = 2;
    private int SIGN_OUT = 0;

    private int showMap = MAP_ALL;

    public int getShowMap() {
        return showMap;
    }

    public void setShowMap(int showMap) {
        this.showMap = showMap;
        addMarkersToMap();
    }

    private boolean isShow = false;

    private AMap aMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;//声明定位回调监听器
    private AMapLocationClient mlocationClient;//声明AMapLocationClient类对象
    private AMapLocationClientOption mLocationOption;
    private UiSettings mUiSettings;//设置地图控件对象

    private LatLng localLatLng;

    private List<ActivityListDto> activityDatas = new ArrayList<>();
    private List<ActivityListDto> jobDatas = new ArrayList<>();
    private List<VolunteerBaseListDto> guidanceCenterDatas = new ArrayList<>();
    private List<VolunteerBaseListDto> serviceStationDatas = new ArrayList<>();

    private Boolean flag = false;//地图签到按钮的判断
    private boolean tag = false;

    Button sign_in;//签到
    Button sign_out;//签退
    Button sign_in_num;//签到码按钮
    LinearLayout linearLayout;//签到计时的布局
    Chronometer chronometer;//计时器
    private TextView tvWarn;
    private LinearLayout llWarm; //注意提示
    private String replace; //动态显示活动结束时间的提示框


    private SimpleDateFormat finishDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FindPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册EventBus
        EventBus.getDefault().register(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View localView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_find_page, null);

        //获取地图控件引用
        this.mapView = (MapView) localView.findViewById(R.id.map);
        this.sign_in = (Button) localView.findViewById(R.id.btn_sign_in);
        this.sign_out = (Button) localView.findViewById(R.id.btn_sign_out);
        this.sign_in_num = (Button) localView.findViewById(R.id.btn_sign_in_num);
        this.linearLayout = (LinearLayout) localView.findViewById(R.id.time_layout);
        this.chronometer = (Chronometer) localView.findViewById(R.id.chronometer);
        this.tvWarn = (TextView) localView.findViewById(R.id.tv_warn);
        this.llWarm = (LinearLayout) localView.findViewById(R.id.ll_warn);
        tvWarn.setHorizontallyScrolling(true);
        tvWarn.setSelected(true);
        chronometer.setFormat("%s");
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        this.mapView.onCreate(savedInstanceState);// 此方法必须重写
        setType(tag);
        init();
        setmListener();

        sign_in.setClickable(false);

        compulsoryChronometer();
        return localView;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }

    //type类型：true:签到界面  false:发现界面
    public void setType(boolean type) {
        flag = LocalDate.getInstance(getActivity()).getLocalDate("flag", false);
        if (type) {
            if (!flag) {
                sign_in.setVisibility(View.VISIBLE);
                sign_in_num.setVisibility(View.VISIBLE);
                llWarm.setVisibility(View.GONE);
            } else {
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                llWarm.setVisibility(View.VISIBLE);
                startChronometer();
            }
            tag = true;
        } else {
            sign_in.setVisibility(View.GONE);
            sign_in_num.setVisibility(View.GONE);
            sign_out.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            llWarm.setVisibility(View.GONE);
            tag = false;
        }
    }

    //接受消息的函数
    @Subscribe
    public void onEventMainThread(final QRCodeResultEvent event) {

        final String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (TextUtils.isEmpty(volunteerId) || !isLogin) {
            showToast("请先登录");
            return;
        }
        //判断距离是否符合条件
        AppActionImpl.getInstance(getActivity()).activityDetail(event.getQrcodeMsg(),
                new ActionCallbackListener<ActivityViewDto>() {
                    @Override
                    public void onSuccess(ActivityViewDto data) {
                        if (data == null) {
                            showToast("该活动或岗位不存在");
                            return;
                        }
                        //0 actionBar上的扫一扫 1 签到 2签退
                        Logger.v(TAG, event.getQrcodeMsg());
                        switch (event.getState()) {
                            case 0:
                                boolean f = LocalDate.getInstance(getActivity()).getLocalDate("flag", false);
                                if (f) {
                                    signOut(event.getQrcodeMsg(), volunteerId, SIGN_IN_QR);
                                } else {
                                    signIn(event.getQrcodeMsg(), volunteerId, SIGN_IN_QR);
                                    LocalDate.getInstance(getActivity()).setLocalDate("activityId", event.getQrcodeMsg());
                                }
                                break;
                            case 1:
                                signIn(event.getQrcodeMsg(), volunteerId, SIGN_IN_QR);
                                LocalDate.getInstance(getActivity()).setLocalDate("activityId", event.getQrcodeMsg());
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        System.out.println(event.getQrcodeMsg());
                            showToast("请扫描正确的We志愿活动/岗位的二维码内容");
                    }
                });
    }

    private void setmListener() {
        sign_in.setOnClickListener(this);
        sign_out.setOnClickListener(this);
        sign_in_num.setOnClickListener(this);
        llWarm.setOnClickListener(this);
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        Logger.d(TAG, "init");
        if (aMap == null) {
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();
            mUiSettings.setZoomControlsEnabled(false);//去除地图缩放按钮控件
        }
    }

    /**
     * 设置一些amap的属性
     * aMap.setMyLocationType()设置定位的类型：
     * 1.定位模式：AMap.LOCATION_TYPE_LOCATE
     * 2.跟随模式：AMap.LOCATION_TYPE_MAP_FOLLOW
     * 3.旋转模式：AMap.LOCATION_TYPE_MAP_ROTATE
     */
    private void setUpMap() {
        Logger.d(TAG, "setUpMap");
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
        if (!tag) {
            addMarkersToMap();// 往地图上添加marker
        }
    }

    /**
     * position(Required) 在地图上标记位置的经纬度值。参数不能为空。
     * title 当用户点击标记，在信息窗口上显示的字符串。
     * snippet 附加文本，显示在标题下方。
     * draggable 如果您允许用户可以自由移动标记，设置为“ true ”。默认情况下为“ false ”。
     *
     * 根据右上角的菜单选择添加显示不同的覆盖物
     */
    private void addMarkersToMap() {
        aMap.clear();
        switch (showMap) {
            case MAP_ALL:
                getActivityData();//50
                getJobActivityData();//50
                getGuidanceCenterData(1);//all
                getServiceStationData(1);//all
                break;
            case MAP_JOB:
                getJobActivityData();//50
                break;
            case MAP_ACTIVITY:
                getActivityData();//50
                break;
            case MAP_CENTER:
                getGuidanceCenterData(1);//all
                break;
            case MAP_STATION:
                getServiceStationData(1);//all
                break;
            default:
                break;
        }
    }


    /**
     * 获取活动的数据
     */
    private void getActivityData() {
        ActivityQueryOptionDto query = new ActivityQueryOptionDto();
        query.setPageIndex(1);
        query.setPageSize(50);
        query.setType(0);
        AppActionImpl.getInstance(getActivity()).activityQuery(query, new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                if (data.getRows() == null || data.getRows().size() == 0) {
                    return;
                }
                activityDatas = data.getRows();
                for (int i = 0; i < activityDatas.size(); i++) {
                    ActivityListDto dto = activityDatas.get(i);
                    Double lng = dto.getLng().doubleValue();
                    Double lat = dto.getLat().doubleValue();
                    String addr = dto.getAddr();
                    String activityName = dto.getActivityName();
                    String operationState = dto.getOperationState();
                    String contactName = TextUtils.isEmpty(dto.getLinker()) ? "" : dto.getLinker();
                    String contactStr = "";
                    contactStr += TextUtils.isEmpty(dto.getTel()) ? "" : dto.getTel() + "\n" + "　　　　　";
                    contactStr += TextUtils.isEmpty(dto.getMobile()) ? "" : dto.getMobile();
                    String contacts_phone = contactStr;
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow) {
                        break;
                    }
                    if (!TextUtils.isEmpty(addr) && !TextUtils.isEmpty(activityName) && !operationState.equals("已结束")) {
                        if (addr.length() < 17 || activityName.length() < 20) {
                            Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(activityName, 1, 19))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_activity))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + "联系人：" + contactName + "\n" + "联系电话：" + contacts_phone)
                                    .draggable(true));
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", MAP_ACTIVITY);
                            bundle.putString("id", dto.getId());
                            marker.setObject(bundle);
                        } else {
                            Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(activityName, 1, 19) + "\n" + subString(activityName, 20, 35))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_activity))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + subString(addr, 17, 37) + "\n" + "联系人：" + contactName + "\n" + "联系电话：" + contacts_phone)
                                    .draggable(true));
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", MAP_ACTIVITY);
                            bundle.putString("id", dto.getId());
                            marker.setObject(bundle);

                        }
                    }

                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("获取地图信息失败");
            }
        });
    }

    /**
     * 获取岗位的数据
     */
    private void getJobActivityData() {
        ActivityQueryOptionDto query = new ActivityQueryOptionDto();
        query.setPageIndex(1);
        query.setPageSize(50);
        query.setType(1);
        AppActionImpl.getInstance(getActivity()).activityQuery(query, new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                if (data.getRows() == null || data.getRows().size() == 0) {
                    return;
                }
                jobDatas = data.getRows();
                for (int i = 0; i < jobDatas.size(); i++) {
                    ActivityListDto dto = jobDatas.get(i);
                    Double lng = dto.getLng().doubleValue();
                    Double lat = dto.getLat().doubleValue();
                    String addr = dto.getAddr();
                    String jobActivityName = dto.getActivityName();
                    String operationState = dto.getOperationState();
                    String contactName = TextUtils.isEmpty(dto.getLinker()) ? "" : dto.getLinker();
                    String contactStr = "";
                    contactStr += TextUtils.isEmpty(dto.getTel()) ? "" : dto.getTel() + "\n" + "　　　　　";
                    contactStr += TextUtils.isEmpty(dto.getMobile()) ? "" : dto.getMobile();
                    String contacts_phone = contactStr;

                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow) {
                        break;
                    }
                    if (!TextUtils.isEmpty(addr) && !TextUtils.isEmpty(jobActivityName) && !operationState.equals("已结束")) {
                        if (addr.length() < 17 || jobActivityName.length() < 20) {
                            Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(jobActivityName, 1, 19))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_job_activity))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + "联系人：" + contactName + "\n" + "联系电话：" + contacts_phone)
                                    .draggable(true));
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", MAP_JOB);
                            bundle.putString("id", dto.getId());
                            marker.setObject(bundle);
                        } else {
                            Marker marker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(jobActivityName, 1, 19) + "\n" + subString(jobActivityName, 20, 35))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_job_activity))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + subString(addr, 17, 37) + "\n" + "联系人：" + contactName + "\n" + "联系电话：" + contacts_phone)
                                    .draggable(true));
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", MAP_JOB);
                            bundle.putString("id", dto.getId());
                            marker.setObject(bundle);

                        }
                    }

                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    /**
     * 获取志愿者指导中心数据
     */
    private void getGuidanceCenterData(int page) {
        VolunteerBaseQueryOptionDto query = new VolunteerBaseQueryOptionDto();
        query.setTypeId(2);
        query.setPageIndex(page);
        AppActionImpl.getInstance(getActivity()).volunteerBaseQuery(query, new ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<VolunteerBaseListDto> data) {

                if (data.getRows() == null || data.getRows().size() == 0) {
                    return;
                }

                guidanceCenterDatas = data.getRows();
                for (int i = 0; i < guidanceCenterDatas.size(); i++) {
                    VolunteerBaseListDto dto = guidanceCenterDatas.get(i);
                    Double lng = Double.parseDouble(dto.getLng());
                    Double lat = Double.parseDouble(dto.getLat());
                    String addr = dto.getAddress();
                    String title = dto.getTitle();
                    String contacts = dto.getContacts();
                    String phone = dto.getPhone();
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow) {
                        break;
                    }
                    if (!TextUtils.isEmpty(addr) && !TextUtils.isEmpty(title)) {
                        if (addr.length() < 17 || title.length() < 20) {
                            aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(title, 1, 19))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_guidance_center))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + "联系人：" + contacts + "\n" + "联系电话：" + phone)
                                    .draggable(true));
                        } else {
                            aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(title, 1, 19) + "\n" + subString(title, 20, 35))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_guidance_center))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + subString(addr, 17, 37) + "\n" + "联系人：" + contacts + "\n" + "联系电话：" + phone)
                                    .draggable(true));
                        }
                    }

                }

                if (!isShow) {
                    return;
                }

                if (data.getHasNextPage()) {
                    getGuidanceCenterData(data.getPageIndex() + 1);
                }

            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });

    }

    /**
     * 获取志愿者服务站数据
     */
    private void getServiceStationData(int page) {
        VolunteerBaseQueryOptionDto query = new VolunteerBaseQueryOptionDto();
        query.setTypeId(1);
        query.setPageIndex(page);
        AppActionImpl.getInstance(getActivity()).volunteerBaseQuery(query, new ActionCallbackListener<PagedListEntityDto<VolunteerBaseListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<VolunteerBaseListDto> data) {

                if (data.getRows() == null || data.getRows().size() == 0) {
                    return;
                }

                serviceStationDatas = data.getRows();
                for (int i = 0; i < serviceStationDatas.size(); i++) {
                    VolunteerBaseListDto dto = serviceStationDatas.get(i);
                    Double lng = Double.parseDouble(dto.getLng());
                    Double lat = Double.parseDouble(dto.getLat());
                    String addr = dto.getAddress();
                    String title = dto.getTitle();
                    String contacts = dto.getContacts();
                    String phone = dto.getPhone();
                    LatLng latlng = new LatLng(lat, lng);
                    if (aMap == null || !isShow) {
                        break;
                    }

                    if (!TextUtils.isEmpty(addr) && !TextUtils.isEmpty(title)) {
                        if (addr.length() < 17 || title.length() < 20) {
                            aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(title, 1, 19))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_service_station))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + "联系人：" + contacts + "\n" + "联系电话：" + phone)
                                    .draggable(true));
                        } else {
                            aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                                    .position(latlng).title(subString(title, 1, 19) + "\n" + subString(title, 20, 35))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_service_station))
                                    .snippet("地点:" + subString(addr, 1, 16) + "\n" + subString(addr, 17, 37) + "\n" + "联系人：" + contacts + "\n" + "联系电话：" + phone)
                                    .draggable(true));
                        }
                    }
                }

                if (!isShow) {
                    return;
                }

                if (data.getHasNextPage()) {
                    getServiceStationData(data.getPageIndex() + 1);
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        isShow = true;
        super.onResume();
        //检查权限后决定是否开启定位(PERMISSION_GRANTED:允许授予权限)
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                if (this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showMessageOKCancel("您需要在应用权限设置中打开对本应用定位的授权才能正常使用该功能",
                            new DialogInterface.OnClickListener() {
                                @TargetApi(Build.VERSION_CODES.M)
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                            LOCATION_REQUEST_CODE);
                                }
                            });
                } else {
                    this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            LOCATION_REQUEST_CODE);
                }
            } else {
                this.mapView.onResume();
                if (mlocationClient != null) {
                    mlocationClient.startLocation();
                }
                if (aMap != null) {
                    setUpMap();
                }
            }
        } else {
            this.mapView.onResume();
            if (mlocationClient != null) {
                mlocationClient.startLocation();
            }
            if (aMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     * 保存数据
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        isShow = false;
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        this.mapView.onDestroy();
        aMap = null;
        EventBus.getDefault().unregister(this);//反注册EventBus
    }


    /**
     * 定位成功后回调函数
     * CameraUpdateFactory.zoomTo(float) 改变缩放级别为一个定值，同时保持其他相同的属性。
     * 这些方法返回一个 CameraUpdate 对象，使用 AMap.moveCamera(CameraUpdate update) 方法更新可视区域显示在地图上。
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
       /* Logger.d(TAG, "onLocationChanged");*/
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null
                    && aMapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
                this.mlocationClient.stopLocation();//停止定位
                // 获取当前地图中心点的坐标
                localLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                //视图中心移动至当前定位的地点
                this.aMap.moveCamera(CameraUpdateFactory.changeLatLng(localLatLng));
                //地图缩放级别为4-20级，缩放级别不必是一个整数。
                //缩放级别较低时，您可以看到更多地区的地图；缩放级别高时，您可以查看地区更加详细的地图。
                this.aMap.moveCamera(CameraUpdateFactory.zoomTo(18.0F));// 改变缩放级别为一个定值，同时保持其他相同的属性。
                sign_in.setClickable(true);
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                showToast("请在设置中赋予应用定位权限");
                this.mlocationClient.stopLocation();//停止定位
                Log.e("AmapErr", errText);
            }
        }
    }


    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getActivity().getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle bundle = (Bundle) marker.getObject();
        int type = bundle.getInt("type");
        switch (type) {
            case MAP_JOB:
                Intent jobIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                jobIntent.putExtra("type", MAP_JOB);
                jobIntent.putExtra("id", bundle.getString("id"));
                startActivity(jobIntent);
                break;
            case MAP_ACTIVITY:
                Intent activityIntent = new Intent(getActivity(), ProjectDetailActivity.class);
                activityIntent.putExtra("type", MAP_ACTIVITY);
                activityIntent.putExtra("id", bundle.getString("id"));
                startActivity(activityIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onClick(View v) {

        final String volunteerId = LocalDate.getInstance(getActivity()).getLocalDate("volunteerId", "");
        boolean isLogin = LocalDate.getInstance(getActivity()).getLocalDate("isLogin", false);
        if (TextUtils.isEmpty(volunteerId) || !isLogin) {
            showToast("请先登录");
            return;
        }

        //打开摄像头扫描二维码,扫描到activitytimeId的值返回到activityresult
        Intent intent = new Intent(getActivity(), MipcaActivityCapture.class);
        switch (v.getId()) {
            case R.id.btn_sign_in:
                LocalDate.getInstance(getActivity()).setLocalDate("SIGN_OUT", "1");
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                            //需要一个解释说明权限问题
                            showMessageOKCancel("您需要在应用权限设置中打开对本应用使用摄像头的授权才能正常使用该功能",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                        } else {
                            //无需解释，我们可以请求权限。
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                                    TAKE_PHOTO_REQUEST_CODE);
                        }
                    } else {
                        startActivityForResult(intent, 1);
                    }
                } else {
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.btn_sign_out:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确定签退么？");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sign_out = LocalDate.getInstance(getActivity()).getLocalDate("SIGN_OUT","");
                        System.out.println("sing_out=========> "+sign_out);
                        if (sign_out.equals("2") || sign_out.equals("")){
                            String activityNumCode = LocalDate.getInstance(getActivity()).getLocalDate("activityNumCode", "");
                            signOut(activityNumCode, volunteerId, SIGN_IN_NUM);
                        }else if (sign_out.equals("1")){
                            String activityId = LocalDate.getInstance(getActivity()).getLocalDate("activityId", "");
                            signOut(activityId, volunteerId,SIGN_IN_QR);
                        }
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_sign_in_num:
                showNumberDialog(volunteerId);
                SIGN_OUT = SIGN_IN_NUM;//作为判断 用户点击的是签到码
                LocalDate.getInstance(getActivity()).setLocalDate("SIGN_OUT", String.valueOf(SIGN_OUT));
                break;


            case R.id.ll_warn:
                showMessageOKCancel(replace, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                break;
            default:
                break;
        }
    }


    /**
     * 当用户忘记签退后，系统给它强制停止计时器
     */
    public void compulsoryChronometer(){
        String nowDate = LocalDate.getInstance(getActivity()).getLocalDate("nowDate","");
        String finishDate = finishDateFormat.format(new Date());
        boolean f = flag = LocalDate.getInstance(getActivity()).getLocalDate("flag", false);;
        System.out.println("当前签到时候纪录的年月日："+nowDate);
        System.out.println("当前系统的年月日："+finishDate);
        System.out.println(f);
        //首先判断是否点击了签到按钮
        if(f){
            //判断当前日期和用户签到时候的日期是否同一天
            if (!finishDate.equals(nowDate)){  //不同一天
                LocalDate.getInstance(getActivity()).setLocalDate("activityId", "");
                LocalDate.getInstance(getActivity()).setLocalDate("activityNumCode", "");
                LocalDate.getInstance(getActivity()).setLocalDate("SIGN_OUT","0");
                LocalDate.getInstance(getActivity()).setLocalDate("nowDate","");
                SIGN_OUT = 0;
                flag = saveFlag(false);
                sign_in.setVisibility(View.VISIBLE);
                sign_in_num.setVisibility(View.VISIBLE);
                sign_out.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                llWarm.setVisibility(View.GONE);
                stopChronometer();
            }
        }

    }



    /**
     * 设置签到码输入框
     * @param volunteerId
     */
    private void showNumberDialog(final String volunteerId) {
        final AlertDialog mDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = View.inflate(getActivity(),R.layout.number_sign_in,null);
        mDialog = builder.create();
        mDialog.setView(view);
        mDialog.show();
        GridPasswordView mView = (GridPasswordView) view.findViewById(R.id.pswView);
        mView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if (psw.length() == 6){
                    signIn(psw,volunteerId,SIGN_IN_NUM);
                    mDialog.dismiss();
                    Log.e(TAG, "onTextChanged: "+psw);

                }

            }

            @Override
            public void onInputFinish(String psw) {

            }
        });
    }


    private void signIn(final String qrcode, String volunteerId, final int signMethod) {
        //直接发送二维码的内容
        SignInOutDto create = new SignInOutDto();
        if (LocalDate.getInstance(getActivity()).getLocalDate("lat", null) != null &&
                LocalDate.getInstance(getActivity()).getLocalDate("lng", null) != null) {
            double myLat = Double.parseDouble(LocalDate.getInstance(getActivity()).getLocalDate("lat", ""));
            double myLng = Double.parseDouble(LocalDate.getInstance(getActivity()).getLocalDate("lng", ""));
            create.setLat(myLat);
            create.setNg(myLng);
            LocalDate.getInstance(getActivity()).setLocalDate("lat", null);
            LocalDate.getInstance(getActivity()).setLocalDate("lng", null);
        } else {
            Location location = aMap.getMyLocation();
            create.setLat(location.getLatitude());
            create.setNg(location.getLongitude());
        }
        if (signMethod == 1){
            create.setActivityId(qrcode);
        }else if (signMethod == 2){
            create.setSignCode(qrcode);
            LocalDate.getInstance(getActivity()).setLocalDate("activityNumCode", qrcode);
        }
        create.setVolunteerId(volunteerId);
        create.setSignMethod(signMethod);
        create.setSigntime(Util.getNowDate());
        create.setDeviceId(DeviceUtils.getMacAddress());
        create.setSourceType(0);
        create.setSignType(0);
        create.setId(UUID.randomUUID().toString());
        List<SignInOutDto> creates = new ArrayList<>();
        creates.add(create);
        AppActionImpl.getInstance(getActivity()).signRecordCreateBoth(creates, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data == null) {
                    showToast("签到失败");
                    return;
                }
                if (data.size() < 1) {
                    showToast("签到失败");
                    return;
                }
                //保存当前用户签到后当天的日期
                LocalDate.getInstance(getActivity()).setLocalDate("nowDate",finishDateFormat.format(new Date()));
                flag = saveFlag(true);
                sign_in.setVisibility(View.GONE);
                sign_in_num.setVisibility(View.GONE);
                sign_out.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                llWarm.setVisibility(View.VISIBLE);
                startChronometer();
                showToast("签到成功");

                getActivityDetails(qrcode,signMethod);

            }

            @Override
            public void onFailure(String errorEvent, String message) {
                System.out.println(message);
                showToast("签到失败" + message);
            }
        });
    }

    private void signOut(String qrcode, String volunteerId,int signMethod) {
        //直接发送二维码的内容
        SignInOutDto create = new SignInOutDto();
        if (LocalDate.getInstance(getActivity()).getLocalDate("lat", null) != null &&
                LocalDate.getInstance(getActivity()).getLocalDate("lng", null) != null) {
            double myLat = Double.parseDouble(LocalDate.getInstance(getActivity()).getLocalDate("lat", ""));
            double myLng = Double.parseDouble(LocalDate.getInstance(getActivity()).getLocalDate("lng", ""));
            create.setLat(myLat);
            create.setNg(myLng);
            LocalDate.getInstance(getActivity()).setLocalDate("lat", null);
            LocalDate.getInstance(getActivity()).setLocalDate("lng", null);
        } else {
            Location location = aMap.getMyLocation();
            create.setLat(location.getLatitude());
            create.setNg(location.getLongitude());
        }
        if (signMethod == 1){
            create.setActivityId(qrcode);
        }else if (signMethod == 2){
            create.setSignCode(qrcode);
        }

        create.setVolunteerId(volunteerId);
        create.setSignMethod(signMethod);
        //create.setActivityTimeId();
        create.setSigntime(Util.getNowDate());
        create.setDeviceId(DeviceUtils.getMacAddress());
        create.setSourceType(0);
        create.setSignType(1);
        create.setId(UUID.randomUUID().toString());
        List<SignInOutDto> creates = new ArrayList<>();
        creates.add(create);
        AppActionImpl.getInstance(getActivity()).signRecordCreateBoth(creates,
                new ActionCallbackListener<List<String>>() {
                    @Override
                    public void onSuccess(List<String> data) {
                        if (data == null) {
                            showToast("签退失败");
                            return;
                        }
                        if (data.size() < 1) {
                            showToast("签退失败");
                            return;
                        }
                        LocalDate.getInstance(getActivity()).setLocalDate("activityId", "");
                        LocalDate.getInstance(getActivity()).setLocalDate("activityNumCode", "");
                        LocalDate.getInstance(getActivity()).setLocalDate("SIGN_OUT","0");
                        LocalDate.getInstance(getActivity()).setLocalDate("nowDate","");
                        SIGN_OUT = 0;
                        flag = saveFlag(false);
                        sign_in.setVisibility(View.VISIBLE);
                        sign_in_num.setVisibility(View.VISIBLE);
                        sign_out.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.GONE);
                        llWarm.setVisibility(View.GONE);
                        stopChronometer();
                        showToast("签退成功");
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("签退失败" + message);
                    }
                });
    }


    //提示对话框
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(message)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    /**
     * 开始计时
     */
    private void startChronometer() {
        if (chronometer == null) {
            return;
        }
        String lastTime = LocalDate.getInstance(getActivity()).getLocalDate("lastTime", "");
        long time = 0;
        Date now = new Date();
        if (TextUtils.isEmpty(lastTime)) {
            LocalDate.getInstance(getActivity()).setLocalDate("lastTime", now.getTime() + "");
        } else {
            time = now.getTime() - Long.parseLong(lastTime);
        }
        chronometer.setBase(SystemClock.elapsedRealtime() - time);
        chronometer.start();
    }

    /**
     * 结束计时
     */
    private void stopChronometer() {
        if (chronometer == null) {
            return;
        }
        chronometer.stop();
        LocalDate.getInstance(getActivity()).setLocalDate("lastTime", "");

    }


    private Boolean saveFlag(Boolean flag) {
        LocalDate.getInstance(getActivity()).setLocalDate("flag", flag);
        return flag;
    }

    //二维码的activity返回的值
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String qrcodeMsg = data.getExtras().getString("result");
        if (qrcodeMsg == null) {
            return;
        }
        if (qrcodeMsg.equals("0")) {
            return;
        }
        //将获取到的二维码的值传到FindPageFragment
        EventBus.getDefault().post(new QRCodeResultEvent(qrcodeMsg, requestCode));

    }

    private String dayETime; //结束时间
    private void getActivityDetails(String qrcode, int signMethod){
        ActivityQueryOptionDto query = new ActivityQueryOptionDto();
        if (signMethod == 1){
            activityIdQuery(qrcode);
        }else if (signMethod == 2){
            query.setSignCode(qrcode);
            activitySignCodeQuery(query);
        }

    }

    /**
     * 利用签到码来获取活动详情信息
     * @param query
     */
    private void activitySignCodeQuery(ActivityQueryOptionDto query){
        AppActionImpl.getInstance(getActivity()).activityQuery(query, new ActionCallbackListener<PagedListEntityDto<ActivityListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<ActivityListDto> data) {
                if (data.getRows() == null || data.getRows().size() == 0) {
                    return;
                }
                dayETime = data.getRows().get(0).getDayETime();
                replace = getString(R.string.tv_warn).replace("活动/岗位结束", dayETime);
                tvWarn.setText(replace);
                showMessageOKCancel(replace, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    /**
     * 利用活动Id来获取详细信息
     * @param qrcode
     */
    private void activityIdQuery(String qrcode){
        AppActionImpl.getInstance(getActivity()).activityDetail(qrcode, new ActionCallbackListener<ActivityViewDto>() {
            @Override
            public void onSuccess(ActivityViewDto data) {
                if (data != null) {
                    dayETime = data.getDayETime();
                    replace = getString(R.string.tv_warn).replace("活动/岗位结束", dayETime);
                    tvWarn.setText(replace);
                    showMessageOKCancel(replace, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }

            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

}
