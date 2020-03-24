package com.wkq.order.modlue.developer.frame.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.database.AppDatabase;
import com.wkq.database.bean.NetBook;
import com.wkq.order.R;
import com.wkq.order.modlue.developer.ui.activity.NovelSubscriptionActivity;
import com.wkq.order.modlue.developer.ui.adapter.DeveloperNovelSubscribeAdapter;
import com.wkq.order.modlue.novel.ui.activity.preview.PreviewActivity;
import com.wkq.order.utils.DataBindingAdapter;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-02-09
 * <p>
 * 用途:
 */


public class NovelSubscriptionView implements MvpView {

    NovelSubscriptionActivity mActivity;

    public NovelSubscriptionView(NovelSubscriptionActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);

        List<NetBook> books = AppDatabase.getAppDatabase().netBookDao().getNetBooks();

        TextView tvTitle = mActivity.binding.toolBar.findViewById(R.id.tv_title);
        tvTitle.setText("小说订阅");
        mActivity.binding.toolBar.findViewById(R.id.rl_back).setOnClickListener(view -> mActivity.finish());
        DeveloperNovelSubscribeAdapter mAdapter = new DeveloperNovelSubscribeAdapter(mActivity);

        mActivity.binding.rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvContent.setAdapter(mAdapter);
        mAdapter.addItems(books);

        if (books == null|| books.size() == 0) {
            mActivity.binding.llEmpty.setVisibility(View.VISIBLE);
            return;
        } else {
            mActivity.binding.llEmpty.setVisibility(View.GONE);
        }

        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                NetBook netBook = (NetBook) program;
                if (v.getId() == R.id.root) {
                    Intent intent = new Intent(mActivity, PreviewActivity.class);
                    intent.putExtra("bookName", netBook.getBookName());
                    intent.putExtra("siteName", netBook.getSiteName());
                    mActivity.startActivity(intent);
                } else if (v.getId() == R.id.bt_delete) {
                    mAdapter.removeItem(mAdapter.getList().indexOf(netBook));
                    mAdapter.notifyDataSetChanged();
                    AppDatabase.getAppDatabase().netBookDao().delete(netBook);
                    if (mAdapter.getList()==null||mAdapter.getList().size()==0){
                        mActivity.binding.llEmpty.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

    }

}
