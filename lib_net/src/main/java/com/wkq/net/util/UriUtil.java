package com.wkq.net.util;

import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Granzon on 2018/9/15.
 */

public class UriUtil {
    private final String link;
    private final Uri uri;
    private Map<String, String> map;

    public UriUtil(String link) {
        this.link = link == null ? "" : link;
        this.uri = Uri.parse(link);
        this.map = initParameter();
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(link)) return false;
        if (uri.isOpaque()) return false;
        return true;
    }

    private Map<String, String> initParameter() {
        Map<String, String> map = new HashMap<>();
        if (isValid()) for (String name : uri.getQueryParameterNames())
            map.put(name, uri.getQueryParameter(name));
        return map;
    }

    public void addQueryParameter(String key, String value) {
        if (TextUtils.isEmpty(key) || value == null) return;
        if (map != null && map.get(key) == null) map.put(key, value);
    }

    public void setQueryParameter(String key, String value) {
        if (TextUtils.isEmpty(key) || value == null) return;
        if (map != null) map.put(key, value);
    }

    public interface SignCallback {
        void onCall(String url);
    }

    private boolean canSign(String... signPaths) {
        if (signPaths == null || signPaths.length == 0) return true;
        if (TextUtils.isEmpty(link)) return true;
        for (String path : signPaths) if (path != null && link.contains(path)) return true;
        return false;
    }

    private String getPathString() {
        if (map == null) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            stringBuilder.append(key).append("=").append(map.get(key)).append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    public Disposable sign(SignCallback callback, String... signPaths) {
        return Observable
                .create((ObservableOnSubscribe<String>) emitter -> emitter.onNext(canSign(signPaths) ?
                        SignUtil.getOpenSignString(map, false, true) :
                        getPathString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    if (TextUtils.isEmpty(query)) {
                        if (callback != null) callback.onCall(link);
                    } else {
                        Uri.Builder builder = new Uri.Builder();
                        builder.scheme(uri.getScheme());
                        builder.encodedAuthority(uri.getAuthority());
                        builder.encodedPath(uri.getPath() == null ? "" : uri.getPath().trim());
                        builder.encodedQuery(query);
                        builder.encodedFragment(uri.getFragment());
                        String url = Uri.encode(builder.build().toString(), "-![.:/,%?&=]");
                        if (callback != null) callback.onCall(url);
                    }
                });
    }


    public String build() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(uri.getScheme());
        builder.encodedAuthority(uri.getAuthority());
        builder.encodedPath(uri.getPath());

        StringBuilder stringBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            stringBuilder.append(key).append("=").append(map.get(key)).append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        builder.encodedQuery(stringBuilder.toString());
        builder.encodedFragment(uri.getFragment());
        return builder.build().toString();
    }

    public static String getQueryParameterWithDecode(String query, String key) {
        if (TextUtils.isEmpty(query) || TextUtils.isEmpty(key)) return "";

        int start = 0;
        do {
            int nextAmpersand = query.indexOf('&', start);
            int end = nextAmpersand != -1 ? nextAmpersand : query.length();
            int separator = query.indexOf('=', start);

            if (separator > end || separator == -1) separator = end;
            if (separator - start == key.length() && query.regionMatches(start, key, 0, key.length()))
                if (separator == end) return "";
                else return Uri.decode(query.substring(separator + 1, end));
            if (nextAmpersand != -1) start = nextAmpersand + 1;
            else break;
        } while (true);
        return null;
    }

    public static String getQueryParameter(String query, String key) {
        if (TextUtils.isEmpty(query) || TextUtils.isEmpty(key)) return "";

        int start = 0;
        do {
            int nextAmpersand = query.indexOf('&', start);
            int end = nextAmpersand != -1 ? nextAmpersand : query.length();
            int separator = query.indexOf('=', start);

            if (separator > end || separator == -1) separator = end;
            if (separator - start == key.length() && query.regionMatches(start, key, 0, key.length()))
                if (separator == end) return "";
                else return Uri.decode(query.substring(separator + 1, end));
            if (nextAmpersand != -1) start = nextAmpersand + 1;
            else break;
        } while (true);
        return null;
    }
}
