package com.novintech.test.utils.factory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.novintech.test.repository.DataRepository;
import com.novintech.test.ui.activity.details.DetailsViewModel;

import com.novintech.test.ui.activity.login.LoginViewModel;
import com.novintech.test.ui.activity.splash.SplashViewModel;
import com.novintech.test.ui.activity.userlist.UserListViewModel;
import com.novintech.test.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by jyotidubey on 22/02/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {


    private final SchedulerProvider schedulerProvider;
    private final DataRepository dataRepository;

    @Inject
    public ViewModelProviderFactory(DataRepository dataRepository,
                                    SchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
        this.dataRepository = dataRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        /*if (modelClass.isAssignableFrom(TurGuidViewModel.class)) {
            //noinspection unchecked
            return (T) new TurGuidViewModel(schedulerProvider, dataRepository);
        } else*/
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(schedulerProvider, dataRepository);
        }else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(schedulerProvider, dataRepository);
        }else if (modelClass.isAssignableFrom(UserListViewModel.class)) {
            //noinspection unchecked
            return (T) new UserListViewModel(schedulerProvider, dataRepository);
        }else if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailsViewModel(schedulerProvider, dataRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}