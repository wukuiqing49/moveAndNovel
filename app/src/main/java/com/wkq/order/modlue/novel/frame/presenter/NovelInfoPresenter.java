package com.wkq.order.modlue.novel.frame.presenter;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.net.logic.Event;
import com.wkq.order.modlue.novel.frame.view.NoveInfolView;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.Catalog;
import com.zia.easybookmodule.bean.rank.HottestRank;
import com.zia.easybookmodule.engine.EasyBook;
import com.zia.easybookmodule.rx.Disposable;
import com.zia.easybookmodule.rx.Subscriber;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-22
 * <p>
 * 用途:
 */


public class NovelInfoPresenter extends MvpBasePresenter<NoveInfolView> {


    private Disposable disposable;
    private Disposable catalog;

    /**
     * 搜索小说
     *
     * @param novelInfoActivity
     * @param ceotent
     */
    public void getNovelInfo(NovelInfoActivity novelInfoActivity, String ceotent) {
        disposable = EasyBook.search(ceotent).subscribe(new Subscriber<List<Book>>() {
            @Override
            public void onFinish(@NonNull List<Book> books) {
                processData(books);

            }

            @Override
            public void onError(@NonNull Exception e) {
                if (getView()!=null) getView().finish(e.getMessage());
            }

            @Override
            public void onMessage(@NonNull String message) {
//                if (getView()!=null) getView().finish(message);
            }

            @Override
            public void onProgress(int progress) {

            }
        });

    }

    /**
     * 获取小说列表
     *
     * @param book
     */

    public void getCatalog(Book book) {
        catalog = EasyBook.getCatalog(book).subscribe(new Subscriber<List<Catalog>>() {
            @Override
            public void onFinish(@NonNull List<Catalog> catalogs) {

                if (getView() != null) getView().initNovelChapter(catalogs);
                if (getView() != null) getView().hindLoading();

            }

            @Override
            public void onError(@NonNull Exception e) {
                if (getView() != null) getView().finish("数据异常");
            }

            @Override
            public void onMessage(@NonNull String message) {
//                if (getView()!=null) getView().finish("数据异常");
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }

    public void cancel() {
        if (disposable != null) disposable.dispose();

        if (catalog != null) catalog.dispose();
    }

    public void processData(List<Book> books) {
        if (books != null && books.size() > 1) {
            getCatalog(books.get(0));
            for (Book book : books) {
                if (!TextUtils.isEmpty(book.getImageUrl())) {
                    if (getView() != null) getView().setBookData(book);
                    return;
                }
            }
            getView().setBookData(books.get(0));
            Log.e("图片1:", books.get(0).toString());

        }

    }
}
