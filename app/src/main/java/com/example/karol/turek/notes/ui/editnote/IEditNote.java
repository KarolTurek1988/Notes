package com.example.karol.turek.notes.ui.editnote;

import android.widget.TextView;

public interface IEditNote {
    void editNote();
    void goBack();
    void showProgressBar();
    void hideProgressBar();
    void showSnackbar();
    TextView getHeader();
    TextView getContent();
}
