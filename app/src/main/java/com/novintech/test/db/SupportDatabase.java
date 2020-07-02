package com.novintech.test.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.novintech.test.db.dao.UserDao;
import com.novintech.test.db.models.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class SupportDatabase extends RoomDatabase {

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    private static SupportDatabase sInstance;

    @VisibleForTesting
    public static final String DATABASE_NAME = "basic-sample-db";

    public abstract UserDao userDao();


    public static SupportDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (SupportDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static SupportDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, SupportDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        SupportDatabase database = SupportDatabase.getInstance(appContext);
                        // notify that the database was created and it's ready to be used
                        database.setDatabaseCreated();

                    }
                })
                .build();
    }


    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'messages' ADD COLUMN 'from_local' INTEGER  NULL DEFAULT 0");
        }
    };

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }


}
