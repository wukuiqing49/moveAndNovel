package wkq.com.lib_move.model;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-02
 * <p>
 * 用途:
 */


public class MoveTopInfo {
    //电影名字
    String moveName;

    //主要演员
    String moveStarring;
    //电影海报
    String moveCover;

    //电影详情地址 或者i d
    String moveHref;
    //电影评分
    String moveScore;
    //页码数
    int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getMoveScore() {
        return moveScore;
    }

    public void setMoveScore(String moveScore) {
        this.moveScore = moveScore;
    }

    public String getMoveHref() {
        return moveHref;
    }

    public void setMoveHref(String moveHref) {
        this.moveHref = moveHref;
    }

    public String getMoveName() {
        return moveName;
    }

    public String getMoveStarring() {
        return moveStarring;
    }

    public void setMoveStarring(String moveStarring) {
        this.moveStarring = moveStarring;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }


    public String getMoveCover() {
        return moveCover;
    }

    public void setMoveCover(String moveCover) {
        this.moveCover = moveCover;
    }

}
