package com.wkq.order.modlue.move.ui.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemMoveCreditsBinding;
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


public class RecommendSimilarMoveAdapter extends DataBindingAdapter<MoveDbMoveDetailInfo.SimilarMoviesBean.ResultsBean> {
    Context context;

    public RecommendSimilarMoveAdapter(Context context) {

        super(context, R.layout.item_move_move_recommend_similar, com.wkq.order.BR.data);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingViewHolder = (DataBindingViewHolder) holder;
        ItemMoveMoveRecommendSimilarBinding binding = (ItemMoveMoveRecommendSimilarBinding) dataBindingViewHolder.getBinding();

        GlideUtlis.loadMoveImg200(context, getItem(position).getPoster_path(), binding.ivIcon);

        binding.root.setOnClickListener(view -> {
            viewClickListener.onViewClick(view,getItem(position));
        });


    }
}
