package com.novintech.test.di.module;


import com.novintech.test.ui.activity.login.LoginActivity;
import com.novintech.test.utils.element.CustomProgress;

import dagger.Module;
import dagger.Provides;


@Module
public class LoginModule {



    @Provides
    CustomProgress provideCustomProgress(LoginActivity context) {
        return CustomProgress.getInstance(context);
    }

}
