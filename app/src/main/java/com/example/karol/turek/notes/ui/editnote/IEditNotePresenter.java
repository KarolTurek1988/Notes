package com.example.karol.turek.notes.ui.editnote;

public interface IEditNotePresenter {
    void takeNote();
    void editNote();
    void goBack();
    void onBindView(IEditNote view);
    void unBindView();
}
