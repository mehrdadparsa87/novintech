package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;


public class CustomTextInputLayout extends TextInputLayout {

    public CustomTextInputLayout(Context context) {
        super(context);

        this.settype();
        if (!this.isInEditMode()) {
            // check for language
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")|| Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
                // set gravity
                this.setGravity(Gravity.RIGHT);
            }
        }
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.settype();
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.settype();
    }


    private void settype() {
        CustomView.setType(getContext(), this);
    }
}
