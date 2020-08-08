package com.novintech.test.ui.activity.details;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.novintech.test.db.models.UserInfo;
import com.novintech.test.repository.DataRepository;
import com.novintech.test.utils.BaseViewModel;
import com.novintech.test.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class FormViewModel extends BaseViewModel<FormNavigator> {

    public final MutableLiveData<UserInfo> items;


    @Inject
    public FormViewModel(SchedulerProvider schedulerProvider, DataRepository mDataRepository) {
        super(schedulerProvider, mDataRepository);
        items = new MutableLiveData<>();
    }

    public void fetchUser(int userID) {
        setIsLoading(true);
        getCompositeDisposable().add(mRepository.fetchData(userID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe((stringDataResponse) -> {
                    if (stringDataResponse.getData() != null) {
                        items.setValue(stringDataResponse.getData());
                        getNavigator().successResult(stringDataResponse.getData());
                    } else {
                        getNavigator().handleError(new Throwable());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<UserInfo> getVehicleData() {
        return items;
    }


    @Override
    public void goToInternetSetting(Context context) {
    }

    public ObservableField<Float> radius = new ObservableField<>(100f);
}
