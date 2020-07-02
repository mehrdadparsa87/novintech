package com.novintech.test.ui.activity.userlist;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.novintech.test.repository.DataRepository;
import com.novintech.test.utils.BaseViewModel;
import com.novintech.test.utils.rx.SchedulerProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class UserListViewModel extends BaseViewModel<UserListNavigator> {

    public final MutableLiveData<String> items;


    @Inject
    public UserListViewModel(SchedulerProvider schedulerProvider, DataRepository mDataRepository) {
        super(schedulerProvider, mDataRepository);
        items = new MutableLiveData<>();
    }

    public void fetchUsers(int page) {
        if (page > 1) {
            setIsLoading(true);
        }
        setServerError(false);
        setNothingFound(false);
        getCompositeDisposable().add(mRepository.userList(page)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe((stringDataResponse) -> {
                    if (stringDataResponse != null) {
//                            items.setValue(stringDataResponse.data.code;
                        getNavigator().successResult(stringDataResponse.getData());
                    } else {
                        getNavigator().unSuccess("");
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    setServerError(true);
                    getNavigator().handleError(throwable);
                }));
    }


    @Override
    public void goToInternetSetting(Context context) {
    }

    public ObservableField<Float> radius = new ObservableField<>(100f);
}
