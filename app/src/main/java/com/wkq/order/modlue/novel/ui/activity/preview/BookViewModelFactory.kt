package com.wkq.order.modlue.novel.ui.activity.preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zia.easybookmodule.bean.Book

class BookViewModelFactory(private val book: Book) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookViewModel(book) as T
    }
}