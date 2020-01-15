package com.project.semicolon.recyclerviewdatabinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GenericAdapter<T extends ListItemViewHolder>
        extends RecyclerView.Adapter<GenericAdapter<T>.GenericViewHolder<T>> {
    private List<T> items;
    @LayoutRes
    int layoutId;
    private OnListItemViewClickListener onListItemViewClickListener;
    private LayoutInflater inflater;

    public GenericAdapter(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        items = new ArrayList<>();
    }

    public void setOnListItemViewClickListener(OnListItemViewClickListener onListItemViewClickListener) {
        this.onListItemViewClickListener = onListItemViewClickListener;
    }

    public void addItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenericViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        return new GenericViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder<T> holder, int position) {
        T t = items.get(position);
        t.adapterPosition = position;
        t.listener = onListItemViewClickListener;
        holder.bind(t);
    }


    @Override
    public int getItemCount() {
        if (items == null) return 0;

        return items.size();
    }


    public class GenericViewHolder<T extends ListItemViewHolder> extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public GenericViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(T t) {
            binding.setVariable(BR.obj, t);
            binding.executePendingBindings();
        }
    }
}
