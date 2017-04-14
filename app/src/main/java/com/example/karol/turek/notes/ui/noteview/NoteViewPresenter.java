package com.example.karol.turek.notes.ui.noteview;

import android.support.v4.app.Fragment;

import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.IDelete;
import com.example.karol.turek.notes.ui.Fragments;
import com.example.karol.turek.notes.ui.editnote.EditNote;
import com.example.karol.turek.notes.ui.notes.Notes;

import io.realm.Realm;

public class NoteViewPresenter implements INoteViewPresenter {
    private ICache cache;
    private IDelete delete;
    private Fragment fragment;
    private INoteView view;

    public NoteViewPresenter(ICache cache, IDelete delete, Fragment fragment) {
        this.cache = cache;
        this.delete = delete;
        this.fragment = fragment;
    }

    @Override
    public void getNote() {
        view.showProgress();
        view.getHeader().setText(cache.getValueString1());
        view.getContent().setText(cache.getValueString2());
        view.getDate().setText(cache.getValueString3());
        view.hideProgress();
    }

    @Override
    public void deleteNote() {
        view.showProgress();
        delete.deleteById(cache.getValueLong1());
        view.hideProgress();
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, Notes.create());
    }

    @Override
    public void updateNote() {
        Fragments.getNewFragment(fragment.getFragmentManager(), fragment,
                EditNote.create());
    }

    @Override
    public void goBack() {
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, Notes.create());
    }

    @Override
    public void onBindView(INoteView view) {
        this.view = view;
    }

    @Override
    public void unBindView() {
        view = null;
        Realm.getDefaultInstance().close();
    }
}
