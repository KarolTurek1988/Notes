package com.example.karol.turek.notes.ui.newnote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.karol.turek.notes.R;
import com.example.karol.turek.notes.cache.Cache;
import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.Add;
import com.example.karol.turek.notes.ui.notifications.SnackBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class NewNote extends Fragment
        implements INewNote {
    private INewNotePresenter presenter;
    private ICache cache;

    @BindView(R.id.progressBarNewNote)
    ProgressBar progressBar;
    @BindView(R.id.newNoteHeader)
    EditText header;
    @BindView(R.id.newNoteContent)
    EditText content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity().getApplicationContext());
        cache = (Cache) getActivity().getApplication();
        presenter = new NewNotePresenter(Add.init(Realm.getDefaultInstance(), cache), this);
    }

    public static Fragment create() {
        return new NewNote();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Realm.init(getActivity().getApplicationContext());
        presenter.onBindView(this);
        View view = inflater.inflate(R.layout.fragment_new_note, container, false);
        ButterKnife.bind(this, view);
        SnackBar.bind(container);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unBindView();
    }

    @Override
    @OnClick(R.id.newNoteSave)
    public void addNote() {
        presenter.addNote(header.getText().toString(),
                content.getText().toString());
    }

    @Override
    @OnClick(R.id.newNoteClose)
    public void cancelNote() {
        presenter.cancelNote();
    }

    @Override
    public void showPreloader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePreloader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnackbar() {
        SnackBar.getIstance().getSnackbar("Note Add");
    }
}
