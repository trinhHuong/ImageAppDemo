package com.test.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public abstract class BaseAdapter<B extends ViewDataBinding, T> extends RecyclerView.Adapter<BaseViewHolder<B>> {
    protected Context context;
    private static final String TAG = "BaseAdapter";
    protected OnItemRecycleViewClick onItemRecycleViewClick;
    protected List<T> listObjects = new ArrayList<>();


    @Override
    public BaseViewHolder<B> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        B binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForViewType(viewType), parent, false);
        return new BaseViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder<B> holder, int position) {
        holder.getBinding().getRoot().setClickable(true);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemRecycleViewClick != null) {
                    onItemRecycleViewClick.onItemRecycleViewClick(holder.getAdapterPosition(), view);
                }
            }
        });
    }

    protected abstract int getLayoutIdForViewType(int viewType);

    public void setOnItemRecycleViewClick(OnItemRecycleViewClick onItemRecycleViewClick) {
        this.onItemRecycleViewClick = onItemRecycleViewClick;
    }

    public interface OnItemRecycleViewClick {
        void onItemRecycleViewClick(int position, View view);
    }

    @Override
    public int getItemCount() {
        return listObjects != null ? listObjects.size() : 0;
    }

    public T getItem(int position) {
        return position >= 0 && position < getItemCount() ? listObjects.get(position) : null;
    }
    public void addItems(List<T> objects) {
        listObjects.clear();
        if (objects != null) {
            listObjects.addAll(objects);
        }
        notifyDataSetChanged();
    }
    public void removeAll() {
        listObjects.clear();
        notifyDataSetChanged();
    }
    public void addItem(T object, int pos) {
        if (pos > listObjects.size()) {
            pos = listObjects.size();
            listObjects.add(object);
        } else if (pos >= 0) {
            listObjects.add(pos, object);
        }
        notifyItemInserted(pos);
    }
    public void addItems(List<T> objects, int pos) {
        if (objects != null && !objects.isEmpty() && pos >=0) {
            listObjects.addAll(pos, objects);
            notifyItemRangeInserted(pos, objects.size());
        }
    }
    public void addMoreItem(List<T> objects) {
        int count = listObjects.size();
        if (objects != null) {
            listObjects.addAll(objects);
            notifyItemRangeInserted(count, objects.size());
        }
    }
    public void remove(int position) {
        if (position >= 0 && position < listObjects.size()) {
            listObjects.remove(position);
            notifyItemRemoved(position);
        }
    }


}
