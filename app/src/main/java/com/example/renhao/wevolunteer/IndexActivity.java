package com.example.renhao.wevolunteer;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.MrL.qrcodescan.MipcaActivityCapture;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.example.core.local.LocalDate;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.common.Constants;
import com.example.renhao.wevolunteer.event.FragmentResultEvent;
import com.example.renhao.wevolunteer.event.QRCodeResultEvent;
import com.example.renhao.wevolunteer.fragment.FindPageFragment;
import com.example.renhao.wevolunteer.fragment.HomePageFragment;
import com.example.renhao.wevolunteer.fragment.NewFindPageFragment;
import com.example.renhao.wevolunteer.fragment.PersonalFragment;
import com.example.renhao.wevolunteer.fragment.ShowPageFragment;
import com.example.renhao.wevolunteer.fragment.SigninPageFragment;
import com.example.renhao.wevolunteer.utils.DeviceUtils;
import com.example.renhao.wevolunteer.utils.RandomUntil;
import com.example.renhao.wevolunteer.view.ChangeColorIconWithTextView;
import com.example.renhao.wevolunteer.view.PopupMenu;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/5 11:19
 * 修改备注：
 */
public class IndexActivity extends BaseActivity implements AMapLocationListener {
    private static final String TAG = "IndexActivity";

    //6.0申请存储权限
    private static final int LOCATION_REQUEST_CODE = 1;
    private static final int TAKE_PHOTO_REQUEST_CODE = 2;
    private static final int SDCARD_REQUEST_CODE = 3;

    public static final int HOME = 0;
    public static final int FIND = 1;
    public static final int SIGNIN = 2;
    public static final int MYSELF = 3;

    @Bind(R.id.changrTv_index_homepage)
    ChangeColorIconWithTextView mChangrTvIndexHomepage;
    @Bind(R.id.changrTv_index_find)
    ChangeColorIconWithTextView mChangrTvIndexFind;
    @Bind(R.id.changrTv_index_signin)
    ChangeColorIconWithTextView mChangrTvIndexSignin;
    @Bind(R.id.changrTv_index_myself)
    ChangeColorIconWithTextView mChangrTvIndexMyself;
    @Bind(R.id.linaerlayout_index_bottombar)
    LinearLayout mLinaerlayoutIndexBottombar;
    @Bind(R.id.framelayout_index_content)
    FrameLayout mFramelayoutIndexContent;

    private View mCustomView;//actionbar的自定义视图
    private HomePageFragment mHomePageFragment;
    private FindPageFragment mFindPageFragment;
    private SigninPageFragment mSigninPageFragment;
    private NewFindPageFragment mNewFindPageFragment;
    private ShowPageFragment mShowPageFragment;
    private PersonalFragment mPersonalFragment;

    private PopupMenu mPopupMenu;
    private ImageView magnifier;

    private int fragmentPosition = -1;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    private double lat;
    private double lng;

    private PowerManager.WakeLock wakeLock = null;


