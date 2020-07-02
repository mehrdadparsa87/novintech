package com.novintech.test.db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResponse<T> {

    @SerializedName("statuscode")
    @Expose
    public int status;
    @SerializedName("result")
    @Expose
    public T data;

    @SerializedName("statustext")
    @Expose
    public String message;
}
