package com.example.renhao.wevolunteer.gps;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/9/2 1:06
 * 修改备注：
 */
public class GetGPS {

    private Context mContext;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (mGPSListener != null) {
                mGPSListener.OnGPSChanged(aMapLocation);
            }
        }
    };

    public interface GPSListener {
        public void OnGPSChanged(AMapLocation aMapLocation);
    }

    private GPSListener mGPSListener;

    public void setGPSListener(GPSListener gpsListener) {
        mGPSListener = gpsListener;
    }

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    public GetGPS(Context context) {
        this.mContext = context;
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }

    public void startGPSListener() {
        mLocationClient.startLocation();
    }
}
