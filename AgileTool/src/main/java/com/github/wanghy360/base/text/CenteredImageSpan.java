package com.github.wanghy360.base.text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.ImageSpan;

public class CenteredImageSpan extends ImageSpan {

    public CenteredImageSpan(Context context, final int drawableRes) {
        super(context, drawableRes);
    }

    public CenteredImageSpan(Drawable drawable) {
        super(drawable);
    }

    /**
     * <a href = "https://www.2cto.com/kf/201703/616815.html">参考</a>
     * 局限性：图片的高度必须小于等于单行文字的高度，否则显示不完全
     */
    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text,
                     int start, int end, float x,
                     int top, int y, int bottom, @NonNull Paint paint) {
        // image to draw
        Drawable b = getDrawable();
        // font metrics of text to be replaced
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2
                - b.getBounds().bottom / 2;

        canvas.save();
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}