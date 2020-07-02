package com.novintech.test.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;


import com.novintech.test.db.models.User;

import java.util.List;


@Dao
public abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(User user);

}
