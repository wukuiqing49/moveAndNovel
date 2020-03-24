package com.wkq.order.modlue.novel.ui.activity.search


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wkq.order.R
import com.wkq.order.modlue.novel.ui.activity.preview.BookActivity
import com.zia.easybookmodule.bean.Book
import com.zia.page.search.BookAdapter
import kotlinx.android.synthetic.main.fragment_search_result.*


/**
 * Created by zzzia on 2019/4/19.
 * 搜索结果fragment
 */
class SearchResultFragment : BaseFragment(), BookAdapter.BookSelectListener {


    var searchRv: androidx.recyclerview.widget.RecyclerView? = null
    var bookAdapter: BookAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        searchRv = searchResult_Rv
        bookAdapter = BookAdapter(this)
        searchResult_Rv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        searchResult_Rv.adapter = bookAdapter
    }

    override fun onBookSelect(itemView: View, book: Book) {
        val intent = Intent(context, BookActivity::class.java)
        intent.putExtra("book", book)
        intent.putExtra("scroll", false)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val p = arrayListOf<Pair<View, String>>(Pair.create(itemView.item_book_image, "book_image"))
//            val options = ActivityOptions.makeSceneTransitionAnimation(activity, *Java2Kotlin.getPairs(p))
//            startActivity(intent, options.toBundle())
//        } else {
//            startActivity(intent)
//        }
        startActivity(intent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }
}
