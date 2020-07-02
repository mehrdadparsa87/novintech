package com.novintech.test.binding;


import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.novintech.test.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Random;

import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

/*
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {


    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"showImage"})
    public static void imagloadter(final View view, final String file) {
//        config.photos[(new Random()).nextInt((31 - 1) + 1) + 1]
        if (file != null) {
            Picasso.get()
                    .load(file)
                    .placeholder(R.drawable.place_holder_image)
                    .into((ImageView) view);
        }
    }

    @BindingAdapter({"htmlText"})
    public static void textHtml(final TextView view, final String string) {
//        view.setupWithViewPager(pagerView, true);
        if (string != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                view.setText(Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY).toString());
            } else {
                view.setText(Html.fromHtml(string).toString());
            }

        }
    }


    private int getRandomNumber(int min, int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }

    @BindingAdapter({"dateText"})
    public static void dateText(final TextView view, final long millisecond) {
//        view.setupWithViewPager(pagerView, true);

        Calendar instance = PersianCalendar.getInstance();
        PersianDateFormat format = new PersianDateFormat("y/m/d");
        PersianDate persianDate = new PersianDate(millisecond);
        String format1 = format.format(persianDate);

//        String s = DateFormat.format("MM/dd/yyyy", new Date(millisecond)).toString();
//        PersianDateParser persianDateParser = new PersianDateParser(s);
//        String persianShortDate = persianDateParser.getDateString();
        view.setText(format1);
    }

}