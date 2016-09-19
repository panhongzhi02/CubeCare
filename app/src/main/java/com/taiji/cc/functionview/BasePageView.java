package com.taiji.cc.functionview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.Map;

/**
 * Created by panho on 2016/9/6.
 */
public abstract class BasePageView extends LinearLayout{

    public BasePageView(Context context) {
        super(context);
    }

    public BasePageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void onRefreshData(Map<String,Object> params);
}
