package com.example.renhao.wevolunteer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.MapView;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseFragment;
import com.orhanobut.logger.Logger;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/7 21:20
 * 修改备注：
 */
public class SigninPageFragment extends BaseFragment {
    private static final String TAG = "SigninPageFragment";

    MapView mMapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        mMapView = (MapView) view.findViewById(R.id.map_signinfragment_signinview);
        mMapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        Logger.v(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Logger.v(TAG, "onDestroy");
        mMapView.onDestroy();
        super.onDestroy();

    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();

    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);

    }
}
