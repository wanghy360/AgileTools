package com.github.wanghy360.base.recyclerview;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WangHongyuan on 2018/4/19.
 */

public class SimpleRecyclerViewAdapter extends BaseRecyclerViewAdapter<String> {
    public SimpleRecyclerViewAdapter() {
    }

    public SimpleRecyclerViewAdapter(@NonNull List<String> list) {
        super(list);
    }

    @Override
    public BaseRecyclerViewHolder<String> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(parent);
    }

    private static class SimpleViewHolder extends BaseRecyclerViewHolder<String> {
        private TextView textView;

        public SimpleViewHolder(ViewGroup root) {
            super(root, android.R.layout.simple_list_item_1);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }

        @Override
        public void onBind(String item) {
            textView.setText(item);
        }
    }
}
