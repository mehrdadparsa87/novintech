package com.novintech.test.ui.activity.details;


import com.novintech.test.utils.element.CustomProgress;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailsActivityModule {
    @Provides
    CustomProgress provideCustomProgress(DetailsActivity context) {
        return CustomProgress.getInstance(context);
    }
}
