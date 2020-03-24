package com.wkq.order.utils;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/18
 * <p>
 * 简介:
 */

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected List<T> mItems;
    protected Context mContext;

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        this.mItems = new ArrayList();
    }

    public List<T> getList() {
        return this.mItems;
    }

    public void addItem(T item) {
        this.addItem(this.mItems.size(), item);
    }

    public void addItem(int index, T item) {
        if (item != null) {
            this.mItems.add(index, item);

            try {
                this.notifyItemInserted(index);
            } catch (Exception var4) {
            }

        }
    }

    public void addItems(List<T> items) {
        if (items != null) {
            int position = this.mItems.size() - 1;
            this.mItems.addAll(items);
            this.notifyDataSetChanged();
        }
    }

    public void addItems(int indext, List<T> items) {
        if (items != null) {
            this.mItems.addAll(indext, items);
            this.notifyItemRangeInserted(indext, items.size());
        }
    }

    public boolean containsAll(List<T> items) {
        return this.mItems.containsAll(items);
    }

    public void updateItem(T tasks, int position) {
        if (tasks != null) {
            this.mItems.set(position, tasks);

            try {
                this.notifyItemChanged(position);
            } catch (Exception var4) {
            }

        }
    }

    public void updateItems(List<T> items) {
        if (items != null) {
            this.mItems.clear();
            this.mItems.addAll(items);
            this.notifyDataSetChanged();
        }
    }

    public int indexOf(T item) {
        return item != null && this.mItems != null && this.mItems.size() > 0 ? this.mItems.indexOf(item) : -1;
    }

    public void removeItem(int index) {
        this.mItems.remove(index);
        this.notifyItemRemoved(index);
    }

    public void removeAllItems() {
        this.mItems.clear();
        this.notifyDataSetChanged();
    }

    public void moveItem(T item, int fromPosition, int toPosition) {
        if (fromPosition > toPosition) {
            Collections.rotate(this.mItems.subList(toPosition, fromPosition + 1), 1);
        } else {
            Collections.rotate(this.mItems.subList(fromPosition, toPosition + 1), -1);
        }

        this.notifyItemMoved(fromPosition, toPosition);
        this.mItems.set(toPosition, item);
        this.notifyItemChanged(toPosition);
    }

    public void getView(int position, RecyclerView.ViewHolder viewHolder, int type, T item) {
    }

    public T getItem(int location) {
        return this.mItems.get(location);
    }

    public int getItemCount() {
        return this.mItems == null ? 0 : this.mItems.size();
    }
}
