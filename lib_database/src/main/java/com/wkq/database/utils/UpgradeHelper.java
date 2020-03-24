package com.wkq.database.utils;

import android.database.Cursor;
import android.text.TextUtils;


import com.wkq.database.dao.DaoMaster;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cnlive20080010 on 2018/8/27.
 */

public class UpgradeHelper {

    private static final String CONVERSION_CLASS_NOT_FOUND_EXCEPTION = "MIGRATION HELPER - CLASS DOESN'T MATCH WITH THE CURRENT PARAMETERS";

    public void migrate(final Database db, final Class<? extends AbstractDao<?, ?>>... daoClasses) {
        generateTempTables(db, daoClasses);
        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);
        restoreData(db, daoClasses);
    }

    private void generateTempTables(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            try {
                DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
                String divider = "";
                String tableName = daoConfig.tablename;
                String tempTableName = daoConfig.tablename.concat("_TEMP");
                ArrayList<String> properties = new ArrayList<>();

                StringBuilder createTableStringBuilder = new StringBuilder();

                createTableStringBuilder.append("CREATE TABLE ").append(tempTableName).append(" (");

                for (int j = 0; j < daoConfig.properties.length; j++) {
                    String columnName = daoConfig.properties[j].columnName;

                    if (getColumns(db, tableName).contains(columnName)) {
                        properties.add(columnName);

                        String type = null;

                        try {
                            type = getTypeByClass(daoConfig.properties[j].type);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                        createTableStringBuilder.append(divider).append(columnName).append(" ").append(type);

                        if (daoConfig.properties[j].primaryKey) {
                            createTableStringBuilder.append(" PRIMARY KEY");
                        }

                        divider = ",";
                    }
                }
                createTableStringBuilder.append(");");

                db.execSQL(createTableStringBuilder.toString());

                StringBuilder insertTableStringBuilder = new StringBuilder();

                insertTableStringBuilder.append("INSERT INTO ").append(tempTableName).append(" (");
                insertTableStringBuilder.append(TextUtils.join(",", properties));
                insertTableStringBuilder.append(") SELECT ");
                insertTableStringBuilder.append(TextUtils.join(",", properties));
                insertTableStringBuilder.append(" FROM ").append(tableName).append(";");

                db.execSQL(insertTableStringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
    }

    private void restoreData(Database db, Class<? extends AbstractDao<?, ?>>... daoClasses) {
        for (int i = 0; i < daoClasses.length; i++) {
            try {
                DaoConfig daoConfig = new DaoConfig(db, daoClasses[i]);
                String tableName = daoConfig.tablename;
                String tempTableName = daoConfig.tablename.concat("_TEMP");
                ArrayList<String> properties = new ArrayList();

                for (int j = 0; j < daoConfig.properties.length; j++) {
                    String columnName = daoConfig.properties[j].columnName;

                    if (getColumns(db, tempTableName).contains(columnName)) {
                        properties.add(columnName);
                    }
                }

                StringBuilder insertTableStringBuilder = new StringBuilder();

                insertTableStringBuilder.append("INSERT INTO ").append(tableName).append(" (");
                insertTableStringBuilder.append(TextUtils.join(",", properties));
                insertTableStringBuilder.append(") SELECT ");
                insertTableStringBuilder.append(TextUtils.join(",", properties));
                insertTableStringBuilder.append(" FROM ").append(tempTableName).append(";");

                StringBuilder dropTableStringBuilder = new StringBuilder();

                dropTableStringBuilder.append("DROP TABLE ").append(tempTableName);

                db.execSQL(insertTableStringBuilder.toString());
                db.execSQL(dropTableStringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                continue;
            }
        }
    }

    private String getTypeByClass(Class<?> type) throws Exception {
        if (type.equals(String.class)) {
            return " TEXT";
        }
        if (type.equals(Integer.class) || type.equals(int.class) ||
                type.equals(byte.class) || type.equals(Byte.class) ||
                type.equals(short.class) || type.equals(Short.class)) {
            return " INTEGER DEFAULT 0";
        }

        if (type.equals(Double.class) || type.equals(double.class)) {
            return "DOUBLE DEFAULT 0";
        }

        if(type.equals(float.class) || type.equals(Float.class)){
            return "FLOAT DEFAULT 0";
        }

        if (type.equals(Long.class) || type.equals(long.class)) {
            return " Long DEFAULT 0";
        }
        if (type.equals(Boolean.class) || type.equals(boolean.class)) {
            return " NUMERIC DEFAULT 0";
        }

        Exception exception = new Exception(CONVERSION_CLASS_NOT_FOUND_EXCEPTION.concat(" - Class: ").concat(type.toString()));
        exception.printStackTrace();
        throw exception;
    }

    private static List<String> getColumns(Database db, String tableName) {

        List<String> columns = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " limit 1", null);
            if (cursor != null) {
                columns = new ArrayList<>(Arrays.asList(cursor.getColumnNames()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return columns;
    }
}

