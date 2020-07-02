package com.novintech.test.ui.activity.splash;



import com.novintech.test.utils.element.CustomProgress;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {
    @Provides
    CustomProgress provideCustomProgress(SplashActivity context) {
        return CustomProgress.getInstance(context);
    }
}
