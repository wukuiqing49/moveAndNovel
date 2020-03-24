package com.wkq.order.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.order.R;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).optionalCenterCrop();
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(context.getApplicationContext())

                .load(path)
                .apply(requestOptions)
                .into(imageView);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    /*@Override
    public ImageView createImageView(Context context) {
         //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }*/
}
