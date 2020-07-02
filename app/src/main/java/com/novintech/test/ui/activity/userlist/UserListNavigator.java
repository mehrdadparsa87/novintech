package com.novintech.test.ui.activity.userlist;


import com.novintech.test.db.models.UserInfo;

import java.util.List;

public interface UserListNavigator {
    /**
     * handle error
     * @param throwable this the Throwable we have
     */
    void handleError(Throwable throwable);

    /**
     * response result base on success
     * @param message
     */
    void successResult(List<UserInfo> message);

    /**
     * response result base on success
     * @param message
     */
    void unSuccess(String message);
}
