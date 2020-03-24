package com.wkq.baseLib.utlis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class UUIDUtils {
    public UUIDUtils() {
    }

    private static String a() {
        if (b()) {
            return a(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.com.cnlive.id/uuid.dat");
        } else {
            String var0 = UUID.randomUUID().toString().replaceAll("-", "");
            a(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.com.cnlive.id/uuid.dat", var0);
            return var0;
        }
    }

    private static boolean b() {
        File var0 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.com.cnlive.id");
        if (var0.exists()) {
            File var1 = new File(var0, "uuid.dat");
            return var1.exists();
        } else {
            if (var0.mkdirs()) {
                Log.e("UUIDUtils", "mkdirs failure.");
            }

            return false;
        }
    }

    private static String a(String var0) {
        try {
            File var1 = new File(var0);
            InputStreamReader var2 = new InputStreamReader(new FileInputStream(var1), "UTF-8");
            BufferedReader var3 = new BufferedReader(var2);

            String var4;
            String var5;
            for (var4 = ""; (var5 = var3.readLine()) != null; var4 = var4 + var5) {
                ;
            }

            return var4;
        } catch (Exception var6) {
            Log.e("UUIDUtils", "file read error.", var6);
            return "uuid";
        }
    }

    private static void a(String var0, String var1) {
        try {
            FileOutputStream var2 = new FileOutputStream(var0, true);
            OutputStreamWriter var3 = new OutputStreamWriter(var2, "utf-8");
            var3.write(var1);
            var3.flush();
            var3.close();
            var2.close();
        } catch (Exception var4) {
            Log.e("UUIDUtils", "file write error.", var4);
        }

    }

    @SuppressLint("MissingPermission")
    private static String getPhoneUUID(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String tmDevice, tmSerial, androidId;
            tmDevice = "" + tm.getDeviceId();
            tmSerial = "" + tm.getSimSerialNumber();
            androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
            return deviceUuid.toString().replaceAll("-", "");
        } catch (Exception e) {
            Log.e("UUIDUtil", "", e);
        }
        return "";
    }

    private static String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private static String initUUID(Context context) {
        String v1 = getPhoneUUID(context);
        if (!TextUtils.isEmpty(v1)) return v1;
        String v2 = getRandomUUID();
        if (!TextUtils.isEmpty(v2)) return v2;
        String v3 = a();
        if (!TextUtils.isEmpty(v3)) return v3;
        return "uuid";
    }

    public static String getUUID(Context context) {
        SharedPreferencesHelper sph = SharedPreferencesHelper.getInstance(context);
        if (sph == null) {
            return "";
        } else {
            String cacheUUID = sph.getValue("cnlive_uuid");
            if (TextUtils.isEmpty(cacheUUID)) {
                String uuid = initUUID(context).concat("_").concat(String.valueOf(System.currentTimeMillis()));
                sph.setValue("cnlive_uuid", uuid);
                return uuid;

            } else return cacheUUID;
        }
    }
}