package com.wkq.qr.frame.presenter;

import android.text.TextUtils;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.qr.frame.view.ScanMvpView;
import com.wkq.qr.ui.activity.ScanActivity;

import cn.bertsir.zbar.utils.QRUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/19
 * <p>
 * 简介:
 */
public class ScanMvpPresenter extends MvpBasePresenter<ScanMvpView> {


    public void IdentifyQRCode(ScanActivity context, String path) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            //优先使用zbar识别一次二维码
            String content=null;
            content = QRUtils.getInstance().decodeQRcode(path);
            if (!TextUtils.isEmpty(path)&&TextUtils.isEmpty(content)){
                content = QRUtils.getInstance().decodeQRcodeByZxing(path);
            }
            if (content != null && content.length() > 0) {
                emitter.onNext(content);
            } else {
                emitter.onNext("");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        checkQr(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    /**
     * 校验扫描出来的瞬狙
     *
     * @param result
     */
    public void checkQr(String result) {
        if (getView() == null) return;
        if (!TextUtils.isEmpty(result)) {
            if (getView() != null) getView().stopScan();
            if (getView() != null) getView().processQrContent(result);

        } else {
            getView().showMessage("未识别二维码");
        }

    }

}
