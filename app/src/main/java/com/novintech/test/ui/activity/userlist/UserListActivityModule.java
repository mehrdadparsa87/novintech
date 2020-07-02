package com.novintech.test.ui.activity.userlist;


import androidx.databinding.DataBindingComponent;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.novintech.test.binding.ActivityDataBindingComponent;
import com.novintech.test.utils.element.CustomProgress;

import dagger.Module;
import dagger.Provides;

@Module
public class UserListActivityModule {
    @Provides
    CustomProgress provideCustomProgress(UserListActivity context) {
        return CustomProgress.getInstance(context);
    }


    @Provides
    DataBindingComponent provideDataBindingComponent(UserListActivity searchFragment) {
        return new ActivityDataBindingComponent(searchFragment.getParent());
    }

    @Provides
    AdapterUserList provideAdapterSimlarCar(DataBindingComponent dataBindingComponent) {
        return new AdapterUserList(dataBindingComponent);
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(UserListActivity context) {
        return new LinearLayoutManager(context);
    }
}
