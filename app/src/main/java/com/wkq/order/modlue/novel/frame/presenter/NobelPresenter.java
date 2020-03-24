package com.wkq.order.modlue.novel.frame.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.wkq.base.frame.mosby.MvpBasePresenter;
import com.wkq.order.modlue.novel.frame.view.NovelView;
import com.wkq.order.modlue.novel.ui.activity.NovelInfoActivity;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.rank.HottestRank;
import com.zia.easybookmodule.engine.EasyBook;
import com.zia.easybookmodule.rx.Disposable;
import com.zia.easybookmodule.rx.Subscriber;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NobelPresenter extends MvpBasePresenter<NovelView> {

    private Disposable disposable;

    public void getData(NovelFragment novelFragment) {


        EasyBook.getHottestRank().subscribe(new Subscriber<HottestRank>() {
            @Override
            public void onFinish(@NonNull HottestRank hottestRank) {

                if (getView()!=null&&hottestRank!=null)getView().setData(hottestRank);
            }

            @Override
            public void onError(@NonNull Exception e) {
                Log.e("获取数据失败:", "");
            }

            @Override
            public void onMessage(@NonNull String message) {

            }

            @Override
            public void onProgress(int progress) {

            }
        });


    }


    /**
     * 搜索小说
     *

     * @param ceotent
     */
    public void getNovelInfo( String ceotent) {
        disposable = EasyBook.search(ceotent).subscribe(new Subscriber<List<Book>>() {
            @Override
            public void onFinish(@NonNull List<Book> books) {
                if (getView()!=null)getView().processData(books);
            }

            @Override
            public void onError(@NonNull Exception e) {
                if (getView()!=null) getView().showMessage(e.getMessage());
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



    public void cancel(){
        if (disposable!=null)disposable.dispose();
    }
}
