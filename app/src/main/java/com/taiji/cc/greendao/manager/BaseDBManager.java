package com.taiji.cc.greendao.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.taiji.cc.greendao.db.DaoMaster;


/**
 * Created by panho on 2016/9/2.
 */
public abstract class BaseDBManager {

    protected final static String dbName = "cubecare_db";

    protected Context context;

    protected DaoMaster.DevOpenHelper openHelper;

    protected BaseDBManager(Context context){
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
    }

    /**
     * 获取可读数据库
     * @return db 可读数据库
     */
    protected SQLiteDatabase getReadableDatabase(){
        if(openHelper==null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     * @return db 可写数据库
     */
    protected SQLiteDatabase getWritableDatabase(){
        if(openHelper==null){
            openHelper = new DaoMaster.DevOpenHelper(context,dbName,null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

}
