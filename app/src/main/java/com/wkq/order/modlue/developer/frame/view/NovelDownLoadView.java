package com.wkq.order.modlue.developer.frame.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.database.AppDatabase;
import com.wkq.database.bean.LocalBook;
import com.wkq.order.R;
import com.wkq.order.modlue.developer.ui.activity.NovelDownLoadActivity;
import com.wkq.order.modlue.developer.ui.adapter.DeveloperNovelDownLoadAdapter;
import com.wkq.order.modlue.novel.ui.activity.preview.ReaderUtil;
import com.wkq.order.utils.DataBindingAdapter;

import java.io.File;
import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-02-09
 * <p>
 * 用途:
 */


public class NovelDownLoadView implements MvpView {

    NovelDownLoadActivity mActivity;

    public NovelDownLoadView(NovelDownLoadActivity mActivity) {
        this.mActivity = mActivity;
    }

    public void initView() {

        StatusBarUtil.setStatusBarWrite(mActivity);
        StatusBarUtil.setColor(mActivity, mActivity.getResources().getColor(R.color.color_2b2b2b), 0);
        StatusBarUtil.setDarkMode(mActivity);

        mActivity.binding.toolBar.findViewById(R.id.rl_back).setOnClickListener(view -> mActivity.finish());

        List<LocalBook> books = AppDatabase.getAppDatabase().localBookDao().getLocalBooks();
        TextView tvTitle = mActivity.binding.toolBar.findViewById(R.id.tv_title);
        tvTitle.setText("小说下载");
        DeveloperNovelDownLoadAdapter mAdapter = new DeveloperNovelDownLoadAdapter(mActivity);
        mActivity.binding.rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        mActivity.binding.rvContent.setAdapter(mAdapter);
        mAdapter.addItems(books);

        if (books == null || books.size() == 0) {
            mActivity.binding.llEmpty.setVisibility(View.VISIBLE);
            return;
        } else {
            mActivity.binding.llEmpty.setVisibility(View.GONE);
        }


        mAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                LocalBook bean = (LocalBook) program;
                if (bean == null) return;
                if (v.getId() == R.id.root) {
                    File file = new File(bean.getFilePath());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.parse(file.getAbsolutePath()), ReaderUtil.getMIMEType(file));
                    try {
                        mActivity.startActivity(intent);
                    } catch (Exception e) {


                    }
                } else if (v.getId() == R.id.bt_delete) {
                    mAdapter.removeItem(mAdapter.getList().indexOf(bean));
                    mAdapter.notifyDataSetChanged();
                    AppDatabase.getAppDatabase().localBookDao().delete(bean.getBookName(), bean.getSiteName());
                    if (mAdapter.getList() == null || mAdapter.getList().size() == 0) {
                        mActivity.binding.llEmpty.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


    }
}
