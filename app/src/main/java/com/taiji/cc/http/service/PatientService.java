package com.taiji.cc.http.service;



import com.taiji.cc.greendao.bean.Patient;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by panho on 2016/9/2.
 */
public interface PatientService {

    /**
     * 获取患者列表数据
     * @param area_id
     * @return
     */
    @GET("patientInfo/getPatientInfo")
    Observable<List<Patient>> getPatients(@Query("area_id")String area_id);

}
