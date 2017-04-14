package com.example.karol.turek.notes.ui.newnote;

import android.support.v4.app.Fragment;

import com.example.karol.turek.notes.crud.IAdd;
import com.example.karol.turek.notes.ui.Fragments;
import com.example.karol.turek.notes.ui.notes.Notes;

import io.realm.Realm;

public class NewNotePresenter implements INewNotePresenter {
    private IAdd add;
    private INewNote view;
    private Fragment fragment;

    public NewNotePresenter(IAdd add, Fragment fragment) {
        this.add = add;
        this.fragment = fragment;
    }

    @Override
    public void addNote(String header, String content) {
        view.showPreloader();
        add.addNote(header, content);
        view.hidePreloader();
        view.showSnackbar();
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, Notes.create());
    }

    @Override
    public void cancelNote() {
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, Notes.create());
    }

    @Override
    public void onBindView(INewNote view) {
        this.view = view;
    }

    @Override
    public void unBindView() {
        Realm.getDefaultInstance().close();
        view = null;
    }
}
