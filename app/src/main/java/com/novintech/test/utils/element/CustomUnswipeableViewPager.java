package com.novintech.test.utils.element;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class CustomUnswipeableViewPager extends ViewPager {
    public CustomUnswipeableViewPager(@NonNull Context context) {
        super(context);
    }

    public CustomUnswipeableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return performClick();
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return false;
    }
}
