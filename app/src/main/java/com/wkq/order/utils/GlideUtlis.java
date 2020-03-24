package com.wkq.order.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkq.order.R;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-28
 * <p>
 * 用途:
 */


public class GlideUtlis {

    public static void loadMoveImg200(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";

        RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.ic_cinema_movie_sold_out).error(R.mipmap.ic_cinema_movie_sold_out)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(Constant.MOVE_DB_IMG_BASE_200.concat(path)).apply(requestOptions).into(view);

    }

    public static void loadMoveImg300(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.bg_pro_comment).error(R.mipmap.bg_pro_comment)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).useAnimationPool(true);

        Glide.with(context).load(Constant.MOVE_DB_IMG_BASE_300.concat(path)).apply(requestOptions).into(view);


    }

    public static void loadMoveImg400(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.bg_pro_comment).error(R.mipmap.bg_pro_comment)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).useAnimationPool(true);


        Glide.with(context).load(Constant.MOVE_DB_IMG_BASE_400.concat(path)).apply(requestOptions).into(view);

    }

    public static void loadMoveImg500(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";
        RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.bg_pro_comment).error(R.mipmap.bg_pro_comment)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(Constant.MOVE_DB_IMG_BASE_500.concat(path)).apply(requestOptions).into(view);

    }

    public static void loadMoveImg200Round(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";

        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideRoundedCornersTransform(2,GlideRoundedCornersTransform.CornerType.TOP));

        Glide.with(context).load(Constant.MOVE_DB_IMG_BASE_200.concat(path)).apply(requestOptions).into(view);

    }


    public static void loadNovel(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";

        RequestOptions requestOptions = RequestOptions.placeholderOf(R.drawable.bg_image_loading).error(R.drawable.bg_image_loading)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(path).apply(requestOptions).into(view);

    }
    public static void loadImg(Context context, String path, ImageView view) {
        if (TextUtils.isEmpty(path))path="";

        RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.bg_pro_comment).error(R.mipmap.bg_pro_comment)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context).load(path).apply(requestOptions).into(view);

    }

}
