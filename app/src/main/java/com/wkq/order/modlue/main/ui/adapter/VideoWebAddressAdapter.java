package com.wkq.order.modlue.main.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.R;
import com.wkq.order.BR;
import com.wkq.order.databinding.ItemVideoWebBinding;
import com.wkq.order.modlue.main.modle.VideoWebInfo;
import com.wkq.order.utils.BaseRecyclerAdapter;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/18
 * <p>
 * 简介:
 */
public class VideoWebAddressAdapter extends DataBindingAdapter<VideoWebInfo> {
    Context mContext;
    OnItemClickListener listener;
    public  void setItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public VideoWebAddressAdapter(Context context) {
        super(context, R.layout.item_video_web, BR.data);
        this.mContext = context;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DataBindingViewHolder myHolder= (DataBindingViewHolder) holder;

        ItemVideoWebBinding binding= (ItemVideoWebBinding) myHolder.getBinding();
        binding.setData(getItem(position));
        if (listener!=null){
            binding.getRoot().setOnClickListener(view -> {listener.onItemClick(getItem(position));});
        }
    }


    public interface OnItemClickListener{
        void onItemClick(VideoWebInfo info);
    }
}
