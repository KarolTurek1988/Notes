package com.example.karol.turek.notes.ui.editnote;

import android.support.v4.app.Fragment;

import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.IUpdate;
import com.example.karol.turek.notes.ui.Fragments;
import com.example.karol.turek.notes.ui.notes.Notes;
import com.example.karol.turek.notes.ui.noteview.NoteView;
import com.example.karol.turek.notes.ui.notifications.SnackBar;

import io.realm.Realm;

public class EditNotePresenter implements IEditNotePresenter {
    private ICache cache;
    private IUpdate update;
    private Fragment fragment;
    private IEditNote view;

    public EditNotePresenter(ICache cache, IUpdate update, Fragment fragment) {
        this.cache = cache;
        this.update = update;
        this.fragment = fragment;
    }

    @Override
    public void takeNote() {
        view.getHeader().setText(cache.getValueString1());
        view.getContent().setText(cache.getValueString2());
    }

    @Override
    public void editNote() {
        view.showProgressBar();
        update.updateNote(
                cache.getValueLong1(),
                view.getHeader().getText().toString(),
                view.getContent().getText().toString()
        );
        cache.clearCache();
        view.hideProgressBar();
        view.showSnackbar();
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, Notes.create());
    }

    @Override
    public void goBack() {
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, NoteView.create());
    }

    @Override
    public void onBindView(IEditNote view) {
        this.view = view;
    }

    @Override
    public void unBindView() {
        view = null;
        SnackBar.unBind();
        Realm.getDefaultInstance().close();
    }
}
