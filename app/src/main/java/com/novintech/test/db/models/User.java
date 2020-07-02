package com.novintech.test.db.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "users")
public class User {


    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("first_name")
    @Expose
    public String firstName="";

    @SerializedName("last_name")
    @Expose
    public String lastName="";
    @SerializedName("email")
    @Expose
    public String email="";

//    @Ignore
//    @Inject
//    TimeAgo timeAgo;
//
//    public String dateFormat() {
//        return timeAgo.timeAgo(date);
//    }


}