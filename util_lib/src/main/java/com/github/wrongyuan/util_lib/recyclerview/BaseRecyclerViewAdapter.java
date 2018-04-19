package com.github.wrongyuan.util_lib.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Wanghy
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> {
    protected List<T> dataList;
    protected RecyclerItemClickListener<T> mListViewItemClickListener;

    public BaseRecyclerViewAdapter() {
        dataList = new ArrayList<>();
    }

    public BaseRecyclerViewAdapter(@NonNull List<T> list) {
        dataList = list;
    }

    public void setRecyclerItemClickListener(RecyclerItemClickListener<T> recyclerItemClickListener) {
        this.mListViewItemClickListener = recyclerItemClickListener;
    }

    public List<T> getDataList() {
        return dataList;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder<T> holder, int position) {
        holder.setRecyclerItemClickListener(mListViewItemClickListener);
        holder.onBindItemData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(T data) {
        if (data != null) {
            dataList.add(data);
            notifyItemInserted(dataList.size() - 1);
        }
    }

    public void addDataList(List<T> list, boolean clearAll) {
        if (clearAll) {
            dataList.clear();
        }
        if (list != null && list.size() > 0) {
            int startPosition = dataList.size();
            dataList.addAll(list);
            if (!clearAll) {
                notifyItemRangeInserted(startPosition, list.size());
            }
        }
        if (clearAll) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

}