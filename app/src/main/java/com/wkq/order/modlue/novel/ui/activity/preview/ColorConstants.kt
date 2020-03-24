package com.wkq.order.modlue.novel.ui.activity.preview

import com.wkq.order.R
import com.wkq.order.application.OrderApplication


/**
 * Created by zia on 2019/4/18.
 */
class ColorConstants {
    companion object {
        val RANK_FIRST = OrderApplication.getContext().resources.getColor(R.color.rank_first)
        val RANK_SECOND = OrderApplication.getContext().resources.getColor(R.color.rank_second)
        val RANK_THIRD = OrderApplication.getContext().resources.getColor(R.color.rank_third)
        val RANK_NORMAL = OrderApplication.getContext().resources.getColor(R.color.rank_normal)

        val RED = OrderApplication.getContext().resources.getColor(R.color.qmui_config_color_red)
        val TEXT_BLACK = OrderApplication.getContext().resources.getColor(R.color.textBlack)
        val GREY = OrderApplication.getContext().resources.getColor(R.color.grey)

        val TEXT_BLACK_LIGHT = OrderApplication.getContext().resources.getColor(R.color.textBlack_light)

        val CLASSIFY1 = OrderApplication.getContext().resources.getColor(R.color.classify_1)
        val CLASSIFY2 = OrderApplication.getContext().resources.getColor(R.color.classify_2)
        val CLASSIFY3 = OrderApplication.getContext().resources.getColor(R.color.classify_3)
        val CLASSIFY4 = OrderApplication.getContext().resources.getColor(R.color.classify_4)
    }
}
