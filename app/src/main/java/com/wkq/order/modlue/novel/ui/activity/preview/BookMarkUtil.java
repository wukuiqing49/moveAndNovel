package com.wkq.order.modlue.novel.ui.activity.preview;

import android.util.Log;

import com.wkq.database.AppDatabase;
import com.wkq.database.bean.BookMark;
import com.wkq.database.bean.BookMarkDao;


/**
 * Created by zia on 2018/11/4.
 */
public class BookMarkUtil {
    public static void insertOrUpdate(int position, String bkName, String siteName) {
        BookMarkDao bookMarkDao = AppDatabase.getAppDatabase().bookMarkDao();
        BookMark bm = bookMarkDao.getBookMark(bkName, siteName);
        if (bm == null) {
            Log.d("BookMarkUtil", "bm == null,insert position == " + position);
            BookMark bookMark = new BookMark();
            bookMark.setPosition(position);
            bookMark.setBookName(bkName);
            bookMark.setSiteName(siteName);
            bookMarkDao.insert(bookMark);
        } else {
            Log.d("BookMarkUtil", "bm != null,update position == " + position);
            bm.setPosition(position);
            bookMarkDao.insert(bm);
        }
    }

    public static int getMarkPosition(String bkName, String siteName) {
        BookMarkDao bookMarkDao = AppDatabase.getAppDatabase().bookMarkDao();
        BookMark bm = bookMarkDao.getBookMark(bkName, siteName);
        if (bm == null){
            return 0;
        }
        return bm.getPosition();
    }
}
