package com.wkq.database.bean;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by zia on 2018/11/4.
 */
@Dao
public interface BookMarkDao {

    @Query("select * from bookMark where bookName = :bkname and siteName = :stname")
    com.wkq.database.bean.BookMark getBookMark(String bkname, String stname);

    @Query("select position from bookMark where bookName = :bkName and siteName = :siteName")
    int getPosition(String bkName, String siteName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(com.wkq.database.bean.BookMark bookMark);

    @Delete
    void delete(com.wkq.database.bean.BookMark bookMark);

    @Query("delete from bookMark where bookName = :bkname and siteName = :stname")
    void delete(String bkname, String stname);

    @Update
    void update(BookMark bookMark);
}
