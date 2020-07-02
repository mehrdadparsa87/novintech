package com.novintech.test.binding;

import android.app.Activity;

import javax.inject.Inject;

/**
 * Binding adapters that work with a fragment instance.
 */
public class ActivtyBindingAdapters {
    final Activity fragment;

    @Inject
    public ActivtyBindingAdapters(Activity fragment) {
        this.fragment = fragment;
    }


}
