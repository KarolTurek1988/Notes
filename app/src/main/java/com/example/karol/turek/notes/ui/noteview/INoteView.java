package com.example.karol.turek.notes.ui.noteview;

import android.widget.TextView;

public interface INoteView {
    TextView getHeader();
    TextView getContent();
    TextView getDate();
    void deleteNote();
    void updateNote();
    void goBack();
    void showProgress();
    void hideProgress();
}
