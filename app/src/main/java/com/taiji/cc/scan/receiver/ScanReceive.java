package com.taiji.cc.scan.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.taiji.cc.scan.bean.ScanRequest;
import com.taiji.cc.scan.helper.ScanHandlerHelper;

/**
 * 扫描广播接收器
 * Created by panho on 2016/9/14.
 */
public class ScanReceive extends BroadcastReceiver{

    /**
     * 摩托罗拉扫描接口
     */
    public static final String MOTO_NAME = "com.motorolasolutions.emdk.datawedge.data_string";
    /**
     * 新联扫描接口
     */
    public static final String XINLIAN_NAME = "lachesis_barcode_value_notice_broadcast_data_string";
    /**
     * 医惠扫描接口
     */
    public static final String YIHUI_NAME = "scanresult";
    /**
     * 清远PDA扫描接口
     */
    public static final String YUANQING_NAME = "barcode_string";

    @Override
    public void onReceive(Context context, Intent intent) {
        String scanResult = intent.getStringExtra(MOTO_NAME);
        ScanHandlerHelper.getInstance(context).doHandler(new ScanRequest(scanResult));
    }
}
