package com.example.karol.turek.notes.crud;

import com.example.karol.turek.notes.dto.NotesDto;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ReadAll implements IReadAll {
    private Realm realm;
    private LinkedList<NotesDto> dto = new LinkedList<>();
    private RealmResults<NotesDto> notes;

    public static IReadAll init(Realm realm){
        return new ReadAll(realm);
    }

    public ReadAll(Realm realm) {
        this.realm = realm;
    }

    @Override
    public LinkedList<NotesDto> read() {
        notes = realm.where(NotesDto.class).findAll();
        for (NotesDto item : notes){
            dto.add(item);
        }
        return dto;
    }
}
