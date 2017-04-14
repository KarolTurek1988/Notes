package com.example.karol.turek.notes.ui.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.karol.turek.notes.R;
import com.example.karol.turek.notes.cache.Cache;
import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.ReadAll;
import com.example.karol.turek.notes.ui.notes.viewadapter.ViewAdapter;
import com.example.karol.turek.notes.ui.notifications.SnackBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class Notes extends Fragment
        implements INotes {
    private ICache cache;
    private INotesPresenter presenter;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static Fragment create() {
        return new Notes();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity().getApplicationContext());
        cache = (Cache) getActivity().getApplication();
        presenter = new NotesPresenter(cache, ReadAll
                .init(Realm.getDefaultInstance()), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Realm.init(getActivity().getApplicationContext());
        presenter.onBindView(this);
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        ButterKnife.bind(this, view);
        SnackBar.bind(container);
        if (cache.getLinkedList().size() == 0) {
            presenter.getNotes();
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.notesList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new ViewAdapter(presenter);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unBindView();
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
        SnackBar.getIstance().getSnackbar("No Notes");
    }
}
