package com.example.karol.turek.notes.ui.noteview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.karol.turek.notes.R;
import com.example.karol.turek.notes.cache.Cache;
import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.crud.Delete;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteView extends Fragment
        implements INoteView {
    private ICache cache;
    private INoteViewPresenter presenter;

    @BindView(R.id.noteViewProgressBar)
    ProgressBar progressBar;
    @BindView(R.id.noteViewHeader)
    TextView header;
    @BindView(R.id.noteViewContent)
    TextView content;
    @BindView(R.id.noteViewDate)
    TextView date;

    public static Fragment create() {
        return new NoteView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cache = (Cache) getActivity().getApplication();
        presenter = new NoteViewPresenter(cache, Delete.init(cache), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter.onBindView(this);
        View view = inflater.inflate(R.layout.content_note, container, false);
        ButterKnife.bind(this, view);
        presenter.getNote();

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
    public TextView getDate() {
        return date;
    }

    @Override
    @OnClick(R.id.noteDelete)
    public void deleteNote() {
        new AlertDialog
                .Builder(getContext())
                .setMessage(R.string.message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteNote();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    @OnClick(R.id.noteEdit)
    public void updateNote() {
        presenter.updateNote();
    }

    @Override
    @OnClick(R.id.noteBack)
    public void goBack() {
        presenter.goBack();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
