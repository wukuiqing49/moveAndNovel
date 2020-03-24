package wkq.com.lib_move.model;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public class MoveDetailInfo {
    //电影名字
    String moveName;
    //电影时间
    String createTime;
    //作者
    String authorName;
    //电影海报
    String moveCover;
    //电影类型
    String moveType;
    //电影详情地址 或者i d
    String moveHref;
    //电影评分
    String moveScore;
    //简介
    String moveDesc;
    //获奖
    String movePrize;
    //主要演员
    List<String> moveActors;
    //电影评论
    List<String> moveComment;

}