package wkq.com.lib_move.utlis;

import wkq.com.lib_move.site.MTimeSite;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:  根据html获取数据的工具类
 */


public class MoveHtmlUtlis {

    /**
     * 获取时光网首页数据
     * @param moveDataCallBack
     * @param <T>
     */
    public static <T> void getMTimeHomeData(MoveDataCallBack<T> moveDataCallBack) {
        MTimeSite.getHomeContent(moveDataCallBack);
    } /**
     * 获取时光网首页数据
     * @param moveDataCallBack
     * @param <T>
     */
    public static <T> void getMTimeMoveDetail(String moveUrl,MoveDataCallBack<T> moveDataCallBack) {
        MTimeSite.getMoveDetail(moveUrl,moveDataCallBack);
    }
}
