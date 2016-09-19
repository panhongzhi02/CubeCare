package com.taiji.cc.bean;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

/**
 * Created by panho on 2016/9/12.
 */
public class ApkItem {

    public Drawable icon;

    public CharSequence title;

    public String versionName;

    public int versionCode;

    public String apkPath;

    public PackageInfo packageInfo;

    public ApkItem(PackageManager pm,PackageInfo pi,String path){
        pi.applicationInfo.sourceDir = path;
        pi.applicationInfo.publicSourceDir = path;
        try {
            icon = pm.getApplicationIcon(pi.applicationInfo);
        }catch (Exception e){
            icon = pm.getDefaultActivityIcon();
        }
        try {
            title = pm.getApplicationLabel(pi.applicationInfo);
        }catch (Exception e){
            title = pi.packageName;
        }
        versionName = pi.versionName;
        versionCode = pi.versionCode;
        apkPath = path;
        packageInfo = pi;
    }

}
