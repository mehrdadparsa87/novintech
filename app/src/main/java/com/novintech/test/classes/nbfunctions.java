package com.novintech.test.classes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.widget.TextView;

import com.novintech.test.R;
import com.tapadoo.alerter.Alerter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class nbfunctions {
    public static String file;
    public static boolean isInCategory;

    private static nbfunctions mInstance;
    public int patnetID;
    public static int adultCount;
    public static int childCount;
    public static int teenCount;

    public static nbfunctions getInstance(Context context) {
        if (mInstance == null)
            mInstance = new nbfunctions(context);

        return mInstance;
    }

    private Context context;

    private nbfunctions(Context context) {
        this.context = context;
    }

    public nbfunctions() {

    }

    /**
     * checks if there is internet connection in the app, it checks Mobile, Wifi connections
     *
     * @return
     */
    public boolean hasConnection() {

        if (context == null) {
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }


    public boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    /**
     * try to read settings from SharedPreferences
     *
     * @param PropertyName The name of the preference to retrieve.
     * @return
     */
    public String SettingManager_ReadString(String PropertyName) {

        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getString(PropertyName, "");

    }



    /**
     * show erro dialog
     *
     * @param context
     * @param mesage
     * @param title
     * @param btn_close
     */
    public void ShowDialogFailed(Activity context, String mesage, String title, String btn_close) {
//        DialogFail dialogFail = new DialogFail(context);
//        dialogFail.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;
//        dialogFail.show();
//        dialogFail.setMessage(title, mesage, btn_close);

        Alerter.create(context)
                .setTitle(title)
                .setTitleAppearance(R.style.AlertTextAppearance_Title)
                .setTitleTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf"))
                .setText(mesage)
                .setTextAppearance(R.style.AlertTextAppearance_Text)
                .setTextTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf"))
                .setDuration(4000)
                .setBackgroundColorRes(R.color.colorGrayStatus)
                .show();

    }

    /**
     * show erro dialog
     *
     * @param context
     * @param mesage
     * @param title
     */
    public void ShowSuccessAlert(Activity context, String mesage, String title) {
        // sound is inside res/raw/mysound
        MediaPlayer mp = MediaPlayer.create(context, R.raw.notification1);
        //set volume to media player
        mp.setVolume(0.5F, 0.5F);
        mp.start();
        Alerter.create(context)
                .setTitle(title)
                .setTitleAppearance(R.style.AlertTextAppearance_Title)
                .setTitleTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf"))
                .setText(mesage)
                .setTextAppearance(R.style.AlertTextAppearance_Text)
                .setTextTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile.ttf"))
                .setDuration(4000)
                .setBackgroundColorRes(R.color.colorPrimary)
                .show();

    }

    public void notifySound(Activity context) {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.noty);
        //set volume to media player
        mp.setVolume(0.5F, 0.5F);
        mp.start();
    }
}
