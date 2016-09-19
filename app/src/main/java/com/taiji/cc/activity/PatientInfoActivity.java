package com.taiji.cc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.taiji.cc.R;
import com.taiji.cc.factorys.CommonFactory;
import com.taiji.cc.greendao.bean.Patient;
import com.taiji.cc.greendao.manager.PatientDBManager;

/**
 * Created by panho on 2016/9/6.
 */
public class PatientInfoActivity extends BaseActivity{

    Patient patient = null;//患者信息

    private TextView patient_bedno;//床号
    private TextView patient_gender;//性别
    private TextView patient_age;//年龄
    private TextView patient_id;//病案号
    private TextView patient_visitid;//住院次数
    private TextView patient_birth;//出生日期
    private TextView patient_doctor;//主治医师
    private TextView patient_level;//护理等级
    private TextView patient_initdate;//入院日期
    private TextView patient_illness;//诊断
    private TextView patient_name;//患者姓名

    private SwipeRefreshLayout patient_info_sr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_info_layout);

        patient = PatientDBManager.getInstance(this).queryPatient(CommonFactory.getFactory(this).getPid(),CommonFactory.getFactory(this).getVid());

        patient_bedno = (TextView) findViewById(R.id.patient_bedno);
        patient_bedno.setText("床号："+patient.getPp_bedNo());
        patient_gender = (TextView) findViewById(R.id.patient_gender);
        patient_gender.setText("性别:"+patient.getP_gender());
        patient_age = (TextView) findViewById(R.id.patient_age);
        patient_age.setText("年龄:"+patient.getAge());
        patient_id = (TextView) findViewById(R.id.patient_id);
        patient_id.setText("病案号："+patient.getP_id());
        patient_visitid = (TextView) findViewById(R.id.patient_visitid);
        patient_visitid.setText("住院次数："+patient.getPp_visitid());
        patient_birth = (TextView) findViewById(R.id.patient_birth);
        patient_birth.setText("出生日期："+patient.getP_birthday());
        patient_doctor = (TextView) findViewById(R.id.patient_doctor);
        patient_doctor.setText("主管医生："+patient.getPp_mainDoctor());
        patient_level = (TextView) findViewById(R.id.patient_level);
        patient_level.setText("护理等级："+patient.getPp_careLevel());
        patient_initdate = (TextView) findViewById(R.id.patient_initdate);
        patient_initdate.setText("入院日期："+patient.getPp_timeInHospital());
        patient_illness = (TextView) findViewById(R.id.patient_illness);
        patient_illness.setText("门急诊诊断："+patient.getPp_diagnosis());
        patient_name = (TextView) findViewById(R.id.patient_name);
        patient_name.setText(patient.getP_name());

        patient_info_sr = (SwipeRefreshLayout) findViewById(R.id.patient_info_sr);
        patient_info_sr.setColorSchemeResources(R.color.colorPrimary);
        patient_info_sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                patient_info_sr.setRefreshing(false);
            }
        });
    }
}
