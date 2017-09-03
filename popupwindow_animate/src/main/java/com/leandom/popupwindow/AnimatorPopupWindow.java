package com.leandom.popupwindow;


import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by leandom on 2017/9/2.
 * 自定义PopupWindow动画。
 */

public abstract class AnimatorPopupWindow extends PopupWindow {

    private OnDismissAnimatorListener onDismissAnimatorListener;

    public AnimatorPopupWindow() {
        init();
    }

    public AnimatorPopupWindow(Context context) {
        super(context);
        init();
    }


    public AnimatorPopupWindow(View contentView) {
        this(contentView, 0, 0);
    }

    public AnimatorPopupWindow(int width, int height) {
        this(null, width, height);
    }

    public AnimatorPopupWindow(View contentView, int width, int height) {
        this(contentView, width, height, false);
    }

    public AnimatorPopupWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
        init();
    }

    protected void init() {
        // 去掉默认的动画效果(showAsDropDown可能会自带默认动画)
        setAnimationStyle(R.style.custom_anim_pop);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        postAnimateIn(anchor);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        postAnimateIn(parent);
    }

    private void postAnimateIn(View postView) {
        postView.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateIn();
            }
        }, 1);
    }

    /**
     * 直接关闭PopupWindow，没有动画效果
     */
    public void superDismiss() {
        super.dismiss();
        if (onDismissAnimatorListener != null) {
            onDismissAnimatorListener.onDismiss();
        }
    }

    @Override
    public void dismiss() {
        animateOut();
        if (onDismissAnimatorListener != null) {
            onDismissAnimatorListener.onStartDismiss();
        }
    }

    public void onAnimationEnd() {
        superDismiss();
    }

    /**
     * PopupWindow进入屏幕动画
     */
    protected abstract void animateIn();

    /**
     * PopupWindow从屏幕消失动画。在动画执行结束时请调用 {@link AnimatorPopupWindow#onAnimationEnd()}
     */
    protected abstract void animateOut();

    public void setOnDismissAnimatorListener(OnDismissAnimatorListener onDismissAnimatorListener) {
        this.onDismissAnimatorListener = onDismissAnimatorListener;
    }

    public interface OnDismissAnimatorListener {

        /**
         * 开始消失，这个时候PopupWindow还在，只是在执行消失动画
         */
        public void onStartDismiss();

        /**
         * 完全消失
         */
        public void onDismiss();
    }


}
