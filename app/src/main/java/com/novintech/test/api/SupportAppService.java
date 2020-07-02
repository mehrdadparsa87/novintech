package com.novintech.test.api;

import com.novintech.test.api.response.LoginResponse;
import com.novintech.test.api.response.SingleUserResponse;
import com.novintech.test.api.response.UserResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SupportAppService {


    @FormUrlEncoded
    @POST("api/login")
    Single<LoginResponse> login(@FieldMap Map<String, String> params);


    @GET("api/users")
    Single<UserResponse> userList(@Query("page") int page);


    @GET("api/users/{id}")
    Single<SingleUserResponse> fetUser(@Path("id") int userID);
}
