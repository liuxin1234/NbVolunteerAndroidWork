package com.example.renhao.wevolunteer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.renhao.wevolunteer.fragment.PresonalServicePage1;
import com.example.renhao.wevolunteer.fragment.PresonalServicePage2;

/**
 * 个人服务时间的适配器
 */
public class PresonalServiceAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[];
    private int numbOfTabs;
    private Activity mActivity;

    public PresonalServiceAdapter(FragmentManager fm, Activity activity, CharSequence titles[], int mNumbOfTabs) {
        super(fm);
        this.titles = titles;
        this.numbOfTabs = mNumbOfTabs;
        this.mActivity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        Intent intent = mActivity.getIntent();
        Integer tag=intent.getIntExtra("tag",-1);
        Bundle bundle = new Bundle();
        bundle.putInt("tag", tag);
        if (position == 0) {
            PresonalServicePage1 page1 = new PresonalServicePage1();
            page1.setArguments(bundle);
            return page1;
        } else {
            PresonalServicePage2 page2 = new PresonalServicePage2();
            page2.setArguments(bundle);
            return page2;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return numbOfTabs;
    }
}