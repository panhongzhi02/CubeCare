package com.taiji.cc.greendao.manager;

import android.content.Context;

import com.taiji.cc.greendao.bean.IpAddress;
import com.taiji.cc.greendao.db.DaoMaster;
import com.taiji.cc.greendao.db.DaoSession;
import com.taiji.cc.greendao.db.IpAddressDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by panho on 2016/9/2.
 */
public class IpAddressDBManager extends BaseDBManager{

    private static IpAddressDBManager mInstance;

    protected IpAddressDBManager(Context context) {
        super(context);
    }

    public static IpAddressDBManager getInstance(Context context){
        if(mInstance==null){
            synchronized (IpAddressDBManager.class){
                if(mInstance==null){
                    mInstance = new IpAddressDBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 插入一条数据
     * @param ipAddress
     */
    public void insert(IpAddress ipAddress){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IpAddressDao ipAddressDao = daoSession.getIpAddressDao();
        ipAddressDao.insert(ipAddress);
    }

    /**
     * 批量插入
     * @param ipAddresses
     */
    public void insertList(List<IpAddress> ipAddresses){
        if(ipAddresses==null||ipAddresses.isEmpty()){
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IpAddressDao ipAddressDao = daoSession.getIpAddressDao();
        ipAddressDao.insertInTx(ipAddresses);
    }

    /**
     * 删除一条记录
     * @param ipAddress
     */
    public void deleteIpAddress(IpAddress ipAddress){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IpAddressDao ipAddressDao = daoSession.getIpAddressDao();
        ipAddressDao.delete(ipAddress);
    }
    /**
     * 删除一条记录
     * @param ipAddress
     */
    public void updateIpAddress(IpAddress ipAddress){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IpAddressDao ipAddressDao = daoSession.getIpAddressDao();
        ipAddressDao.update(ipAddress);
    }

    /**
     * 查询全部地址列表
     * @return
     */
    public List<IpAddress> queryUserList(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        IpAddressDao ipAddressDao = daoSession.getIpAddressDao();
        QueryBuilder<IpAddress> qb = ipAddressDao.queryBuilder();
        List<IpAddress> ipAddresses = qb.list();
        return ipAddresses;
    }
}
