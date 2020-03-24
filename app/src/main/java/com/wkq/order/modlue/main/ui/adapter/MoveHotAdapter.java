package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.net.model.MoveDataInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveHotBinding;
import com.wkq.order.databinding.ItemMoveTopBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.wkq.order.utils.GlideUtlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-29
 * <p>
 * 用途:
 */


public class MoveHotAdapter extends DataBindingAdapter<MoveDataInfo.ResultsBean> {
    Context context;

    public MoveHotAdapter(Context context) {
        super(context, R.layout.item_move_hot, com.wkq.order.BR.data);
        this.context = context;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveHotBinding binding = (ItemMoveHotBinding) bindingViewHolder.getBinding();

//        if (binding != null) {
//            //需要Item高度不同才能出现瀑布流的效果，此处简单粗暴地设置一下高度
//            if (position % 2 == 0) {
//                binding.ivMovePoster.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));
//            } else {
//                binding.ivMovePoster.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
//            }
//        }

        GlideUtlis.loadMoveImg200Round(context, getItem(position).getPoster_path(), binding.ivMovePoster);

        binding.root.setOnClickListener(view -> {
            viewClickListener.onViewClick(view,getItem(position));
        });
    }
}