    private SimpleDateFormat finishDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        isLoginOrAccesssToken();
//        isUpdate();
        //初始化shareSDK
        ShareSDK.initSDK(this);
        //在使用百度或高德地图时，防止切换Fragment出现闪屏黑屏情况。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initActionBar();
        setFragment(HOME);

        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);

        //用来判断 强制签退后的标记
        isSignOutFlag();

        savePhoneMac();
    }

    /**
     * 保存手机唯一标识MAC
     */
    private void savePhoneMac() {
        String isSaveMac = LocalDate.getInstance(this).getLocalDate(Constants.MAC,"");
        if (isSaveMac == null || isSaveMac.equals("")){ //如果本地的mac地址存储数据为空，则进行获取存储
            String mobileunique = DeviceUtils.getMacAddress();
            Logger.e(mobileunique);
            //如果获取不到手机mac标识，则随机生成一个16位的标识
            if (mobileunique == null || mobileunique.equals("") || mobileunique.isEmpty()){
                mobileunique = RandomUntil.getNumSmallLetter(16);
            }
            LocalDate.getInstance(this).setLocalDate(Constants.MAC,mobileunique);
        }

    }

    //获取电源锁，保持该服务在屏幕熄灭时仍然获取CPU时，保持运行
    @SuppressLint("InvalidWakeLockTag")
    private void acquireWakeLock()
    {
        if (null == wakeLock)
        {
            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, "PostLocationService");
            if (null != wakeLock)
            {
                wakeLock.acquire();
            }
        }
    }

    //释放设备电源锁
    private void releaseWakeLock()
    {
        if (null != wakeLock)
        {
            wakeLock.release();
            wakeLock = null;
        }
    }

    //强制签退后的标记
    private void isSignOutFlag(){

        String nowDate = LocalDate.getInstance(this).getLocalDate("nowDate","");
        String finishDate = finishDateFormat.format(new Date());
        boolean f  = LocalDate.getInstance(this).getLocalDate("flag", false);
        System.out.println("当前签到时候纪录的年月日："+nowDate);
        System.out.println("当前系统的年月日："+finishDate);
        System.out.println(f);
        //首先判断是否点击了签到按钮
        if(f){
            //判断当前日期和用户签到时候的日期是否同一天
            if (!finishDate.equals(nowDate)){  //不同一天
                //如果signOutFlag为true 则为强制签退过
                LocalDate.getInstance(this).setLocalDate("signOutFlag",true);
            } else {
                //如果signOutFlag为false 则为没有强制签退过
                LocalDate.getInstance(this).setLocalDate("signOutFlag",false);
            }
        }
    }




    private void isLoginOrAccesssToken() {
        //判断用户是否登录
        boolean isLogin = LocalDate.getInstance(this).getLocalDate("isLogin", false);
        if (isLogin) {
            Logger.v(TAG, "user is login");
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            String strExpires = LocalDate.getInstance(this).getLocalDate("expires", "");
            Logger.v(TAG, strExpires);
            if (!strExpires.equals("")) {
                try {
                    Date expires = format.parse(strExpires);
                    //判断票据是否过期
                    if (System.currentTimeMillis() >= expires.getTime()) {
                        Logger.v(TAG, "accesstoken is overdue");
                        getAccessToken();
                    } else {
                        //用户已登录，票据尚未过期--刷新票据
                        Logger.v(TAG, "accesstoken is not overdue");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                //第一次登录
                Logger.v(TAG, "第一次登录  " + strExpires);
                getAccessToken();
            }
        } else {
            getAccessToken();
        }
    }


    /**
     * 初始化actionbar 并绑定监听
     */
    private void initActionBar() {
        String[] tabs = {"全部", "岗位", "活动", "志愿者指导中心", "志愿者服务站"};
        mPopupMenu = new PopupMenu(this, tabs);
//        mPopupMenu.setWidth(360);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        mCustomView = mInflater.inflate(R.layout.actionbar_homepage, null);

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0, 0);

        LinearLayout scan = (LinearLayout) mCustomView.findViewById(R.id.linearlayout_homepage_scan);
        magnifier = (ImageView) mCustomView.findViewById(R.id.imageview_homepage_magnifier);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单次定位
                locationOption.setOnceLocation(true);
                //设置定位参数
                locationClient.setLocationOption(locationOption);
                // 启动定位
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(IndexActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(IndexActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel("您需要在应用权限设置中对本应用定位进行授权才能正常使用该功能",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions(IndexActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                    LOCATION_REQUEST_CODE);
                                        }
                                    });
                            return;
                        }
                        ActivityCompat.requestPermissions(IndexActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                LOCATION_REQUEST_CODE);
                        return;
                    }
                    locationClient.startLocation();
                } else {
                    locationClient.startLocation();
                }
            }
        });
        magnifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 二期的代码
