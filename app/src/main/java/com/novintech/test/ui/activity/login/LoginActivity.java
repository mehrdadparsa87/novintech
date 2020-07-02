package com.novintech.test.ui.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Patterns;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.novintech.test.BR;
import com.novintech.test.R;
import com.novintech.test.api.response.LoginResponse;
import com.novintech.test.classes.consts;
import com.novintech.test.classes.nbfunctions;
import com.novintech.test.databinding.ActivityLoginBinding;
import com.novintech.test.ui.activity.BaseActivity;
import com.novintech.test.ui.activity.userlist.UserListActivity;
import com.novintech.test.utils.element.CustomProgress;
import com.novintech.test.utils.factory.ViewModelProviderFactory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

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
     * this viewMode is for fetch user list
     */
    LoginViewModel viewModel;
    /**
     * this is for data binding for this activiy
     */
    ActivityLoginBinding binding;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.setNavigator(this);
        binding = getViewDataBinding();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        // viewModel.setNavigator(this);

        setUpLogin();


    }

    /**
     * action fo login
     */
    private void setUpLogin() {
        binding.btnLogin.setOnClickListener(view -> {
            login();
        });
    }


    /**
     * send request login
     */
    private void login() {

        if (!isValidEmail(binding.etEmail.getText().toString())) {
            nbfunctions.getInstance(this).ShowDialogFailed(this, "Email address is note valid", "opps", "ok");
            return;
        }
        if (TextUtils.isEmpty(binding.etPass.getText().toString().trim())) {
            nbfunctions.getInstance(this).ShowDialogFailed(this, "Please enter Your password", "opps", "ok");
            return;
        }
        customProgress.show();
        Map<String, String> params = new HashMap<>();
        params.put("email", binding.etEmail.getText().toString());
        params.put("password", binding.etPass.getText().toString());
        viewModel.login(params);


}


    @Override
    public LoginViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);
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
        //show error message to user
        nbfunctions.getInstance(this).ShowDialogFailed(this, "Server error", "ops", "Ok");

    }

    /**
     * success result from response
     *
     * @param item
     */
    @Override
    public void successResult(LoginResponse item) {
        customProgress.hideProgress();
        sharedPreferences.edit().putString(consts.TOKEN, item.getToken()).commit();
        startActivity(new Intent(this, UserListActivity.class));
        finish();
    }

    /**
     * unsuccess message if response has message
     *
     * @param message
     */
    @Override
    public void unSccessResult(String message) {
        //show error message to user
        customProgress.hideProgress();
        nbfunctions.getInstance(this).ShowDialogFailed(this, message, "oops", "Ok");
    }


    /**
     * check email is valid or note
     *
     * @param target
     * @return
     */
    public boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}

