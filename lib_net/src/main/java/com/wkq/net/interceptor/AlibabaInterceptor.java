package com.wkq.net.interceptor;


import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-22
 * <p>
 * 用途:
 */


public class AlibabaInterceptor extends MoveDbResponseBodyInterceptor {
    @Override
    Response intercept(Response response, String url, String body)  {

        JSONObject jsonObject = null;
        String data = null;
        if (response.isSuccessful()) {
            try {
                jsonObject = new JSONObject(body);
                int code = (int) jsonObject.get("code");
                String message = (String) jsonObject.get("message");
                if (code == 200) {
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("errorMessage", "成功");
                    jsonData.put("errorCode", "200");
                    jsonData.put("data", jsonObject);
                    jsonData.put("data", jsonObject.get("data"));
                    MediaType contentType = response.body().contentType();
                    ResponseBody responseBody = ResponseBody.create(contentType, jsonData.toString());
                    return response.newBuilder().body(responseBody).build();

                } else {
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("errorMessage", message);
                    jsonData.put("errorCode", code + "");
                    MediaType contentType = response.body().contentType();
                    ResponseBody responseBody = ResponseBody.create(contentType, jsonData.toString());
                    return response.newBuilder().body(responseBody).build();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                jsonObject = new JSONObject(body);
                JSONObject jsonData = new JSONObject();
                jsonData.put("errorMessage", response.code() + "");
                jsonData.put("errorCode", response.body().string());


                MediaType contentType = response.body().contentType();
                ResponseBody responseBody = ResponseBody.create(contentType, jsonData.toString());
                return response.newBuilder().body(responseBody).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return response;
    }
}
