package com.wkq.order.modlue.novel.ui.activity.preview;

/**
 * Created by Chu on 2017/8/15.
 */

public interface OnPageChangeListener {
    void onChapterChange(int pos);

    //页码改变
    void onPageCountChange(int count);

    //页面改变
    void onPageChange(int pos);
}
