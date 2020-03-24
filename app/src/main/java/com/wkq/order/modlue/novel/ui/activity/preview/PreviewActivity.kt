package com.wkq.order.modlue.novel.ui.activity.preview;

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.lifecycle.Observer

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProviders
import com.wkq.database.AppDatabase
import com.wkq.database.bean.NetBook

import com.wkq.order.R

import com.wkq.order.modlue.novel.ui.activity.preview.PageLoader.STATUS_FINISH
import com.wkq.order.modlue.novel.ui.activity.preview.PageLoader.STATUS_LOADING

import com.wkq.order.modlue.novel.ui.activity.preview.notchtools.NotchTools
import com.wkq.order.modlue.novel.ui.activity.preview.notchtools.core.NotchProperty
import com.wkq.order.modlue.novel.ui.activity.preview.notchtools.core.OnNotchCallBack
import com.wkq.order.modlue.novel.ui.activity.preview.threadPool.DefaultExecutorSupplier

import com.zia.toastex.ToastEx
import kotlinx.android.synthetic.main.activity_preview.*


import java.io.File
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class PreviewActivity : BaseActivity() {

    private val textSizeSP = "textSize_new"
    private val themeSP = "theme"
    private val pageModeSP = "pageMode"
    private val theme_white = 0
    private val theme_dark = 1
    private val theme_green = 2
    private val theme_paper = 3

    private var animMode: Int = 0

    private var showControl = true

    private val defaultTextSize = 48

    private lateinit var viewModel: PreviewModel
    private var bookName: String = ""
    private var siteName: String = ""

    private lateinit var selectedDrawable: Drawable
    private lateinit var unSelectedDrawable: Drawable

    val pool by lazy {
        val pool = ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(1))
        pool.rejectedExecutionHandler = ThreadPoolExecutor.DiscardPolicy()
        pool
    }

    private val dialog by lazy {
        val dialog = ProgressDialog(this@PreviewActivity)
        dialog.setCancelable(true)
        dialog.progress = 0
        dialog.setTitle("正在下载")
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog
    }

    private var downloadDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigationColor()

        setContentView(R.layout.activity_preview)

        init()

        setTextSize(defaultSharedPreferences.getInt(textSizeSP, defaultTextSize))

        //设置背景、文字颜色、字体
        setTvTheme(defaultSharedPreferences.getInt(themeSP, theme_white))

        viewModel = ViewModelProviders.of(this, PreviewModelFactory(bookName, siteName))
                .get(PreviewModel::class.java)

        //设置控件属性
        initView()

        //设置页面的监听
        setPanelClick()

        initObserver()

        //设置阅读view
        setReaderView()

        //开始加载
        load(usePageHistory = true)
    }

    private fun init() {
        bookName = intent.getStringExtra("bookName")
        siteName = intent.getStringExtra("siteName")
    }

    private fun load(usePageHistory: Boolean = false) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute {
            animMode = defaultSharedPreferences.getInt(pageModeSP, PageView.PAGE_MODE_SIMULATION)
            useAnimMode(animMode)
            val pos = viewModel.getBookMark()
            preview_progress.max = viewModel.readerAdapter.size - 1
            //防止并修复越界
            val fixSection = if (pos >= viewModel.readerAdapter.size) {
                if (viewModel.readerAdapter.size <= 0) {
                    0
                } else {
                    viewModel.readerAdapter.size - 1
                }
            } else {
                pos
            }
            preview_progress.progress = fixSection
            viewModel.loadSingleContent(fixSection)
            readerView.setPageAnimMode(animMode)
            readerView.post {
                //适配刘海屏
                fixWindow()
                if (usePageHistory) {
                    readerView.openSection(fixSection, viewModel.getReadProgress())
                } else {
                    readerView.openSection(fixSection)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            init()

            //重置
            readerView.pageLoader.newAdapter(viewModel.newAdapter())

            //加载
            load(usePageHistory = false)
        }
    }

    private fun initView() {
        preview_light_sb.max = 255
        preview_light_sb.progress = LightUtil.getScreenBrightness(this)

        selectedDrawable = resources.getDrawable(R.drawable.bg_source)
        unSelectedDrawable = resources.getDrawable(R.drawable.bg_source_white)
    }

    private fun initObserver() {
        viewModel.error.observe(this, Observer {
            it?.printStackTrace()
            ToastUtil.onError(it?.message)
        })

        viewModel.toast.observe(this, Observer {
            ToastUtil.onNormal(it)
        })

        viewModel.requestLoadPage.observe(this, Observer {
            if (it != null) {
                readerView.pageLoader.mStatus = STATUS_FINISH
                readerView.openSection(it)
            }
        })

        viewModel.downloadProgress.observe(this, Observer {
            if (it == null || it.isEmpty()) {
                preview_tv_download_progress.text = ""
                preview_tv_download_progress.visibility = View.INVISIBLE
            } else {
                preview_tv_download_progress.visibility = View.VISIBLE
                preview_tv_download_progress.text = it
            }
        })

        viewModel.file.observe(this, Observer {
            dialog.hide()
            if (it == null) {
                return@Observer
            }
            Log.e("PreviewActivity", "font saved : ${it.path}")
            defaultSharedPreferences.editor {
                putString("fontPath", it.path)
            }
            readerView.pageLoader.textPaint.typeface = Typeface.createFromFile(it.path)
            readerView.openSection(
                    readerView.pageLoader.mCurChapterPos,
                    readerView.pageLoader.pagePos
            )
        })

        viewModel.dialogMessage.observe(this, Observer {
            if (!dialog.isShowing) {
                dialog.show()
            }
            dialog.setProgressNumberFormat(it)
        })

        viewModel.dialogProgress.observe(this, Observer {
            if (!dialog.isShowing) {
                dialog.show()
            }
            dialog.progress = it ?: 0
        })

        preview_light_sb.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {


            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(this@PreviewActivity)) {
                    //是否有Settings写入权限
                    // 以下是请求写入系统设置权限逻辑
                    val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                    intent.data = Uri.parse("package:$packageName")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //开启一个新activity
                    startActivity(intent)
                } else {
                    //有了权限，具体的动作
                    LightUtil.autoBrightness(this@PreviewActivity, false)
                    if (progress == 0) {
                        LightUtil.setBrightness(this@PreviewActivity, 1)
                    } else {
                        LightUtil.setBrightness(this@PreviewActivity, progress)
                    }
                    preview_light_system.background = unSelectedDrawable
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                preview_light_system.background = unSelectedDrawable
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        preview_progress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pool.execute {
                    val name = AppDatabase.getAppDatabase().bookCacheDao()
                            .getChapterName(progress, bookName, siteName)
                    runOnUiThread {
                        preview_tv_sb_catalog.text = name
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (preview_tv_sb_catalog.visibility != View.VISIBLE) {
                    preview_tv_sb_catalog.visibility = View.VISIBLE
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    //更换书签
                    BookMarkUtil.insertOrUpdate(seekBar.progress, bookName, siteName)
                    //重置
                    readerView.pageLoader.newAdapter(viewModel.newAdapter())
                    //加载
                    load(usePageHistory = false)

                    preview_tv_sb_catalog.visibility = View.INVISIBLE
                }
            }

        })
    }

    private fun setReaderView() {
        readerView.setAdapter(viewModel.readerAdapter)

        readerView.setOnPageChangeListener(object : OnPageChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onChapterChange(pos: Int) {
                Log.e("onChapterChange", "$pos")
                viewModel.saveBookMark(pos)
                runOnUiThread {
                    preview_title.text = viewModel.getTitle(pos)
                    preview_progress.progress = pos + 1
                }
            }

            override fun onPageCountChange(count: Int) {
            }

            override fun onPageChange(pos: Int) {
                viewModel.saveReadProgress(pos)
            }

        })

        readerView.setTouchListener(object : PageView.TouchListener {
            override fun clickAny() {
                if (!showControl) {
                    preview_control_top.slideUpOut()
                    preview_control_bottom.slideDownOut()
                    hideSecondControl()
                    showControl = !showControl
                }
            }

            override fun center() {
                if (showControl) {
                    preview_control_top.slideDownIn()
                    preview_control_bottom.slideUpIn()
                } else {
                    preview_control_top.slideUpOut()
                    preview_control_bottom.slideDownOut()
                    hideSecondControl()
                }
                showControl = !showControl
            }

            override fun cancel() {

            }

        })
    }

    private fun setPanelClick() {
        preview_back.setOnClickListener { finish() }

        preview_previous.setOnClickListener {
            readerView.pageLoader.skipPreChapter()
        }

        preview_next.setOnClickListener {
            readerView.pageLoader.skipNextChapter()
        }

        preview_control_setting.setOnClickListener {
            showSecondControl()
        }


        preview_expand.setOnClickListener {
            var size = defaultSharedPreferences.getInt(textSizeSP, defaultTextSize)
            if (size > 100) {
                ToastEx.info(this@PreviewActivity, "不能再放大了").show()
                return@setOnClickListener
            }
            size += 4
            defaultSharedPreferences.editor {
                putInt(textSizeSP, size)
            }
            setTextSize(size)
        }

        preview_narrow.setOnClickListener {
            var size = defaultSharedPreferences.getInt(textSizeSP, defaultTextSize)
            if (size < 12) {
                ToastEx.info(this@PreviewActivity, "不能再缩小了").show()
                return@setOnClickListener
            }
            size -= 4
            defaultSharedPreferences.editor {
                putInt(textSizeSP, size)
            }
            setTextSize(size)
        }

        preview_text_default.setOnClickListener {
            defaultSharedPreferences.editor {
                putInt(textSizeSP, defaultTextSize)
            }
            setTextSize(defaultTextSize)
        }

        preview_theme_dark.setOnClickListener {
            defaultSharedPreferences.editor { putInt(themeSP, theme_dark) }
            setTvTheme(theme_dark)
        }

        preview_theme_white.setOnClickListener {
            defaultSharedPreferences.editor { putInt(themeSP, theme_white) }
            setTvTheme(theme_white)
        }

        preview_theme_green.setOnClickListener {
            defaultSharedPreferences.editor { putInt(themeSP, theme_green) }
            setTvTheme(theme_green)
        }

//        preview_theme_paper.setOnClickListener {
//            defaultSharedPreferences.editor { putInt(themeSP, theme_paper) }
//            setTvTheme(theme_paper)
//        }

        preview_anim_vertical.setOnClickListener {
            useAnimMode(PageView.PAGE_MODE_SCROLL)
        }

        preview_anim_cover.setOnClickListener {
            useAnimMode(PageView.PAGE_MODE_COVER)
        }

        preview_anim_sim.setOnClickListener {
            useAnimMode(PageView.PAGE_MODE_SIMULATION)
        }

        preview_anim_none.setOnClickListener {
            useAnimMode(PageView.PAGE_MODE_NONE)
        }

        preview_text_style.setOnClickListener {
            val items = arrayOf(
                    "思源黑体 (17.3M)", "思源宋体 (11.2M)", "小米兰亭 (3.7M)", "字体管家细圆体 (4.4M)",
                    "浪漫雅圆 (9.4M)", "汉仪润圆 (5.5M)", "索尼楷书 (15.6M)"
            )
            downloadDialog = AlertDialog.Builder(this@PreviewActivity)
                    .setTitle("选择字体")
                    .setItems(items) { dialog, which ->
                        val url = "http://qiniu.zzzia.net/"
                        val fontName: String
                        when (which) {
                            0 -> {
                                fontName = "NotoSansCJKsc.otf"
                            }
                            1 -> {
                                fontName = "NotoSerifSC.otf"
                            }
                            2 -> {
                                fontName = "xiaomilanting.ttf"
                            }
                            3 -> {
                                fontName = "zitiguanjia.ttf"
                            }
                            4 -> {
                                fontName = "langmanyayuan.ttf"
                            }
                            5 -> {
                                fontName = "hanyirunyuan.ttf"
                            }
                            else -> {
                                fontName = "suonikaishu.ttf"
                            }
                        }
                        //查看是否已经下载过了
                        val file = File(FileUtil.fontDirPath + File.separator + fontName)
                        Log.d("PreviewActivity", "font filePath:${file.path}")
                        if (file.exists()) {
                            viewModel.file.postValue(file)
                            return@setItems
                        }
                        //提示是否下载
                        downloadDialog = AlertDialog.Builder(this@PreviewActivity)
                                .setTitle("是否下载字体")
                                .setPositiveButton("下载") { _, _ ->
                                    this.dialog.show()
                                    viewModel.downloadFile(url + fontName, FileUtil.fontDirPath, fontName)
                                }
                                .setNegativeButton("取消") { _, _ -> downloadDialog?.dismiss() }
                                .create()
                        downloadDialog!!.show()
                    }
                    .create()
            downloadDialog!!.show()
        }

        preview_light_system.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(this@PreviewActivity)) {
                //是否有Settings写入权限
                // 以下是请求写入系统设置权限逻辑
                val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
                intent.data = Uri.parse("package:$packageName")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) //开启一个新activity
                startActivity(intent)
            } else {
                //有了权限，具体的动作
                preview_light_system.background = selectedDrawable
                val brightness = LightUtil.getScreenBrightness(this)
                preview_light_sb.progress = brightness
                LightUtil.setBrightness(this, brightness)
                LightUtil.autoBrightness(this, true)
                preview_light_system.background = selectedDrawable
            }
        }

        preview_freshChapter.setOnClickListener {
            readerView.pageLoader.mStatus = STATUS_LOADING
            readerView.drawCurPage(false)
            viewModel.forceLoadFromNet(readerView.pageLoader.mCurChapterPos)
        }

        preview_control_catalog.setOnClickListener {
            goCatalog()
        }

        preview_control_download.setOnClickListener {
            val items = arrayOf("后50章", "后100章", "后200章", "全部")
            downloadDialog =
                    AlertDialog.Builder(this).setTitle("选择缓存章节").setItems(items) { dialog, which ->
                        val cur = BookMarkUtil.getMarkPosition(bookName, siteName)
                        val size = viewModel.readerAdapter.size
                        when (which) {
                            0 -> {
                                val to = if (cur + 50 < size - 1) cur + 50 else size - 1
                                viewModel.download(cur, to)
                            }
                            1 -> {
                                val to = if (cur + 100 < size - 1) cur + 100 else size - 1
                                viewModel.download(cur, to)
                            }
                            2 -> {
                                val to = if (cur + 200 < size - 1) cur + 200 else size - 1
                                viewModel.download(cur, to)
                            }
                            else -> {
                                viewModel.download(0, size - 1)
                            }
                        }
                        downloadDialog?.hide()
                    }.create()
            downloadDialog?.show()
        }
    }

    private fun goCatalog() {
        val netBook: NetBook? =
                AppDatabase.getAppDatabase().netBookDao().getNetBook(bookName, siteName)
        //还没有添加到书架，说明现在在
        if (netBook == null) {
            onBackPressed()
            return
        }
//        val intent = Intent(this@PreviewActivity, BookActivity::class.java)
//        intent.putExtra("book", netBook.rawBook)
//        intent.putExtra("canAddFav", false)
//        startActivity(intent)
    }

    private fun useAnimMode(mode: Int) {
        readerView.post {
            getAnimModeTv(animMode)?.background = unSelectedDrawable
            getAnimModeTv(mode)?.background = selectedDrawable
            animMode = mode
            readerView.setPageAnimMode(mode)
        }
        defaultSharedPreferences.editor { putInt(pageModeSP, mode) }
    }

    private fun getAnimModeTv(mode: Int): View? {
        when (mode) {
            PageView.PAGE_MODE_COVER -> {
                return preview_anim_cover
            }
            PageView.PAGE_MODE_NONE -> {
                return preview_anim_none
            }
            PageView.PAGE_MODE_SCROLL -> {
                return preview_anim_vertical
            }
            PageView.PAGE_MODE_SIMULATION -> {
                return preview_anim_sim
            }
            else -> {
                return null
            }
        }
    }

    private fun showSecondControl() {
        preview_control_setting_layout.visibility = View.VISIBLE
        preview_control_base_layout.visibility = View.INVISIBLE
    }

    private fun hideSecondControl() {
        preview_control_setting_layout.visibility = View.INVISIBLE
        preview_control_base_layout.visibility = View.VISIBLE
    }

    private fun fixWindow() {
        NotchTools.getFullScreenTools().fullScreenUseStatus(this, object : OnNotchCallBack {
            override fun onNotchPropertyCallback(notchProperty: NotchProperty?) {
                if (notchProperty != null && notchProperty.isNotch) {
                    preview_control_top.post {
                        preview_control_top.setPadding(0, notchProperty.marginTop, 0, 0)
                    }
                    readerView.pageLoader.setHairHeight(notchProperty.marginTop)
                }
            }

        })
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            val decorView = window.decorView
//            val displayCutout = decorView.rootWindowInsets?.displayCutout
//            val rects = displayCutout?.boundingRects ?: return
//
//            if (rects.isNotEmpty()) {
//                //是刘海屏
//                Log.d("PreviewActivity", "刘海屏")
//                preview_control_top.post {
//                    preview_control_top.setPadding(0, displayCutout.safeInsetTop, 0, 0)
//                }
//                readerView.pageLoader.setHairHeight(displayCutout.safeInsetTop)
//            }
//
//        }
    }

    private fun setNavigationColor() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.decorView.systemUiVisibility = uiOptions
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTextSize(textSize: Int) {
        readerView.textSize = textSize
        preview_textSize.text = "$textSize"
    }

    override fun onResume() {
        setNavigationColor()
        super.onResume()
    }

    override fun onDestroy() {
        preview_control_top.clearSlideAnimation()
        preview_control_bottom.clearSlideAnimation()
        pool.shutdownNow()
        super.onDestroy()
    }

    private val whiteBg by lazy {
        getBitmap(resources.getColor(R.color.preview_theme_white))
    }

    private val greenBg by lazy {
        getBitmap(resources.getColor(R.color.preview_theme_green))
    }

    private fun getBitmap(color: Int): Bitmap {

        val width = preview_bg.width
        val height = preview_bg.height
        Log.d("previewActivity", "width:$width  height:$height")


        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(color)

        var alphaNoiseReg: Bitmap? = null
        try {
            val argb = IntArray(width * height)

            val noiseReg = BitmapFactory.decodeResource(
                    resources, R.drawable.bg_paper, BitmapFactory
                    .Options()
            )
            noiseReg.getPixels(argb, 0, noiseReg.width, 0, 0, noiseReg.width, noiseReg.height)
            val alpha = 140 * 255 / 100

            for (i in 0 until argb.size) {
                argb[i] = (alpha.shl(24)) or (argb[i] and 0x00FFFFFF)
            }
            alphaNoiseReg =
                    Bitmap.createBitmap(argb, noiseReg.width, noiseReg.height, Bitmap.Config.ARGB_8888)
            noiseReg.recycle()

            val shader = BitmapShader(alphaNoiseReg!!, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            val matrix = Matrix()
            shader.setLocalMatrix(matrix)
            val paint = Paint()
            paint.shader = shader
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
            paint.color = color
            canvas.drawPaint(paint)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        } finally {
            alphaNoiseReg?.recycle()
        }

        return bitmap
    }

    private fun setTvTheme(themeId: Int) {
        readerView.post {
            when (themeId) {
                theme_white -> {
                    readerView.pageBackground = resources.getColor(R.color.preview_theme_white)
//                readerView.pageBackground = Color.parseColor("#E5DECF")
                    readerView.textColor = Color.argb(255, 91, 60, 30)
                    //顺序不能变，先textcolor，后paintColor，否则会被覆盖
                    readerView.pageLoader.batteryPaint.color = Color.argb(255, 173, 134, 97)
                    readerView.pageLoader.tipPaint.color = Color.argb(255, 173, 134, 97)
                    readerView.backGround = whiteBg
                }
                theme_dark -> {
                    readerView.pageBackground = resources.getColor(R.color.preview_theme_dark)
                    readerView.textColor = Color.argb(255, 77, 89, 106)
                    readerView.pageLoader.batteryPaint.color = Color.argb(255, 45, 53, 66)
                    readerView.pageLoader.tipPaint.color = Color.argb(255, 45, 53, 66)
                    readerView.backGround = null
                }
                theme_green -> {
                    readerView.pageBackground = resources.getColor(R.color.preview_theme_green)
                    readerView.textColor = Color.argb(255, 28, 67, 38)
                    readerView.pageLoader.batteryPaint.color = Color.argb(255, 162, 198, 155)
                    readerView.pageLoader.tipPaint.color = Color.argb(255, 162, 198, 155)
                    readerView.backGround = greenBg
                }
                theme_paper -> {
                    readerView.pageBackground = Color.parseColor("#E5DECF")
                    readerView.textColor = resources.getColor(R.color.textBlack)
                }
            }

            val fontPath = defaultSharedPreferences.getString("fontPath", "")
            if (fontPath != null && fontPath.isNotEmpty()) {
                //我认为有可能用户清除缓存后，加载了不存在的文件会报错
                try {
                    readerView.pageLoader.textPaint.typeface = Typeface.createFromFile(fontPath)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    ToastUtil.onError(e.message)
                }
            }
            readerView.drawCurPage(false)
        }
    }

    //音量键设置
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            //什么都不做
            true
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            //什么都不做
            true
        } else
            super.onKeyDown(keyCode, event)
    }

    //音量键设置
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
//                if (readerView.pageAnim is HorizonPageAnim){
//                    (readerView.pageAnim as HorizonPageAnim).runAnim(true)
//                }else{
                readerView.clearAnimation()
                readerView.pageLoader.next()
//                }
                true
            }
            KeyEvent.KEYCODE_VOLUME_UP -> {
//                if (readerView.pageAnim is HorizonPageAnim){
//                    (readerView.pageAnim as HorizonPageAnim).runAnim(false)
//                }else{
                readerView.clearAnimation()
                readerView.pageLoader.prev()
//                }
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }

}