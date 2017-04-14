package com.example.karol.turek.notes.ui.newnote;

public interface INewNotePresenter {
    void addNote(String header, String content);
    void cancelNote();
    void onBindView(INewNote view);
    void unBindView();
}
