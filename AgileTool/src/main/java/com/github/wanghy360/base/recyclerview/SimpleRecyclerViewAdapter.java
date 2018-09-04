package com.github.wanghy360.base.recyclerview;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by WangHongyuan on 2018/4/19.
 */

public class SimpleRecyclerViewAdapter extends BaseRecyclerViewAdapter<String> {
    public SimpleRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimpleViewHolder(parent);
    }

    private static class SimpleViewHolder extends BaseRecyclerViewHolder<String> {
        private TextView textView;

        public SimpleViewHolder(ViewGroup root) {
            super(root, android.R.layout.simple_list_item_1);
            textView = itemView.findViewById(android.R.id.text1);
        }

        @Override
        public void onBindItemData(String item) {
            super.onBindItemData(item);
            textView.setText(item);
        }
    }
}
