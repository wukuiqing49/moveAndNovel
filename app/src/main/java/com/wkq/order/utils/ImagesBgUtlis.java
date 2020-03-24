package com.wkq.order.utils;

import android.content.Context;

import com.wkq.order.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2020/1/6
 * <p>
 * 简介: 获取随机图片为背景的工具类
 */
public class ImagesBgUtlis {
    /**
     * 获取随机背景图
     *
     * @return
     */
    public static Integer getImg() {

        List<Integer> pics = new ArrayList<>();
        pics.add(R.mipmap.movie_2);
        pics.add(R.mipmap.movie_3);
        pics.add(R.mipmap.movie_4);
        pics.add(R.mipmap.movie_6);
        pics.add(R.mipmap.movie_7);
        pics.add(R.mipmap.movie_8);
        pics.add(R.mipmap.movie_9);

        //创建Random类对象
        Random random = new Random();
        int START = 0;   //定义范围开始数字

        int END = pics.size() - 1;
        //产生随机数
        int number = random.nextInt(END - START + 1) + START;
        Integer bgImg = pics.get(number);

        return bgImg;

    }
}
