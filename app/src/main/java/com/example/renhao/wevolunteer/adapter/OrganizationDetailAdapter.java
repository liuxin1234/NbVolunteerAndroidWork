package com.example.renhao.wevolunteer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.renhao.wevolunteer.fragment.OrganizationPage1;
import com.example.renhao.wevolunteer.fragment.OrganizationPage2;

public class OrganizationDetailAdapter extends FragmentStatePagerAdapter {

    CharSequence titles[];
    int numbOfTabs;

    public OrganizationDetailAdapter(FragmentManager fm, CharSequence titles[], int mNumbOfTabs) {
        super(fm);
        this.titles = titles;
        this.numbOfTabs = mNumbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            OrganizationPage1 page1 = new OrganizationPage1();
            return page1;
        } else {
            OrganizationPage2 page2 = new OrganizationPage2();
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