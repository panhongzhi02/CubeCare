package com.taiji.cc.factorys;

import android.content.Context;
import android.content.SharedPreferences;

import com.taiji.cc.constant.C;
import com.taiji.cc.util.L;


/**
 * Created by panho on 2016/9/5.
 */
public class CommonFactory {

    private SharedPreferences preferences;

    private Context mContext;

    private static CommonFactory factory;

    private CommonFactory(Context context){
        this.mContext = context;
        preferences = context.getSharedPreferences(C.CubeCare.BIZ_INFO,Context.MODE_PRIVATE);
    }

    public static CommonFactory getFactory(Context context){
        if(factory==null){
            factory = new CommonFactory(context);
        }
        return factory;
    }

    /**
     * 切换患者
     * @param pid
     * @param vid
     */
    public void changePatient(String pid,String vid){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(C.CubeCare.PID,pid);
        editor.putString(C.CubeCare.VID,vid);
        editor.commit();
    }

    /**
     * 获取当前患者病案号
     * @return
     */
    public String getPid(){
        String pid = preferences.getString(C.CubeCare.PID,"null");
        if(pid.equals("null")){
            L.d("未找到病案号");
        }
        return pid;
    }

    /**
     * 获取当前患者住院次数
     * @return
     */
    public String getVid(){
        String vid = preferences.getString(C.CubeCare.VID,"null");
        if(vid.equals("null")){
            L.d("未找到住院次数");
        }
        return vid;
    }

    /**
     * 切换科室
     * @param did
     */
    public void changeDid(String did){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(C.CubeCare.DID,did);
        editor.commit();
    }

    /**
     * 获取当前科室号
     * @return
     */
    public String getDid(){
        String did = preferences.getString(C.CubeCare.DID,"null");
        if(did.equals("null")){
            L.d("未找到科室号");
        }
        return did;
    }

    /**
     * 切换当前病区号
     * @param areaId
     */
    public void changeAreaId(String areaId){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(C.CubeCare.AREA_ID,areaId);
        editor.commit();
    }

    /**
     * 获取当前病区号
     * @return
     */
    public String getAreaId(){
        String areaId = preferences.getString(C.CubeCare.AREA_ID,"null");
        if(areaId.equals("null")){
            L.d("未找到病区号");
        }
        return areaId;
    }

    /**
     * 切换登陆用户
     * @param nid
     */
    public void changeNurse(String nid){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(C.CubeCare.NID,nid);
        editor.commit();
    }

    public String getNid(){
        String nid = preferences.getString(C.CubeCare.NID,"null");
        if(nid.equals("null")){
            L.d("未找到护士ID");
        }
        return nid;
    }

}
