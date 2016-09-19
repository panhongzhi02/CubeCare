package com.taiji.cc.fragment;/**
 * Created by panho on 2016-08-08.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taiji.cc.R;
import com.taiji.cc.bean.OrderTerm;
import com.taiji.cc.factorys.CommonFactory;
import com.taiji.cc.functionview.ExeListView;
import com.taiji.cc.functionview.MedicaAdviceView;
import com.taiji.cc.pageadapter.OrderPageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：panho on 2016-08-08 10:41
 * 邮箱：panhongzhi02@163.com
 */
public class OrderFragment extends BaseFragment{

    private TabLayout mTabLayout;

    private ViewPager mViewPage;

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合

    private List<View> mViewList = new ArrayList<>();//页卡视图集合

    private MedicaAdviceView medicaAdviceView;

    private ExeListView exeListView;

    private Map<String,Object> params = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPage = (ViewPager) view.findViewById(R.id.order_vp);
        mTabLayout = (TabLayout) view.findViewById(R.id.order_tab);
        medicaAdviceView = new MedicaAdviceView(mActivity);
        exeListView = new ExeListView(mActivity);

        mViewList.add(medicaAdviceView);
        mViewList.add(exeListView);

        mTitleList.add("医嘱");
        mTitleList.add("执行单");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));

        OrderPageAdapter adapter = new OrderPageAdapter(mViewList,mTitleList);
        mViewPage.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPage);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    params.clear();
                    OrderTerm term = new OrderTerm();
                    term.setAnam_id(CommonFactory.getFactory(mActivity).getPid());
                    term.setVid(CommonFactory.getFactory(mActivity).getVid());
                    term.setStart("0");
                    term.setEnd("50");
                    params.put("order",term);
                    medicaAdviceView.onRefreshData(params);
                }else if(position==1){
                    exeListView.onRefreshData(params);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        params.clear();
        OrderTerm term = new OrderTerm();
        term.setAnam_id(CommonFactory.getFactory(mActivity).getPid());
        term.setVid(CommonFactory.getFactory(mActivity).getVid());
        term.setStart("0");
        term.setEnd("50");
        params.put("order",term);
        medicaAdviceView.onRefreshData(params);

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
