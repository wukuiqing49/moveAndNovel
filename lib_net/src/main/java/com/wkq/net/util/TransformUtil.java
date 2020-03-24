package com.wkq.net.util;

import com.google.gson.Gson;

public class TransformUtil {

    public static <T> T transform(Class<T> clazz, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return gson.fromJson(json, clazz);
    }
}
