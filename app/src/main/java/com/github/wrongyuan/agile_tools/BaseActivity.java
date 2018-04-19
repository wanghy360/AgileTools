package com.github.wrongyuan.agile_tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * must be called after {@link android.app.Activity#setContentView(int)}
     */
    protected abstract void initView();

    protected void navigateTo(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
