/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.novintech.test.utils;

import android.content.Context;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;


import com.novintech.test.repository.DataRepository;
import com.novintech.test.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Base all ViewModel
 * Created by amitshekhar on 07/07/17.
 */

public abstract class BaseViewModel<N> extends ViewModel {


    //    private final DataManager mDataManager;
    protected DataRepository mRepository;
    private final ObservableBoolean mIsLoading = new ObservableBoolean();
    private final ObservableBoolean mNothingFound = new ObservableBoolean();
    private final ObservableBoolean serverError = new ObservableBoolean();

    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    private WeakReference<N> mNavigator;
    private ObservableField<String> title;


    public BaseViewModel(SchedulerProvider schedulerProvider, DataRepository mDataRepository) {
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
        this.mRepository = mDataRepository;

    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    /**
     * define loading for progress
     *
     * @param isLoading
     */
    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }


    /**
     * define loading for progress
     *
     * @param isLoading
     */
    public void setServerError(boolean isLoading) {
        serverError.set(isLoading);
    }
    public ObservableBoolean getNothingFound() {
        return mNothingFound;
    }

    public ObservableBoolean getServerError() {
        return serverError;
    }

    /**
     * define loading for progress
     *
     * @param isNothingFound
     */
    public void setNothingFound(boolean isNothingFound) {
        mNothingFound.set(isNothingFound);
    }

    /**
     * this function for call back data or error
     *
     * @return
     */
    public N getNavigator() {
        return mNavigator.get();
    }

    /**
     * set WeakRefrence of Navigator
     *
     * @param navigator this is from activity or fragment
     */
    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    /**
     * thus function SchedulerProvider UI,IO
     *
     * @return
     */
    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public abstract void goToInternetSetting(Context context);

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = new ObservableField<>(title);
    }


}
