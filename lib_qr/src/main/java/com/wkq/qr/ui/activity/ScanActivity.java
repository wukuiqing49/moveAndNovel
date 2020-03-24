package com.wkq.qr.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;

import androidx.annotation.NonNull;

import com.wkq.base.frame.activity.MvpBindingActivity;
import com.wkq.base.utlis.PermissionChecker;
import com.wkq.media.PickerConfig;
import com.wkq.media.entity.Media;
import com.wkq.qr.R;
import com.wkq.qr.databinding.ActivityScanQrBinding;
import com.wkq.qr.frame.presenter.ScanMvpPresenter;
import com.wkq.qr.frame.view.ScanMvpView;
import com.wkq.qr.utlis.QrContentUtil;

import java.io.File;
import java.util.ArrayList;

import cn.bertsir.zbar.Qr.ScanResult;
import cn.bertsir.zbar.Qr.Symbol;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.ScanCallback;
import cn.bertsir.zbar.utils.QRUtils;


/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/19
 * <p>
 * 简介:
 */
public class ScanActivity extends MvpBindingActivity<ScanMvpView, ScanMvpPresenter, ActivityScanQrBinding> {

    //闪光灯是否开启
    public boolean isFlight;
    //type
    public String type;
    //七牛压缩的文件
    public File luBanFile;
    //用户页面的返回码
    public int userResult = 10010;
    //是否支持双指放大
    boolean isFinger_zoom = true;
    public SoundPool soundPool;
    public static int REQUEST_CODE_START_MOMENT_READ_WRITE_CAMERA = 10015;
    public static int REQUEST_CODE_PERMISSION_CAMERA = 10013;

    private float oldDist = 5f;


    public static void startScanActivity(Context context) {
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        initScanView();
        return R.layout.activity_scan_qr;
    }

    /**
     * 初始换 二维码识别的配置你参数
     */
    private void initScanView() {
        Symbol.scanType = QrConfig.TYPE_QRCODE;
        Symbol.scanFormat = QrConfig.BARCODE_EAN13;
        Symbol.is_only_scan_center = true;
    }

    /**
     * 处理手指放大的逻辑
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isFinger_zoom) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = QRUtils.getInstance().getFingerSpacing(event);

                    break;
                case MotionEvent.ACTION_MOVE:
                    if (event.getPointerCount() == 2) {
                        float newDist = QRUtils.getInstance().getFingerSpacing(event);
                        if (newDist > oldDist) {
                            binding.cp.handleZoom(true);
                        } else if (newDist < oldDist) {
                            binding.cp.handleZoom(false);
                        }
                        oldDist = newDist;
                    }
                    break;
            }
        }
        return super.onTouchEvent(event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getMvpView() != null) getMvpView().initView();
        boolean hasPermission = PermissionChecker.checkPermissions(this
                , new String[]{Manifest.permission.CAMERA}
                , REQUEST_CODE_START_MOMENT_READ_WRITE_CAMERA, R.string.CAMERA);
        if (hasPermission) getMvpView().startScan();

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (getMvpView() != null) getMvpView().onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getMvpView() != null) getMvpView().stopScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getMvpView() != null) getMvpView().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.cp != null) {
            binding.cp.setScanCallback(resultCallback);
            binding.cp.start();
        }
        binding.sv.onResume();

    }

    private ScanCallback resultCallback = new ScanCallback() {
        @Override
        public void onScanResult(ScanResult result) {
            if (binding.cp != null) {
                binding.cp.setFlash(false);
            }
//            getPresenter().checkQr(result);
            getMvpView().vibrate();
        }

    };

    @Override
    protected void onDestroy() {
        if (luBanFile != null && luBanFile.exists()) getMvpView().clearLuBan(luBanFile);
        super.onDestroy();
        if (binding.cp != null) {
            binding.cp.setFlash(false);
            binding.cp.stop();
        }
        if (getMvpView() != null) getMvpView().onDestory();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == PickerConfig.DEFAULT_RESULT_CODE) {
            ArrayList<Media> select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
            if (select != null && select.size() > 0) {
                String path = select.get(0).path;
                if (!TextUtils.isEmpty(path)&&getPresenter() != null)getPresenter().IdentifyQRCode(this,path);

            } else getMvpView().showMessage("请选择图片");
        } else if (requestCode == userResult) {
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
            boolean hasPermission = PermissionChecker.onRequestPermissionsResult(this, requestCode, permissions, grantResults,
                    false, R.string.dialog_imagepicker_permission_sdcard_nerver_ask_message)[0];
            if (hasPermission) {
                if (getMvpView() != null) getMvpView().showAum(true);
            }
        } else if (requestCode == REQUEST_CODE_START_MOMENT_READ_WRITE_CAMERA) {
            boolean hasPermission = PermissionChecker.onRequestPermissionsResult(this
                    , requestCode, permissions, grantResults,
                    false, R.string.request_camera)[0];
            if (hasPermission) getMvpView().startScan();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("where", type);
    }
}

