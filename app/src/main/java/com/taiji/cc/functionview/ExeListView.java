package com.taiji.cc.functionview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taiji.cc.R;
import com.taiji.cc.util.L;

import java.util.Map;

/**
 * Created by panho on 2016/9/6.
 */
public class ExeListView extends BasePageView implements View.OnClickListener{

    private LinearLayout lay_executelistview_transfuse;
    private LinearLayout lay_executelistview_drug;
    private LinearLayout lay_executelistview_cure;
    private LinearLayout lay_executelistview_injection;

    private ImageView iv_executelistview_transfuse;
    private ImageView iv_executelistview_drug;
    private ImageView iv_executelistview_cure;
    private ImageView iv_executelistview_injection;

    private TextView tv_executelistview_transfuse;
    private TextView tv_executelistview_drug;
    private TextView tv_executelistview_cure;
    private TextView tv_executelistview_injection;

    private Context context;

    public ExeListView(Context context) {
        this(context,null);
    }

    public ExeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.exelist_layout,this,true);
        this.context = context;
        lay_executelistview_transfuse = (LinearLayout) findViewById(R.id.lay_executelistview_transfuse);
        lay_executelistview_drug = (LinearLayout) findViewById(R.id.lay_executelistview_drug);
        lay_executelistview_cure = (LinearLayout) findViewById(R.id.lay_executelistview_cure);
        lay_executelistview_injection = (LinearLayout) findViewById(R.id.lay_executelistview_injection);

        lay_executelistview_transfuse.setOnClickListener(this);
        lay_executelistview_drug.setOnClickListener(this);
        lay_executelistview_cure.setOnClickListener(this);
        lay_executelistview_injection.setOnClickListener(this);

        iv_executelistview_transfuse = (ImageView) findViewById(R.id.iv_executelistview_transfuse);
        iv_executelistview_drug = (ImageView) findViewById(R.id.iv_executelistview_drug);
        iv_executelistview_cure = (ImageView) findViewById(R.id.iv_executelistview_cure);
        iv_executelistview_injection = (ImageView) findViewById(R.id.iv_executelistview_injection);

        tv_executelistview_transfuse = (TextView) findViewById(R.id.tv_executelistview_transfuse);
        tv_executelistview_drug = (TextView) findViewById(R.id.tv_executelistview_drug);
        tv_executelistview_cure = (TextView) findViewById(R.id.tv_executelistview_cure);
        tv_executelistview_injection = (TextView) findViewById(R.id.tv_executelistview_injection);

        setBtnPress(R.id.lay_executelistview_transfuse);
    }

    @Override
    public void onRefreshData(Map<String,Object> params) {
        L.d("选中执行单界面");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            default:
                setBtnPress(view.getId());
                break;
        }
    }

    private void setBtnPress(int id){
        lay_executelistview_transfuse.setClickable(true);
        lay_executelistview_drug.setClickable(true);
        lay_executelistview_cure.setClickable(true);
        lay_executelistview_injection.setClickable(true);

        tv_executelistview_transfuse.setTextColor(ContextCompat.getColor(context,R.color.white));
        tv_executelistview_drug.setTextColor(ContextCompat.getColor(context,R.color.white));
        tv_executelistview_cure.setTextColor(ContextCompat.getColor(context,R.color.white));
        tv_executelistview_injection.setTextColor(ContextCompat.getColor(context,R.color.white));

        iv_executelistview_transfuse.setImageResource(R.drawable.btn_transfusion_normal);
        iv_executelistview_drug.setImageResource(R.drawable.btn_drug_normal);
        iv_executelistview_cure.setImageResource(R.drawable.btn_cure_normal);
        iv_executelistview_injection.setImageResource(R.drawable.btn_injection_normal);

        switch (id){
            case R.id.lay_executelistview_transfuse:
                iv_executelistview_transfuse.setImageResource(R.drawable.btn_transfusion_press);
                lay_executelistview_transfuse.setClickable(false);
                tv_executelistview_transfuse.setTextColor(0xff03c9c5);
                break;
            case R.id.lay_executelistview_drug:
                iv_executelistview_drug.setImageResource(R.drawable.btn_drug_press);
                lay_executelistview_drug.setClickable(false);
                tv_executelistview_drug.setTextColor(0xff03c9c5);
                break;
            case R.id.lay_executelistview_cure:
                iv_executelistview_cure.setImageResource(R.drawable.btn_cure_press);
                lay_executelistview_cure.setClickable(false);
                tv_executelistview_cure.setTextColor(0xff03c9c5);
                break;
            case R.id.lay_executelistview_injection:
                iv_executelistview_injection.setImageResource(R.drawable.btn_injection_press);
                lay_executelistview_injection.setClickable(false);
                tv_executelistview_injection.setTextColor(0xff03c9c5);
                break;
        }
    }
}
