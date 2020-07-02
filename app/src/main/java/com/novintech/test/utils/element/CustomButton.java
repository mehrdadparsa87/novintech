package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;


public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        super(context);
        this.settype();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.settype();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        this.settype();
    }

    private void settype() {
        CustomView.setType(getContext(), this);
    }

}
