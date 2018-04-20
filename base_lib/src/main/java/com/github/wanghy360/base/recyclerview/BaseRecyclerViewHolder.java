package com.github.wanghy360.base.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * created by Wanghy
 */
public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    protected WeakReference<RecyclerItemClickListener<T>> listenerWeakRef;
    protected T item;

    public BaseRecyclerViewHolder(ViewGroup root, @LayoutRes int layoutRes) {
        super(LayoutInflater.from(root.getContext()).inflate(layoutRes, root, false));
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick();
            }
        });
    }

    protected void onItemClick() {
        if (listenerWeakRef != null && listenerWeakRef.get() != null){
            listenerWeakRef.get().onItemClick(itemView, getAdapterPosition(), item);
        }
    }

    public void setRecyclerItemClickListener(RecyclerItemClickListener<T> listener) {
        listenerWeakRef = new WeakReference<>(listener);
    }

    public void onBindItemData(T item) {
        this.item = item;
        onBind(item);
    }

    public abstract void onBind(T item);

}