package com.novintech.test.ui.activity.details;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.novintech.test.BR;
import com.novintech.test.R;
import com.novintech.test.classes.consts;
import com.novintech.test.databinding.ActivityDetailsBinding;
import com.novintech.test.db.models.UserInfo;
import com.novintech.test.ui.activity.BaseActivity;
import com.novintech.test.utils.element.CustomProgress;
import com.novintech.test.utils.factory.ViewModelProviderFactory;

import javax.inject.Inject;

public class DetailsActivity extends BaseActivity<ActivityDetailsBinding, DetailsViewModel> implements DetailsNavigator {

    private static final String TAG = "BaseActivity";
    /**
     * this variable comes from dagger componnet and we initilaz it from dagger componnet
     */
    @Inject
    ViewModelProviderFactory factory;
    /**
     * THis is simple progress dialog for loading
     */
    @Inject
    CustomProgress customProgress;

    /**
     * for temp data
     */
    @Inject
    SharedPreferences sharedPreferences;

    /**
     * this viewMode is for fetch user
     */
    DetailsViewModel viewModel;


    /**
     * this is for data binding for this activiy
     */
    ActivityDetailsBinding binding;

    /**
     * user id from user selected
     */
    private int userId;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    /**
     * for set layout and this is abstrac method
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        binding = getViewDataBinding();

        //check is has extra
        if (getIntent().hasExtra(consts.USERID)) {
            userId = getIntent().getIntExtra(consts.USERID, 0);
        }

        //listen to live data
        subscribeToLiveData();
        //fetch data from server
        fetchData();
    }


    /**
     * this func for fetch user from server
     */
    private void fetchData() {
        customProgress.show();
        viewModel.fetchUser(userId);

    }



    /**
     * this is for define viewMOdel
     *
     * @return
     */
    @Override
    public DetailsViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(DetailsViewModel.class);
        return viewModel;
    }

    /**
     * handel error for response
     *
     * @param throwable this the Throwable we have
     */
    @Override
    public void handleError(Throwable throwable) {
        customProgress.hideProgress();
    }

    /**
     * success result from response
     *
     * @param items
     */
    @Override
    public void successResult(UserInfo items) {
        customProgress.hideProgress();
    }

    /**
     * unsuccess message if response has message
     *
     * @param message
     */
    @Override
    public void unSucess(String message) {
        customProgress.hideProgress();
    }


    /**
     * listen to live data from viewModel
     */
    void subscribeToLiveData() {
        viewModel.getVehicleData().observe(this, item -> {
            //set item to view
            binding.setItem(item);
            binding.executePendingBindings();
        });
    }



}

