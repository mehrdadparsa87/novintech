package com.novintech.test.ui.common;

import androidx.fragment.app.FragmentManager;

import com.novintech.test.MainActivity;
import com.novintech.test.R;

import javax.inject.Inject;


public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    private MainActivity activity;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
        this.activity = mainActivity;

    }

    public void navigateToLogin() {
//        LoginFragment searchFragment = new LoginFragment();
//        fragmentManager.beginTransaction()
//                .replace(containerId, searchFragment)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                .commitAllowingStateLoss();
    }

}
