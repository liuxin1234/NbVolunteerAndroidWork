package com.example.renhao.wevolunteer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/8 16:26
 * 修改备注：
 */
public class FragmentSwitchAdapter extends FragmentPagerAdapter {
    private static final String TAG = "FragmentSwitchAdapter";

    private FragmentManager fm;
    private List<Fragment> fragmentList;

    public FragmentSwitchAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return (Fragment)this.fragmentList.get(position % this.fragmentList.size());
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }

    @Override
    public long getItemId(int position) {
        return -2;
    }
}
