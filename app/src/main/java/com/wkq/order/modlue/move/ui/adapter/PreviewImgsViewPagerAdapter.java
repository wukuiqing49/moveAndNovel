package com.wkq.order.modlue.move.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.cnlive.largeimage.CustomPreviewClickListener;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemPrevieImgBinding;
import com.wkq.order.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class PreviewImgsViewPagerAdapter extends PagerAdapter {
    List<String> imgs = new ArrayList<>();
    Context context;
    OnPicItemClickListener listener;

    public void setOnLongClickerListener(OnPicItemClickListener longClickerListener) {
        listener = longClickerListener;
    }

    public PreviewImgsViewPagerAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;

    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    //判断是否是否为同一张图片，这里返回方法中的两个参数做比较就可以
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ItemPrevieImgBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_previe_img, container, false);

        if (imgs.get(position).startsWith("http")) {
            binding.largeView.setImageView(imgs.get(position), "");

        } else {
            binding.largeView.setImageView(Constant.MOVE_DB_IMG_BASE_500.concat(imgs.get(position)), "");

        }


        binding.largeView.setClickLisetner(new CustomPreviewClickListener() {
            @Override
            public void longClick(String file) {
                if (listener != null) listener.onItemLongClicker(imgs.get(position));
            }

            @Override
            public void click() {

            }
        });
        container.addView(binding.getRoot());

        return binding.getRoot();
    }

    //因为它默认是看三张图片，第四张图片的时候就会报错，还有就是不要返回父类的作用
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnPicItemClickListener {
        void onItemLongClicker(String imgUrl);

    }
}
