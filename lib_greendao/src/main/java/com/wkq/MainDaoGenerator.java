package com.wkq;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(6, "com.wkq.database.dao");
        addDefautDao(schema);
        addAdTimeInfo(schema);
        addTopBanneInfo(schema);
        addHomeMoveDbDataHitory(schema);
        addMoveSearchHistory(schema);
        addHUser(schema);
        new DaoGenerator().generateAll(schema, args[0]);
    }


    //
    private static void addDefautDao(Schema schema) {
        Entity uploadVideo = schema.addEntity("ExceptionInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addIdProperty().autoincrement().primaryKey();
        uploadVideo.addStringProperty("ErrorTag").notNull();
        uploadVideo.addStringProperty("ErrorMessage").notNull();
    }

    private static void addAdTimeInfo(Schema schema) {
        Entity uploadVideo = schema.addEntity("AdTimeInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addStringProperty("AdTimeKey").primaryKey();
        uploadVideo.addStringProperty("AdTime").notNull();
        uploadVideo.addIntProperty("AdClickCount").notNull();
    }


    private static void addTopBanneInfo(Schema schema) {

        Entity uploadVideo = schema.addEntity("HomeTopBannerInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addStringProperty("key").primaryKey();
        uploadVideo.addStringProperty("data").notNull();
    }


    private static void addHomeMoveDbDataHitory(Schema schema) {

        Entity uploadVideo = schema.addEntity("MoveDbDataHitory");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addStringProperty("key").primaryKey();
        uploadVideo.addStringProperty("data").notNull();

    }
    private static void addHUser(Schema schema) {

        Entity uploadVideo = schema.addEntity("UserInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addStringProperty("userID").primaryKey();
        uploadVideo.addStringProperty("userPhoneNum").notNull();
        uploadVideo.addStringProperty("userPwd").notNull();
        uploadVideo.addStringProperty("userIMEI").notNull();
        uploadVideo.addStringProperty("userState").notNull();

    }

    private static void addMoveSearchHistory(Schema schema) {

        Entity uploadVideo = schema.addEntity("MoveSearchHistory");
        uploadVideo.setHasKeepSections(true);
//        uploadVideo.addIntProperty("moveId").primaryKey();
        uploadVideo.addStringProperty("moveName").primaryKey();
//        uploadVideo.addStringProperty("movePoster").notNull();
//
//        uploadVideo.addStringProperty("moveType").notNull();
//        uploadVideo.addStringProperty("moveTime").notNull();

    }


}