//                Intent intent = new Intent(IndexActivity.this, SearchActivity.class);
//                startActivity(intent);

                //一期的代码
                if (fragmentPosition == FIND) {
                    mPopupMenu.showLocation(R.id.imageview_homepage_magnifier);
                    mPopupMenu.setOnItemClickListener(new PopupMenu.OnItemClickListener() {
                        @Override
                        public void onClick(PopupMenu.MENUITEM item, String str) {
                            switch (str) {
                                case "全部":
                                    if (mFindPageFragment != null) {
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_ALL);
                                    }
                                    break;
                                case "岗位":
                                    if (mFindPageFragment != null) {
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_JOB);
                                    }
                                    break;
                                case "活动":
                                    if (mFindPageFragment != null) {
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_ACTIVITY);
                                    }
                                    break;
                                case "志愿者指导中心":
                                    if (mFindPageFragment != null) {
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_CENTER);
                                    }
                                    break;
                                case "志愿者服务站":
                                    if (mFindPageFragment != null) {
                                        mFindPageFragment.setShowMap(FindPageFragment.MAP_STATION);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                } else {
                    Intent intent = new Intent(IndexActivity.this, SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //授权回调中检查是否授权后执行相应操作
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (ContextCompat.checkSelfPermission(IndexActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                locationClient.startLocation();
            }
        }
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (ContextCompat.checkSelfPermission(IndexActivity.this,
                    Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                setFragment(SIGNIN);
                Intent intent = new Intent(IndexActivity.this, MipcaActivityCapture.class);
                startActivityForResult(intent, 1);
                locationClient.stopLocation();
            }
        }
//        if (requestCode == SDCARD_REQUEST_CODE) {
//            if (ContextCompat.checkSelfPermission(IndexActivity.this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    == PackageManager.PERMISSION_GRANTED) {
//                updateManger.checkUpdateInfo(upDataText);
//            }
//        }

    }

    /**
     * 设置fragment
     * 0主页，1发现，2签到，3我的
     *
     * @param position
     */
    public void setFragment(int position) {
        if (fragmentPosition == position) {
            return;
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexFind.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexSignin.setIconColor(getResources().getColor(R.color.colorGray));
        mChangrTvIndexMyself.setIconColor(getResources().getColor(R.color.colorGray));
        switch (position) {
            case HOME:
                magnifier.setImageResource(R.drawable.magnifier);
                mChangrTvIndexHomepage.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mHomePageFragment == null) {
                    mHomePageFragment = new HomePageFragment();
                }
                setFractionTranslate(transaction, HOME);
                transaction.replace(R.id.framelayout_index_content, mHomePageFragment);
                fragmentPosition = HOME;
                break;
            case FIND:
                magnifier.setImageResource(R.drawable.icon_menu);
//                magnifier.setImageResource(R.drawable.icon_photo);
                mChangrTvIndexFind.setIconColor(getResources().getColor(R.color.colorCyan));
                if (fragmentPosition != SIGNIN) {

                    if (mFindPageFragment == null) {
                        mFindPageFragment = new FindPageFragment();
                        mFindPageFragment.setTag(false);
                    } else {
                        //发送消息，隐藏签到按钮控件
                        mFindPageFragment.setType(false);
                    }
                    setFractionTranslate(transaction, FIND);
                    transaction.replace(R.id.framelayout_index_content, mFindPageFragment);
                } else {
                    //发送消息，隐藏签到按钮控件
                    mFindPageFragment.setType(false);
                }
                //这里是二期项目新增的代码
//                if (mShowPageFragment == null){
//                    mShowPageFragment = new ShowPageFragment();
//                }
//                setFractionTranslate(transaction,FIND);
//                transaction.replace(R.id.framelayout_index_content,mShowPageFragment);

                fragmentPosition = FIND;
                break;
            case SIGNIN:
                magnifier.setImageResource(R.drawable.magnifier);
                mChangrTvIndexSignin.setIconColor(getResources().getColor(R.color.colorCyan));
                if (fragmentPosition != FIND) {

                    if (mFindPageFragment == null) {
                        mFindPageFragment = new FindPageFragment();
                        mFindPageFragment.setTag(true);
                    } else {
                        mFindPageFragment.setType(true);
                    }
                    setFractionTranslate(transaction, FIND);
                    transaction.replace(R.id.framelayout_index_content, mFindPageFragment);
                } else {
                    //发送消息，显示签到按钮控件
                    mFindPageFragment.setType(true);
                }
                //这里是二期项目新增的代码
//                if (mSigninPageFragment == null){
//                    mSigninPageFragment = new SigninPageFragment();
//                }
//                setFractionTranslate(transaction,SIGNIN);
//                transaction.replace(R.id.framelayout_index_content,mSigninPageFragment);
                fragmentPosition = SIGNIN;
                break;
            case MYSELF:
                magnifier.setImageResource(R.drawable.magnifier);
                mChangrTvIndexMyself.setIconColor(getResources().getColor(R.color.colorCyan));
                if (mPersonalFragment == null) {
                    mPersonalFragment = new PersonalFragment();
                }
                setFractionTranslate(transaction, MYSELF);
                transaction.replace(R.id.framelayout_index_content, mPersonalFragment);
                fragmentPosition = MYSELF;
                break;
            default:
                break;

        }
        transaction.commit();

    }

    /**
     * 判断两种活动切换的效果
     *
     * @param transaction
     * @param type
     */
    private void setFractionTranslate(FragmentTransaction transaction, int type) {
        if (fragmentPosition > type) {
            transaction.setCustomAnimations(
                    R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out,
                    R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out);
        } else {
            transaction.setCustomAnimations(
                    R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out,
                    R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out);
        }

    }

    @OnClick({R.id.changrTv_index_homepage, R.id.changrTv_index_find, R.id.changrTv_index_signin, R.id.changrTv_index_myself})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changrTv_index_homepage:
                setFragment(HOME);
                break;
            case R.id.changrTv_index_find:
                setFragment(FIND);

                break;
            case R.id.changrTv_index_signin:
                setFragment(SIGNIN);

                break;
            case R.id.changrTv_index_myself:
                setFragment(MYSELF);
                break;
            default:
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(FragmentResultEvent resultEvent) {
        switch (resultEvent.getResultCode()) {
            case HOME:
                setFragment(HOME);
                break;
            case FIND:
                setFragment(FIND);
                break;
            case SIGNIN:
                setFragment(SIGNIN);
                break;
            case MYSELF:
                setFragment(MYSELF);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*mWifiLock.release();*/
        releaseWakeLock();
        EventBus.getDefault().unregister(this);
        ShareSDK.stopSDK(this);
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        acquireWakeLock();
         /*登陆界面返回*/
        boolean back_home = LocalDate.getInstance(getApplicationContext()).getLocalDate("BACKHOME", false);
        if (back_home) {
            LocalDate.getInstance(getApplicationContext()).setLocalDate("BACKHOME", false);
            setFragment(HOME);
        }
    }

    //二维码的activity返回的值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle bundle = data.getExtras();
        if(bundle == null){
            ToastUtils.showLong("未获取到二维码中有效信息！！！");
            return;
        }
        String qrcodeMsg = bundle.getString("result");
        Logger.v("QRCode", "qrcode result in indexActivity  " + qrcodeMsg);
        if (qrcodeMsg == null) {
            return;
        }
        if (qrcodeMsg.equals("0")) {
            return;
        }
        Logger.v(TAG, requestCode);
        if (requestCode == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(qrcodeMsg);
            builder.setTitle("二维码内容");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.create().show();
        } else if (requestCode == 1) {
            //将获取到的二维码的值传到FindPageFragment
            EventBus.getDefault().post(new QRCodeResultEvent(qrcodeMsg, 0));

        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                lat = aMapLocation.getLatitude();//获取纬度
                lng = aMapLocation.getLongitude();//获取经度
                LocalDate.getInstance(this).setLocalDate("lat", String.valueOf(lat));
                LocalDate.getInstance(this).setLocalDate("lng", String.valueOf(lng));
                String myLat = String.valueOf(lat);
                String myLng = String.valueOf(lng);
                if (!TextUtils.isEmpty(myLat) && !TextUtils.isEmpty(myLng)) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (ContextCompat.checkSelfPermission(IndexActivity.this,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(IndexActivity.this, Manifest.permission.CAMERA)) {
                                showMessageOKCancel("您需要在应用权限设置中对本应用使用摄像头进行授权才能正常使用该功能",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                ActivityCompat.requestPermissions(IndexActivity.this, new String[]{Manifest.permission.CAMERA},
                                                        TAKE_PHOTO_REQUEST_CODE);
                                            }
                                        });
                                return;
                            }
                            ActivityCompat.requestPermissions(IndexActivity.this, new String[]{Manifest.permission.CAMERA},
                                    TAKE_PHOTO_REQUEST_CODE);
                            return;
                        }
                        setFragment(SIGNIN);
                        Intent intent = new Intent(IndexActivity.this, MipcaActivityCapture.class);
                        startActivityForResult(intent, 1);
                        locationClient.stopLocation();

                    } else {
                        setFragment(SIGNIN);
                        Intent intent = new Intent(IndexActivity.this, MipcaActivityCapture.class);
                        startActivityForResult(intent, 1);
                        locationClient.stopLocation();
                    }
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }
}
