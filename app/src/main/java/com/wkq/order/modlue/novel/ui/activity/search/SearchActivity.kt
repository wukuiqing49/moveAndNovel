package com.wkq.order.modlue.novel.ui.activity.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.donkingliang.labels.LabelsView
import com.wkq.baseLib.utlis.KeyboardUtils
import com.wkq.order.utils.StatusBarUtil
import com.wkq.order.R
import com.wkq.order.modlue.novel.ui.activity.preview.BaseActivity
import com.wkq.order.modlue.novel.ui.activity.preview.ToastUtil
import com.zia.easybookmodule.bean.Book


import kotlinx.android.synthetic.main.activity_search_novel.*


import kotlinx.android.synthetic.main.item_catalog.view.*

class SearchActivity : BaseActivity() {

    private lateinit var viewModel: SearchViewModel

    private val searchFragment = SearchResultFragment()
    private val recommendFragment = RecommendFragment()

    private var searchKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_novel)
//        StatusBarUtil.setLightMode(this)
        StatusBarUtil.setColor(this,resources.getColor(R.color.colorPrimary),0)
        searchKey = intent.getStringExtra("searchKey")

        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        addFragment(searchFragment)
        addFragment(recommendFragment)

        recommendFragment.labelClickListener = LabelsView.OnLabelClickListener { label, data, position ->
            searchEt.setText(label.text)
        }

        recommendFragment.itemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            searchEt.setText(view.item_catalog_name.text)
        }

        initObservers()

        initSearchLayout()

        if (searchKey != null && searchKey!!.isNotEmpty()) {
            searchEt.setText(searchKey)
            search(searchKey)
        }
    }

    private fun initObservers() {
        viewModel.loadBooks.observe(this, Observer<List<Book>> {
            if (it != null) {
//                hideFragment(recommendFragment)
//                ToastUtil.onSuccess("搜索到${it.size}本书籍")
//                searchFragment.bookAdapter?.freshBooks(ArrayList(it))
//                searchFragment.searchRv?.scrollToPosition(0)
//                hideDialog()
            }
        })

        viewModel.partBooks.observe(this, Observer {
            if (it != null && searchKey != null) {
                searchFragment.searchRv?.post {
                    searchFragment.bookAdapter?.addBooks(searchKey!!, it)
                    searchFragment.searchRv?.scrollToPosition(0)
                }
//                hideDialog()
            }
        })

        viewModel.error.observe(this, Observer {
            it?.printStackTrace()
            ToastUtil.onError(it?.message)
//            hideDialog()
        })

        viewModel.toast.observe(this, Observer {
            ToastUtil.onInfo(it)
        })

//        viewModel.dialogMessage.observe(this, Observer {
//            updateDialog(it)
//        })
//
//        viewModel.dialogProgress.observe(this, Observer {
//            updateDialog(it)
//        })
    }

    private fun search(name: String?) {
        viewModel.shutDown()
        if (name != null && name.isNotEmpty()) {
            recommendFragment.addHistory(name)
            searchFragment.bookAdapter?.clear()
            searchKey = name
            viewModel.search(name)
            hideFragment(recommendFragment)
        }
    }

    private fun initSearchLayout() {
        search_edit_cancel.setOnClickListener {
            searchEt.setText("")
        }

        searchEt.setOnEditorActionListener { _, actionId, event ->
            if ((actionId == KeyEvent.KEYCODE_UNKNOWN || actionId == KeyEvent.KEYCODE_SEARCH || actionId == KeyEvent.KEYCODE_HOME)
                && event != null && event.action == KeyEvent.ACTION_DOWN
            ) {
                search(searchEt.text.toString())
                KeyboardUtils.hideSoftInput(searchBt)
                search_edit_cancel.visibility = View.INVISIBLE
                return@setOnEditorActionListener true
            }
            false
        }

        searchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!searchEt.text?.toString().isNullOrEmpty()) {
                    search_edit_cancel.visibility = View.VISIBLE
                } else {
                    search_edit_cancel.visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        searchBt.setOnClickListener {
            search(searchEt.text.toString())
        }
    }

//    private fun initDialog() {
//        updateDialog(0)
//        updateDialog("")
//    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.search_fragmentLayout, fragment)
        transaction.commit()
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.search_fragmentLayout, fragment)
        transaction.commit()
    }

    private fun hideFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragment)
        transaction.commit()
    }

//    private fun updateDialog(progress: Int?) {
//        if (progress != null) {
//            dialog.progress = progress
//        }
//        if (!dialog.isShowing) {
//            dialog.show()
//        }
//    }
//
//    private fun updateDialog(msg: String?) {
//        if (msg != null) {
//            dialog.setMessage(msg)
//        }
//        if (!dialog.isShowing) {
//            dialog.show()
//        }
//    }
//
//    private fun hideDialog() {
//        dialog.dismiss()
//    }
}
