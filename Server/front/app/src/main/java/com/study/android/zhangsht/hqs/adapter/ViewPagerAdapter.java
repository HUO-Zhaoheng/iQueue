package com.study.android.zhangsht.hqs.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangsht on 2017/5/26.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;
    private List<String> titleList;

    public ViewPagerAdapter(List<View> mViewList,  List<String> titleList) {
        this.mViewList = mViewList;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {//必须实现
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {//必须实现
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
        container.removeView(mViewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
