package com.example.karol.turek.notes.crud;

import android.util.Log;

import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.dto.NotesDto;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public class Add implements IAdd {
    private Realm realm;
    private ICache cache;
    private static IAdd istance;

    public static IAdd init(Realm realm, ICache cache){
        if (istance == null){
            synchronized (Add.class){
                if (istance == null){
                    istance = new Add(realm, cache);
                }
            }
        }
        return istance;
    }

    public Add(Realm realm, ICache cache) {
        this.realm = realm;
        this.cache = cache;
    }

    @Override
    public void addNote(String header, String content) {
        cache.getLinkedList().clear();
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        final NotesDto note = new NotesDto();
        note.setId(PrimaryKeyFactory.getIstance().nextKey());
        Log.e("addNote: ", String.valueOf(PrimaryKeyFactory.getIstance().nextKey()));
        if (header == null){
            note.setHeader(timeStamp);
        }else {
            note.setHeader(header);
        }
        note.setContent(content);
        note.setDate(timeStamp);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(note);
            }
        });
    }
}
