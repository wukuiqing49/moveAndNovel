package com.wkq.database.utils;

import android.content.Context;
import android.text.TextUtils;

import com.wkq.database.dao.AdTimeInfo;
import com.wkq.database.dao.AdTimeInfoDao;
import com.wkq.database.dao.HomeTopBannerInfo;
import com.wkq.database.dao.HomeTopBannerInfoDao;
import com.wkq.database.dao.MoveDbDataHitory;
import com.wkq.database.dao.MoveDbDataHitoryDao;
import com.wkq.database.dao.MoveSearchHistory;
import com.wkq.database.dao.MoveSearchHistoryDao;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介: 数据库操作工具基类
 */
public class DataBaseUtils {
    /**
     * 获取广告点击次数的 数据
     *
     * @param context
     * @param key     主键id
     * @return
     */
    public static AdTimeInfo getAdTimeInfo(Context context, String key) {

        AdTimeInfoDao adTimeInfoDao = DaoHelper.getInstance(context).getAdTimeDao();
        return adTimeInfoDao.load(key);
    }

    /**
     * 存储更新 广告点击的数据
     *
     * @param context
     * @param key     主键
     * @param time    时间
     */
    public static void updateAdTimeInfo(Context context, String key, String time) {
        AdTimeInfoDao adTimeInfoDao = DaoHelper.getInstance(context).getAdTimeDao();
        AdTimeInfo adTimeInfo = adTimeInfoDao.load(key);
        if (adTimeInfo == null) {
            adTimeInfo = new AdTimeInfo();
            adTimeInfo.setAdTimeKey(key);
            adTimeInfo.setAdTime(time);
            adTimeInfo.setAdClickCount(1);
            adTimeInfoDao.insert(adTimeInfo);
        } else {
            adTimeInfo.setAdTime(time);
            adTimeInfo.setAdClickCount(adTimeInfo.getAdClickCount() + 1);
            adTimeInfoDao.insertOrReplace(adTimeInfo);
        }
    }

    /**
     * c插入数据
     *
     * @param context
     * @param key
     * @param time
     * @param count
     */
    public static void insertAdTimeInfo(Context context, String key, String time, int count) {
        AdTimeInfoDao adTimeInfoDao = DaoHelper.getInstance(context).getAdTimeDao();
        AdTimeInfo adTimeInfo = adTimeInfoDao.load(key);
        if (adTimeInfo == null) {
            adTimeInfo = new AdTimeInfo();
            adTimeInfo.setAdTimeKey(key);
            adTimeInfo.setAdTime(time);
            adTimeInfo.setAdClickCount(count);
            adTimeInfoDao.insert(adTimeInfo);
        }
    }

    /**
     * 插入首页banner数据
     *
     * @param context
     * @param key
     * @param data
     */
    public static void insertHomeTopData(Context context, String key, String data) {

        HomeTopBannerInfoDao dao = DaoHelper.getInstance(context).getHomeTopDao();
        HomeTopBannerInfo info = new HomeTopBannerInfo();
        info.setKey(key);
        info.setData(data);

        dao.insertOrReplace(info);

    }

    /**
     * 获取首页banner数据
     *
     * @param context
     * @param key
     * @return
     */

    public static HomeTopBannerInfo getHomeTopData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) return null;

        HomeTopBannerInfoDao dao = DaoHelper.getInstance(context).getHomeTopDao();
        HomeTopBannerInfo info = dao.load(key);
        return info;


    }

    /**
     * 插入首页列表数据
     *
     * @param context
     * @param key
     * @param data
     */
    public static void insertMoveDbHistoryData(Context context, String key, String data) {

        MoveDbDataHitoryDao dao = DaoHelper.getInstance(context).getMoveDbDataHistoryDao();
        MoveDbDataHitory info = new MoveDbDataHitory();
        info.setKey(key);
        info.setData(data);

        dao.insertOrReplace(info);

    }

    /**
     * 插入获取首页时光html获取的数据
     *
     * @param context
     * @param key
     * @param data
     */
    public static void insertMoveHtmlHome(Context context, String key, String data) {

        MoveDbDataHitoryDao dao = DaoHelper.getInstance(context).getMoveDbDataHistoryDao();
        MoveDbDataHitory info = new MoveDbDataHitory();
        info.setKey(key);
        info.setData(data);

        dao.insertOrReplace(info);

    }


    /**
     * 获取首页列表数据
     *
     * @param context
     * @param key
     * @return
     */
    public static MoveDbDataHitory getMoveHtmlHome(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) return null;
        MoveDbDataHitoryDao dao = DaoHelper.getInstance(context).getMoveDbDataHistoryDao();
        MoveDbDataHitory info = dao.load(key);
        return info;
    }


    /**
     * 获取首页列表数据
     *
     * @param context
     * @param key
     * @return
     */
    public static MoveDbDataHitory getMoveDbHistoryData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) return null;

        MoveDbDataHitoryDao dao = DaoHelper.getInstance(context).getMoveDbDataHistoryDao();
        MoveDbDataHitory info = dao.load(key);
        return info;

    }

    /**
     * 获取首页列表数据
     *
     * @param context
     * @return
     */
    public static void insertHistoryData(Context context, String moveName) {

        if (TextUtils.isEmpty(moveName)) return;

        MoveSearchHistoryDao dao = DaoHelper.getInstance(context).getMoveSearchHistoryDao();

        if (dao.load(moveName) != null) return;

        MoveSearchHistory info = new MoveSearchHistory();

        info.setMoveName(TextUtils.isEmpty(moveName) ? "" : moveName);

        dao.insertOrReplace(info);
    }

    /**
     * 获取查询历史
     *
     * @param context
     * @return
     */
    public static List<MoveSearchHistory> getMoveHistoryData(Context context) {

        MoveSearchHistoryDao dao = DaoHelper.getInstance(context).getMoveSearchHistoryDao();

        return dao == null ? null : dao.queryBuilder().limit(10).list();

    }
}
