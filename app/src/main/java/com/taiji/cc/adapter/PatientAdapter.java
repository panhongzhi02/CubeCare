package com.taiji.cc.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taiji.cc.R;
import com.taiji.cc.greendao.bean.Patient;

import java.util.List;


/**
 * Created by panho on 2016/9/5.
 */
public class PatientAdapter extends BaseQuickAdapter<Patient> {

    private OnItemClickListener listener;

    public PatientAdapter(List<Patient> data) {
        super(R.layout.patient_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final Patient patient) {
        baseViewHolder.setText(R.id.bed_tv,patient.getPp_bedNo()+"床");
        baseViewHolder.setText(R.id.pname_tv,patient.getP_name());
        baseViewHolder.setText(R.id.sex_tv,patient.getP_gender());
        baseViewHolder.setText(R.id.age_tv,patient.getAge());
        TextView pid_tv = baseViewHolder.getView(R.id.pid_tv);
        pid_tv.setText(patient.getP_id());
        baseViewHolder.setText(R.id.other_tv,patient.getPp_exten_2());
        baseViewHolder.addOnClickListener(R.id.card_view);
        CardView cardView =  baseViewHolder.getView(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.itemClick(patient);
                }
            }
        });
        if(patient.getPp_careLevel()!=null){
            if(patient.getPp_careLevel().equals("一级护理")){
                baseViewHolder.setBackgroundRes(R.id.bed_tv,R.drawable.care_level1);
                pid_tv.setTextColor(ContextCompat.getColor(mContext,R.color.care_level_1));
            }else if(patient.getPp_careLevel().equals("二级护理")){
                baseViewHolder.setBackgroundRes(R.id.bed_tv,R.drawable.care_level2);
                pid_tv.setTextColor(ContextCompat.getColor(mContext,R.color.care_level_2));
            }else if(patient.getPp_careLevel().equals("三级护理")){
                baseViewHolder.setBackgroundRes(R.id.bed_tv,R.drawable.care_level3);
                pid_tv.setTextColor(ContextCompat.getColor(mContext,R.color.care_level_3));
            }else if(patient.getPp_careLevel().equals("特级护理")){
                baseViewHolder.setBackgroundRes(R.id.bed_tv,R.drawable.care_level4);
                pid_tv.setTextColor(ContextCompat.getColor(mContext,R.color.care_level_4));
            }else {
                baseViewHolder.setBackgroundRes(R.id.bed_tv,R.drawable.care_level5);
                pid_tv.setTextColor(ContextCompat.getColor(mContext,R.color.care_level_5));
            }
        }else {
            baseViewHolder.setBackgroundRes(R.id.bed_tv,R.drawable.care_level5);
            pid_tv.setTextColor(ContextCompat.getColor(mContext,R.color.care_level_5));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{

        void itemClick(Patient patient);

    }
}
