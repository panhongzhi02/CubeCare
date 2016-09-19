package com.taiji.cc.greendao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.taiji.cc.greendao.bean.IpAddress;
import com.taiji.cc.greendao.bean.Patient;

import com.taiji.cc.greendao.db.IpAddressDao;
import com.taiji.cc.greendao.db.PatientDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig ipAddressDaoConfig;
    private final DaoConfig patientDaoConfig;

    private final IpAddressDao ipAddressDao;
    private final PatientDao patientDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        ipAddressDaoConfig = daoConfigMap.get(IpAddressDao.class).clone();
        ipAddressDaoConfig.initIdentityScope(type);

        patientDaoConfig = daoConfigMap.get(PatientDao.class).clone();
        patientDaoConfig.initIdentityScope(type);

        ipAddressDao = new IpAddressDao(ipAddressDaoConfig, this);
        patientDao = new PatientDao(patientDaoConfig, this);

        registerDao(IpAddress.class, ipAddressDao);
        registerDao(Patient.class, patientDao);
    }
    
    public void clear() {
        ipAddressDaoConfig.getIdentityScope().clear();
        patientDaoConfig.getIdentityScope().clear();
    }

    public IpAddressDao getIpAddressDao() {
        return ipAddressDao;
    }

    public PatientDao getPatientDao() {
        return patientDao;
    }

}
