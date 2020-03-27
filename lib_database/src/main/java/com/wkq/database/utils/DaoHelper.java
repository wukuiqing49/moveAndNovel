package com.wkq.database.utils;

import android.content.Context;


import com.wkq.database.dao.AdTimeInfoDao;
import com.wkq.database.dao.DaoMaster;
import com.wkq.database.dao.DaoSession;
import com.wkq.database.dao.ExceptionInfoDao;
import com.wkq.database.dao.HomeTopBannerInfoDao;
import com.wkq.database.dao.MoveDbDataHitoryDao;
import com.wkq.database.dao.MoveSearchHistoryDao;
import com.wkq.database.dao.UserInfoDao;

import org.greenrobot.greendao.database.Database;


/**
 * Created by xiansong on 2017/9/8.
 */

public class DaoHelper {
    private static char[] databasePassword = "com.wkq.qr".toCharArray();
    private static DaoHelper instance;
    private Database db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    public DaoHelper(Context context) {
        DaoMaster.OpenHelper helper = new MigrationHelper(context, "wy-db", null);
        db = helper.getEncryptedWritableDb(databasePassword);
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public static synchronized DaoHelper getInstance(Context ctx) {
        if (instance == null)
            instance = new DaoHelper(ctx.getApplicationContext());
        return instance;
    }

    public static synchronized DaoHelper updateInstance(Context ctx) {
        instance = new DaoHelper(ctx.getApplicationContext());
        return instance;
    }


    public AdTimeInfoDao getAdTimeDao() {
        return daoSession.getAdTimeInfoDao();
    }

    public HomeTopBannerInfoDao getHomeTopDao() {
        return daoSession.getHomeTopBannerInfoDao();
    }

    public MoveDbDataHitoryDao getMoveDbDataHistoryDao() {
        return daoSession.getMoveDbDataHitoryDao();
    }
  public MoveSearchHistoryDao getMoveSearchHistoryDao() {
        return daoSession.getMoveSearchHistoryDao();
    }
 public UserInfoDao getUserDao() {
        return daoSession.getUserInfoDao();
    }


}
