package com.novintech.test.di;


import com.novintech.test.ui.activity.details.FormActivity;
import com.novintech.test.ui.activity.details.FormActivityModule;
import com.novintech.test.ui.activity.splash.SplashActivity;
import com.novintech.test.ui.activity.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * all subcompponnet module
 */
@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributeSplashActivity();


    @ContributesAndroidInjector(modules = FormActivityModule.class)
    abstract FormActivity contributeDetailsActivity();


}
