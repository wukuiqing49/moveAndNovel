package com.wkq.database.bean;

import androidx.annotation.Nullable;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;



import java.util.List;



/**
 * Created by zia on 2018/11/2.
 */
@Dao
public interface NetBookDao {
    @Query("select * from netBook order by time desc")
    List<com.wkq.database.bean.NetBook> getNetBooks();

    @Query("select * from netBook where bookName = :bkName and siteName = :stName")
    @Nullable
    com.wkq.database.bean.NetBook getNetBook(String bkName, String stName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(com.wkq.database.bean.NetBook netBook);

    @Delete
    void delete(com.wkq.database.bean.NetBook netBook);

    @Query("delete from netBook where bookName = :bkname and siteName = :stName")
    void delete(String bkname, String stName);

    @Update
    void update(com.wkq.database.bean.NetBook netBook);
}
