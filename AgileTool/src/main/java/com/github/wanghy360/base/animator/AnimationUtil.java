package com.github.wanghy360.base.animator;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import java.lang.ref.WeakReference;

public class AnimationUtil {
    public static void startAnimation(Context context, View view, int animResId, long durationMillis, boolean showWhileEnd) {
        startAnimation(context, view, animResId, durationMillis, showWhileEnd ? View.VISIBLE : View.GONE);
    }

    public static void startAnimation(Context context, View view, int animResId, long durationMillis, int visibility) {
        if (context == null || view == null)
            return;
        Animation anim = android.view.animation.AnimationUtils.loadAnimation(context.getApplicationContext(), animResId);
        anim.setDuration(durationMillis);
        anim.setAnimationListener(new ShowHideAnimationListener(view, visibility));
        view.startAnimation(anim);
    }

    private static class ShowHideAnimationListener implements AnimationListener {

        private WeakReference<View> weakView;
        private int visibility;

        public ShowHideAnimationListener(View view, int visibility) {
            super();
            this.weakView = new WeakReference<>(view);
            this.visibility = visibility;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            View view = weakView.get();
            if (view != null) {
                view.clearAnimation();
                view.setVisibility(visibility);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }
    }

    public static AlphaAnimation getAlphaHideAnimation() {
        return getAlphaAnimation(1.0f, 0.0f, 300);
    }

    public static AlphaAnimation getAlphaHideAnimation(int durationMillis) {
        return getAlphaAnimation(1.0f, 0.0f, durationMillis);
    }

    public static AlphaAnimation getAlphaShowAnimation() {
        return getAlphaAnimation(0.0f, 1.0f, 300);
    }

    public static AlphaAnimation getAlphaShowAnimation(int durationMillis) {
        return getAlphaAnimation(0.0f, 1.0f, durationMillis);
    }

    public static AlphaAnimation getAlphaAnimation(float from, float to) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(300);
        return alphaAnimation;
    }

    public static AlphaAnimation getAlphaAnimation(float from, float to, int durationMillis) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(from, to);
        alphaAnimation.setDuration(durationMillis);
        return alphaAnimation;
    }
}
