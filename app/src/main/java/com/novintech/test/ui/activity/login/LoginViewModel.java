package com.novintech.test.ui.activity.login;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.novintech.test.api.response.LoginResponse;
import com.novintech.test.api.response.UserResponse;
import com.novintech.test.repository.DataRepository;
import com.novintech.test.utils.BaseViewModel;
import com.novintech.test.utils.factory.RetrofitException;
import com.novintech.test.utils.rx.SchedulerProvider;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public final MutableLiveData<String> items;

    @Inject
    public LoginViewModel(SchedulerProvider schedulerProvider, DataRepository mDataRepository) {
        super(schedulerProvider, mDataRepository);
        items = new MutableLiveData<>();
    }

    public void login(Map<String, String> params) {
        setIsLoading(true);
        getCompositeDisposable().add(mRepository.requestLogin(params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe((stringDataResponse) -> {
                    if (stringDataResponse.getError() == null) {
                        getNavigator().successResult(stringDataResponse);
                    } else {
                        getNavigator().unSccessResult(stringDataResponse.getError());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    if (throwable instanceof RetrofitException) {
                        if (((RetrofitException) throwable).getResponse().code() == 400) {
                            Gson gson = new GsonBuilder().create();
                            try {
                                LoginResponse mError = gson.fromJson(((RetrofitException) throwable).getResponse().errorBody().string(), LoginResponse.class);
                                getNavigator().unSccessResult(mError.getError());
                            } catch (Exception e) {
                                // handle failure to read error
                                getNavigator().handleError(throwable);
                            }
                        }

                    } else {

                        getNavigator().handleError(throwable);
                    }


                }));
    }


    @Override
    public void goToInternetSetting(Context context) {
    }

}
