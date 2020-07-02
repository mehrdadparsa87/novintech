package com.novintech.test.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.novintech.test.db.models.Ad;
import com.novintech.test.db.models.UserInfo;

public class SingleUserResponse {

    @SerializedName("data")
    @Expose
    UserInfo data;

    @SerializedName("ad")
    @Expose
    Ad ad;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
