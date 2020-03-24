package com.wkq.order.modlue.novel.ui.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wkq.order.BR;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemNovelBinding;
import com.wkq.order.utils.DataBindingAdapter;
import com.wkq.order.utils.DataBindingViewHolder;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.rank.HottestRankClassify;
import com.zia.easybookmodule.bean.rank.RankBook;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-30
 * <p>
 * 用途:
 */


public class NovelAdapter extends DataBindingAdapter<HottestRankClassify> {
    Context context;

    public NovelAdapter(Context context) {
        super(context, R.layout.item_novel, com.wkq.order.BR.data);
        this.context = context;
    }
    OnNovelAdapterClickListener listeer;
    public void setOnClickListener(OnNovelAdapterClickListener listener){
        this.listeer=listener;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingHolder = (DataBindingViewHolder) holder;
        ItemNovelBinding binding = (ItemNovelBinding) dataBindingHolder.getBinding();
        binding.setData(getItem(position));

        NovelItemAdapter novelItemAdapter = new NovelItemAdapter(context);
        binding.rvContent.setLayoutManager(new LinearLayoutManager(context));
        binding.rvContent.setAdapter(novelItemAdapter);
        novelItemAdapter.addItems(getItem(position).getRankBookList());
        novelItemAdapter.setOnViewClickListener(new OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId()==R.id.root){
                    RankBook book= (RankBook) program;
                    if (listeer!=null){
                        listeer.onItemClick(book);
                    }
                }
            }
        });

    }

    public interface OnNovelAdapterClickListener{
         void onItemClick(RankBook book);
    }
}
