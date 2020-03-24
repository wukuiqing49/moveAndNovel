package com.wkq.order.modlue.move.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveImgsBinding;
import com.wkq.order.databinding.ItemMoveMoveRecommendSimilarBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideUtlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-28
 * <p>
 * 用途:
 */


public class MoveImgsAdapter extends DataBindingAdapter<MoveDbMoveDetailInfo.ImagesBean.PostersBean> {
    Context context;

    public MoveImgsAdapter(Context context) {

        super(context, R.layout.item_move_imgs, com.wkq.order.BR.data);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveImgsBinding binding = (ItemMoveImgsBinding) dataBindingViewHolder.getBinding();

        GlideUtlis.loadMoveImg500(context, getItem(position).getFile_path(), binding.ivIcon);

        binding.ivIcon.setOnClickListener(view -> {viewClickListener.onViewClick(binding.ivIcon,getItem(position));});


    }
}
