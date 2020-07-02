package com.novintech.test.di;

import androidx.lifecycle.ViewModelProvider;


import com.novintech.test.utils.factory.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
abstract class ViewModelModule {


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory factory);


}
