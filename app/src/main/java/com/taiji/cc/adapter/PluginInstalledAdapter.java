package com.taiji.cc.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taiji.cc.R;
import com.taiji.cc.bean.ApkItem;
import com.taiji.cc.controller.ApkOperator;

import java.util.List;


/**
 * Created by panho on 2016/9/12.
 */
public class PluginInstalledAdapter extends BaseQuickAdapter<ApkItem> {

    private int mType;

    public PluginInstalledAdapter(List<ApkItem> data, int type){
        this(data);
        this.mType = type;
    }

    public PluginInstalledAdapter(List<ApkItem> data) {
        super(R.layout.plugin_installed_item_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ApkItem apkItem) {
        if(mType== ApkOperator.TYPE_UNINSTALL){
            baseViewHolder.setText(R.id.uninstall_btn,"删除");
            baseViewHolder.setText(R.id.install_btn,"安装");
        }else if(mType==ApkOperator.TYPE_INSTALL){
            baseViewHolder.setText(R.id.uninstall_btn,"卸载");
            baseViewHolder.setText(R.id.install_btn,"运行");
        }
        baseViewHolder.addOnClickListener(R.id.uninstall_btn);
        baseViewHolder.addOnClickListener(R.id.install_btn);
        baseViewHolder.setImageDrawable(R.id.apk_icon_iv,apkItem.icon);
        baseViewHolder.setText(R.id.apk_title_tv,apkItem.title);
        baseViewHolder.setText(R.id.apk_item_tv_version,String.format("%s(%s)", apkItem.versionName, apkItem.versionCode));
    }
}
