package com.wkq.order.modlue.developer.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.binioter.guideview.Component;
import com.binioter.guideview.Guide;
import com.binioter.guideview.GuideBuilder;
import com.bumptech.glide.Glide;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemDeveloperBinding;
import com.wkq.order.databinding.ItemPlayHelpBinding;
import com.wkq.order.modlue.developer.model.DeveloperInfo;
import com.wkq.order.modlue.htmlmove.ui.widget.MutiComponent;
import com.wkq.order.modlue.htmlmove.ui.widget.SearchMoveComponent;
import com.wkq.order.modlue.main.modle.PlayHelpInfo;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-23
 * <p>
 * 用途:
 */


public class DeveloperAdapter extends DataBindingAdapter<DeveloperInfo> {

    Context mContext;

    boolean isShow;

    public DeveloperAdapter(Context context) {

        super(context, R.layout.item_developer, BR.data);
        mContext = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;

        ItemDeveloperBinding binding = (ItemDeveloperBinding) dataBindingViewHolder.getBinding();
        binding.setData(getItem(position));

        binding.root.setOnClickListener(view -> {
            viewClickListener.onViewClick(binding.root, getItem(position));
        });


        if (getItem(position).getItemStrig().equals("播放电影帮助") &&isShow) {
            binding.root.post(new Runnable() {
                @Override
                public void run() {
                    showGuideView(binding.root);
                }
            });
        }


    }


    public void showGuide( boolean isShow) {
        this.isShow = isShow;
        notifyDataSetChanged();
    }

    private void showGuideView(RelativeLayout root) {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(root)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10);

        builder.setHighTargetGraphStyle(Component.ROUNDRECT);

        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                SharedPreferencesHelper.getInstance(mContext).setValue("isDevelopeFirst", false);
            }
        });

        builder.addComponent(new MutiComponent());
        Guide guide = builder.createGuide();
        guide.show((Activity) mContext);
    }
}
