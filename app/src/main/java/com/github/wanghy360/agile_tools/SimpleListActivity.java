package com.github.wanghy360.agile_tools;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.wanghy360.base.recyclerview.SimpleRecyclerViewAdapter;

/**
 * Created by Wanghy on 2018/4/19.
 */

public abstract class SimpleListActivity extends BaseActivity {
    protected SimpleRecyclerViewAdapter simpleAdapter;

    public void initSimpleRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        simpleAdapter = new SimpleRecyclerViewAdapter();
        recyclerView.setAdapter(simpleAdapter);
    }
}
