package com.wkq.order.modlue.developer.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wkq.baseLib.utlis.TimerHelper;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.logic.callback.FailureCallback;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.net.model.MoveDbMoveImagesInfo;
import com.wkq.net.model.MoveDbPopularInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ActivityApiTestBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import wkq.com.lib_move.utlis.MoveDataCallBack;
import wkq.com.lib_move.model.MTimeHomeBean;
import wkq.com.lib_move.site.MTimeSite;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class ApiTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityApiTestBinding binding;
    TimerHelper timerHelper;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ApiTestActivity.class);
        Activity activity = (Activity) context;
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_api_test);

        binding.setOnclick(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_top:
                getMoveTop();
                break;
            case R.id.tv_images:
                getMoveImgs();
                break;
            case R.id.tv_search:
                getMoveSearch();
                break;
            case R.id.tv_upcoming:
                getUpComing();
                break;
            case R.id.tv_detail:
                getMoveDetail();
                break;
            case R.id.tv_popular:
                getMoveDbPopularInfo();
                break;
            case R.id.tv_movie:
                getMoveForHtml();
                break;

        }

    }

    private void getMoveForHtml() {

        MTimeSite.getMTimeTop(new MoveDataCallBack() {
            @Override
            public void onFail(String message) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onLoading() {

            }
        });


//        MTimeSite.getHomeContent(new MoveDataCallBack<List<MTimeHomeBean>>() {
//            @Override
//            public void onFail(String message) {
//
//            }
//
//            @Override
//            public void onSuccess(List<MTimeHomeBean> lists) {
//
//                Log.e("", "");
//
//            }
//
//            @Override
//            public void onLoading() {
//
//            }
//        });

    }

    private void getUpComing() {

        HashMap<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");
        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getUpComing(data)).subscribe(ApiTestActivity.this, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback(new FailureCallback() {
            @Override
            public void onFailure(int state, String message) {
                Log.e("", "");
            }
        }).setSuccessCallback(data -> {
            Log.e("", "");

        }).start();

    }

    int count = 0;

    private void getMoveTop() {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");

        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getTopRated(data)).subscribe(ApiTestActivity.this, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");

        }).setSuccessCallback(data -> {
            count += 1;
        }).start();

    }


    private void getMoveImgs() {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");

        Logic.create("51533").action(new Logic.Action<String, BaseInfo<MoveDbMoveImagesInfo>>() {
            @Override
            public Disposable action(String data, DataCallback<BaseInfo<MoveDbMoveImagesInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getMoveImages(data)).subscribe(ApiTestActivity.this, callback);
            }
        }).<BaseInfo<MoveDbMoveImagesInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");

        }).setSuccessCallback(data -> {
            Log.e("", "");

        }).start();

    }

    private void getMoveSearch() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");


        requestMap.put("query", "让子弹飞");

        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.searchMovies(data)).subscribe(ApiTestActivity.this, callback);
            }
        }).<BaseInfo<MoveDataInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");

        }).setSuccessCallback(data -> {
            Log.e("", "");

        }).start();

    }

    private void getMoveDbPopularInfo() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("page", 1 + "");


        Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveDataInfo>>() {
            @Override
            public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveDataInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getPopular(data)).subscribe(ApiTestActivity.this, callback);
            }
        }).<BaseInfo<MoveDbPopularInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");

        }).setSuccessCallback(data -> {
            Log.e("", "");

        }).start();

    }

    private void getMoveDetail() {
//        similar_movies,alternative_titles,images
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("append_to_response", "similar_movies,alternative_titles,images");
        Logic.create("419704").action(new Logic.Action<String, BaseInfo<MoveDbMoveDetailInfo>>() {
            @Override
            public Disposable action(String data, DataCallback<BaseInfo<MoveDbMoveDetailInfo>> callback) {
                return ApiRequest.serviceMoveDb(ApiMoveDb.class, apiMoveDb -> apiMoveDb.getMovieDetail(data, requestMap)).subscribe(ApiTestActivity.this, callback);
            }
        }).<BaseInfo<MoveDbMoveDetailInfo>>event().setFailureCallback((state, message) -> {
            Log.e("", "");
        }).setSuccessCallback(data -> {
            Log.e("", "");
        }).start();

    }
}
