package com.novintech.test.ui.activity.userlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import com.novintech.test.R;
import com.novintech.test.databinding.ItemUserBinding;
import com.novintech.test.db.models.UserInfo;
import com.novintech.test.ui.adapter.base.DataBoundListAdapter;
import com.novintech.test.ui.common.ItemClickCallback;


public class AdapterUserList extends DataBoundListAdapter<UserInfo, ItemUserBinding> {


    private final DataBindingComponent dataBindingComponent;
    private ItemClickCallback<UserInfo> itemClickCallback;

    public AdapterUserList(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;

    }

    public void setItemClickCallback(ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    protected ItemUserBinding createBinding(ViewGroup parent, int viewType) {
        ItemUserBinding userItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false, dataBindingComponent);
        userItemBinding.root.setOnClickListener(view -> {
            if (itemClickCallback != null) {
                itemClickCallback.onClick(userItemBinding.getItem());
            }
        });

        return userItemBinding;
    }

    @Override
    protected void bind(ItemUserBinding binding, UserInfo item) {
        binding.setItem(item);

    }

    @Override
    protected void bindAnimation(ItemUserBinding binding, int position) {

    }


    @Override
    protected boolean areItemsTheSame(UserInfo oldItem, UserInfo newItem) {
        return false;
    }

    @Override
    protected boolean areContentsTheSame(UserInfo oldItem, UserInfo newItem) {
        return false;
    }


}

