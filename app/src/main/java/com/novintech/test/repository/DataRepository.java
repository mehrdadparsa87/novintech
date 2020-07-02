package com.novintech.test.repository;

import android.content.SharedPreferences;

import com.novintech.test.AppExecutors;
import com.novintech.test.api.SupportAppService;
import com.novintech.test.api.response.LoginResponse;
import com.novintech.test.api.response.SingleUserResponse;
import com.novintech.test.api.response.UserResponse;
import com.novintech.test.classes.consts;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class DataRepository {

    private static final String TAG = "DataRepository";

    private SupportAppService supportAppService;

    private AppExecutors appExecutors;


    @Inject
    public SharedPreferences prefs;

    @Inject
    public DataRepository(SupportAppService supportAppService, AppExecutors appExecutors) {
        this.supportAppService = supportAppService;
        this.appExecutors = appExecutors;


    }


    /**
     * send request for login with paramters
     * @param params
     * @return token and error base on success or not
     */
    public Single<LoginResponse> requestLogin(Map<String, String> params) {
        return supportAppService.login(params);
    }

    /**
     * fetch all user with paginate base on pagenumber
     * @param params
     * @return LIST of User
     */
    public Single<UserResponse> userList(int params) {
        return supportAppService.userList(params);
    }


    /**
     * fetch single user base on user id
     * @param userID
     * @return USERINFO
     */
    public Single<SingleUserResponse> fetchData(int userID) {
        return supportAppService.fetUser(userID);
    }

    private Map<String, String> getParamters() {
        Map<String, String> parasm = new HashMap<>();
        //parasm.put("language", prefs.getString(consts.TOKEN, ""));

        return parasm;
    }

}