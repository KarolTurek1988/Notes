package com.example.karol.turek.notes.ui.notes;

import com.example.karol.turek.notes.cache.ICache;

public interface INotesPresenter {
    void getNotes();
    ICache getCache();
    void closeNotesList();
    void onBindView(INotes view);
    void unBindView();
}
