package com.taiji.cc.fragment;/**
 * Created by panho on 2016-08-08.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taiji.cc.R;


/**
 * 作者：panho on 2016-08-08 10:43
 * 邮箱：panhongzhi02@163.com
 */
public class EducationFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.education_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            Log.d("pan","进入健康教育界面");
        }
        super.onHiddenChanged(hidden);
    }
}
