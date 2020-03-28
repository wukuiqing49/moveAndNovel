package com.wkq.order.modlue.htmlmove.ui.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.binioter.guideview.Component;
import com.wkq.order.R;


/**
 * Created by binIoter on 16/6/17.
 */
public class SearchNoveComponent implements Component {

  @Override
  public View getView(LayoutInflater inflater) {

    LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_move_search, null);

    return ll;
  }
  @Override public int getAnchor() {
    return Component.ANCHOR_TOP;
  }

  @Override public int getFitPosition() {
    return Component.FIT_END;
  }

  @Override public int getXOffset() {
    return 20;
  }

  @Override public int getYOffset() {
    return -10;
  }
}
