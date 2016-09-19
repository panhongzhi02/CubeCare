package com.taiji.cc.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.widget.Toast;

import com.morgoo.droidplugin.pm.PluginManager;
import com.taiji.cc.bean.ApkItem;

import java.io.File;

/**
 * Created by panho on 2016/9/12.
 */
public class ApkOperator {
    /**
     * 已安装
     */
    public static final int TYPE_INSTALL = 0;
    /**
     * 未安装
     */
    public static final int TYPE_UNINSTALL = 1;

    private Activity mActivity;

    private RemoveCallback mCallback;

    public ApkOperator(Activity activity,RemoveCallback callback){
        mActivity = activity;
        mCallback = callback;
    }

    /**
     * 删除APK
     * @param item
     */
    public void deleteApk(final ApkItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("警告");
        builder.setMessage("你确定要删除"+item.title+"么？");
        builder.setNegativeButton("删除成功", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(new File(item.apkPath).delete()){
                    mCallback.removeItem(item);
                    Toast.makeText(mActivity,"删除成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mActivity,"删除失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNeutralButton("取消",null);
        builder.show();
    }

    /**
     * 安装APK 耗时较长 需要使用异步线程
     * @param item
     * @return
     */
    public String installApk(final ApkItem item){
        if(!PluginManager.getInstance().isConnected()){
            return "连接失败";
        }
        if(isApkInstall(item)){
            return "已安装";
        }
        try {
            int result = PluginManager.getInstance().installPackage(item.apkPath, 0);
            boolean isRequestPermission = (result == PluginManager.INSTALL_FAILED_NO_REQUESTEDPERMISSION);
            if (isRequestPermission) {
                return "权限不足";
            }
        }catch (RemoteException e){
            e.printStackTrace();
            return "安装失败";
        }
        return "成功";
    }

    /**
     * 判断Apk是否安装
     * @param apkItem
     * @return
     */
    private boolean isApkInstall(ApkItem apkItem){
        PackageInfo info = null;
        try {
            info = PluginManager.getInstance().getPackageInfo(apkItem.packageInfo.packageName, 0);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        return info!=null;
    }

    /**
     * 卸载APK
     * @param item
     */
    public void uninstallApk(final ApkItem item){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("警告");
        builder.setMessage("警告，你确定要卸载"+item.title+"么？");
        builder.setNegativeButton("卸载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               if(!PluginManager.getInstance().isConnected()){
                   Toast.makeText(mActivity,"服务未连接",Toast.LENGTH_SHORT).show();
               } else {
                   try {
                       PluginManager.getInstance().deletePackage(item.packageInfo.packageName, 0);
                       mCallback.removeItem(item);
                       Toast.makeText(mActivity, "卸载完成", Toast.LENGTH_SHORT).show();
                   }catch (RemoteException e){
                       e.printStackTrace();
                   }
               }
            }
        });
        builder.setNegativeButton("取消",null);
        builder.show();
    }

    /**
     * 打开APK
     * @param item
     */
    public void openApk(final ApkItem item){
        PackageManager pm = mActivity.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(item.packageInfo.packageName);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mActivity.startActivity(intent);
    }

    public interface RemoveCallback{
        void removeItem(ApkItem apkitem);
    }
}
