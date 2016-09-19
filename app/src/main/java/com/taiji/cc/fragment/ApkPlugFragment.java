package com.taiji.cc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taiji.cc.R;
import com.taiji.cc.functionview.PluginInstalledView;
import com.taiji.cc.functionview.PluginUnInstalledView;
import com.taiji.cc.pageadapter.ApkPlugPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panho on 2016/9/12.
 */
public class ApkPlugFragment extends BaseFragment{

    private TabLayout apkplug_tab;

    private ViewPager apkplug_vp;

    private List<String> mTitleLists = new ArrayList<>();//页卡标题集合

    private List<View> mViewList = new ArrayList<>();

    private PluginInstalledView pluginInstalledView;
    private PluginUnInstalledView pluginUnInstalledView;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.apkplug_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apkplug_tab = (TabLayout) view.findViewById(R.id.apkplug_tab);
        apkplug_vp = (ViewPager) view.findViewById(R.id.apkplug_vp);

        pluginInstalledView = new PluginInstalledView(mActivity);
        pluginUnInstalledView = new PluginUnInstalledView(mActivity);

        mViewList.add(pluginInstalledView);
        mViewList.add(pluginUnInstalledView);

        mTitleLists.add("已安装");
        mTitleLists.add("未安装");

        apkplug_tab.setTabMode(TabLayout.MODE_FIXED);
        apkplug_tab.addTab(apkplug_tab.newTab().setText(mTitleLists.get(0)));
        apkplug_tab.addTab(apkplug_tab.newTab().setText(mTitleLists.get(1)));

        ApkPlugPageAdapter adapter = new ApkPlugPageAdapter(mViewList,mTitleLists);
        apkplug_vp.setAdapter(adapter);
        apkplug_tab.setupWithViewPager(apkplug_vp);
        apkplug_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){

        }
        super.onHiddenChanged(hidden);
    }
}
