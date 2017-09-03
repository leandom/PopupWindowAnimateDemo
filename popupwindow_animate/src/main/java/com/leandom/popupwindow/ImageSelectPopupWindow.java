package com.leandom.popupwindow;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by leandom on 2017/9/2.
 * 自定义PopupWindow动画。
 */

public class ImageSelectPopupWindow extends AnimatorPopupWindow {

    private View contentView;
    private final int duration = 300;

    public ImageSelectPopupWindow(Context context, int width, int height) {
        super();
        View view = LayoutInflater.from(context).inflate(R.layout.pop_custom_animate, null);
        contentView = view.findViewById(R.id.content);
        setContentView(view);
        setWidth(width);
        setHeight(height);
        // 去掉默认的动画效果(showAsDropDown可能会自带默认动画)
        // setAnimationStyle(R.style.custom_anim_pop);
        // 下面两行是为了让PopupWindow能够相应返回按键
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    protected void animateIn() {

        int height = contentView.getHeight();
        contentView.setTranslationY(height);
        contentView.animate().translationY(0).setDuration(duration)
                .setListener(null).start();

    }


    protected void animateOut() {

        int height = contentView.getHeight();
        contentView.animate().translationY(height).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                ImageSelectPopupWindow.this.onAnimationEnd();
                contentView.animate().setListener(null);
            }
        }).setDuration(duration).start();

    }

}
