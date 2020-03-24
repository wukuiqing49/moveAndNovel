package com.wkq.order.modlue.move.frame.presenter;

import android.app.Activity;
import android.util.Log;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.ApiRequest;
import com.wkq.net.BaseInfo;
import com.wkq.net.api.ApiMTimeSearch;
import com.wkq.net.api.ApiMoveDb;
import com.wkq.net.logic.Event;
import com.wkq.net.logic.Logic;
import com.wkq.net.logic.callback.DataCallback;
import com.wkq.net.model.MoveDataInfo;
import com.wkq.net.model.MoveMTimeSearchInfo;
import com.wkq.order.modlue.main.frame.view.SearchView;
import com.wkq.order.modlue.main.ui.activity.SearchActivity;
import com.wkq.order.modlue.move.frame.view.SearchMTiemView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:
 */
public class SearchMTimePresenter extends MvpBasePresenter<SearchMTiemView> {


    private Event event;

    public void searchData(Activity mActivity, String searchContent) {



//=true&Ajax_CallBackType=Mtime.Channel.Services&Ajax_CallBackMethod=GetSearchResult&Ajax_CrossDomain=1&Ajax_RequestUrl=http://search.mtime.com/search/?q=加勒比海盗&t=2020323225615504&Ajax_CallBackArgument0=加勒比海盗&Ajax_CallBackArgument1=0&Ajax_CallBackArgument2=974&Ajax_CallBackArgument3=0&Ajax_CallBackArgument4=1

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("Ajax_CallBack",  "true");

        requestMap.put("Ajax_CallBackType", "Mtime.Channel.Services");
        requestMap.put("Ajax_CallBackMethod", "GetSearchResult");
        requestMap.put("Ajax_CrossDomain", "1");
        requestMap.put("Ajax_RequestUrl", "http://search.mtime.com/search/?q="+searchContent);
        requestMap.put("Ajax_CallBackArgument0", searchContent);
        requestMap.put("Ajax_CallBackArgument1", "0");
        requestMap.put("Ajax_CallBackArgument2", "974");
        requestMap.put("Ajax_CallBackArgument3", "0");
        requestMap.put("Ajax_CallBackArgument4", "1");

        event = Logic.create(requestMap).action(new Logic.Action<Map<String, String>, BaseInfo<MoveMTimeSearchInfo>>() {
              @Override
              public Disposable action(Map<String, String> data, DataCallback<BaseInfo<MoveMTimeSearchInfo>> callback) {
                  return ApiRequest.serviceMTimeSearch(ApiMTimeSearch.class, apiMoveDb -> apiMoveDb.search(data)).subscribe(mActivity, callback);
              }
          }).<BaseInfo<MoveMTimeSearchInfo>>event().setFailureCallback((state, message) -> {
              Log.e("", "");
              if (getView()!=null)getView().hindLoading();

          }).setSuccessCallback(data -> {
            if (getView()!=null)getView().hindLoading();
             if(getView()!=null)getView().setSearchData(data);

          }).start();

    }

    public void cancel() {
        if (event != null) event.cencel();
    }
}
