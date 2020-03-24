package com.wkq.net.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUtil {
    //服务器地址-获取时间
    public static final String URL_TIME_SERVICE = "https://api.cnlive.com/open/api2/timestamp";
    //服务器=时间戳缓存
    public static long service_time = 0;
    private static final long cache_time = 4 * 60_000;

    private static final String TAG = "SignUtil";

    /**
     * 接口签名方法,返回rxJava观察者 (特殊接口单独调用)
     *
     * @param params 接口参数Map数组
     * @return rxJava Observable<Map<String, String>>
     */
    public static Observable<Map<String, String>> getSignMapObservable(final Map<String, String> params) {
        return Observable.create((ObservableOnSubscribe<Map<String, String>>) oe -> {
            oe.onNext(getOpenSignMap(params));
            oe.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 接口签名方法,返回rxJava观察者 (特殊接口单独调用)
     *
     * @param params 接口参数Map数组
     * @return rxJava Observable<Map<String, String>>
     */
    public static Observable<Map<String, RequestBody>> getRequestBodySignMapObservable(Map<String, String> params) {
        return getRequestBodySignMapObservable(params, false);
    }

    public static Observable<Map<String, RequestBody>> getRequestBodySignMapObservable(Map<String, String> params, boolean valueUrlencode) {
        return Observable.create((ObservableEmitter<Map<String, RequestBody>> oe) -> {
            oe.onNext(orderRequestBody(getOpenSignMap(params, valueUrlencode)));
            oe.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static Map<String, RequestBody> orderRequestBody(Map<String, String> map) {
        HashMap<String, RequestBody> tempMap = new LinkedHashMap<>();
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());

        Collections.sort(infoIds, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            if (item != null && item.getValue() != null)
                tempMap.put(item.getKey(), RequestBody.create(MediaType.parse("text/plain"), item.getValue()));
        }
        return tempMap;
    }

    public static Map<String, String> getOpenSignMap(Map<String, String> params) {
        return getOpenSignMap(params, false, false);
    }

    public static Map<String, String> getOpenSignMap(Map<String, String> params, boolean valueUrlencode) {
        return getOpenSignMap(params, valueUrlencode, false);
    }

    public static Map<String, String> getOpenSignMap(Map<String, String> params, boolean valueUrlencode, boolean replace) {
        if (params == null) return new HashMap<>();
//
//        if (replace || !params.containsKey("platform_id")) {
//            params.put("platform_id", AppConfig.getAppPackeg());
//        }
//        if (AppConfig.isDebug() && (replace || !params.containsKey("test"))) {
//            params.put("test", "0");
//        }
//        if (replace || !params.containsKey("appId")) {
//            params.put("appId", AppConfig.getAppId());
//        }
//        if (replace || !params.containsKey("plat")) {
//            params.put("plat", "a");
//        }
//        if (replace || !params.containsKey("app_channel_id")) {
//            params.put("app_channel_id", AppConfig.getAppChannel());
//        }
//        if (replace || !params.containsKey("timestamp")) {
//            long time = getTime();
//            params.put("timestamp", String.valueOf(time / 1000));
//        }
//        if (replace || !params.containsKey("ver")) {
//            params.put("ver", AppConfig.getAppVersion());
//        }
//        if (replace || !params.containsKey("uuid")) {
//            params.put("uuid", AppConfig.getAppUUID());
//        }
//        if (replace || !params.containsKey("loginSid")) {
//            params.put("loginSid", AppConfig.getSid());
//        }
//
//        //拼接推广渠道
//        if (replace || !params.containsKey("promoFrom")) {
//            if (!TextUtils.isEmpty(AppConfig.getPromoFrom())) {
//                params.put("promoFrom", AppConfig.getPromoFrom());
//            }
//        }

        Map<String, String> map = order(params, valueUrlencode);

        if (map.containsKey("sign")) {
            map.remove("sign");
        }

//        String signValue = SHA1Util
//                .SHA1Digest(mapJoin(map, valueUrlencode).concat("&key=").concat(AppConfig.getAppKey()))
//                .toUpperCase(Locale.getDefault());
//        map.put("sign", signValue);
        return map;
    }


    public static String getOpenSignString(Map<String, String> params) {
        return getOpenSignString(params, false, false);
    }

    public static String getOpenSignString(Map<String, String> params, boolean valueUrlencode) {
        return getOpenSignString(params, valueUrlencode, false);
    }

    public static String getOpenSignString(Map<String, String> params, boolean valueUrlencode, boolean replace) {
//        if (params == null) return "";
//
//        if (replace || !params.containsKey("platform_id")) {
//            params.put("platform_id", AppConfig.getAppPackeg());
//        }
//        if (AppConfig.isDebug() && (replace || !params.containsKey("test"))) {
//            params.put("test", "0");
//        }
//        if (replace || !params.containsKey("appId")) {
//            params.put("appId", AppConfig.getAppId());
//        }
//        if (replace || !params.containsKey("plat")) {
//            params.put("plat", "a");
//        }
//        if (replace || !params.containsKey("app_channel_id")) {
//            params.put("app_channel_id", AppConfig.getAppChannel());
//        }
//        if (replace || !params.containsKey("timestamp")) {
//            long time = getTime();
//            params.put("timestamp", String.valueOf(time / 1000));
//        }
//        if (replace || !params.containsKey("ver")) {
//            params.put("ver", AppConfig.getAppVersion());
//        }
//        if (replace || !params.containsKey("uuid")) {
//            params.put("uuid", AppConfig.getAppUUID());
//        }
//        if (replace || !params.containsKey("androidVersion")) {
//            params.put("androidVersion", Build.VERSION.SDK_INT + "");
//        }
//
//        //拼接推广渠道
//        if (replace || !params.containsKey("promoFrom")) {
//            if (!TextUtils.isEmpty(AppConfig.getPromoFrom())) {
//                params.put("promoFrom", AppConfig.getPromoFrom());
//            }
//        }
//
//        String signParam = mapParam(params);
//
//        Map<String, String> map = order(params, valueUrlencode);
//
//        if (map.containsKey("sign")) {
//            map.remove("sign");
//        }
//
//        String signValue = SHA1Util
//                .SHA1Digest(mapJoin(map, valueUrlencode).concat("&key=").concat(AppConfig.getAppKey()))
//                .toUpperCase(Locale.getDefault());
//
//        return signParam.concat("&sign=").concat(signValue);
//    }
//
//    public static Map<String, String> getNetPPSignString(Map<String, String> params) {
//
//        if (params == null) return null;
//
//        if (!params.containsKey("platFormId")) {
//            params.put("platFormId", AppConfig.getAppPackeg());
//        }
//        if (!params.containsKey("timestamp")) {
//            params.put("timestamp", String.valueOf(getTime() / 1000));
//        }
//
//        String mapJoin = mapJoin(order(params, false), false);
//
//        Map<String, String> map = new HashMap<>();
////        map.put("appId", AppConfig.getAppId());
////        map.put("sign", CNLiveEncipher.encryptSHA1(AppConfig.getAppId(), mapJoin).replaceAll("[\r\n]", ""));
////        map.put("p1", CNLiveEncipher.encryptAES(AppConfig.getAppId(), mapJoin).replaceAll("[\r\n]", ""));
//
//        return map;
//    }
//
//    public static Map<String, String> getNetPPPaySignString(Map<String, String> params) {
//
//        if (params == null) return null;
//
//        if (!params.containsKey("platFormId")) {
//            params.put("platFormId", AppConfig.getAppPackeg());
//        }
//        if (!params.containsKey("timestamp")) {
//            params.put("timestamp", String.valueOf(getTime() / 1000));
//        }
//
//        String publicKey = "";
//        String randomAesKey = "";
//
//        if (params.containsKey("publicKey")) {
//            publicKey = CNLiveEncipher.decryptAES(AppConfig.getAppId(), params.get("publicKey"));
//            params.remove("publicKey");
//        }
//        if (params.containsKey("randomAesKey")) {
//            randomAesKey = CNLiveEncipher.decryptAES(AppConfig.getAppId(), params.get("randomAesKey"));
//            params.remove("randomAesKey");
//        }
//
//        String mapJoin = mapJoin(order(params, false), false);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("appId", AppConfig.getAppId());
//        map.put("sign", CNLiveEncipher.encryptSHA1(AppConfig.getAppId(), mapJoin).replaceAll("[\r\n]", ""));
//        map.put("p1", CNLiveEncipher.encryptAES(randomAesKey, mapJoin).replaceAll("[\r\n]", ""));
//        map.put("p2", CNLiveEncipher.encryptRSA(publicKey, "aesKey=" + randomAesKey).replaceAll("[\r\n]", ""));

        return "";
    }

    private static String mapParam(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            stringBuilder.append(key).append("=").append(map.get(key)).append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public static long getTime() {
        long local_time = System.currentTimeMillis();

        if (Math.abs(service_time - local_time) < cache_time) return local_time;

        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Request request = new Request.Builder().url(URL_TIME_SERVICE).build();

            Response response = okHttpClient.newCall(request).execute();

            Headers headers = response.headers();

            Date date = headers.getDate("Date");
            return date == null ? local_time : (service_time = date.getTime());
        } catch (Exception ignored) {
        }
        return local_time;
    }

    private static Map<String, String> order(Map<String, String> map, boolean valueUrlencode) {
        HashMap<String, String> tempMap = new LinkedHashMap<>();
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());

        Collections.sort(infoIds, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));

//        for (int i = 0; i < infoIds.size(); i++) {
//            Map.Entry<String, String> item = infoIds.get(i);
//            tempMap.put(item.getKey(), valueUrlencode ? StringUtils.encode(item.getValue()) : item.getValue());
//        }
        return tempMap;
    }

    public static String mapJoin(Map<String, String> map, boolean valueUrlencode) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            if (map.get(key) != null && !"".equals(map.get(key))) {
                stringBuilder.append(key)
                        .append("=")
                        .append(valueUrlencode ? map.get(key).replace("+", "%20") : map.get(key))
                        .append("&");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
