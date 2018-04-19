package com.github.wrongyuan.util_lib.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public abstract class BaseFrameLayout extends FrameLayout {
    public Context context;
    public LayoutInflater inflater;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        try {
            if (getLayoutResourceId() != -1) {
                this.inflater.inflate(getLayoutResourceId(), this, true);
                initView();
            }
        } catch (OutOfMemoryError localOutOfMemoryError) {
            localOutOfMemoryError.printStackTrace();
        }
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        try {
            if (getLayoutResourceId() != -1) {
                this.inflater.inflate(getLayoutResourceId(), this, true);
                initView();
            }
        } catch (OutOfMemoryError localOutOfMemoryError) {
            localOutOfMemoryError.printStackTrace();
        }
    }

    public BaseFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseFrameLayout(Context context) {
        this(context, null);
    }

    protected abstract int getLayoutResourceId();

    protected abstract void initView();

    protected void showView(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

}
