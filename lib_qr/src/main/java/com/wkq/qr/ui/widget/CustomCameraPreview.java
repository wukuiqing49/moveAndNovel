package com.wkq.qr.ui.widget;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import cn.bertsir.zbar.CameraManager;
import cn.bertsir.zbar.ScanCallback;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/6/1
 * <p>
 * 简介:
 */
public class CustomCameraPreview extends FrameLayout implements SurfaceHolder.Callback {

    private CameraManager mCameraManager;
    private CustomCameraScanAnalysis mPreviewCallback;
    private SurfaceView mSurfaceView;

    public CustomCameraPreview(Context context) {
        this(context, null);
    }

    public CustomCameraPreview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCameraPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mCameraManager = new CameraManager(context);
        mPreviewCallback = new CustomCameraScanAnalysis(context);
    }

    /**
     * Set Scan results callback.
     *
     * @param callback {@link ScanCallback}.
     */
    public void setScanCallback(ScanCallback callback) {
        mPreviewCallback.setScanCallback(callback);
    }

    /**
     * Camera start preview.
     */
    public boolean start() {
        try {
            mCameraManager.openDriver();
        } catch (Exception e) {
            Toast.makeText(getContext(), "摄像头权限被拒绝！", Toast.LENGTH_SHORT).show();
            return false;
        }
        mPreviewCallback.onStart();

        if (mSurfaceView == null) {
            mSurfaceView = new SurfaceView(getContext());
            addView(mSurfaceView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            SurfaceHolder holder = mSurfaceView.getHolder();
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        startCameraPreview(mSurfaceView.getHolder());
        return true;
    }

    /**
     * Camera stop preview.
     */
    public void stop() {
        removeCallbacks(mAutoFocusTask);
        mPreviewCallback.onStop();

        mCameraManager.stopPreview();
        mCameraManager.closeDriver();
    }

    private void startCameraPreview(SurfaceHolder holder) {
        try {
            mCameraManager.startPreview(holder, mPreviewCallback);
            mCameraManager.autoFocus(mFocusCallback);
        } catch (Exception e) {
            e.printStackTrace();
            //如果异常延迟200ms再试
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mCameraManager.autoFocus(mFocusCallback);
                }
            }, 200);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (holder.getSurface() == null) {
            return;
        }
        mCameraManager.stopPreview();
        startCameraPreview(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private Camera.AutoFocusCallback mFocusCallback = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            postDelayed(mAutoFocusTask, 1000);
        }
    };

    private Runnable mAutoFocusTask = new Runnable() {
        public void run() {
            mCameraManager.autoFocus(mFocusCallback);
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        stop();
        super.onDetachedFromWindow();
    }

    public void setFlash() {
        mCameraManager.setFlash();
    }

    public void setFlash(boolean open) {
        mCameraManager.setFlash(open);
    }

    public void setZoom(float zoom){
        mCameraManager.setCameraZoom(zoom);
    }

    public void handleZoom(boolean isZoomIn){
        mCameraManager.handleZoom(isZoomIn);
    }
}