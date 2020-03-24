package com.wkq.order.modlue.htmlmove.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.R;
import com.wkq.order.databinding.ItemHomeHtmlMtimeBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;

import wkq.com.lib_move.model.MTimeHomeBean;
import wkq.com.lib_move.model.MoveInfo;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-21
 * <p>
 * 用途:
 */


public class MoveHomeHtmlMTimeAdapter extends DataBindingAdapter<MTimeHomeBean> {

    Context mContext;
    OnItemClickListener listener;
    public MoveHomeHtmlMTimeAdapter(Context context) {
        super(context, R.layout.item_home_html_mtime, com.wkq.order.BR.data);
        mContext=context;
    }

    public void setItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder bindingHolder = (DataBindingViewHolder) holder;

        ItemHomeHtmlMtimeBinding binding = (ItemHomeHtmlMtimeBinding) bindingHolder.getBinding();
        binding.setData(getItem(position));
        binding.rvContent.setLayoutManager(new LinearLayoutManager(mContext));

        MoveHtmlMTimeAdapter moveHtmlMTimeAdapter=new MoveHtmlMTimeAdapter(mContext);

        binding.rvContent.setAdapter(moveHtmlMTimeAdapter);
        moveHtmlMTimeAdapter.addItems(getItem(position).getMoveInfos());



        moveHtmlMTimeAdapter.setOnViewClickListener(new OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
               MoveInfo info= (MoveInfo) program;
              if (listener!=null)listener.onItemClick(info);

            }
        });
    }


    public interface  OnItemClickListener{
        void onItemClick(MoveInfo info);
    }
}
