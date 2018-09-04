package com.github.wanghy360.base.text;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;

/**
 * Created by Wanghy on 2017/8/11
 */
public class ImageTextImage {
    /**
     * 格式：图片+文字+图片
     *
     * @param context context
     * @param before  文字前的图片
     * @param id      图片id
     * @param after   文字后的图片
     * @return 混合后的字符串
     */
    public static CharSequence generateText(@NonNull Context context, @Nullable CharSequence before, @DrawableRes int id, @Nullable CharSequence after) {
        if (id == 0) {
            StringBuilder stringBuilder = new StringBuilder("");
            if (before != null) {
                stringBuilder.append(before);
            }
            if (after != null) {
                stringBuilder.append(after);
            }
            return stringBuilder;
        }
        if (before == null) {
            before = "";
        } else {
            before = before + " ";
        }
        if (after == null) {
            after = "";
        } else {
            after = " " + after;
        }
        Drawable drawable = context.getResources().getDrawable(id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        String content = before + "1" + after;

        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new CenteredImageSpan(drawable), before.length(), before.length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
