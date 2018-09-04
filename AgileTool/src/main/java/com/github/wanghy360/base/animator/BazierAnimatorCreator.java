package com.github.wanghy360.base.animator;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;

/**
 * 贝塞尔曲线
 */
public class BazierAnimatorCreator {

    public static ValueAnimator create(final View target, PointF start, PointF end, PointF pointF1, PointF pointF2) {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierEvaluator(pointF1, pointF2), start, end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                target.setTranslationX(pointF.x);
                target.setTranslationY(pointF.y);
            }
        });
        return valueAnimator;
    }
}
