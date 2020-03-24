package com.wkq.order.modlue.novel.ui.activity.rank

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle

import android.util.Log
import android.view.View
import com.wkq.baseLib.utlis.DensityUtils
import com.wkq.order.utils.StatusBarUtil
import com.wkq.order.R
import com.wkq.order.application.OrderApplication
import com.wkq.order.modlue.novel.ui.activity.preview.BaseActivity
import com.wkq.order.modlue.novel.ui.activity.preview.ColorConstants
import com.wkq.order.modlue.novel.ui.activity.preview.ToastUtil

import com.zia.easybookmodule.bean.rank.RankClassify
import com.zia.easybookmodule.bean.rank.RankConstants
import com.zia.easybookmodule.bean.rank.RankInfo
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.layout_tool_bar.*


/**
 * Created by zzzia on 2019/4/17.
 * 书城排行
 */
class RankActivity : BaseActivity() {

    //默认配置是24小时热销榜
    private var config = RankConstants.hotsales
    private lateinit var viewModel: RankViewModel
    private val adapter = RankAdapter()
    private val classifyAdapter = RankClassifyAdapter()
    private val adapterErrorRunnable = Runnable {
        viewModel.loadMore(config)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)
        StatusBarUtil.setStatusBarWrite(this)
        StatusBarUtil.setColor(this, this.resources.getColor(R.color.color_2b2b2b), 0)
        StatusBarUtil.setDarkMode(this)
        //获取请求的配置
        val rankInfo = intent.getSerializableExtra("rankInfo") as RankInfo?
        if (rankInfo != null) {
            config = rankInfo
        }

        tv_title.text = config.rankName

        rl_back.setOnClickListener { finish() }

        viewModel = ViewModelProviders.of(this).get(RankViewModel::class.java)
        initObserver()

        //初始化RecyclerView
        initRv()

        viewModel.loadMore(config)
    }

    private fun initRv() {
        rank_rv.adapter = adapter
        rank_rv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rank_rv.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        rank_rv.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rank_rv.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()) {
                    viewModel.loadMore(config)
                }
            }
        })

        classifyAdapter.clickRunnable = object : RankClassifyAdapter.OnClassifyItemClickListener {
            override fun onClick(rankClassify: RankClassify) {
                try {
                    config.chn = rankClassify.data_chanid.toInt()
                    config.data_eid = rankClassify.data_eid
                    config.page = 1
                    viewModel.loadMore(config)
                } catch (e: Exception) {

                }
            }
        }
        rank_classify_rv.adapter = classifyAdapter
        rank_classify_rv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            this,
            androidx.recyclerview.widget.RecyclerView.HORIZONTAL,
            false
        )
        rank_classify_rv.addItemDecoration(object : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

            private val paint = Paint()
            private val f2 = DensityUtils.dp2px(OrderApplication.getContext(), 2F)
            private val f5 = DensityUtils.dp2px(OrderApplication.getContext(), 5F)

            override fun onDraw(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
                paint.color = ColorConstants.GREY
                if (parent.layoutManager != null) {
                    for (i in 0 until parent.childCount - 1) {
                        val child = parent.getChildAt(i)
                        val centerX = child.right + f5
                        c.drawCircle(centerX.toFloat(), c.height / 2f, f2.toFloat(), paint)
                    }
                }
            }

            override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
                outRect.set(f5, 0, f5, 0)
            }
        })
    }

    private fun initObserver() {
        viewModel.rank.observe(this, Observer {
            if (it == null) {
                adapter.loadingHolder?.showStatus("没有更多了...")
                return@Observer
            }
            if (it.rankClassifies == null || it.rankClassifies.size == 0) {
                if (rank_classify_rv.visibility == View.VISIBLE) {
                    rank_classify_rv.visibility = View.GONE
                }
            } else {
                classifyAdapter.initList(it.rankClassifies)
            }
            Log.e(javaClass.simpleName, it.toString())
            if (it.currentPage == 1) {
                //如果刚刚是加载的第一页
                adapter.refreshData(it.rankBookList)
                if (it.rankBookList != null && it.rankBookList.isNotEmpty()) {
                    rank_rv.smoothScrollToPosition(0)
                }
            } else {
                //加载更多
                adapter.addMoreData(it.rankBookList)
            }
        })
        viewModel.dialogProgress.observe(this, Observer {
            //第一次加载，在屏幕中间显示一个等待动画
            if (config.page == 1) {
                when (it) {
                    0 -> {
                        rank_loadingView.visibility = View.VISIBLE
                        rank_loadingView.startAnim()
                    }
                    100 -> {
                        rank_loadingView.visibility = View.INVISIBLE
                        rank_loadingView.reset()
                    }
                    -1 -> {
                        rank_loadingView.visibility = View.INVISIBLE
                        rank_loadingView.reset()
                        ToastUtil.onError("加载失败")
                    }
                }
            } else {
                //加载更多时，在footer中改变状态
                when (it) {
                    0 -> {
                        adapter.loadingHolder?.showLoading()
                    }
                    100 -> {
                        adapter.loadingHolder?.stopLoading()
                    }
                    -1 -> {
                        adapter.loadingHolder?.showStatus("网络错误，点击重试..", adapterErrorRunnable)
                    }
                }
            }
        })

        viewModel.toast.observe(this, Observer {
            ToastUtil.onNormal(it)
        })
        viewModel.error.observe(this, Observer {
            ToastUtil.onError(it?.message)
        })
    }

    override fun onDestroy() {
        rank_loadingView.reset()
        viewModel.shutdown()
        super.onDestroy()
    }
}
