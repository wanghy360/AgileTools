package com.github.wrongyuan.util_lib.recyclerview;

import android.view.View;

/**
 * Created by Wanghy on 2017/9/25
 */
public interface RecyclerItemClickListener<T> {
    void onItemClick(View view, int position, T item);
}
