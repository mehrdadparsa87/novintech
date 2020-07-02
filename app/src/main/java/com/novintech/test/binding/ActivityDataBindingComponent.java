package com.novintech.test.binding;

import android.app.Activity;

/**
 * A Data Binding Component implementation for fragments.
 */
public class ActivityDataBindingComponent implements androidx.databinding.DataBindingComponent {
    private final ActivtyBindingAdapters adapter;

    public ActivityDataBindingComponent(Activity fragment) {
        this.adapter = new ActivtyBindingAdapters(fragment);
    }


    public ActivtyBindingAdapters getFragmentBindingAdapters() {
        return adapter;
    }
}
