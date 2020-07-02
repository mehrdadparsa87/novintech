package com.novintech.test.ui.activity.splash;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;


import com.novintech.test.repository.DataRepository;
import com.novintech.test.utils.BaseViewModel;
import com.novintech.test.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public final MutableLiveData<String> items;


    @Inject
    public SplashViewModel(SchedulerProvider schedulerProvider, DataRepository mDataRepository) {
        super(schedulerProvider, mDataRepository);
        items = new MutableLiveData<>();
    }





    @Override
    public void goToInternetSetting(Context context) {
    }

    public ObservableField<Float> radius = new ObservableField<>(100f);
}
