package com.wkq.order.modlue.main.frame.view;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
//import com.wkq.media.ImagePicker;
//import com.wkq.media.PickerConfig;
import com.wkq.order.modlue.web.ui.EasyWebActivity;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.modlue.main.modle.VideoWebInfo;
import com.wkq.order.modlue.web.ui.VideoWebListActivity;
import com.wkq.order.modlue.main.ui.adapter.VideoWebAddressAdapter;
import com.wkq.order.modlue.web.ui.VideoSiteActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/11/9
 * <p>
 * 简介:
 */
public class VideoWebView implements MvpView {
    VideoWebListActivity mActivity;
    List<VideoWebInfo> videoList = new ArrayList<>();

    //发布文件最大值
    private static final int media_item_max_size = 30 * 1024 * 1024;
    private static final int image_item_max_size = 20 * 1024 * 1024;
    //发布视频最大时长
    //发布视频最大时长
    private static final int media_item_max_time = 15_000;
    private static final int media_item_select_max_time = 10 * 1000;

    public static final int camera_def = 0;
    public static final int camera_bx = 1;

    public static final int ACTION_CAMERA_BX = 20010;
    public static final int REQUEST_CODE_START_MOMENT_CAMERA = 10001;
    public static final int REQUEST_CODE_START_MOMENT_BX_CAMERA = 10002;
    public static final int REQUEST_CODE_START_MOMENT_READ_WRITE = 10003;
    public static final int REQUEST_CODE_START_DOWNLOAD_READ_WRITE = 10012;


    public VideoWebView(VideoWebListActivity activity) {
        mActivity = activity;
    }

    public void openCam() {
//
//        new ImagePicker.Builder()
//                .setSelectGif(true)
//                .maxNum(9)
//                .needCamera(true)
//                .maxVideoSize(media_item_max_size)
//                .maxImageSize(image_item_max_size)
//                .showTime(true)
//                .maxTime(media_item_select_max_time)
//                .selectMode(PickerConfig.PICKER_IMAGE_VIDEO)
//                .cachePath((Build.VERSION.SDK_INT == Build.VERSION_CODES.Q ? mActivity.getExternalFilesDir("") : Environment.getExternalStorageDirectory()) + "/strike/file/")
//                .videoTrimPath((Build.VERSION.SDK_INT == Build.VERSION_CODES.Q ? mActivity.getExternalFilesDir("") : Environment.getExternalStorageDirectory()) + "/strike/file/")
//                .isFriendCircle(true)
//                .builder()
//                //跳转到图片选择页面 activity    请求码            结果码
//                .start(mActivity, 200, PickerConfig.DEFAULT_RESULT_CODE);

    }


    public void initData() {
        videoList.add(new VideoWebInfo("https://v.qq.com/", "进入腾讯视频"));
        videoList.add(new VideoWebInfo("https://www.iqiyi.com/", "进入奇艺视频"));
        videoList.add(new VideoWebInfo("https://www.youku.com/", "进入优酷视频"));
//        videoList.add(new VideoWebInfo("http://www.mgtv.com/", "进入芒果视频"));
//        videoList.add(new VideoWebInfo("https://sports.qq.com/nba/", "进入腾讯体育"));
        videoList.add(new VideoWebInfo("https://tv.sohu.com/", "进入搜狐视频"));
//        videoList.add(new VideoWebInfo("http://www.pptv.com/", "进入pptv视频"));
//        videoList.add(new VideoWebInfo("http://www.le.com/", "进入乐视视频"));
    }


    public void initView() {

        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);
        mActivity.binding.rvWeb.setLayoutManager(new LinearLayoutManager(mActivity));
        VideoWebAddressAdapter myVideoAdapter = new VideoWebAddressAdapter(mActivity);
        mActivity.binding.rvWeb.setAdapter(myVideoAdapter);
        myVideoAdapter.addItems(videoList);
        myVideoAdapter.setItemClickListener(new VideoWebAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(VideoWebInfo info) {
                EasyWebActivity.startActivity(mActivity, info.getVideoWebAddress());
            }
        });
// 圆角
//        RequestOptions options = RequestOptions.bitmapTransform(new GlideRoundedCornersTransform(5, GlideRoundedCornersTransform.CornerType.ALL)).placeholder(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading).priority(Priority.HIGH);
//        Glide.with(mContext).load(getItem(position).getPic()).apply(options).into(binding.ivTopic);
//  圆形头像
//        RequestOptions options = RequestOptions.circleCropTransform().dontAnimate().skipMemoryCache(false)
//                .placeholder(R.drawable.touxiang_circle)
//                .error(R.drawable.touxiang_circle)
//                .priority(Priority.HIGH);
    }
}
