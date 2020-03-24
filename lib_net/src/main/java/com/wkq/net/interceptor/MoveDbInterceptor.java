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


public class MoveDbInterceptor extends MoveDbResponseBodyInterceptor {
    @Override
    Response intercept(Response response, String url, String body) throws IOException {

        JSONObject jsonObject = null;
        if (response.isSuccessful()) {
            try {
                jsonObject=new JSONObject(body);
                JSONObject jsonData = new JSONObject();
                jsonData.put("errorMessage", "成功");
                jsonData.put("errorCode", "200");
                jsonData.put("data", jsonObject);
                MediaType contentType = response.body().contentType();
                ResponseBody responseBody = ResponseBody.create(contentType, jsonData.toString());

                return response.newBuilder().body(responseBody).build();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            try {
                jsonObject=new JSONObject(body);
                JSONObject jsonData = new JSONObject();
                jsonData.put("errorMessage", response.code()+"");
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
