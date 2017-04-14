package com.example.karol.turek.notes.dto;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class NotesListDto extends RealmObject{
    private RealmList<NotesDto> notes;

    public RealmList<NotesDto> getNotes() {
        return notes;
    }

    public void setNotes(RealmList<NotesDto> notes) {
        this.notes = notes;
    }
}
