package com.taiji.cc.pageadapter;/**
 * Created by panho on 2016-08-09.
 */

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 作者：panho on 2016-08-09 09:30
 * 邮箱：panhongzhi02@163.com
 */
public class OrderPageAdapter extends PagerAdapter {

    private List<View> mViewList;

    private List<String> mTitleList;

    public OrderPageAdapter(List<View> viewList, List<String> mTitleList){
        this.mViewList = viewList;
        this.mTitleList = mTitleList;
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
