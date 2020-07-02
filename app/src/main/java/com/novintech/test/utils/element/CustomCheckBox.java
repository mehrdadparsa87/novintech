package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatCheckBox;

import java.util.Locale;

public class CustomCheckBox extends AppCompatCheckBox {

	public CustomCheckBox(Context context) {
		super(context);
		this.setType();
		if (!this.isInEditMode()) {
			// check for language
			if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")|| Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
				// set gravity
				this.setGravity(Gravity.RIGHT);
			}
		}
	}

	public CustomCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setType();
		if (!this.isInEditMode()) {
			// check for language
			if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")|| Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
				// set gravity
				this.setGravity(Gravity.RIGHT);
			}
		}
	}

	public CustomCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setType();
		if (!this.isInEditMode()) {
			// check for language
			if (Locale.getDefault().getLanguage().equalsIgnoreCase("fa_ir")|| Locale.getDefault().getLanguage().equalsIgnoreCase("fa")) {
				// set gravity
				this.setGravity(Gravity.RIGHT);
			}
		}
	}

	private void setType() {
		CustomView.setType(getContext(), this);
	}

}
