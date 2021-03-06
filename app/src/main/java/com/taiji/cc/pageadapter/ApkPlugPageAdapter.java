package com.taiji.cc.pageadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by panho on 2016/9/12.
 */
public class ApkPlugPageAdapter extends PagerAdapter{

    private List<View> mViewList;

    private List<String> mTitleList;


    public ApkPlugPageAdapter(List<View> viewList,List<String> titleList){
        this.mViewList = viewList;
        this.mTitleList = titleList;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
