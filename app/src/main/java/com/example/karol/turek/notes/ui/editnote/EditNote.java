package com.example.karol.turek.notes.ui.editnote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.karol.turek.notes.R;
import com.example.karol.turek.notes.cache.Cache;
import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.Update;
import com.example.karol.turek.notes.ui.notifications.SnackBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class EditNote extends Fragment
        implements IEditNote {
    private IEditNotePresenter presenter;
    private ICache cache;

    @BindView(R.id.editNoteProgressBar)
    ProgressBar progressBar;
    @BindView(R.id.editNoteHeader)
    TextView header;
    @BindView(R.id.editNoteContent)
    TextView content;

    public static Fragment create(){
        return new EditNote();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity().getApplicationContext());
        cache = (Cache) getActivity().getApplication();
        presenter = new EditNotePresenter(cache, Update.getInstance(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Realm.init(getActivity().getApplicationContext());
        presenter.onBindView(this);
        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
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
    public TextView getHeader() {
        return header;
    }

    @Override
    public TextView getContent() {
        return content;
    }

    @Override
    @OnClick(R.id.editNoteSave)
    public void editNote() {
        presenter.editNote();
    }

    @Override
    @OnClick(R.id.editNoteCancel)
    public void goBack() {
        presenter.goBack();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnackbar() {
        SnackBar.getIstance().getSnackbar("Note edit");
    }
}
