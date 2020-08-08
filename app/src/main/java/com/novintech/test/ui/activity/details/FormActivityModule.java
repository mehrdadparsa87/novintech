package com.novintech.test.ui.activity.details;


import com.novintech.test.utils.element.CustomProgress;

import dagger.Module;
import dagger.Provides;

@Module
public class FormActivityModule {
    @Provides
    CustomProgress provideCustomProgress(FormActivity context) {
        return CustomProgress.getInstance(context);
    }
}
