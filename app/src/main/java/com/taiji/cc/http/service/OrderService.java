package com.taiji.cc.http.service;


import com.taiji.cc.bean.PageOrder;
import com.taiji.cc.scan.bean.ScanResponse;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by panho on 2016/9/7.
 */
public interface OrderService {

    /**
     * 获取分页医嘱列表
     * @param orderJson
     * @return
     */
    @POST("order/selectOrders")
    Observable<PageOrder> getOrderList(@Query("orderJson")String orderJson);

    /**
     *
     * @param dic_code 主医嘱号
     * @param date 计划执行日期
     * @param time 计划执行时间
     * @return
     */
    Observable<ScanResponse> checkOrder(@Query("dic_code")String dic_code,
                                        @Query("date")String date,
                                        @Query("time")String time);

}
