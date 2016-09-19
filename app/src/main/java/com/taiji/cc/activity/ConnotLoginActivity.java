package com.taiji.cc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taiji.cc.R;
import com.taiji.cc.customview.ClearEditText;
import com.taiji.cc.greendao.bean.IpAddress;
import com.taiji.cc.greendao.manager.IpAddressDBManager;
import com.taiji.cc.util.DeviceUtils;

import java.util.List;


/**
 * 无法连接服务器时设置服务器连接地址
 */
public class ConnotLoginActivity extends BaseActivity{

    private TextView deviceid_tv;

    private ClearEditText ip_address_et;

    private ClearEditText port_address_et;

    private Button check_save_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connot_login_acy);

        IpAddressDBManager manager = IpAddressDBManager.getInstance(ConnotLoginActivity.this);
        List<IpAddress> ipAddresses = manager.queryUserList();

        deviceid_tv = (TextView) findViewById(R.id.deviceid_tv);
        deviceid_tv.setText(DeviceUtils.getDeviceId(this));

        ip_address_et = (ClearEditText) findViewById(R.id.ip_address_et);
        port_address_et = (ClearEditText) findViewById(R.id.port_address_et);

        check_save_btn = (Button) findViewById(R.id.check_save_btn);
        check_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IpAddress ipAddress = new IpAddress();
                ipAddress.setIp(ip_address_et.getText().toString());
                ipAddress.setPort(Integer.valueOf(port_address_et.getText().toString()));
                ipAddress.setServerName("CubeCare");
                IpAddressDBManager manager = IpAddressDBManager.getInstance(ConnotLoginActivity.this);
                manager.insert(ipAddress);
            }
        });
    }
}
