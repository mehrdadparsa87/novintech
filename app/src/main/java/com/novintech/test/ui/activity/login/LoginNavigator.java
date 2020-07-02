package com.novintech.test.ui.activity.login;


import com.novintech.test.api.response.LoginResponse;

public interface LoginNavigator {
    /**
     * handle error
     * @param throwable this the Throwable we have
     */
    void handleError(Throwable throwable);

    /**
     * response result base on success
     * @param item
     */
    void successResult(LoginResponse item);

    /**
     * response result base on success
     * @param message
     */
    void unSccessResult(String message);


}
