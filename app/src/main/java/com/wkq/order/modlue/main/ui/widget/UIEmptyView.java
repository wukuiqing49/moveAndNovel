package com.wkq.order.modlue.main.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.wkq.order.R;


/**
 * 用于显示界面的 loading、错误信息提示等状态。
 * <p>
 * 提供了一个 LoadingView、一行标题、一行说明文字、一个按钮, 可以使用 {@link #show(boolean, String, String, String, OnClickListener)} 系列方法控制这些控件的显示内容
 * </p>
 */
public class UIEmptyView extends FrameLayout {
    private QMUILoadingView mLoadingView;
    private TextView mTitleTextView;
    private TextView mDetailTextView;
    protected TextView mButton;
    private ImageView mEmptyImage;

    public UIEmptyView(Context context) {
        this(context, null);
    }

    public UIEmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_ui_empty_view, this, true);
        mLoadingView = (QMUILoadingView) findViewById(R.id.empty_view_loading);
        mTitleTextView = (TextView) findViewById(R.id.empty_view_title);
        mDetailTextView = (TextView) findViewById(R.id.empty_view_detail);
        mButton = (TextView) findViewById(R.id.empty_view_button);
        mEmptyImage = (ImageView) findViewById(R.id.empty_image);
    }

    /**
     * 显示emptyView
     *
     * @param loading               是否要显示loading
     * @param titleText             标题的文字，不需要则传null
     * @param detailText            详情文字，不需要则传null
     * @param buttonText            按钮的文字，不需要按钮则传null
     * @param onButtonClickListener 按钮的onClick监听，不需要则传null
     */
    public void show(boolean loading, String titleText, String detailText, String buttonText, OnClickListener onButtonClickListener) {
        setLoadingShowing(loading);
        setTitleText(titleText);
        setDetailText(detailText);
        setButton(buttonText, onButtonClickListener);
        setImage(-1);
        show();
    }

    /**
     * 显示emptyView
     *
     * @param loading               是否要显示loading
     * @param titleText             标题的文字，不需要则传null
     * @param detailText            详情文字，不需要则传null
     * @param buttonText            按钮的文字，不需要按钮则传null
     * @param resImageId            视图view，不需要则传-1
     * @param onButtonClickListener 按钮的onClick监听，不需要则传null
     */
    public void show(boolean loading, String titleText, String detailText, String buttonText, int resImageId, OnClickListener onButtonClickListener) {
        setLoadingShowing(loading);
        setTitleText(titleText);
        setDetailText(detailText);
        setButton(buttonText, onButtonClickListener);
        setImage(resImageId);
        show();
    }

    /**
     * 用于显示emptyView并且只显示loading的情况，此时title、detail、button都被隐藏
     *
     * @param loading 是否显示loading
     */
    public void show(boolean loading) {
        setLoadingShowing(loading);
        setTitleText(null);
        setDetailText(null);
        setButton(null, null);
        setImage(-1);
        show();
    }

    /**
     * 用于显示纯文本的简单调用方法，此时loading、button均被隐藏
     *
     * @param titleText  标题的文字，不需要则传null
     * @param detailText 详情文字，不需要则传null
     */
    public void show(String titleText, String detailText) {
        setLoadingShowing(false);
        setTitleText(titleText);
        setDetailText(detailText);
        setButton(null, null);
        setImage(-1);
        show();
    }

    /**
     * 用于显示纯文本和图的简单调用方法，此时loading、button均被隐藏
     *
     * @param titleText  标题的文字，不需要则传null
     * @param detailText 详情文字，不需要则传null
     * @param resImageId 视图view，不需要则传-1
     */
    public void show(String titleText, String detailText, int resImageId) {
        setLoadingShowing(false);
        setTitleText(titleText);
        setDetailText(detailText);
        setButton(null, null);
        setImage(resImageId);
        show();
    }

    /**
     * 显示Icon 文本 描述 按钮的方法
     *
     * @param titleText
     * @param detailText
     * @param buttonText
     * @param resImageId
     * @param onButtonClickListener
     */
    public void show(String titleText, String detailText, String buttonText, int resImageId, OnClickListener onButtonClickListener) {
        setLoadingShowing(false);
        setTitleText(titleText);
        setDetailText(detailText);
        setButton(buttonText, onButtonClickListener);
        setImage(resImageId);
        show();
    }

    private void showLoadError() {

    }

    private void showDatqaEmpty() {

    }

    private void showNoConnect() {

    }

    /**
     * 显示emptyView，不建议直接使用，建议调用带参数的show()方法，方便控制所有子View的显示/隐藏
     */
    public void show() {
        setVisibility(VISIBLE);
    }

    /**
     * 隐藏emptyView
     */
    public void hide() {
        setVisibility(GONE);
        setLoadingShowing(false);
        setTitleText(null);
        setDetailText(null);
        setImage(-1);
        setButton(null, null);
    }

    public boolean isShowing() {
        return getVisibility() == VISIBLE;
    }

    public boolean isLoading() {
        return mLoadingView.getVisibility() == VISIBLE;
    }

    public void setLoadingShowing(boolean show) {
        mLoadingView.setVisibility(show ? VISIBLE : GONE);
    }

    public void setTitleText(String text) {
        mTitleTextView.setText(text);
        mTitleTextView.setVisibility(text != null ? VISIBLE : GONE);
    }

    public void setDetailText(String text) {
        mDetailTextView.setText(text);
        mDetailTextView.setVisibility(text != null ? VISIBLE : GONE);
    }

    public void setTitleColor(int color) {
        mTitleTextView.setTextColor(color);
    }

    public void setDetailColor(int color) {
        mDetailTextView.setTextColor(color);
    }

    public void setButton(String text, OnClickListener onClickListener) {
        mButton.setText(text);
        mButton.setVisibility(text != null ? VISIBLE : GONE);
        mButton.setOnClickListener(onClickListener);
    }

    public void setImage(int image) {
        mEmptyImage.setVisibility(image <= 0 ? View.GONE : View.VISIBLE);
        if (image > 0) mEmptyImage.setImageResource(image);
    }

    public void setImageClickListener(OnClickListener listener) {
        if (mEmptyImage != null) mEmptyImage.setOnClickListener(listener);
    }
}
