package com.taiji.cc.functionview;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.morgoo.droidplugin.pm.PluginManager;
import com.taiji.cc.R;
import com.taiji.cc.adapter.PluginInstalledAdapter;
import com.taiji.cc.bean.ApkItem;
import com.taiji.cc.controller.ApkOperator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 插件已安装界面
 */
public class PluginInstalledView extends BasePageView{

    private Context context;

    private RecyclerView plugin_install_rv;

    private List<ApkItem> apkitems = new ArrayList<>();

    private InstallApkReceiver mInstallApkReceiver;

    private PluginInstalledAdapter adapter;

    private InstallApkReceiver receiver;

    private ServiceConnection mServicesConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            loadApks();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public PluginInstalledView(Context context) {
        this(context,null);
    }

    public PluginInstalledView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.plugin_installed_view_layout,this,true);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        plugin_install_rv = (RecyclerView) findViewById(R.id.plugin_install_rv);
        plugin_install_rv.setLayoutManager(manager);

        adapter = new PluginInstalledAdapter(apkitems, ApkOperator.TYPE_INSTALL);
        plugin_install_rv.setAdapter(adapter);

        receiver = new InstallApkReceiver();

        receiver.registerReceiver(context);

        if(PluginManager.getInstance().isConnected()){
            loadApks();
        }else {
            PluginManager.getInstance().addServiceConnection(mServicesConnection);
        }
    }

    private void loadApks(){
        Observable.just(getApkFromDownload())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ApkItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ApkItem> apkItems) {

                    }
                });
    }

    /**
     * 从下载文件夹获取APK
     * @return
     */
    private ArrayList<ApkItem> getApkFromDownload(){
        File files = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        PackageManager pm = context.getPackageManager();
        ArrayList<ApkItem> apkItems = new ArrayList<>();
        if(files.listFiles()!=null){
            for (File file:files.listFiles()) {
                if(file.exists()&&file.getPath().toLowerCase().endsWith(".apk")){
                    final PackageInfo info = pm.getPackageArchiveInfo(file.getPath(),0);
                    apkItems.add(new ApkItem(pm,info,file.getPath()));
                }
            }
        }
        return apkItems;
    }

    @Override
    public void onRefreshData(Map<String, Object> params) {

    }

    public class InstallApkReceiver extends BroadcastReceiver {

        /**
         * 注册监听
         * @param context
         */
        public void registerReceiver(Context context){
            IntentFilter filter = new IntentFilter();
            filter.addAction(PluginManager.ACTION_PACKAGE_ADDED);
            filter.addAction(PluginManager.ACTION_PACKAGE_REMOVED);
            filter.addDataScheme("package");
            context.registerReceiver(this,filter);
        }

        /**
         * 关闭监听
         * @param context
         */
        public void unregisterReceiver(Context context){
            context.unregisterReceiver(this);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if(PluginManager.ACTION_PACKAGE_ADDED.equals(intent.getAction())){
                try {
                    PackageManager pm = context.getPackageManager();
                    String pkg = intent.getData().getAuthority();
                    PackageInfo info = PluginManager.getInstance().getPackageInfo(pkg, 0);
                    adapter.add(0,new ApkItem(pm, info, info.applicationInfo.publicSourceDir));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if(PluginManager.ACTION_PACKAGE_REMOVED.equals(intent.getAction())){
                String pkg = intent.getData().getAuthority();
                int num = adapter.getItemCount();
                int removedPosition = 0;
                ApkItem removedItem = null;
                for (int i = 0; i < num; i++) {
                    ApkItem item = adapter.getItem(i);
                    if(TextUtils.equals(item.packageInfo.packageName,pkg)){
                        removedPosition = i;
                        removedItem = item;
                        break;
                    }
                }
                if(removedItem!=null){
                    adapter.remove(removedPosition);
                }
            }
        }
    }
}
