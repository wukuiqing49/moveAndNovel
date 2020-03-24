package com.wkq.order.modlue.novel.frame.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.squareup.picasso.Picasso;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.wkq.order.modlue.novel.ui.activity.preview.BookActivity;
import com.wkq.order.modlue.novel.ui.adapter.NovelChapterAdapter;
import com.wkq.order.utils.DataBindingAdapter;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.Catalog;


import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-22
 * <p>
 * 用途:
 */


public class NoveInfolView implements MvpView {

    NovelInfoActivity mActivity;
    private NovelChapterAdapter mAdapter;


    public NoveInfolView(NovelInfoActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {
        StatusBarUtil.setTransparentForWindow(mActivity);
        StatusBarUtil.addTranslucentView(mActivity, 0);
        StatusBarUtil.setLightMode(mActivity);
        mActivity.binding.rvChapter.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new NovelChapterAdapter(mActivity);
        mActivity.binding.rvChapter.setAdapter(mAdapter);

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId() == R.id.root) {
                    Intent intent = new Intent(mActivity, BookActivity.class);
                    intent.putExtra("book", mActivity.bookName);
                    intent.putExtra("scroll", false);
                    mActivity.startActivity(intent);

                }
            }
        });

        mActivity.binding.rlBack.setOnClickListener(view -> finish(""));
    }

    public void setBookData(Book bookData) {
        if (bookData == null) return;
        mActivity.binding.setData(bookData);
        if (!TextUtils.isEmpty(bookData.getImageUrl())) {
            Picasso.with(mActivity).load(bookData.getImageUrl()).into(mActivity.binding.ivDrop);
            Picasso.with(mActivity).load(bookData.getImageUrl()).into(mActivity.binding.ivIcon);
        }
    }

    public void initNovelChapter(List<Catalog> catalogs) {
        mAdapter.addItems(catalogs);
    }

    public void hindLoading() {
        mActivity.binding.rlLoading.setVisibility(View.GONE);
    }

    public void finish(String message) {
        showMessage(message);

        mActivity.finish();
    }

    public void showMessage(String message) {
        if (mActivity == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mActivity, message);
    }


}
