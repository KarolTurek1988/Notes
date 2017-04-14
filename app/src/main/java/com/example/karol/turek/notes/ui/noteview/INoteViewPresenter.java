package com.example.karol.turek.notes.ui.noteview;

public interface INoteViewPresenter {
    void getNote();
    void deleteNote();
    void updateNote();
    void goBack();
    void onBindView(INoteView view);
    void unBindView();
}
