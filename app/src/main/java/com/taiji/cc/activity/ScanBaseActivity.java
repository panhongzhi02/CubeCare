package com.taiji.cc.activity;

import com.taiji.cc.scan.bean.ScanResponse;
import com.taiji.cc.scan.helper.ScanQueue;
import com.taiji.cc.util.T;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by panho on 2016/9/19.
 */
public abstract class ScanBaseActivity extends BaseActivity{


    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onScanEvent(ScanResponse response){
        ScanQueue scanQueue = new ScanQueue(this);
        scanQueue.addResponse(response, new ScanQueue.OnFilterListener() {
            @Override
            public void onSuccess(ScanResponse response) {
                onScanSuccess(response);
            }

            @Override
            public void onFaild(ScanResponse response) {
                T.showShort(ScanBaseActivity.this,response.getMessage());
            }

            @Override
            public void onMatchSuccess(List<ScanResponse> responses) {
                onMatchSuccess(responses);
            }

            @Override
            public void onMatchFaild(List<ScanResponse> responses) {

            }
        });
    }

    /**
     * 扫描回掉方法
     * @param response
     */
    public abstract void onScanSuccess(ScanResponse response);

    /**
     * 匹配成功的回掉方法
     * @param responses
     */
    public void onMatchSuccess(List<ScanResponse> responses){

    }

}
