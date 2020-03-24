package com.wkq.order.modlue.move.frame.view;

import android.Manifest;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cnlive.largeimage.FileTypeUtil;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utils.MD5Util;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.AndroidQUtil;
import com.wkq.baseLib.utlis.PermissionChecker;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.move.ui.ProcessImgsActivity;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-03
 * <p>
 * 用途:
 */


public class ProcessImgsView implements MvpView {
    ProcessImgsActivity mActivity;

    public ProcessImgsView(ProcessImgsActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setDarkMode(mActivity);
        mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mActivity.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        mActivity.binding.tvSave.setOnClickListener(view -> {
            boolean hasPermission = PermissionChecker.checkPermissions(mActivity
                    , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
                    , mActivity.REQUEST_CODE_PERMISSION_CODE, R.string.string_permission_save_pic);
            if (hasPermission) {
                saveImg();
            }
        });
        mActivity.binding.tvCancel.setOnClickListener(view -> {
            mActivity.finish();
        });



    }

    public void saveImg() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                if (mActivity.imgUrl.contains("http")) {
                    File file = Glide.with(mActivity).asFile().load(mActivity.imgUrl).submit().get();
                    if (file != null && file.exists() && file.length() > 0) {


                        emitter.onNext(file.getAbsolutePath());
                    } else {
                        emitter.onError(new Throwable("非法图片地址"));
                    }
                } else {
                    emitter.onError(new Throwable("非法图片地址"));
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

                if (!TextUtils.isEmpty(s)) {
                    saveImageToGallery(new File(s));
                } else {
                    showMessage("数据异常");
                }

            }

            @Override
            public void onError(Throwable e) {
                showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 复制本地图片到指定文件夹下
     *
     * @param
     */

    public void saveImageToGallery(File file) {
        try {

            String md5 = MD5Util.getMD5(file);
            String type = FileTypeUtil.getFileType(file.getAbsolutePath(), true);
            String extension;//初始文件扩展名
            if (TextUtils.isEmpty(type)) {
                extension = ".jpg";
            } else {
                extension = "." + type;
            }
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    Bitmap bitmap = Glide.with(mActivity).asBitmap().load(file).submit().get();
                    String url = AndroidQUtil.saveBitmapToFile(mActivity, bitmap, md5 + extension, "网家家");
                    if (!TextUtils.isEmpty(url)) {
                        emitter.onNext("保存成功");
                    } else {
                        emitter.onError(new Throwable("文件保存失败"));
                    }

                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(String s) {
                    showSucessfulMessage("保存成功");

                }

                @Override
                public void onError(Throwable e) {
                    showMessage(e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });

        } catch (Exception e) {
            showMessage("图片保存异常");

        }
    }

    public void showSucessfulMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showSuccessToast(mActivity, message);
        mActivity.finish();
    }

    public void showMessage(String message) {
        if (mActivity == null) return;
        AlertUtil.showDeftToast(mActivity, message);

    }
}
