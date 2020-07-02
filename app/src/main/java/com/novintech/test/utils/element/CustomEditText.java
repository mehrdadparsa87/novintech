package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {

    public CustomEditText(Context context) {
        super(context);
        this.settype();
//        if (!this.isInEditMode()) {
//            // check for language
//            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")) {
//                // set gravity
//                this.setGravity(Gravity.RIGHT);
//            }
//        }

    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.settype();

//        if (!this.isInEditMode()) {
//            // check for language
//            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")) {
//                // set gravity
//                this.setGravity(Gravity.RIGHT);
//            }
//        }
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.settype();

//        if (!this.isInEditMode()) {
//            // check for language
//            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")) {
//                // set gravity
//                this.setGravity(Gravity.RIGHT);
//            }
//        }
    }

    private void settype() {
        CustomView.setType(getContext(), this);
    }

}
