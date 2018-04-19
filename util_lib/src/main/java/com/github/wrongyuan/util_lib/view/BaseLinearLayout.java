package com.github.wrongyuan.util_lib.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public abstract class BaseLinearLayout extends LinearLayout {

    public BaseLinearLayout(Context context) {
        this(context, null);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayout(context, getLayoutResourceId());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

    protected abstract int getLayoutResourceId();

    protected abstract void initView();

    protected void showView(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    protected <V extends View> V bindView(@IdRes int id) {
        //noinspection unchecked
        return (V) findViewById(id);
    }

}