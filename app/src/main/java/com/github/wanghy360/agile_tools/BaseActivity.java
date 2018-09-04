package com.github.wanghy360.agile_tools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    /**
     * must be called after {@link #setContentView(int)}
     */
    protected abstract void initView();

    protected void navigateTo(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
