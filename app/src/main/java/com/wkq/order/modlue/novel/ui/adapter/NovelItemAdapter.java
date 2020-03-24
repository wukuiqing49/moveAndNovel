package com.wkq.order.modlue.novel.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wkq.order.R;
import com.wkq.order.databinding.ItemItemNovelBinding;
import com.wkq.order.databinding.ItemNovelBinding;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.wkq.order.modlue.novel.ui.activity.preview.BookActivity;
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


public class NovelItemAdapter extends DataBindingAdapter<RankBook> {
    Context context;

    public NovelItemAdapter(Context context) {
        super(context, R.layout.item_item_novel, com.wkq.order.BR.data);
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        DataBindingViewHolder dataBindingHolder = (DataBindingViewHolder) holder;
        ItemItemNovelBinding binding = (ItemItemNovelBinding) dataBindingHolder.getBinding();
        binding.setData(getItem(position));


        if (position == 0) {
            Glide.with(context).load(getItem(position).getImgUrl()).into(binding.ivNovel);
            binding.tvCount.setText(getItem(position).getViewInfo());
            binding.tvRank.setText("No." + getItem(position).getRank());
            binding.llOther.setVisibility(View.GONE);
            binding.ivNovel.setVisibility(View.VISIBLE);
        } else {
            binding.ivNovel.setVisibility(View.GONE);
            binding.tvCount.setVisibility(View.GONE);
            binding.tvUser.setVisibility(View.GONE);
            binding.tvRank.setVisibility(View.GONE);
            binding.tvName.setVisibility(View.GONE);
            binding.llOther.setVisibility(View.VISIBLE);

            binding.tvRank2.setText(getItem(position).getRank() + "");
            binding.tvRank2.setBackgroundColor(context.getResources().getColor(R.color.color_666));
            if (!TextUtils.isEmpty(getItem(position).getViewInfo())) {
                binding.tvCount2.setText(getItem(position).getViewInfo() + "票");
            }


        }

        if (position == 9) {
            binding.line.setVisibility(View.GONE);
        }

        binding.root.setOnClickListener(view -> {
            viewClickListener.onViewClick(view,getItem(position));
        });


    }
}
