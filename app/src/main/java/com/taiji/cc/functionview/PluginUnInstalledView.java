package com.taiji.cc.functionview;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.morgoo.droidplugin.pm.PluginManager;
import com.taiji.cc.R;
import com.taiji.cc.adapter.PluginInstalledAdapter;
import com.taiji.cc.bean.ApkItem;
import com.taiji.cc.controller.ApkOperator;
import com.taiji.cc.http.methods.HttpMethod;
import com.taiji.cc.http.service.ApkPlugService;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 插件未安装界面
 */
public class PluginUnInstalledView extends BasePageView{

    private RecyclerView plugin_install_rv;

    private PluginInstalledAdapter adapter;

    private List<ApkItem> apkItems = new ArrayList<>();

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public PluginUnInstalledView(Context context) {
        this(context,null);
    }

    public PluginUnInstalledView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.plugin_installed_view_layout,this,true);
        plugin_install_rv = (RecyclerView) findViewById(R.id.plugin_install_rv);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        plugin_install_rv.setLayoutManager(manager);

        adapter = new PluginInstalledAdapter(apkItems, ApkOperator.TYPE_UNINSTALL);
        plugin_install_rv.setAdapter(adapter);

        if (PluginManager.getInstance().isConnected()) {
//            loadApks();
        } else {
            PluginManager.getInstance().addServiceConnection(serviceConnection);
        }
    }


    private void loadApks(){
        ApkPlugService service = HttpMethod.getInstance().getRetrofit().create(ApkPlugService.class);
        service.loadApks("112")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ApkItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<ApkItem> apkItems) {
                        adapter.setNewData(apkItems);
                    }
                });
    }

    @Override
    public void onRefreshData(Map<String, Object> params) {

    }
}
