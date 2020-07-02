package com.novintech.test.ui.adapter.base;

import android.os.AsyncTask;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class DataBoundListAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<DataBoundViewHolder<V>> {

    @Nullable
    private List<T> items;

    protected List<T> filterOriginal;

    private int dataVersion;

    @Nullable
    public V binder;


    @NonNull
    @Override
    public DataBoundViewHolder<V> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        V binding = createBinding(parent, viewType);
        this.binder = binding;
        return new DataBoundViewHolder<>(binding);
    }

    protected abstract V createBinding(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull DataBoundViewHolder<V> holder, int position) {


        bind(holder.binding, items.get(position));
        bindAnimation(holder.binding, position);
        holder.binding.executePendingBindings();
    }

    public int getSize() {
        if (items != null)
            return items.size();

        return 0;
    }

    public List<T> getItems() {
        return items;
    }

    public void insertList(List<T> update) {
        if (items == null) {
            if (update == null) {
                return;
            }

            items = update;
            notifyDataSetChanged();

        } else {
            if (update == null) {
                return;
            }
            this.items.addAll(update);
            notifyDataSetChanged();

        }

    }

    public void insertListOrReplace(List<T> update) {

        if (update == null) {
            return;
        }

        items = update;
        if (filterOriginal == null) {
            filterOriginal = update;
        }
        notifyDataSetChanged();
    }

    @SuppressWarnings("StaticFieldLeak")
    @MainThread
    public void replace(List<T> update) {
        dataVersion++;
        if (items == null) {
            if (update == null) {
                return;
            }

            items = update;
            notifyDataSetChanged();
        } else if (update == null) {
            int oldsize = items.size();
            items = null;
            notifyItemRangeRemoved(0, oldsize);
        } else {
            final int startVersion = dataVersion;
            final List<T> oldItems = items;
            new AsyncTask<Void, Void, DiffUtil.DiffResult>() {

                @Override
                protected DiffUtil.DiffResult doInBackground(Void... voids) {
                    return DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return oldItems.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return update.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            T oldItem = oldItems.get(oldItemPosition);
                            T newItem = update.get(newItemPosition);
                            return DataBoundListAdapter.this.areItemsTheSame(oldItem, newItem);
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            T oldItem = oldItems.get(oldItemPosition);
                            T newItem = update.get(newItemPosition);
                            return DataBoundListAdapter.this.areContentsTheSame(oldItem, newItem);
                        }
                    });
                }

                @Override
                protected void onPostExecute(DiffUtil.DiffResult diffResult) {
                    if (startVersion != dataVersion) {
                        return;
                    }
                    items = update;
                    diffResult.dispatchUpdatesTo(DataBoundListAdapter.this);
                }
            }.execute();
        }


    }

    public T getItemAtPosition(int position) {
        if (items == null) {
            return null;
        }

        return items.get(position);
    }

    protected abstract void bind(V binding, T t);

    protected abstract void bindAnimation(V binding, int position);


    protected abstract boolean areItemsTheSame(T oldItem, T newItem);

    protected abstract boolean areContentsTheSame(T oldItem, T newItem);


    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void insertItem(T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);

    }

    public void clearOrignItems() {
        if (filterOriginal != null) {
            filterOriginal.clear();
        }
    }
}
