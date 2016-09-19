package com.taiji.cc.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taiji.cc.R;
import com.taiji.cc.greendao.bean.Patient;

import java.util.List;


/**
 * Created by panho on 2016/9/7.
 */
public class PatientListAdapter extends BaseQuickAdapter<Patient> {

    public PatientListAdapter(List<Patient> data) {
        super(R.layout.patient_list_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Patient patient) {
        baseViewHolder.setText(R.id.bedno_tv,patient.getPp_bedNo()+"床");
        baseViewHolder.setText(R.id.pname_tv,patient.getP_name());
    }
}
