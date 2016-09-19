package com.taiji.cc.scan.helper;


import android.content.Context;

import com.taiji.cc.scan.bean.ScanRequest;
import com.taiji.cc.scan.handler.InfusionHandler;
import com.taiji.cc.scan.handler.MedicineHandler;
import com.taiji.cc.scan.handler.PatientHandler;
import com.taiji.cc.scan.handler.ScanHandler;

/**
 * Created by panho on 2016/9/19.
 */
public class ScanHandlerHelper {

    private static Context mContext;

    private static ScanHandlerHelper instance = new ScanHandlerHelper();

    private ScanHandlerHelper(){

    }

    public static ScanHandlerHelper getInstance(Context context){
        mContext = context;
        if(instance==null){
            instance = new ScanHandlerHelper();
        }
        return instance;
    }

    public void doHandler(ScanRequest request){
        ScanHandler patientHandler = new PatientHandler(mContext);
        ScanHandler infusionHandler = new InfusionHandler(mContext);
        ScanHandler medicineHandler = new MedicineHandler(mContext);
        patientHandler.setNextHandler(infusionHandler);
        infusionHandler.setNextHandler(medicineHandler);
        patientHandler.doHandler(request);
    }
}
