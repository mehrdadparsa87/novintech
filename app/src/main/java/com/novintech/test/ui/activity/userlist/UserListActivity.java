package com.novintech.test.ui.activity.userlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.novintech.test.BR;
import com.novintech.test.R;
import com.novintech.test.classes.consts;
import com.novintech.test.databinding.ActivityUserListBinding;
import com.novintech.test.db.models.UserInfo;
import com.novintech.test.ui.activity.BaseActivity;
import com.novintech.test.ui.activity.details.DetailsActivity;
import com.novintech.test.utils.element.CustomProgress;
import com.novintech.test.utils.factory.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

public class UserListActivity extends BaseActivity<ActivityUserListBinding, UserListViewModel> implements UserListNavigator {

    private static final String TAG = "BaseActivity";
    /**
     * this variable comes from dagger componnet and we initilaz it from dagger componnet
     */
    @Inject
    ViewModelProviderFactory factory;

    /**
     * define  from {@link UserListActivityModule}.
     * we use this layput manager for recycler view and paginate
     */
    @Inject
    LinearLayoutManager layoutManager;

    /**
     * define from  {@link UserListActivityModule}}
     * this adapter for showing user list
     */
    @Inject
    AdapterUserList adapterUserList;
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
    UserListViewModel viewModel;

    /**
     * this is for data binding for this activiy
     */
    ActivityUserListBinding binding;

    /**
     * this flag for checking is request in progress or not
     */
    private boolean loading = false;
    /**
     * for paginate
     */
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private boolean item_per_page;


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
        return R.layout.activity_user_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * set navigator interface
         */
        viewModel.setNavigator(this);
        //get bindaing
        binding = getViewDataBinding();
        //for hidding no internet
        binding.setCheckIntent(false);
        //retry call back
        binding.setInternetCallBack(this::fetchData);

        //set custom progress Cancelable
        customProgress.Cancelable();

        //define refresh layout
        initSwipeRefress();

        //set toolbar
        setToolbar();
        //define adapter for recyclerview
        initAdapter();
        //fetch data from server
        fetchData();
    }

    private void initSwipeRefress() {
        binding.refres.setOnRefreshListener(() -> {
            if (!loading) {
                //clear data
                resetData();
                //fetch data
                fetchData();
            } else {
                binding.refres.cancelLongPress();
            }
        });
    }

    /**
     * reset data fro refresh layout
     */
    private void resetData() {
        pageNumber = 1;
        if (adapterUserList.getItems() != null) {
            adapterUserList.getItems().clear();
            adapterUserList.notifyDataSetChanged();
        }
    }

    /**
     * setup toolbar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Novin Tech Test");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * define adapater and scroll for recycler view
     */
    private void initAdapter() {
        //setup recyclerview
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapterUserList);

        //for checking paginate
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager
                        .findLastVisibleItemPosition();
                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD) && item_per_page) {
                    pageNumber++;
                    viewModel.fetchUsers(pageNumber);
                    loading = true;
                }
            }
        });

        //listen for click on adapter
        adapterUserList.setItemClickCallback(item -> {
            if (item instanceof UserInfo) {
                startActivity(new Intent(this, DetailsActivity.class).putExtra(consts.USERID, ((UserInfo) item).getId()));
            }
        });
    }


    /**
     * fetch data from server
     */
    private void fetchData() {

        loading = true;
        if (pageNumber == 1) {
            customProgress.show();
        }

        viewModel.fetchUsers(pageNumber);

    }


    /**
     * this is for define viewMOdel
     *
     * @return
     */
    @Override
    public UserListViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(UserListViewModel.class);
        return viewModel;
    }

    /**
     * handel error for response
     *
     * @param throwable this the Throwable we have
     */
    @Override
    public void handleError(Throwable throwable) {

        loading = false;
        hideProgress();
    }

    /**
     * success result from response
     *
     * @param items
     */
    @Override
    public void successResult(List<UserInfo> items) {
        hideProgress();
        loading = false;
        item_per_page = items.size() == 6;
        adapterUserList.insertList(items);
    }

    /**
     * unsuccess message if response has message
     *
     * @param message
     */
    @Override
    public void unSuccess(String message) {
        hideProgress();
        loading = false;
    }


    /**
     * hide progress if first page
     */
    void hideProgress() {
        if (pageNumber == 1) {
            binding.refres.setRefreshing(false);
            customProgress.hideProgress();
        }
    }
}

