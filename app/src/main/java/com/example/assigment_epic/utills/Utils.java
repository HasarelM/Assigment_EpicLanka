package com.example.assigment_epic.utills;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.assigment_epic.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Utils {

    public static boolean isListNotNullAndEmpty(ArrayList list) {
        return list != null && list.size() > 0;
    }

    public static Dialog getProgress(Activity activity, String message) {
        Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.setContentView(R.layout.popup_progressbar);
        if(dialog.findViewById(R.id.txtMessage)!=null)
            ((TextView)dialog.findViewById(R.id.txtMessage)).setText(message);
        ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(activity.getResources().getColor(R.color.purple_500), PorterDuff.Mode.MULTIPLY);
        dialog.setCancelable(false);
        return dialog;
    }

    public static Snackbar showSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }
}
