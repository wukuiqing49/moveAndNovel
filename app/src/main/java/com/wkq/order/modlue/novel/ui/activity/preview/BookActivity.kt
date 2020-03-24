package com.wkq.order.modlue.novel.ui.activity.preview

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.wkq.baseLib.utlis.DensityUtils
import com.wkq.order.utils.StatusBarUtil
import com.wkq.order.R
import com.zia.easybookmodule.bean.Book
import com.zia.easybookmodule.bean.Type
import com.zia.toastex.ToastEx
import kotlinx.android.synthetic.main.activity_book.*

/**
 *
 * 作者:吴奎庆
 *
 * 时间:2020-02-07
 *
 * 用途:
 */




class BookActivity : BaseActivity(), CatalogPagingAdapter.CatalogSelectListener {

    private lateinit var book: Book
    private var scroll = true
    private var introduceExpand = false
    private lateinit var adapter: CatalogPagingAdapter

    private lateinit var viewModel: BookViewModel

    private val dialog by lazy {
        val dialog = ProgressDialog(this)
        dialog.setCancelable(false)
        dialog.progress = 0
        dialog.setTitle("正在下载")
        dialog.setMessage("")
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setTranslucentForImageView(this, book_blurImage)
        setContentView(R.layout.activity_book)

        book_sl.isRefreshing = true

        book = intent.getSerializableExtra("book") as Book
        scroll = intent.getBooleanExtra("scroll", true)
        var canAddFav = intent.getBooleanExtra("canAddFav", true)

        book_name.text = book.bookName
        book_author.text = book.author
        book_lastUpdateChapter.text = book.lastChapterName
        book_site.text = book.site.siteName
        book_lastUpdateTime.text = book.lastUpdateTime

        //加载图片、模糊图片
        if (book.imageUrl.isNotEmpty()) {
            loadImage()
        } else {
            Glide.with(this).load(R.drawable.ic_book_cover_default).into(book_image)
        }

        initRv()

        viewModel =
                ViewModelProviders.of(this, BookViewModelFactory(book)).get(BookViewModel::class.java)
        initObservers()

        book_sl.setOnRefreshListener { viewModel.loadCatalog(true) }

        book_download.setOnClickListener { chooseType() }

        book_beginRead.setOnClickListener {
            if (adapter.itemCount == 0) {
                ToastUtil.onWarning("需要解析目录后才能添加，请稍等")
                return@setOnClickListener
            }
            //跳转到阅读界面
            val intent = Intent(this@BookActivity, PreviewActivity::class.java)
            intent.putExtra("bookName", book.bookName)
            intent.putExtra("siteName", book.siteName)
            startActivity(intent)
        }

        book_introduce.setOnClickListener {
            if (introduceExpand) {
                book_introduce.maxLines = 4
            } else {
                book_introduce.maxLines = 15
            }
            introduceExpand = !introduceExpand
        }

        //添加到书架
        if (!canAddFav) {//从书架打开
            book_favorite.setTextColor(Color.GRAY)
        }
        book_favorite.setOnClickListener {
            if (canAddFav) {
                if (adapter.itemCount == 0) {
                    ToastUtil.onWarning("需要解析目录后才能添加，请稍等")
                    return@setOnClickListener
                }
                book_favorite.setTextColor(Color.GRAY)
                canAddFav = false
                viewModel.insertBookIntoBookRack()
                Glide.with(this).load(book.imageUrl).into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                    ) {
                        viewModel.addShortcut(resource)
                    }
                })
            } else {
                ToastEx.info(this@BookActivity, "已经在书架了").show()
            }
        }

        book_back.setOnClickListener {
            onBackPressed()
        }

        //加载目录
        viewModel.loadCatalog()
    }

    private fun initRv() {
        adapter = CatalogPagingAdapter(this)
        catalogRv.adapter = adapter

        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        catalogRv.layoutManager = layoutManager

        val paint = Paint()
        paint.color = Color.GRAY
        val padding = DensityUtils.dp2px(this, 8f).toFloat()

        catalogRv.addItemDecoration(object :
                androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
            override fun onDraw(
                    c: Canvas,
                    parent: androidx.recyclerview.widget.RecyclerView,
                    state: androidx.recyclerview.widget.RecyclerView.State
            ) {
                if (parent.layoutManager != null) {
                    for (i in 0 until parent.childCount - 1) {
                        val child = parent.getChildAt(i)
                        val y = child.bottom.toFloat()
                        c.drawLine(padding, y, child.right - 10f, y, paint)
                    }
                }
            }
        })

