package com.wkq.order.modlue.htmlmove.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.net.model.MTimeMoveDetailInfo;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveCreditsBinding;
import com.wkq.order.databinding.ItemMoveHtmlCreditsBinding;
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


public class MoveHtmlCreditsAdapter extends DataBindingAdapter<MTimeMoveDetailInfo.BasicBean.ActorsBean> {
    Context context;

    public MoveHtmlCreditsAdapter(Context context) {

        super(context, R.layout.item_move_html_credits, com.wkq.order.BR.data);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveHtmlCreditsBinding binding = (ItemMoveHtmlCreditsBinding) dataBindingViewHolder.getBinding();
        binding.setData(getItem(position));
        GlideUtlis.loadNovel(context, getItem(position).getImg(), binding.ivIcon);


    }
}
