package com.example.karol.turek.notes.crud;

import com.example.karol.turek.notes.dto.NotesDto;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public class Update implements IUpdate {
    Realm realm = Realm.getDefaultInstance();
    private static final Update ourInstance = new Update();

    public static Update getInstance() {
        return ourInstance;
    }

    private Update() {
    }

    @Override
    public void updateNote(long id, final String header, final String content) {
        final String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        final NotesDto dto = realm
                .where(NotesDto.class)
                .equalTo("id", id)
                .findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                dto.setHeader(header);
                dto.setContent(content);
                dto.setDate(timeStamp);
                realm.insertOrUpdate(dto);
            }
        });
    }
}
