package com.study.android.zhangsht.hqs.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhangsht on 2017/5/26.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mStrings;

    public FragmentAdapter(FragmentManager fm, List<Fragment> mFragments,  String[] titles) {
        super(fm);
        this.mFragments = mFragments;
        mStrings = titles;
    }

    @Override
    public Fragment getItem(int position) {//必须实现
        return mFragments.get(position);
    }

    @Override
    public int getCount() {//必须实现
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {//选择性实现
        return mStrings == null ? super.getPageTitle(position) : mStrings[position];
    }
}
