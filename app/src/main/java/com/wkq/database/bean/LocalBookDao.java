package com.wkq.database.bean;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by zia on 2018/11/2.
 */
@Dao
public interface LocalBookDao {

    @Query("select * from localBook")
    List<com.wkq.database.bean.LocalBook> getLocalBooks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(com.wkq.database.bean.LocalBook localBook);

    @Query("delete from localBook where bookName = :bkname and siteName = :stname")
    void delete(String bkname, String stname);

    @Update
    void update(LocalBook localBook);
}
