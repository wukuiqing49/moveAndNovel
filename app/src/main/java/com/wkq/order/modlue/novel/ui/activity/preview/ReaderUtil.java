package com.wkq.order.modlue.novel.ui.activity.preview;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.util.List;

/**
 * Created by zia on 2019/4/7.
 */
public class ReaderUtil {
    public static void openFile(File file, Context mContext) {
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //设置intent的Action属性
            intent.setAction(Intent.ACTION_VIEW);
            //获取文件file的MIME类型
            String type = getMIMEType(file);
            //设置intent的data和Type属性。android 7.0以上crash,改用provider
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Uri fileUri = FileUtil.INSTANCE.getFileUri(mContext, file);//android 7.0以上
                intent.setDataAndType(fileUri, type);
                grantUriPermission(mContext, fileUri, intent);
            } else {
                intent.setDataAndType(FileUtil.INSTANCE.getFileUri(mContext, file), type);
            }
            //跳转
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void grantUriPermission(Context context, Uri fileUri, Intent intent) {
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resInfoList) {
            String packageName = resolveInfo.activityInfo.packageName;
            context.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     *
     * @param file
     */
    public static String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名 */
        String end = fName.substring(dotIndex).toLowerCase();
        Log.e("ReaderUtil", "getMIMEType: " + end);
        return getMIMEType(end);
    }

    public static String getMIMEType(String suffix) {
        String type = "*/*";
        if (suffix.equals("")) return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (String[] strings : MIME_MapTable) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (suffix.equals(strings[0])) {
                type = strings[1];
                break;
            }
        }
        return type;
    }


    private static final String[][] MIME_MapTable = {
            //{后缀名， MIME类型}
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".gif", "image/gif"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".log", "text/plain"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".txt", "text/plain"},
            {".epub", "application/epub+zip"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };
}
