package com.novintech.test.utils.element;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novintech.test.R;


public class CustomProgress {

    public static CustomProgress customProgress = null;
    private Dialog mDialog;

    public static CustomProgress getInstance(Context context) {

        customProgress = new CustomProgress(context);

        return customProgress;
    }

    public CustomProgress(Context context) {
        mDialog = new Dialog(context);
        // no tile for the dialog
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.prograss_bar_dialog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ProgressBar mProgressBar = (ProgressBar) mDialog.findViewById(R.id.progress_bar);
        //  mProgressBar.getIndeterminateDrawable().setColorFilter(context.getResources()
        // .getColor(R.color.material_blue_gray_500), PorterDuff.Mode.SRC_IN);
        TextView progressText = (TextView) mDialog.findViewById(R.id.progress_text);
        progressText.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        // you can change or add this line according to your need
        mProgressBar.setIndeterminate(true);
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);

    }

    public void hideProgress() {
        if (mDialog != null) {
            mDialog.dismiss();

        }
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    public void Cancelable(){
        mDialog.setCancelable(true);

    }
}
