package com.github.wanghy360.base;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;


/**
 * Created by wanghy360
 */
public class ImageLoader {
    private static class LazyHolder {
        private static final ImageLoader sInstance = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return LazyHolder.sInstance;
    }

    public static void loadImg(View view, @DrawableRes int resId) {
    }

    private static String getResUrl(Context context, @DrawableRes int resId) {
        return "res://" + context.getPackageName() + "/" + resId;
    }
}