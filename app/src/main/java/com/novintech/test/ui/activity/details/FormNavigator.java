package com.novintech.test.ui.activity.details;


import com.novintech.test.db.models.UserInfo;

public interface FormNavigator {
    /**
     * handle error
     * @param throwable this the Throwable we have
     */
    void handleError(Throwable throwable);

    /**
     * response result base on success
     * @param message
     */
    void successResult(UserInfo message);

    /**
     * response result base on success
     * @param message
     */
    void unSucess(String message);
}
