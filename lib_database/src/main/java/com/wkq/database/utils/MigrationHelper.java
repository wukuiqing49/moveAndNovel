package com.wkq.database.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wkq.database.dao.AdTimeInfoDao;
import com.wkq.database.dao.DaoMaster;
import com.wkq.database.dao.ExceptionInfoDao;
import com.wkq.database.dao.HomeTopBannerInfoDao;
import com.wkq.database.dao.MoveDbDataHitoryDao;
import com.wkq.database.dao.MoveSearchHistoryDao;

import org.greenrobot.greendao.database.Database;


/**
 * Createdby PedroOkawa and modified by MBH on 16/08/16.
 */
public final class MigrationHelper extends DaoMaster.OpenHelper {

    public MigrationHelper(Context context, String name) {
        super(context, name);
    }

    public MigrationHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        new UpgradeHelper().migrate(db,
                ExceptionInfoDao.class, AdTimeInfoDao.class,
                HomeTopBannerInfoDao.class,
                MoveDbDataHitoryDao.class,
                MoveSearchHistoryDao.class
        );
    }
}