package com.wkq.order.modlue.htmlmove.ui.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.binioter.guideview.Component;
import com.wkq.order.R;


/**
 * Created by binIoter on 16/6/17.
 */
public class SimpleComponent implements Component {

  @Override
  public View getView(LayoutInflater inflater) {

    LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_frends, null);

    return ll;
  }

  @Override
  public int getAnchor() {
    return Component.ANCHOR_TOP;
  }

  @Override
  public int getFitPosition() {
    return Component.FIT_CENTER;
  }

  @Override
  public int getXOffset() {
    return 10;
  }

  @Override
  public int getYOffset() {
    return -10;
  }
}
