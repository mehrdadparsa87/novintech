package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.Locale;

/**
 * this view will use custom font based on the app language user has choose
 */
public class CustomTextView extends AppCompatTextView {


    public CustomTextView(Context context) {
        super(context);
        this.setTypeFaceToView();

        if (!this.isInEditMode() && this.getGravity() == 8388659) {
            // check for language
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir") || Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
                // set gravity
                this.setGravity(Gravity.RIGHT);
            }
        }

    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeFaceToView();
        if (!this.isInEditMode() && this.getGravity() == 8388659) {
            // check for language
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir") || Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
                // set gravity
                this.setGravity(Gravity.RIGHT);
            }
        }
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeFaceToView();
        if (!this.isInEditMode() && this.getGravity() == 8388659) {
            // check for language
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir") || Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
                // set gravity
                this.setGravity(Gravity.RIGHT);
            }
        }
    }

    /**
     * with this function, we will load the type face from asset and set to the view
     */
    private void setTypeFaceToView() {
        CustomView.setType(getContext(), this);
    }

}
