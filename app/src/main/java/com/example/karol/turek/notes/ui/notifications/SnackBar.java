package com.example.karol.turek.notes.ui.notifications;

import android.support.design.widget.Snackbar;
import android.view.View;

public class SnackBar {
    private View view;
    private static SnackBar istance;

    public static SnackBar bind(View view){
        if (istance == null){
            synchronized (SnackBar.class){
                if (istance == null){
                    istance = new SnackBar(view);
                }
            }
        }
        return istance;
    }

    public static SnackBar getIstance(){
        return istance;
    }

    public static void unBind(){
        istance = null;
    }

    public SnackBar(View view) {
        this.view = view;
    }

    public void getSnackbar(String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
