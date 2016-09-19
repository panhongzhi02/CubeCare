package com.taiji.cc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by panho on 2016/9/1.
 */
public class DeviceUtils {

    private static  final String PREFS_FILE = "device_id.xml";
    private static  final String PREFS_DEVICE_ID  = "device_id";
    private static  UUID uuid;

    private DeviceUtils(){};

    public static String getDeviceId(Context context){

        if(uuid==null){
            synchronized (DeviceUtils.class){
                if(uuid==null){
                    final SharedPreferences prefs = context.getSharedPreferences( PREFS_FILE, Context.MODE_PRIVATE);
                    final String id = prefs.getString(PREFS_DEVICE_ID,null);
                    if(id!=null){
                        uuid = UUID.fromString(id);
                    }else {
                        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                        try {
                            if (!"9774d56d682e549c".equals(androidId)) {
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } else {
                                final String deviceId = ((TelephonyManager) context.getSystemService( Context.TELEPHONY_SERVICE )).getDeviceId();
                                uuid = deviceId!=null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString() ).commit();
                    }
                }
            }
        }
        return uuid.toString();
    }

}
