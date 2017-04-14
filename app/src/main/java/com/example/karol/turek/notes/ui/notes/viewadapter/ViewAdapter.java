package com.example.karol.turek.notes.ui.notes.viewadapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.karol.turek.notes.R;
import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.dto.NotesDto;
import com.example.karol.turek.notes.ui.notes.INotesPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.Holder> {
    private ICache cache;
    private Fragment fragment;
    private INotesPresenter presenter;

    public ViewAdapter(INotesPresenter presenter) {
        this.presenter = presenter;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.noteHeader)
        TextView header;
        @BindView(R.id.noteContent)
        TextView content;
        @BindView(R.id.noteDate)
        TextView date;
        @BindView(R.id.noteCardView)
        CardView cardView;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notes, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final NotesDto dto = presenter.getCache().getLinkedList().get(position);
        Log.e("onBindViewHolder: ", String.valueOf(dto.getId()));
        holder.header.setText(dto.getHeader());
        holder.content.setText(dto.getContent());
        holder.date.setText(dto.getDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getCache().setValueLong1(dto.getId());
                presenter.getCache().setValueString1(dto.getHeader());
                presenter.getCache().setValueString2(dto.getContent());
                presenter.getCache().setValueString3(dto.getDate());
                presenter.closeNotesList();
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getCache().getLinkedList().size() / 2;
    }
}
