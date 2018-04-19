package com.github.wrongyuan.agile_tools;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.wrongyuan.util_lib.recyclerview.SimpleRecyclerViewAdapter;

/**
 * Created by Wanghy on 2018/4/19.
 */

public abstract class SimpleListActivity extends BaseActivity {
    protected SimpleRecyclerViewAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initSimpleRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        simpleAdapter = new SimpleRecyclerViewAdapter();
        recyclerView.setAdapter(simpleAdapter);
    }
}
