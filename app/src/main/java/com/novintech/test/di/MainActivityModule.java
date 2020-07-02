package com.novintech.test.di;


import com.novintech.test.ui.activity.details.DetailsActivity;
import com.novintech.test.ui.activity.details.DetailsActivityModule;
import com.novintech.test.ui.activity.login.LoginActivity;
import com.novintech.test.ui.activity.login.LoginActivityModule;
import com.novintech.test.ui.activity.splash.SplashActivity;
import com.novintech.test.ui.activity.splash.SplashActivityModule;
import com.novintech.test.ui.activity.userlist.UserListActivity;
import com.novintech.test.ui.activity.userlist.UserListActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


/**
 * all subcompponnet module
 */
@Module
public abstract class MainActivityModule {


    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributeSplashActivity();


    @ContributesAndroidInjector(modules = DetailsActivityModule.class)
    abstract DetailsActivity contributeDetailsActivity();

    @ContributesAndroidInjector(modules = UserListActivityModule.class)
    abstract UserListActivity contributeUserListActivity();


}
