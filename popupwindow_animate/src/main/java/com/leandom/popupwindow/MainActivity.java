package com.leandom.popupwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private ImageSelectPopupWindow imageSelectPopupWindow;
    private ViewGroup layoutBottom;
    private View shadeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutBottom = (ViewGroup) findViewById(R.id.layout_bottom);
        shadeView = findViewById(R.id.view_shade);

        final int screenHeight = getResources().getDisplayMetrics().heightPixels;

        layoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (imageSelectPopupWindow == null) {
                    imageSelectPopupWindow = new ImageSelectPopupWindow(MainActivity.this,
                            ViewGroup.LayoutParams.MATCH_PARENT, (int) (screenHeight * 0.7));
                    imageSelectPopupWindow.setOnDismissAnimatorListener(new ImageSelectPopupWindow.OnDismissAnimatorListener() {
                        @Override
                        public void onStartDismiss() {
                            dismissShade();
                        }

                        @Override
                        public void onDismiss() {

                        }
                    });
                }
                if (imageSelectPopupWindow.isShowing()) {
                    imageSelectPopupWindow.dismiss();
                } else {
                    imageSelectPopupWindow.showAsDropDown(layoutBottom, 0, 0);
                    showShade();
                }
            }
        });

    }

    private void dismissShade() {
        shadeView.animate().alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        shadeView.setVisibility(View.GONE);
                    }
                }).setDuration(300).start();
    }

    private void showShade() {
        shadeView.setVisibility(View.VISIBLE);
        shadeView.setAlpha(0);
        shadeView.animate().alpha(1)
                .setListener(null).setDuration(300).start();
    }
}
