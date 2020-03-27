package com.wkq.order.modlue.login.frame.view;


import android.app.Dialog;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.supplier.IIdentifierListener;
import com.bun.supplier.IdSupplier;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertDialogUtils;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.PermissionChecker;
import com.wkq.database.dao.UserInfo;
import com.wkq.database.utils.DataBaseUtils;
import com.wkq.order.R;
import com.wkq.order.modlue.login.ui.activity.LoginActivity;
import com.wkq.order.modlue.login.ui.activity.SplashActivity;
import com.wkq.order.modlue.main.ui.activity.ContactDeveloperActivity;
import com.wkq.order.modlue.main.ui.activity.HomeActivity;
import com.wkq.order.utils.Constant;
import com.wkq.order.utils.DeviceUtlis;
import com.wkq.order.utils.MoveDbDataSaveUtlis;
import com.wkq.order.utils.StatusBarUtil;

import java.util.List;


import static com.wkq.order.utils.Constant.DEBUG_USE_TIME;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_BANNER_KEY;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_DATA_KEY;
import static com.wkq.order.utils.Constant.MOVE_DB_HOME_HTML_MTIME;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashView implements MvpView {
    SplashActivity mActivity;

    String TAG = "广告";


    public SplashView(SplashActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        if (System.currentTimeMillis() < DEBUG_USE_TIME) {
            checkPermissions();
        } else {
            showMessage("已过期,请联系开发者");
            ContactDeveloperActivity.startActivity(mActivity);
            mActivity.finish();
        }
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);

        initData();

//        jumpHomeActivity();


    }

    private void initData() {
        MoveDbDataSaveUtlis.putType(mActivity);
        //banner
        if (DataBaseUtils.getHomeTopData(mActivity, MOVE_DB_HOME_BANNER_KEY) == null) {
            DataBaseUtils.insertHomeTopData(mActivity, MOVE_DB_HOME_BANNER_KEY, Constant.MOVE_INIT_BANNER);
        }
        //banner  MT  时光网的初始化数据
        if (DataBaseUtils.getMoveHtmlHome(mActivity, MOVE_DB_HOME_HTML_MTIME) == null) {
            DataBaseUtils.insertMoveHtmlHome(mActivity, MOVE_DB_HOME_HTML_MTIME, Constant.MOVE_INIT_HOME_HTML);
        }
        //初始化欢迎页面
        if (DataBaseUtils.getMoveDbHistoryData(mActivity, MOVE_DB_HOME_DATA_KEY) == null) {
            DataBaseUtils.insertMoveDbHistoryData(mActivity, MOVE_DB_HOME_DATA_KEY, Constant.MOVE_INIT_UP_COMING);
        }
    }


    public void showPermission(List<String> needList, int requestCode) {
        if (mActivity.dialog != null) mActivity.dialog.dismiss();
        mActivity.dialog = AlertDialogUtils.showTwoButtonDialog(
                mActivity,
                "取消",
                "我知道了",
                "你的手机没有授权软件权限,将无法正常使用!",
                R.color.color_dialog_btn, R.color.color_ffa300, new AlertDialogUtils.DialogTwoListener() {
                    @Override
                    public void onClickLeft(Dialog dialog) {
                        dialog.dismiss();
                        showMessage("无法获取存读取权限,您的app将无法正常使用");
                        mActivity.finish();
                    }

                    @Override
                    public void onClickRight(Dialog dialog) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(mActivity, needList.toArray(new String[needList.size()]), requestCode);
                    }
                });
    }

    public void jumpHomeActivity() {
        HomeActivity.startPlayHelperActivity(mActivity);
        mActivity.finish();
    }

    String OAIDStr = "";
    String tag = "获取Imei:";

    public void initImei() {

        if (Build.VERSION.SDK_INT < 29) {
            OAIDStr = DeviceUtlis.getDeviceId(mActivity);
        } else {
            int errorCode = MdidSdkHelper.InitSdk(mActivity, true, new IIdentifierListener() {
                @Override
                public void OnSupport(boolean b, IdSupplier idSupplier) {
                    if (idSupplier == null) {
                        return;
                    }
                    OAIDStr = idSupplier.getOAID();
                }
            });
            if (errorCode == ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT) {
                OAIDStr = DeviceUtlis.getUniqueID(mActivity);
            } else if (errorCode == ErrorCode.INIT_ERROR_LOAD_CONFIGFILE) {
                Log.e(tag, "获取OAID：" + "加载配置文件出错");
                OAIDStr = DeviceUtlis.getUniqueID(mActivity);
            } else if (errorCode == ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT) {
                Log.e(tag, "获取OAID：" + "不支持的设备厂商");
                OAIDStr = DeviceUtlis.getUniqueID(mActivity);
            } else if (errorCode == ErrorCode.INIT_ERROR_RESULT_DELAY) {
                Log.e(tag, "获取OAID：" + "获取接口是异步的，结果会在回调中返回，回调执行的回调可能在工作线程");
                OAIDStr = DeviceUtlis.getUniqueID(mActivity);
            } else if (errorCode == ErrorCode.INIT_HELPER_CALL_ERROR) {
                Log.e(tag, "获取OAID：" + "反射调用出错");
                OAIDStr = DeviceUtlis.getUniqueID(mActivity);
            } else {

            }

        }

        UserInfo userInfo = DataBaseUtils.getUser(mActivity);
        if (userInfo == null) {
            LoginActivity.startActivity(mActivity, OAIDStr);
            mActivity.finish();
        } else {

            if ("0".equals(userInfo.getUserState())) {
                showMessage("请联系开发者放开授权");
                LoginActivity.startActivity(mActivity, OAIDStr);
                mActivity.finish();
            } else {
                jumpHomeActivity();
            }
        }


    }


    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }


    public void showPermissionPerpetual(int requestCode) {
        if (mActivity.dialog != null) mActivity.dialog.dismiss();
        mActivity.dialog = AlertDialogUtils.showTwoButtonDialog(
                mActivity,
                "取消",
                "去设置",
                "你的手机没有授权软件权限,将无法正常使用!", R.color.color_dialog_btn,
                R.color.color_ffa300, new AlertDialogUtils.DialogTwoListener() {
                    @Override
                    public void onClickLeft(Dialog dialog) {
                        dialog.dismiss();
                        showMessage("无法获取存读取权限,您的app将无法正常使用");
                        mActivity.finish();
                    }

                    @Override
                    public void onClickRight(Dialog dialog) {
                        dialog.dismiss();
                        PermissionChecker.settingPermissionActivity(mActivity, requestCode);
                    }
                });
    }

    public void checkPermissions() {
        boolean hasPermission = mActivity.getPresenter().checkPermissions(mActivity
                , mActivity.permissionsREAD,
                mActivity.REQUEST_CODE_LAUNCH);

        if (hasPermission) {
            initImei();
        }

    }
}
