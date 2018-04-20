package com.github.wanghy360.base.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public abstract class BaseRelativeLayout extends RelativeLayout {
    public BaseRelativeLayout(Context context) {
        this(context, null);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context, getLayoutResourceId());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateLayout(context, getLayoutResourceId());
    }

    private void inflateLayout(Context context, @LayoutRes int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(context);
        try {
            inflater.inflate(layoutResId, this, true);
            initView();
        } catch (OutOfMemoryError localOutOfMemoryError) {
            localOutOfMemoryError.printStackTrace();
        }
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected abstract void initView();

    protected void showView(View view, boolean show) {
        showView(view, show, false);
    }

    protected void showView(View view, boolean show, boolean showAnim) {
        showView(view, show, showAnim, 500);
    }

    protected void showView(View view, boolean show, boolean showAnim, int duration) {
        if (view == null) return;
        int visibility = show ? View.VISIBLE : View.GONE;
        if (view.getVisibility() == visibility) return;

        if (showAnim) {
            AlphaAnimation alphaAnimation;
            if (show) {
                alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            } else {
                alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            }
            alphaAnimation.setDuration(duration);
            alphaAnimation.setAnimationListener(new AnimationListener(view, visibility));
            view.startAnimation(alphaAnimation);
        } else {
            view.setVisibility(visibility);
        }
    }

    private static class AnimationListener implements Animation.AnimationListener {
        private View view;
        private int visibility;

        public AnimationListener(View view, int visibility) {
            this.view = view;
            this.visibility = visibility;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            view.setVisibility(visibility);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    protected <V extends View> V bindView(@IdRes int id) {
        //noinspection unchecked
        return (V) findViewById(id);
    }

}
