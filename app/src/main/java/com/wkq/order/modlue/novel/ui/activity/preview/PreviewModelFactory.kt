package com.wkq.order.modlue.novel.ui.activity.preview;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wkq.order.modlue.novel.ui.activity.preview.PreviewModel

/**
 * Created by zia on 2018/12/1.
 */
class PreviewModelFactory(private val bookName: String, private val siteName: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PreviewModel(bookName, siteName) as T
    }
}