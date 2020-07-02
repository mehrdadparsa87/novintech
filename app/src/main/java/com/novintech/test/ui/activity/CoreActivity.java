package com.novintech.test.ui.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.novintech.test.classes.nbfunctions;

import java.util.Locale;

import javax.inject.Inject;


public class CoreActivity extends AppCompatActivity {


    @Inject
    SharedPreferences pref;
    public nbfunctions nbInstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        nbInstance = nbfunctions.getInstance(this);
        setLocal();
        if (IsRightToLeft()) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }
    }


    /**
     * this function will checks for the user language and if the language wring is write to left, it
     * returns true, otherwise returns false. for example for the Persian and arabic languages returns
     * true
     *
     * @return
     */
    public boolean IsRightToLeft() {
        return Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir") || Locale.getDefault().getLanguage().equalsIgnoreCase("fa");
    }


    /**
     * this function will try to force set local to the activity
     */
    private void setLocal() {

        String language = nbfunctions.getInstance(this).SettingManager_ReadString("language");
        Locale locale = new Locale(language);
        Resources resources = this.getResources();
//        Locale locale = new Locale(ln);
        Locale.setDefault(locale);
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        configuration.setLayoutDirection(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());


    }

}