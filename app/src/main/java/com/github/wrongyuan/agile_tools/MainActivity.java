package com.github.wrongyuan.agile_tools;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {

    }

    public void onNetChange(View view) {
        navigateTo(NetChangeActivity.class);
    }
}
