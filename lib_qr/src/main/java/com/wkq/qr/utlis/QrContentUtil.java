package com.wkq.qr.utlis;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.wkq.base.utils.AlertUtil;
import com.wkq.base.utlis.SimpleDES;
import com.wkq.base.utlis.UriUtil;
import com.wkq.qr.model.QrContentBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//
//import android.content.Context;
//import android.net.Uri;
//import android.text.TextUtils;
//
//import com.alibaba.android.arouter.launcher.ARouter;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import io.reactivex.disposables.Disposable;
//
///**
// * 作者: 吴奎庆
// * <p>
// * 时间: 2018/9/17
// * <p>
// * 简介: 扫码页面处理的util
// */
//
public class QrContentUtil {
    private static QrContentUtil instance = new QrContentUtil();

    //    private QrInfo qrInfo;
//    private Event event;
//    private Event qrContentCvent;
//
    public static QrContentUtil getInstance() {
        return instance == null ? new QrContentUtil() : instance;
    }

    //
    public QrContentBean getQrContentBean(String result) {
        QrContentBean bean = new QrContentBean();
        JSONObject jsonObject = null;


        try {
            if (!TextUtils.isEmpty(result) && result.startsWith("http://apps.pay.cnlive.com/cash-register/payauto/default?")) {
                Uri uri = Uri.parse(result);
                String darenID = UriUtil.getQueryParameterWithDecode(uri.getEncodedQuery(), "darenId");
                if (!TextUtils.isEmpty(darenID)) {
//                    QrPayBean qrInfo = new QrPayBean();
//                    qrInfo.setDarenId(darenID);
//                    Gson gson = new Gson();
//                    String content = gson.toJson(qrInfo);
//                    bean.setType("qrPay");
//                    bean.setContent(content);
                } else {
                    bean.setType("other");
                    bean.setContent(result);
                }

            } else if (isNetUrl(result)) {
                Uri uri = Uri.parse(result);
                String isWjjQr = UriUtil.getQueryParameterWithDecode(uri.getEncodedQuery(), "isWjjQr");
                if (TextUtils.isEmpty(isWjjQr)) {
                    bean.setType("other");
                    bean.setContent(result);
                    return bean;
                }
                String res = SimpleDES.ebotongDecrypto(isWjjQr, "cnliveIm");
                if (TextUtils.isEmpty(res)) {
                    bean.setType("other");
                    bean.setContent(result);
                    return bean;
                }
                jsonObject = new JSONObject(res);
                String type = (String) jsonObject.get("type");
                if (TextUtils.isEmpty(type)) {
                    bean.setType("other");
                    bean.setContent(result);
                    return bean;
                }
                switch (type) {
                    case "witnessQrType":
                        String obContent = (String) jsonObject.get("content");
                        if (TextUtils.isEmpty(obContent)) {
                            bean.setType("other");
                            bean.setContent(result);
                            return bean;
                        }
                        bean.setContent(obContent);
                        bean.setType(type);
                        break;
                    case "dazhongQrType":
                        String eventId = (String) jsonObject.get("eventId");
                        if (TextUtils.isEmpty(eventId)) {
                            bean.setType("other");
                            bean.setContent(result);
                            return bean;
                        }
                        bean.setContent(eventId);
                        bean.setType(type);
                        break;
                    case "starMoodShareType":
                        String homepageId = (String) jsonObject.get("homepageId");
                        if (TextUtils.isEmpty(homepageId)) {
                            bean.setType("other");
                            bean.setContent(result);
                            return bean;
                        }
                        bean.setContent(homepageId);
                        bean.setType(type);
                        break;

                    case "subscriShareType":
                        String homepageid = (String) jsonObject.get("homepageId");
                        if (TextUtils.isEmpty(homepageid)) {
                            bean.setType("other");
                            bean.setContent(result);
                            return bean;
                        }
                        bean.setContent(homepageid);
                        bean.setType(type);
                        break;

                    case "xiaoJiaSpeakerType":
                        String content = (String) jsonObject.get("xiajiaContent");
                        if (TextUtils.isEmpty(content)) {
                            bean.setType("other");
                            bean.setContent(result);
                            return bean;
                        }
                        bean.setContent(content);
                        bean.setType(type);
                        break;
                    case "groupQrType":
                        String qrContent = (String) jsonObject.get("content");
                        if (TextUtils.isEmpty(qrContent)) {
                            bean.setType("other");
                            bean.setContent(result);
                            return bean;
                        }
                        bean.setContent(qrContent);
                        bean.setType(type);

                        break;


                    default:
                        bean.setType("other");
                        bean.setContent(result);
                        return bean;

                }

            } else {
                jsonObject = new JSONObject(result);
                String obContent = (String) jsonObject.get("content");
                String jmContent = SimpleDES.ebotongDecrypto(obContent, "cnliveIm");
                String type = (String) jsonObject.get("type");
                if (!jsonObject.isNull("time")) {
                    Long time = (Long) jsonObject.get("time");
                    bean.setTime(time);
                }
                bean.setContent(jmContent);
                bean.setType(type);
            }


        } catch (Exception e) {
            bean = null;
        }
        return bean;
    }

    //
    public void getQrContent(Context mContext, String content, IdentifyTheQRCodeCallback successfulCallback) {
        if (TextUtils.isEmpty(content)) AlertUtil.showDeftToast(mContext, "请选择有效的二维码");

        Observable.create(new ObservableOnSubscribe<QrContentBean>() {
            @Override
            public void subscribe(ObservableEmitter<QrContentBean> emitter) throws Exception {
                QrContentBean bean = getQrContentBean(content);
                if (bean != null) {
                    emitter.onNext(bean);
                } else {
                    emitter.onError(new Throwable("非网家家二维码"));
                }
            }
        }).observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<QrContentBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(QrContentBean qrContentBean) {

                if (qrContentBean != null) {
                    jumpTest(mContext, qrContentBean.getContent(), successfulCallback);
                } else {

                }

                switch (qrContentBean.getType()) {
                    case "user":
//                        jumpQrPay(mContext, data.getContent(), successfulCallback);
                        break;

                    default:
                        jumpTest(mContext, qrContentBean.getContent(), successfulCallback);
                        break;

                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    private void jumpTest(Context mContext, String content, IdentifyTheQRCodeCallback successfulCallback) {

        if (!TextUtils.isEmpty(content)){
            successfulCallback.qRCodeCallback(true, "");
        }else {
            successfulCallback.qRCodeCallback(false, "未识别二维码");
        }
    }


//    /**
//     * 跳转扫码支付页面
//     *
//     * @param jmContent
//     * @param successfulCallback
//     */
//    private void jumpQrPay(Context mContext, String jmContent, IdentifyTheQRCodeCallback successfulCallback) {
//        Gson gson = new Gson();
//        try {
//            QrPayBean qrInfo = gson.fromJson(jmContent, QrPayBean.class);
//            QrRouters.startQrPay(mContext, 1001, qrInfo.getDarenId());
//            successfulCallback.qRCodeCallback(true, "");
//        } catch (Exception e) {
//            successfulCallback.qRCodeCallback(false, "请扫描网家家提供的二维码");
//        }
//
//    }


    /**
     * 判断是否是url
     *
     * @param url
     * @return
     */
    public static boolean isNetUrl(String url) {

        if (TextUtils.isEmpty(url)) return false;

        String UrlEndAppendNextChars = "[a-zA-z]+://[^\\s]*";

        // 邮箱验证规则
        // 编译正则表达式
        Pattern pattern = Pattern.compile(UrlEndAppendNextChars);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }



}
