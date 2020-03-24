package wkq.com.lib_move.model;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-02
 * <p>
 * 用途:
 */


public class MoveInfo {
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

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getMoveCover() {
        return moveCover;
    }

    public void setMoveCover(String moveCover) {
        this.moveCover = moveCover;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
}
