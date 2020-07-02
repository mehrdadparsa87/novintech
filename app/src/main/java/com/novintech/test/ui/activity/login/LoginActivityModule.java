package com.novintech.test.ui.activity.login;



import com.novintech.test.utils.element.CustomProgress;

import dagger.Module;
import dagger.Provides;
@Module
public class LoginActivityModule {
    @Provides
    CustomProgress provideCustomProgress(LoginActivity context) {
        return CustomProgress.getInstance(context);
    }
}
