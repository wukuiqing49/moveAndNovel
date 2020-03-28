package com.wkq.order.modlue.novel.frame.view;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.binioter.guideview.Component;
import com.binioter.guideview.Guide;
import com.binioter.guideview.GuideBuilder;
import com.wkq.base.frame.mosby.delegate.MvpView;
import com.wkq.baseLib.utlis.AlertUtil;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.modlue.htmlmove.ui.widget.SearchMoveComponent;
import com.wkq.order.modlue.htmlmove.ui.widget.SearchNoveComponent;
import com.wkq.order.utils.StatusBarUtil;
import com.wkq.order.R;
import com.wkq.order.modlue.novel.ui.activity.rank.RankActivity;
import com.wkq.order.modlue.novel.ui.activity.search.SearchActivity;
import com.wkq.order.modlue.novel.ui.adapter.NovelAdapter;
import com.wkq.order.modlue.novel.ui.adapter.NovelTopAdapter;
import com.wkq.order.modlue.novel.ui.fragment.NovelFragment;
import com.wkq.order.utils.DataBindingAdapter;
import com.zia.easybookmodule.bean.Book;
import com.zia.easybookmodule.bean.rank.HottestRank;
import com.zia.easybookmodule.bean.rank.RankBook;
import com.zia.easybookmodule.bean.rank.RankInfo;

import java.util.List;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-01-29
 * <p>
 * 用途:
 */


public class NovelView implements MvpView {
    NovelFragment mFragment;
    private NovelAdapter mAdapter;
    private NovelTopAdapter mTopAdapter;

    public NovelView(NovelFragment mFragment) {
        this.mFragment = mFragment;
    }

    public void initView() {
        StatusBarUtil.setDarkMode(mFragment.getActivity());
        mFragment.binding.rvContent.setLayoutManager(new LinearLayoutManager(mFragment.getActivity()));

        mAdapter = new NovelAdapter(mFragment.getActivity());
        mFragment.binding.rvContent.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new NovelAdapter.OnNovelAdapterClickListener() {
            @Override
            public void onItemClick(RankBook book) {
//                if (mFragment != null && mFragment.getPresenter() != null)
//                    mFragment.getPresenter().getNovelInfo(book.getBookName());

                Intent intent = new Intent(mFragment.getActivity(), SearchActivity.class);
                intent.putExtra("searchKey", book.getBookName());
                mFragment.getActivity().startActivity(intent);
            }
        });
        mTopAdapter = new NovelTopAdapter(mFragment.getActivity());
        mFragment.binding.rvTop.setLayoutManager(new LinearLayoutManager(mFragment.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mFragment.binding.rvTop.setAdapter(mTopAdapter);


        mTopAdapter.setOnViewClickListener(new DataBindingAdapter.OnAdapterViewClickListener() {
            @Override
            public void onViewClick(View v, Object program) {
                if (v.getId()== R.id.root){
                    RankInfo bean= (RankInfo) program;
                    Intent intent = new Intent(mFragment.getActivity(), RankActivity.class);

                    intent.putExtra("rankInfo", bean);
                    mFragment.getActivity().startActivity(intent);
                }
            }
        });

        mFragment.binding.cdSearch.setOnClickListener(view -> {
          Intent intent =new Intent(mFragment.getActivity(),SearchActivity.class);
            intent.putExtra("searchKey", "");


          mFragment.getActivity().startActivity(intent);
        });
    }

    public void initGuide() {
        mFragment.binding.cdSearch.post(new Runnable() {
            @Override
            public void run() {
                showGuideView();
            }
        });
    }
    private void showGuideView() {


        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(mFragment.binding.cdSearch)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(20);

        builder.setHighTargetGraphStyle(Component.CIRCLE);

        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                SharedPreferencesHelper.getInstance(mFragment.getActivity()).setValue("isSearchNovelFirst", false);
            }
        });

        builder.addComponent(new SearchNoveComponent());
        Guide guide = builder.createGuide();
        guide.show(mFragment.getActivity());
    }


    public void setData(HottestRank hottestRank) {
        mAdapter.addItems(hottestRank.getHottestRankClassifies());
        mTopAdapter.addItems(hottestRank.getRankInfos());
    }

    public void showMessage(String message) {
        if (mFragment == null || TextUtils.isEmpty(message)) return;
        AlertUtil.showDeftToast(mFragment.getActivity(), message);
    }

    public void processData(List<Book> books) {
        if (books != null && books.size() > 1) {
            for (Book book : books) {
                if (!TextUtils.isEmpty(book.getImageUrl())) {
                    Intent intent = new Intent(mFragment.getActivity(), SearchActivity.class);
                    mFragment.getActivity().startActivity(intent);
                    return;
                }
            }


        }
    }
}
