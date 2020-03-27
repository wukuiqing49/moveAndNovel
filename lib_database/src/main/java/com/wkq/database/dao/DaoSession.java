package com.wkq.database.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.wkq.database.dao.ExceptionInfo;
import com.wkq.database.dao.AdTimeInfo;
import com.wkq.database.dao.HomeTopBannerInfo;
import com.wkq.database.dao.MoveDbDataHitory;
import com.wkq.database.dao.MoveSearchHistory;
import com.wkq.database.dao.UserInfo;

import com.wkq.database.dao.ExceptionInfoDao;
import com.wkq.database.dao.AdTimeInfoDao;
import com.wkq.database.dao.HomeTopBannerInfoDao;
import com.wkq.database.dao.MoveDbDataHitoryDao;
import com.wkq.database.dao.MoveSearchHistoryDao;
import com.wkq.database.dao.UserInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig exceptionInfoDaoConfig;
    private final DaoConfig adTimeInfoDaoConfig;
    private final DaoConfig homeTopBannerInfoDaoConfig;
    private final DaoConfig moveDbDataHitoryDaoConfig;
    private final DaoConfig moveSearchHistoryDaoConfig;
    private final DaoConfig userInfoDaoConfig;

    private final ExceptionInfoDao exceptionInfoDao;
    private final AdTimeInfoDao adTimeInfoDao;
    private final HomeTopBannerInfoDao homeTopBannerInfoDao;
    private final MoveDbDataHitoryDao moveDbDataHitoryDao;
    private final MoveSearchHistoryDao moveSearchHistoryDao;
    private final UserInfoDao userInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        exceptionInfoDaoConfig = daoConfigMap.get(ExceptionInfoDao.class).clone();
        exceptionInfoDaoConfig.initIdentityScope(type);

        adTimeInfoDaoConfig = daoConfigMap.get(AdTimeInfoDao.class).clone();
        adTimeInfoDaoConfig.initIdentityScope(type);

        homeTopBannerInfoDaoConfig = daoConfigMap.get(HomeTopBannerInfoDao.class).clone();
        homeTopBannerInfoDaoConfig.initIdentityScope(type);

        moveDbDataHitoryDaoConfig = daoConfigMap.get(MoveDbDataHitoryDao.class).clone();
        moveDbDataHitoryDaoConfig.initIdentityScope(type);

        moveSearchHistoryDaoConfig = daoConfigMap.get(MoveSearchHistoryDao.class).clone();
        moveSearchHistoryDaoConfig.initIdentityScope(type);

        userInfoDaoConfig = daoConfigMap.get(UserInfoDao.class).clone();
        userInfoDaoConfig.initIdentityScope(type);

        exceptionInfoDao = new ExceptionInfoDao(exceptionInfoDaoConfig, this);
        adTimeInfoDao = new AdTimeInfoDao(adTimeInfoDaoConfig, this);
        homeTopBannerInfoDao = new HomeTopBannerInfoDao(homeTopBannerInfoDaoConfig, this);
        moveDbDataHitoryDao = new MoveDbDataHitoryDao(moveDbDataHitoryDaoConfig, this);
        moveSearchHistoryDao = new MoveSearchHistoryDao(moveSearchHistoryDaoConfig, this);
        userInfoDao = new UserInfoDao(userInfoDaoConfig, this);

        registerDao(ExceptionInfo.class, exceptionInfoDao);
        registerDao(AdTimeInfo.class, adTimeInfoDao);
        registerDao(HomeTopBannerInfo.class, homeTopBannerInfoDao);
        registerDao(MoveDbDataHitory.class, moveDbDataHitoryDao);
        registerDao(MoveSearchHistory.class, moveSearchHistoryDao);
        registerDao(UserInfo.class, userInfoDao);
    }
    
    public void clear() {
        exceptionInfoDaoConfig.clearIdentityScope();
        adTimeInfoDaoConfig.clearIdentityScope();
        homeTopBannerInfoDaoConfig.clearIdentityScope();
        moveDbDataHitoryDaoConfig.clearIdentityScope();
        moveSearchHistoryDaoConfig.clearIdentityScope();
        userInfoDaoConfig.clearIdentityScope();
    }

    public ExceptionInfoDao getExceptionInfoDao() {
        return exceptionInfoDao;
    }

    public AdTimeInfoDao getAdTimeInfoDao() {
        return adTimeInfoDao;
    }

    public HomeTopBannerInfoDao getHomeTopBannerInfoDao() {
        return homeTopBannerInfoDao;
    }

    public MoveDbDataHitoryDao getMoveDbDataHitoryDao() {
        return moveDbDataHitoryDao;
    }

    public MoveSearchHistoryDao getMoveSearchHistoryDao() {
        return moveSearchHistoryDao;
    }

    public UserInfoDao getUserInfoDao() {
        return userInfoDao;
    }

}
