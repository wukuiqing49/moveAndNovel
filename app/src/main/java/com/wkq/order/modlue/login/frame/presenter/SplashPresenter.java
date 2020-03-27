package com.wkq.order.modlue.login.frame.presenter;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.order.modlue.login.frame.view.SplashView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class SplashPresenter extends MvpBasePresenter<SplashView> {


    public boolean checkPermissions(Activity activity, String[] permissions, int requestCode) {
        //Android6.0以下默认有权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        List<String> needList = new ArrayList<>();
        boolean needShowRationale = false;
        int length = permissions.length;

        for (int i = 0; i < length; i++) {
            String permisson = permissions[i];
            if (TextUtils.isEmpty(permisson)) continue;
            if (ActivityCompat.checkSelfPermission(activity, permisson)
                    != PackageManager.PERMISSION_GRANTED) {
                needList.add(permisson);
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permisson))
                    needShowRationale = true;
            }
        }

        if (needList.size() != 0) {
            if (needShowRationale) {
                getView().showPermission(needList, requestCode);
                return false;
            }

            ActivityCompat.requestPermissions(activity, needList.toArray(new String[needList.size()]), requestCode);
            return false;
        } else {
            return true;
        }
    }

    public boolean[] onRequestPermissionsResult(final Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        boolean result = true;
        boolean isNerverAsk = false;

        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            String permission = permissions[i];
            int grandResult = grantResults[i];
            if (grandResult == PackageManager.PERMISSION_DENIED) {
                result = false;
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission))
                    isNerverAsk = true;
            }
        }

        return new boolean[]{result, isNerverAsk};
    }


}
