package com.wkq.order.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;




/**
 * Created by gujun on 2017/5/23.
 */

public class DataBindingAdapter<T> extends BaseRecyclerAdapter<T> {
    private int layoutId;
    protected int itemBRId;

    protected OnAdapterViewClickListener<T> viewClickListener;
    protected OnAdapterViewLoadMoreListener loadMoreListener;

    public DataBindingAdapter(Context context) {
        super(context);
    }

    public DataBindingAdapter(Context context, int layoutId, int itemBRId) {
        super(context);
        this.layoutId = layoutId;
        this.itemBRId = itemBRId;
    }

    public void setOnViewClickListener(OnAdapterViewClickListener listener) {
        viewClickListener = listener;
    }

    public void setOnLoadMoreListener(OnAdapterViewLoadMoreListener listener) {
        loadMoreListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        DataBindingViewHolder viewHolder = new DataBindingViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataBindingViewHolder videoListViewHolder = (DataBindingViewHolder) holder;
        videoListViewHolder.getBinding().setVariable(itemBRId, getItem(position));
//        if (l != null)
//            videoListViewHolder.getBinding().setVariable(BR.viewClickListener, l);
        videoListViewHolder.getBinding().executePendingBindings();
        if (loadMoreListener != null && position == getList().size() - 1) {
            loadMoreListener.onLoadMore();
        }
    }

    public interface OnAdapterViewClickListener<T> {
        void onViewClick(View v, T program);
    }

    public interface OnAdapterViewLoadMoreListener {
        void onLoadMore();
    }

}
