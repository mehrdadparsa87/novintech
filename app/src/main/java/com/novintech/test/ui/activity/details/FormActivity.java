package com.novintech.test.ui.activity.details;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.novintech.test.BR;
import com.novintech.test.R;
import com.novintech.test.classes.consts;
import com.novintech.test.databinding.ActivityCheckoutBinding;
import com.novintech.test.db.models.UserInfo;
import com.novintech.test.ui.activity.BaseActivity;
import com.novintech.test.ui.fragment.FragmentConfirmation;
import com.novintech.test.ui.fragment.FragmentForm;
import com.novintech.test.ui.fragment.FragmentInfo;
import com.novintech.test.utils.Tools;
import com.novintech.test.utils.element.CustomProgress;
import com.novintech.test.utils.factory.ViewModelProviderFactory;

import javax.inject.Inject;

public class FormActivity extends BaseActivity<ActivityCheckoutBinding, FormViewModel> implements FormNavigator {

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
    FormViewModel viewModel;


    /**
     * this is for data binding for this activiy
     */
    ActivityCheckoutBinding binding;

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
        return R.layout.activity_checkout;
    }


    private enum State {
        SHIPPING,
        PAYMENT,
        CONFIRMATION
    }

    State[] array_state = new State[]{State.SHIPPING, State.PAYMENT, State.CONFIRMATION};

    private View line_first, line_second;
    private ImageView image_shipping, image_payment, image_confirm;
    private TextView tv_shipping, tv_payment, tv_confirm;

    private int idx_state = 0;

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
      //  subscribeToLiveData();
        //fetch data from server
      // fetchData();


    initToolbar();
    initComponent();

    displayFragment(State.SHIPPING);
}

    private void initComponent() {
        line_first = (View) findViewById(R.id.line_first);
        line_second = (View) findViewById(R.id.line_second);
        image_shipping = (ImageView) findViewById(R.id.image_shipping);
        image_payment = (ImageView) findViewById(R.id.image_payment);
        image_confirm = (ImageView) findViewById(R.id.image_confirm);

        tv_shipping = (TextView) findViewById(R.id.tv_shipping);
        tv_payment = (TextView) findViewById(R.id.tv_payment);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);

        image_payment.setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_ATOP);
        image_confirm.setColorFilter(getResources().getColor(R.color.grey_10), PorterDuff.Mode.SRC_ATOP);

        (findViewById(R.id.lyt_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx_state == array_state.length - 1) return;
                idx_state++;
                displayFragment(array_state[idx_state]);
            }
        });

        (findViewById(R.id.lyt_previous)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx_state < 1) return;
                idx_state--;
                displayFragment(array_state[idx_state]);
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_60), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, android.R.color.white);
        Tools.setSystemBarLight(this);
    }

    private void displayFragment(State state) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);

        Fragment fragment = null;
        refreshStepTitle();

        if (state.name().equalsIgnoreCase(State.SHIPPING.name())) {
            fragment = new FragmentInfo();
            tv_shipping.setTextColor(getResources().getColor(R.color.grey_90));

            image_shipping.clearColorFilter();
        } else if (state.name().equalsIgnoreCase(State.PAYMENT.name())) {
            fragment = new FragmentForm();
            line_first.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            image_shipping.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_payment.clearColorFilter();
            tv_payment.setTextColor(getResources().getColor(R.color.grey_90));
        } else if (state.name().equalsIgnoreCase(State.CONFIRMATION.name())) {
            fragment = new FragmentConfirmation();
            line_second.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            image_payment.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_confirm.clearColorFilter();
            tv_confirm.setTextColor(getResources().getColor(R.color.grey_90));
        }

        if (fragment == null) return;
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();
    }

    private void refreshStepTitle() {
        tv_shipping.setTextColor(getResources().getColor(R.color.grey_20));
        tv_payment.setTextColor(getResources().getColor(R.color.grey_20));
        tv_confirm.setTextColor(getResources().getColor(R.color.grey_20));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
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
    public FormViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(FormViewModel.class);
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
//            binding.setItem(item);
//            binding.executePendingBindings();
        });
    }



}

