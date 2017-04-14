package com.example.karol.turek.notes.ui.notes;

import android.support.v4.app.Fragment;

import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.IReadAll;
import com.example.karol.turek.notes.dto.NotesDto;
import com.example.karol.turek.notes.ui.Fragments;
import com.example.karol.turek.notes.ui.noteview.NoteView;
import com.example.karol.turek.notes.ui.notifications.SnackBar;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NotesPresenter implements INotesPresenter {
    private INotes view;
    private ICache cache;
    private IReadAll read;
    private Fragment fragment;
    private Subscription subscription;

    public NotesPresenter(ICache cache, IReadAll read, Fragment fragment) {
        this.cache = cache;
        this.read = read;
        this.fragment = fragment;
    }

    @Override
    public void getNotes() {
        if (read.read().size() != 0) {
            view.showPreloader();
            subscription = Observable.from(read.read())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NotesDto>() {
                        @Override
                        public void onCompleted() {
                            view.hidePreloader();
                        }

                        @Override
                        public void onError(Throwable e) {
                            view.hidePreloader();
                        }

                        @Override
                        public void onNext(NotesDto notesDto) {
                            cache.getLinkedList().add(notesDto);
                        }
                    });
        } else
            view.showSnackbar();
    }

    @Override
    public ICache getCache() {
        return cache;
    }

    @Override
    public void closeNotesList() {
        Fragments.getNewFragment(fragment.getFragmentManager(),
                fragment, NoteView.create());
    }

    @Override
    public void onBindView(INotes view) {
        this.view = view;
    }

    @Override
    public void unBindView() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        Realm.getDefaultInstance().close();
        SnackBar.unBind();
        this.view = null;
    }
}
