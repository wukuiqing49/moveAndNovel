package wkq.com.lib_move.model;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-02
 * <p>
 * 用途:
 */


public class MTimeHomeBean {

    String titleName;

    List<MoveInfo> moveInfos;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public List<MoveInfo> getMoveInfos() {
        return moveInfos;
    }

    public void setMoveInfos(List<MoveInfo> moveInfos) {
        this.moveInfos = moveInfos;
    }


}
