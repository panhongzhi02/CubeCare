package com.taiji.cc.http.service;



import com.taiji.cc.bean.ApkItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by panho on 2016/9/13.
 */
public interface ApkPlugService {

    @GET("apk/loadApks")
    Observable<List<ApkItem>> loadApks(@Query("did")String did);

}
