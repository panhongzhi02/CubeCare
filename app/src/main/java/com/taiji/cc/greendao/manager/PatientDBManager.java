package com.taiji.cc.greendao.manager;

import android.content.Context;

import com.taiji.cc.greendao.bean.Patient;
import com.taiji.cc.greendao.db.DaoMaster;
import com.taiji.cc.greendao.db.DaoSession;
import com.taiji.cc.greendao.db.PatientDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by panho on 2016/9/2.
 */
public class PatientDBManager extends BaseDBManager{

    private static PatientDBManager mInstance;

    protected PatientDBManager(Context context) {
        super(context);
    }

    public static PatientDBManager getInstance(Context context){
        if(mInstance==null){
            synchronized (PatientDBManager.class){
                if(mInstance==null){
                    mInstance = new PatientDBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 批量插入患者信息
     * @param patients
     */
    public void insertList(List<Patient> patients){
        if(patients==null||patients.isEmpty()){
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PatientDao patientDao = daoSession.getPatientDao();
        patientDao.insertInTx(patients);
    }

    /**
     * 查询全部患者列表
     * @return
     */
    public List<Patient> queryPatientList(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PatientDao patientDao = daoSession.getPatientDao();
        QueryBuilder<Patient> qb = patientDao.queryBuilder();
        List<Patient> patients = qb.list();
        return patients;
    }

    public Patient queryPatient(String pid,String vid){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PatientDao patientDao = daoSession.getPatientDao();
        QueryBuilder<Patient> qb = patientDao.queryBuilder()
                .where(PatientDao.Properties.P_id.eq(pid))
                .where(PatientDao.Properties.Pp_visitid.eq(vid));
        return qb.unique();
    }

    /**
     * 清空全部数据
     *
     */
    public void deleteAll(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        PatientDao patientDao = daoSession.getPatientDao();
        patientDao.deleteAll();
    }

}
