package com.wkq.qr.frame.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.base.utils.AlertUtil;
import com.wkq.base.utils.FileUtils;
import com.wkq.base.utlis.PermissionChecker;
import com.wkq.base.utlis.StatusBarUtil;
import com.wkq.media.ImagePicker;
import com.wkq.media.PickerConfig;
import com.wkq.media.utils.ImagePickerComUtils;
import com.wkq.qr.R;
import com.wkq.qr.ui.activity.ScanActivity;
import com.wkq.qr.utlis.IdentifyTheQRCodeCallback;
import com.wkq.qr.utlis.QrContentUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.bertsir.zbar.QrConfig;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/19
 * <p>
 * 简介:
 */
public class ScanMvpView implements MvpView {
    ScanActivity mContext;

    private static final int image_item_max_size = 20 * 1024 * 1024;
    int CORNER_COLOR = Color.parseColor("#1AAD19");
    int LINE_FAST = 1500;
    private Disposable disposable;

    public ScanMvpView(ScanActivity mActivity) {
        this.mContext = mActivity;
    }

    public void initView() {
        StatusBarUtil.setColor(mContext, mContext.getResources().getColor(R.color.white), 0);

        mContext.binding.cp.setFlash(false);
        mContext.binding.sv.setType(QrConfig.TYPE_QRCODE);

        mContext.binding.sv.setCornerColor(CORNER_COLOR);
        mContext.binding.sv.setLineSpeed(LINE_FAST);
        mContext.binding.sv.setLineColor(CORNER_COLOR);

        mContext.soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        mContext.soundPool.load(mContext, cn.bertsir.zbar.R.raw.qrcode, 1);
    }

    public void vibrate() {
        Vibrator vibrator = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        if (mContext.soundPool != null)
            mContext.soundPool.play(1, 1, 1, 0, 0, 1);
    }

    /**
     * 开始扫描
     */
    public void startScan() {
        if (mContext == null) return;
        if (mContext.binding.cp != null) {
            mContext.binding.cp.start();
        }
        if (mContext.binding.cp != null) {
            mContext.binding.sv.startScan();
        }

    }

    //页面停止
    public void stopScan() {
        if (mContext == null) return;
        if (mContext == null) return;
        if (mContext.binding.cp != null) {
            mContext.binding.cp.stop();
        }
        if (mContext.binding.cp != null) {
            mContext.binding.sv.stop();
        }
        mContext.binding.rlLoading.setVisibility(View.VISIBLE);
    }


    /**
     * 清除鲁班压缩后的文件
     *
     * @param luBanFile
     */
    public void clearLuBan(File luBanFile) {
        if (luBanFile != null && luBanFile.getAbsolutePath().contains("current")) {
            FileUtils.deleteFile(luBanFile);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(luBanFile);
            intent.setData(uri);
            mContext.sendBroadcast(intent);
        }
    }


    public void processQrContent(String qrContent) {
        if (mContext == null) return;
        QrContentUtil.getInstance().getQrContent(mContext, qrContent, new IdentifyTheQRCodeCallback() {

            @Override
            public void qRCodeCallback(boolean isIdentify, String message) {
                mContext.binding.rlLoading.setVisibility(View.GONE);
                if (!isIdentify) {
                    showMessage(message);
                    disposable = Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
                        startScan();
                    });
                } else {
                    mContext.finish();
                }
            }
        });
    }


    //开始的生命周期
    public void onStart() {
        if (mContext == null) return;
        try {
        } catch (Exception e) {
            showMessage("扫描异常");
        }
    }

    public void onPause() {
        if (mContext == null) return;
        if (mContext.binding.cp != null) {
            mContext.binding.cp.stop();
        }
        if (mContext.binding.cp != null) {
            mContext.binding.sv.stop();
        }

        mContext.binding.sv.onPause();
        mContext.binding.cp.setFlash(false);
        mContext.binding.ivFlashlightKg.setBackgroundResource(R.drawable.gz_shoudian);
        mContext.binding.tvFlashlightArlt.setText("轻触点亮");
        mContext.binding.tvFlashlightArlt.setTextColor(mContext.getResources().getColor(R.color.white));
        mContext.isFlight = false;
    }

    public void onDestory() {
        if (disposable != null) disposable.dispose();
    }


    /**
     * 展示错误信息
     *
     * @param content
     */
    public void showMessage(String content) {
        if (mContext == null) return;
        AlertUtil.showDeftToast(mContext, content);
    }


    public static boolean checkPermissions(Context context, String[] PERMISSION) {
        if (Build.VERSION.SDK_INT >= 23) for (String permisson : PERMISSION) {
            if (TextUtils.isEmpty(permisson)) continue;
            if (ActivityCompat.checkSelfPermission(context, permisson) == 0) continue;
            return false;
        }
        return true;
    }


    private String[] showAumPermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};


    /**
     * 打开相册
     */
    public void showAum(boolean checkOnly) {
        if (mContext == null) return;
        if (!ImagePickerComUtils.isSdExist()) {
            showMessage("SD卡不存在");
            return;
        }
        boolean hasPermission = checkOnly ?
                checkPermissions(mContext, showAumPermission) :
                PermissionChecker.checkPermissions((Activity) mContext
                        , showAumPermission
                        , mContext.REQUEST_CODE_PERMISSION_CAMERA, R.string.dialog_imagepicker_permission_sdcard_message);

        if (hasPermission) {
            new ImagePicker.Builder()
                    .maxNum(1)
                    .maxImageSize(image_item_max_size)
                    .setSelectGif(false)
                    .selectMode(PickerConfig.PICKER_IMAGE)
                    .cachePath(Environment.getExternalStorageDirectory() + "/strike/file/")
                    .isFriendCircle(true)
                    .builder()
                    .start((Activity) mContext, 200, PickerConfig.DEFAULT_RESULT_CODE);
        }
    }
}
