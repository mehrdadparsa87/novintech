package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatRadioButton;

import java.util.Locale;

public class CustomRadioButton extends AppCompatRadioButton {

    public CustomRadioButton(Context context) {
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

    public CustomRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.settype();
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        this.settype();
    }

    private void settype() {
        CustomView.setType(getContext(), this);
    }

}