//        catalogRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//        })
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        //paging的数据源观察
        viewModel.catalogStrings.observe(this, Observer {
            adapter.submitList(it)
        })

        //加载完毕的监听
        viewModel.onCatalogUpdate.observe(this, Observer {
            book_loading.startAnimation(
                    AnimationUtil.getHideAlphaAnimation(
                            500,
                            endListener = Runnable {
                                book_loading.visibility = View.GONE
                            })
            )
            book_sl.isRefreshing = false
            if (it != null) {
                book_lastUpdateChapter.text = it
            }
            if (book.introduce.isNotBlank()) {
                Log.e("BookActivity", book.introduce)
                book_introduce.text = book.introduce
            }
            if (book.imageUrl.isNotEmpty()) {
                loadImage()
            }
        })

        viewModel.history.observe(this, Observer {
            if (it != null) {
                book_history.text = "第${(it + 1)}章"
                val count = adapter.itemCount
                val p = if (count - 1 < it + 5) {
                    val pp = count - 1
                    if (pp < 0) 0 else pp
                } else {
                    it + 5
                }
                catalogRv.scrollToPosition(p)
                adapter.freshHistory(it)
            }
        })

        viewModel.savedFile.observe(this, Observer {
            if (it != null) {
                ToastUtil.onSuccess("保存成功，路径为${it.path}")
            } else {
                ToastUtil.onError("保存失败")
            }
            hideDialog()
        })

        viewModel.dialogProgress.observe(this, Observer { updateDialog(it) })

        viewModel.dialogMessage.observe(this, Observer { updateDialog(it) })

        viewModel.error.observe(this, Observer {
            hideDialog()
            book_loading.visibility = View.VISIBLE
            val text = "解析失败，点击重试" + "\n" + it?.message
            book_loading.text = text
            book_loading.setOnClickListener {
                book_loading.setOnClickListener(null)
                book_loading.text = "正在重试.."
                viewModel.loadCatalog()
            }
        })

        viewModel.toast.observe(this, Observer { ToastUtil.onNormal(it) })
    }

    private fun chooseType() {
        val types = arrayOf("EPUB(推荐)", "TXT")
        val style =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) android.R.style.ThemeOverlay_Material_Dialog
                else android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth
        AlertDialog.Builder(this, style)
                .setItems(types) { _, which ->
                    var type = Type.EPUB
                    when (which) {
                        0 -> {
                            type = Type.EPUB
                        }
                        1 -> {
                            type = Type.TXT
                        }
                    }
                    download(type)
                }.show()
    }

    private fun download(type: Type) {
        updateDialog(0)
        updateDialog("")
        //在viewModel中进行了数据库插入和通知bookRackFragment刷新界面
        viewModel.downloadBook(this, type)
    }


    override fun onCatalogSelect(itemView: View, position: Int) {
        //更新书签
        BookMarkUtil.insertOrUpdate(position, book.bookName, book.site.siteName)
        adapter.freshHistory(position)
        //跳转到阅读界面
        val intent = Intent(this@BookActivity, PreviewActivity::class.java)
        intent.putExtra("bookName", book.bookName)
        intent.putExtra("siteName", book.siteName)
        startActivity(intent)
    }

    private fun loadImage() {
        Glide.with(this).load(book.imageUrl).into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
//                book_image.background = null
//                book_image.background = resource
                book_image.setImageDrawable(resource)
                book_blurImage.setImageBitmap(BlurUtil.blurBitmap(this@BookActivity, resource))
            }
        })
    }

    private fun updateDialog(progress: Int?) {
        if (progress != null) {
            dialog.progress = progress
        }
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    private fun updateDialog(msg: String?) {
        if (msg != null) {
            dialog.setMessage(msg)
        }
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    private fun hideDialog() {
        dialog.dismiss()
    }

    override fun onDestroy() {
        viewModel.shutDown()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.shutDown()
    }

    override fun onResume() {
        super.onResume()
//        book_loading.visibility = View.VISIBLE
//        viewModel.freshHistory()

    }
}

