package com.example.lenovo.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/15 0015.
 */

public class SyntheAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private String[] str;

    public SyntheAdapter(FragmentManager fm, ArrayList<Fragment> list, String[] str) {
        super(fm);
        this.list = list;
        this.str = str;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
