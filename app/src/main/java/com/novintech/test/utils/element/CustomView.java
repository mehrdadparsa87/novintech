package com.novintech.test.utils.element;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

public class CustomView {


    /**
     * normal typeface when we are using the normal mode
     */
    private static Typeface normalTypeFace = null;

    /**
     * typeface of the view when it sets to bold
     */
    private static Typeface boldTypeface = null;

    /**
     * set typeface to a textview, it will checkes for the application language ans set good font based
     * on the request
     *
     * @param context
     * @param view
     */
    public static void setType(Context context, TextView view) {
        if (!view.isInEditMode()) {

            // check for language
            String language = Locale.getDefault().getLanguage();
            if (language.equalsIgnoreCase("en_us") || language.equalsIgnoreCase("en")) {
                if (view.getTypeface() != null) {
                    if (view.getTypeface().getStyle() == Typeface.BOLD) {
                        // it should be bold
                        Typeface font = getBoldTypeface(context);
                        view.setTypeface(font);
                    } else {
                        Typeface font = getNormalTypeface(context);
                        view.setTypeface(font);
                    }
                } else {
                    Typeface font = getNormalTypeface(context);
                    view.setTypeface(font);
                }
            } else {

                if (view.getTypeface() != null) {
                    if (view.getTypeface().getStyle() == Typeface.BOLD) {
                        // it should be bold
                        Typeface font = getBoldTypeface(context);
                        view.setTypeface(font);
                    } else {
                        Typeface font = getNormalTypeface(context);
                        view.setTypeface(font);
                    }
                } else {
                    Typeface font = getNormalTypeface(context);
                    view.setTypeface(font);
                }
            }

        }
    }


    public static Typeface getTypeFace(Context context, int typefaceStyle) {
        // check for language
        String language = Locale.getDefault().getLanguage();
        if (language.equalsIgnoreCase("en_us") || language.equalsIgnoreCase("en")) {
            // English
            if (typefaceStyle == Typeface.BOLD) {
                // it should be bold
                Typeface font = getBoldTypeface(context);
                return font;
            } else {
                Typeface font = getNormalTypeface(context);
                return font;
            }
        } else {
            if (typefaceStyle == Typeface.BOLD) {
                // it should be bold
                Typeface font = getBoldTypeface(context);
                return font;
            } else {
                Typeface font = getNormalTypeface(context);
                return font;
            }
        }
    }


    /**
     * set typeface to a textview, it will checkes for the application language ans set good font based
     * on the request
     *
     * @param context
     * @param view
     */
    public static void setType(Context context, TextInputLayout view) {
        if (!view.isInEditMode()) {

            // check for language
            String language = Locale.getDefault().getLanguage();
            if (language.equalsIgnoreCase("en_us") || language.equalsIgnoreCase("en")) {
                if (view.getTypeface() != null) {
                    if (view.getTypeface().getStyle() == Typeface.BOLD) {
                        // it should be bold
                        Typeface font = getBoldTypefaceEn(context);
                        view.setTypeface(font);
                    } else {
                        Typeface font = getNormalTypefaceEn(context);
                        view.setTypeface(font);
                    }
                } else {
                    Typeface font = getNormalTypefaceEn(context);
                    view.setTypeface(font);
                }
            } else {

                if (view.getTypeface() != null) {
                    if (view.getTypeface().getStyle() == Typeface.BOLD) {
                        // it should be bold
                        Typeface font = getBoldTypeface(context);
                        view.setTypeface(font);
                    } else {
                        Typeface font = getNormalTypeface(context);
                        view.setTypeface(font);
                    }
                } else {
                    Typeface font = getNormalTypeface(context);
                    view.setTypeface(font);
                }
            }

        }
    }

    private static Typeface getBoldTypeface(Context context) {
        // check if the such typeface exist before
        if (boldTypeface == null) {
            boldTypeface = Typeface.createFromAsset(context.getAssets(),
                    "fonts/IRANYekanBold.ttf");
            return boldTypeface;
        } else {
            return boldTypeface;
        }
    }
 private static Typeface getBoldTypefaceEn(Context context) {
        // check if the such typeface exist before
        if (boldTypeface == null) {
            boldTypeface = Typeface.createFromAsset(context.getAssets(),
                    "fonts/IRANYekanMobileMedium.ttf");
            return boldTypeface;
        } else {
            return boldTypeface;
        }
    }

    private static Typeface getNormalTypeface(Context context) {

        // check if the such typeface exist before
        if (normalTypeFace == null) {
            normalTypeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/IRANYekanMobileMedium.ttf");
            return normalTypeFace;
        } else {
            return normalTypeFace;
        }
    }

    private static Typeface getNormalTypefaceEn(Context context) {

        // check if the such typeface exist before
        if (normalTypeFace == null) {
            normalTypeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/IRANYekanMobileMedium.ttf");
            return normalTypeFace;
        } else {
            return normalTypeFace;
        }
    }
}
