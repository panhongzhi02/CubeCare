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
 * 作者：panho on 2016-08-08 10:42
 * 邮箱：panhongzhi02@163.com
 */
public class RecordFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("pan","护理文书");
        return inflater.inflate(R.layout.record_fragment_layout,container,false);
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
            Log.d("pan","进入护理文书界面");
        }
        super.onHiddenChanged(hidden);
    }
}
