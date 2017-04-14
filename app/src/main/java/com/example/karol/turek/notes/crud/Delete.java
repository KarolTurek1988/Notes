package com.example.karol.turek.notes.crud;

import com.example.karol.turek.notes.cache.ICache;
import com.example.karol.turek.notes.dto.NotesDto;

import io.realm.Realm;
import io.realm.RealmResults;

public class Delete implements IDelete {
    private ICache cache;
    private Realm realm = Realm.getDefaultInstance();
    private static IDelete instance;

    public static IDelete init(ICache cache) {
        if (instance == null) {
            synchronized (Delete.class) {
                if (instance == null) {
                    instance = new Delete(cache);
                }
            }
        }
        return instance;
    }

    public Delete(ICache cache) {
        this.cache = cache;
    }

    @Override
    public void deleteById(long id) {
        realm.beginTransaction();
        RealmResults<NotesDto> dto = realm.where(NotesDto.class)
                .equalTo("id", id)
                .findAll();
        dto.deleteAllFromRealm();
        realm.commitTransaction();
        cache.clearCache();
    }
}
