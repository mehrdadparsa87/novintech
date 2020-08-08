package com.novintech.test.ui.activity.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.novintech.test.AppExecutors;
import com.novintech.test.BR;
import com.novintech.test.R;
import com.novintech.test.classes.consts;
import com.novintech.test.databinding.ActivitySplashBinding;
import com.novintech.test.ui.activity.BaseActivity;
import com.novintech.test.ui.activity.details.FormActivity;
import com.novintech.test.utils.factory.ViewModelProviderFactory;

import javax.inject.Inject;


public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    /**
     * this variable comes from dagger componnet and we initilaz it from dagger componnet
     */
    @Inject
    ViewModelProviderFactory factory;

    /**
     * this viewMode is for fetch spash
     */
    SplashViewModel viewModel;

    ActivitySplashBinding binding;


    /**
     * for temp data
     */
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    AppExecutors appExecutors;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(SplashViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        binding.progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#3C80F7"), PorterDuff.Mode.MULTIPLY);

        viewModel.setNavigator(this);
        binding = getViewDataBinding();


        //stop thread for second for splash
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                    String userId = sharedPreferences.getString(consts.TOKEN, "0");
                    //check if user is register or not

                    Intent intent = new Intent(SplashActivity.this, FormActivity.class);
                    startActivity(intent);
                    finish();


                } catch (Exception e) {

                }

            }
        });
        thread.start();
    }


    @Override
    public void handleError(Throwable throwable) {

    }


}