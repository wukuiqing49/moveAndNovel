package com.wkq.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.wkq.database.bean.BookCacheDao;
import com.wkq.order.application.OrderApplication;


/**
 * Created by zia on 2018/5/6.
 */
@Database(entities = {com.wkq.database.bean.LocalBook.class, com.wkq.database.bean.NetBook.class, com.wkq.database.bean.BookMark.class, com.wkq.database.bean.BookCache.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "book_db";

    public abstract com.wkq.database.bean.LocalBookDao localBookDao();

    public abstract com.wkq.database.bean.NetBookDao netBookDao();

    public abstract com.wkq.database.bean.BookMarkDao bookMarkDao();

    public abstract BookCacheDao bookCacheDao();

    private static AppDatabase INSTANCE;

//    private static final Migration m_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("create table IF NOT EXISTS bookMark (markId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, siteName TEXT, bookName TEXT,position INTEGER)");
//        }
//    };

//    private static final Migration m_2_3 = new Migration(2,3) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("alter table netBook add column time timestamp not null default CURRENT_TIMESTAMP");
//        }
//    };

    public static AppDatabase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(OrderApplication.getContext(), AppDatabase.class, DATABASE_NAME)
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.
                    .allowMainThreadQueries()
//                    .addMigrations(m_2_3)
                    .fallbackToDestructiveMigration()//版本不一致直接重建数据库=_=
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
